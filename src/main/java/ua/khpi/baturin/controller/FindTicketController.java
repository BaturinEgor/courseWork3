package ua.khpi.baturin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ua.khpi.baturin.dao.contract.BusDao;
import ua.khpi.baturin.dao.contract.DrivingDao;
import ua.khpi.baturin.dao.contract.RouteDao;
import ua.khpi.baturin.dao.contract.StationDao;
import ua.khpi.baturin.dao.contract.TicketDao;
import ua.khpi.baturin.entity.Driving;
import ua.khpi.baturin.entity.Route;
import ua.khpi.baturin.entity.Station;
import ua.khpi.baturin.entity.TicketToBuyForm;

@Controller
@RequestMapping("/findTicket")
public class FindTicketController {

    public static Station arrivalStation;
    public static Station departureStation;

    @Autowired
    private BusDao busDao;

    @Autowired
    private RouteDao routeDao;

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private DrivingDao drivingDao;

    @Autowired
    private StationDao stationDao;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView createInit(@ModelAttribute("departureStation") String departureStation,
            @ModelAttribute("arrivalStation") String arrivalStation, @ModelAttribute("driving") Driving driving,
            BindingResult result, Model model) {
        System.out.println(departureStation);
        System.out.println(arrivalStation);
        System.out.println(driving);

        FindTicketController.arrivalStation = stationDao.findByTitle(arrivalStation);
        FindTicketController.departureStation = stationDao.findByTitle(departureStation);

        List<TicketToBuyForm> routes = new ArrayList<>();

        for (Route route : routeDao.findAll()) {
            boolean haveDeparture = false;
            boolean haveArrival = false;
            boolean haveCorrectDate = false;
            for (Driving driving2 : drivingDao.findByRoute(route)) {
                if (driving2.getDepartureStation().getTitle().equals(departureStation)) {
                    haveDeparture = true;
                }
                if (driving2.getArrivalStation().getTitle().equals(arrivalStation) && haveDeparture) {
                    haveArrival = true;
                }
                if (driving.getDepartureDate().equals(driving2.getDepartureDate())) {
                    haveCorrectDate = true;
                }
            }
            if (haveDeparture && haveArrival && haveCorrectDate) {
                TicketToBuyForm ttb = new TicketToBuyForm();
                ttb.setRoute(route);
                ttb.setAmountOfSeats(route.getBus().getSeats() - ticketDao.findByRoute(route).size());
                ttb.setDrivings(drivingDao.findByRoute(route));
                routes.add(ttb);
            }
        }

        System.out.println(routes.size());
        for (TicketToBuyForm ticketToBuyForm : routes) {
            System.out.println(ticketToBuyForm.getRoute());
            System.out.println(ticketToBuyForm.getAmountOfSeats());
        }

        ModelAndView modelAndView = new ModelAndView("routesList", "routes", routes);
        return modelAndView;
    }
}