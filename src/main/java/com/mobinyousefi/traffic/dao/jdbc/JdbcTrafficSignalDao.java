package com.mobinyousefi.traffic.dao.jdbc;

import com.mobinyousefi.traffic.config.DatabaseConfig;
import com.mobinyousefi.traffic.dao.DaoException;
import com.mobinyousefi.traffic.dao.TrafficSignalDao;
import com.mobinyousefi.traffic.model.TrafficSignal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcTrafficSignalDao implements TrafficSignalDao {

    private static final String SELECT_ALL = "SELECT id, intersection_name, direction, state, last_updated FROM traffic_signal";
    private static final String SELECT_BY_ID = SELECT_ALL + " WHERE id = ?";
    private static final String UPDATE = "UPDATE traffic_signal SET state = ?, last_updated = ? WHERE id = ?";

    @Override
    public List<TrafficSignal> findAll() throws DaoException {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            List<TrafficSignal> result = new ArrayList<>();
            while (rs.next()) {
                result.add(mapRow(rs));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException("Failed to load traffic signals", e);
        }
    }

    @Override
    public Optional<TrafficSignal> findById(long id) throws DaoException {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to load traffic signal with id=" + id, e);
        }
    }

    @Override
    public void update(TrafficSignal signal) throws DaoException {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE)) {

            ps.setString(1, signal.getState().name());
            ps.setTimestamp(2, Timestamp.valueOf(signal.getLastUpdated()));
            ps.setLong(3, signal.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Failed to update traffic signal with id=" + signal.getId(), e);
        }
    }

    private TrafficSignal mapRow(ResultSet rs) throws SQLException {
        TrafficSignal signal = new TrafficSignal();
        signal.setId(rs.getLong("id"));
        signal.setIntersectionName(rs.getString("intersection_name"));
        signal.setDirection(rs.getString("direction"));
        signal.setState(TrafficSignal.State.valueOf(rs.getString("state")));
        Timestamp ts = rs.getTimestamp("last_updated");
        signal.setLastUpdated(ts != null ? ts.toLocalDateTime() : LocalDateTime.now());
        return signal;
    }
}
