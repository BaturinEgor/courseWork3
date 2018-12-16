package ua.khpi.baturin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ua.khpi.baturin.dao.contract.DrivingDao;
import ua.khpi.baturin.dao.contract.RouteDao;
import ua.khpi.baturin.entity.Route;
import ua.khpi.baturin.entity.RouteForm;

@Controller
@RequestMapping("/routeManagement")
public class RouteManagement {

    @Autowired
    private RouteDao stationDao;

    @Autowired
    DrivingDao drivingDao;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getManageAdminCabinet(@RequestParam(value = "message", required = false) String message) {

        CreateRoute.drivings.clear();
        UpdateRoute.drivings.clear();

        List<Route> routs = stationDao.findAll();
        List<RouteForm> routeForms = new ArrayList<>();
        for (Route route : routs) {
            RouteForm routeForm = new RouteForm();
            routeForm.setRout(route);
            routeForm.setDrivings(drivingDao.findByRoute(route));
            routeForms.add(routeForm);
        }

        ModelAndView mv = new ModelAndView("route", "routs", routeForms);
        mv.addObject("message", message);
        return mv;
    }
}
