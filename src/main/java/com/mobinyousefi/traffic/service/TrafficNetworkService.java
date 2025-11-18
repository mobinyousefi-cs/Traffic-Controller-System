package com.mobinyousefi.traffic.service;

import com.mobinyousefi.traffic.dao.RouteDao;
import com.mobinyousefi.traffic.dao.TrafficSignalDao;
import com.mobinyousefi.traffic.model.Route;
import com.mobinyousefi.traffic.model.TrafficSignal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Application service that orchestrates traffic network operations.
 */
public class TrafficNetworkService {

    private final TrafficSignalDao trafficSignalDao;
    private final RouteDao routeDao;

    public TrafficNetworkService(TrafficSignalDao trafficSignalDao, RouteDao routeDao) {
        this.trafficSignalDao = trafficSignalDao;
        this.routeDao = routeDao;
    }

    public List<TrafficSignal> getAllSignals() {
        return trafficSignalDao.findAll();
    }

    public List<Route> getAllRoutes() {
        return routeDao.findAll();
    }

    public void updateSignalState(long signalId, TrafficSignal.State newState) {
        trafficSignalDao.findById(signalId).ifPresent(signal -> {
            signal.setState(newState);
            signal.setLastUpdated(LocalDateTime.now());
            trafficSignalDao.update(signal);
        });
    }

    public void updateRouteStatus(long routeId, Route.Status newStatus) {
        routeDao.findById(routeId).ifPresent(route -> {
            route.setStatus(newStatus);
            routeDao.update(route);
        });
    }
}
