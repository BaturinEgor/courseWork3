package ua.khpi.baturin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ua.khpi.baturin.dao.contract.TicketDao;
import ua.khpi.baturin.entity.Ticket;
import ua.khpi.baturin.entity.User;

@Controller
@RequestMapping("/privateCabinet")
@SessionAttributes({ "user" })
public class PrivateCabinet {

    @Autowired
    TicketDao ticketDao;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView manageGetUserCabinet(@SessionAttribute("user") User user) {
        List<Ticket> tickets = ticketDao.findByClient(user);
        System.out.println(tickets);
        ModelAndView modelAndiew = new ModelAndView("showCabinet", "tickets", tickets);
        return modelAndiew;
    }
}
