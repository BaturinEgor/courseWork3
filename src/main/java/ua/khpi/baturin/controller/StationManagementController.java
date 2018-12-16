package ua.khpi.baturin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ua.khpi.baturin.dao.contract.StationDao;

@Controller
@RequestMapping("/stationManagement")
public class StationManagementController {

    @Autowired
    private StationDao stationDao;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getManageAdminCabinet(@RequestParam(value = "message", required = false) String message) {
        ModelAndView mv = new ModelAndView("station", "stations", stationDao.findAll());
        mv.addObject("message", message);
        return mv;
    }
}