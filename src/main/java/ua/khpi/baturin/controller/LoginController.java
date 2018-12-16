package ua.khpi.baturin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.khpi.baturin.dao.contract.AdminDao;
import ua.khpi.baturin.dao.contract.TicketDao;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    TicketDao ticketDao;
    @Autowired
    AdminDao adminDao;

    @RequestMapping(method = RequestMethod.GET)
    public String errorManagement(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            error = "Login or password is incorrect";
            model.addAttribute("error", error);
        }
        return "loginPage";
    }
}
