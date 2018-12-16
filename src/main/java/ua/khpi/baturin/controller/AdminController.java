package ua.khpi.baturin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ua.khpi.baturin.dao.contract.DrivingDao;
import ua.khpi.baturin.dao.contract.RouteDao;
import ua.khpi.baturin.entity.Route;
import ua.khpi.baturin.entity.RouteForm;
import ua.khpi.baturin.entity.User;
import ua.khpi.baturin.service.contract.UserService;

@Controller
@RequestMapping("/admin")
@SessionAttributes({ "user" })
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private DrivingDao drivingDao;

    @Autowired
    private RouteDao routeDao;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView postManageAdminCabinet(@RequestAttribute(value = "user", required = false) User user) {

        CreateRoute.drivings.clear();
        System.out.println("there1");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", userService.findAll());
        modelAndView.addObject("user", user);
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getManageAdminCabinet(@RequestParam(value = "message", required = false) String message) {

        CreateRoute.drivings.clear();
        System.out.println("there2");

        List<RouteForm> routeForms = new ArrayList<>();

        for (Route route : routeDao.findAll()) {
            RouteForm routeForm = new RouteForm();
            routeForm.setRout(route);
            System.out.println(route);
            System.out.println("find by route = " + drivingDao.findByRoute(route));
            System.out.println("find all = " + drivingDao.findAll());
            routeForm.setDrivings(drivingDao.findByRoute(route));
            routeForms.add(routeForm);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", userService.findAll());
        modelAndView.addObject("message", message);

        modelAndView.setViewName("admin");
        return modelAndView;
    }
}
