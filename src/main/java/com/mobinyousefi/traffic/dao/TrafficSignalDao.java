package com.mobinyousefi.traffic.dao;

import com.mobinyousefi.traffic.model.TrafficSignal;

import java.util.List;
import java.util.Optional;

public interface TrafficSignalDao {

    List<TrafficSignal> findAll() throws DaoException;

    Optional<TrafficSignal> findById(long id) throws DaoException;

    void update(TrafficSignal signal) throws DaoException;
}
