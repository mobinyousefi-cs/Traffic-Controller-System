package com.mobinyousefi.traffic.dao;

import com.mobinyousefi.traffic.model.Route;

import java.util.List;
import java.util.Optional;

public interface RouteDao {

    List<Route> findAll() throws DaoException;

    Optional<Route> findById(long id) throws DaoException;

    void update(Route route) throws DaoException;
}
