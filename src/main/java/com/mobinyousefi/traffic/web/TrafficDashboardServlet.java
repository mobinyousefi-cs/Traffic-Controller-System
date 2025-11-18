package com.mobinyousefi.traffic.web;

import com.mobinyousefi.traffic.dao.RouteDao;
import com.mobinyousefi.traffic.dao.TrafficSignalDao;
import com.mobinyousefi.traffic.dao.jdbc.JdbcRouteDao;
import com.mobinyousefi.traffic.dao.jdbc.JdbcTrafficSignalDao;
import com.mobinyousefi.traffic.model.Route;
import com.mobinyousefi.traffic.model.TrafficSignal;
import com.mobinyousefi.traffic.service.TrafficNetworkService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TrafficDashboardServlet", urlPatterns = "/dashboard")
public class TrafficDashboardServlet extends HttpServlet {

    private transient TrafficNetworkService trafficNetworkService;

    @Override
    public void init() throws ServletException {
        TrafficSignalDao signalDao = new JdbcTrafficSignalDao();
        RouteDao routeDao = new JdbcRouteDao();
        this.trafficNetworkService = new TrafficNetworkService(signalDao, routeDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("signals", trafficNetworkService.getAllSignals());
        req.setAttribute("routes", trafficNetworkService.getAllRoutes());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dashboard.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("updateSignal".equals(action)) {
            long id = Long.parseLong(req.getParameter("signalId"));
            String state = req.getParameter("state");
            trafficNetworkService.updateSignalState(id, TrafficSignal.State.valueOf(state));
        } else if ("updateRoute".equals(action)) {
            long id = Long.parseLong(req.getParameter("routeId"));
            String status = req.getParameter("status");
            trafficNetworkService.updateRouteStatus(id, Route.Status.valueOf(status));
        }

        resp.sendRedirect(req.getContextPath() + "/dashboard");
    }
}
