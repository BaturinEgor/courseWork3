package ua.khpi.baturin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ua.khpi.baturin.entity.User;
import ua.khpi.baturin.service.contract.UserService;

@Controller
@RequestMapping("/usersManagement")
@SessionAttributes({ "user" })
public class UsersManagement {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView postManageAdminCabinet1(@RequestAttribute(value = "user", required = false) User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", userService.findAll());
        modelAndView.addObject("user", user);
        modelAndView.setViewName("admin_cabinet");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getManageAdminCabinet2(@RequestParam(value = "message", required = false) String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", userService.findAll());
        modelAndView.addObject("message", message);
        modelAndView.setViewName("admin_cabinet");
        return modelAndView;
    }
}
