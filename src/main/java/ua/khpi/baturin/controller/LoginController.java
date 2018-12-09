package ua.khpi.baturin.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.khpi.baturin.dao.contract.ClientDao;
import ua.khpi.baturin.dao.contract.TicketDao;
import ua.khpi.baturin.entity.Bus;
import ua.khpi.baturin.entity.Carrier;
import ua.khpi.baturin.entity.Client;
import ua.khpi.baturin.entity.Route;
import ua.khpi.baturin.entity.Station;
import ua.khpi.baturin.entity.Status;
import ua.khpi.baturin.entity.Ticket;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    TicketDao ticketDao;
    @Autowired
    ClientDao clientDao;

    @RequestMapping(method = RequestMethod.GET)
    public String errorManagement(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            error = "Login or password is incorrect";
            model.addAttribute("error", error);
        }
        Client client = new Client(1l, "123", "321", "email", "name", "surname", new Status(1l, "some status title"));
        Ticket ticket = new Ticket(1l, Time.valueOf("2:31:16"), Date.valueOf("2018-3-11"),
                new Station(1l, "some station1"), Time.valueOf("16:31:45"), Date.valueOf("2018-11-17"),
                new Station(2l, "some station 2"), new BigDecimal(125.5),
                new Route(1l, "someRouteName", new Bus(1l, "123H1", 154, new Carrier(1l, "PAN TRANS BUS"))), client);

        clientDao.create(client);
        ticketDao.create(ticket);
        System.out.println(ticketDao.findByClient(client));
        ticket.setPrice(new BigDecimal(343));
        ticketDao.update(ticket);
        System.out.println(ticketDao.findByClient(client));
        ticketDao.remove(ticket);
        System.out.println(ticketDao.findByClient(client));
        return "loginPage";
    }
}
