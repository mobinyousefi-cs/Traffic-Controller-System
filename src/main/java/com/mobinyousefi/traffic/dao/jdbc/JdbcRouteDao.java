package com.mobinyousefi.traffic.dao.jdbc;

import com.mobinyousefi.traffic.config.DatabaseConfig;
import com.mobinyousefi.traffic.dao.DaoException;
import com.mobinyousefi.traffic.dao.RouteDao;
import com.mobinyousefi.traffic.model.Route;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcRouteDao implements RouteDao {

    private static final String SELECT_ALL = "SELECT id, name, from_intersection, to_intersection, status FROM route";
    private static final String SELECT_BY_ID = SELECT_ALL + " WHERE id = ?";
    private static final String UPDATE = "UPDATE route SET status = ? WHERE id = ?";

    @Override
    public List<Route> findAll() throws DaoException {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            List<Route> result = new ArrayList<>();
            while (rs.next()) {
                result.add(mapRow(rs));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException("Failed to load routes", e);
        }
    }

    @Override
    public Optional<Route> findById(long id) throws DaoException {
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
            throw new DaoException("Failed to load route with id=" + id, e);
        }
    }

    @Override
    public void update(Route route) throws DaoException {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE)) {

            ps.setString(1, route.getStatus().name());
            ps.setLong(2, route.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Failed to update route with id=" + route.getId(), e);
        }
    }

    private Route mapRow(ResultSet rs) throws SQLException {
        Route route = new Route();
        route.setId(rs.getLong("id"));
        route.setName(rs.getString("name"));
        route.setFromIntersection(rs.getString("from_intersection"));
        route.setToIntersection(rs.getString("to_intersection"));
        route.setStatus(Route.Status.valueOf(rs.getString("status")));
        return route;
    }
}
