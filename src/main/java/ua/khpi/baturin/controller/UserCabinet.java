package ua.khpi.baturin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ua.khpi.baturin.dao.contract.StationDao;
import ua.khpi.baturin.entity.Driving;

@Controller
@RequestMapping("/user")
public class UserCabinet {

    @Autowired
    StationDao stationDao;

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView manageGetUserCabinet() {
        ModelAndView modelAndiew = new ModelAndView("user", "stations", stationDao.findAll());
        modelAndiew.addObject("driving", new Driving());
        return modelAndiew;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView manageGetUserCabinet2() {
        ModelAndView modelAndiew = new ModelAndView("user", "stations", stationDao.findAll());
        modelAndiew.addObject("driving", new Driving());
        return modelAndiew;
    }
}
