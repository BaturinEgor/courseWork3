package ua.khpi.baturin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ua.khpi.baturin.dao.contract.CarrierDao;

@Controller
@RequestMapping("/carrierManagement")
public class CarrierManagementController {

    @Autowired
    private CarrierDao carrierDao;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getManageAdminCabinet(@RequestParam(value = "message", required = false) String message) {
        ModelAndView mv = new ModelAndView("carrier", "carriers", carrierDao.findAll());
        System.out.println(message);
        mv.addObject("message", message);
        return mv;
    }
}
