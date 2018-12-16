package ua.khpi.baturin.controller;

import java.sql.Date;
import java.sql.Time;
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
import ua.khpi.baturin.entity.Bus;
import ua.khpi.baturin.entity.Driving;
import ua.khpi.baturin.entity.Route;
import ua.khpi.baturin.entity.Station;

@Controller
@RequestMapping("/updateRoute")
public class UpdateRoute {

    public static List<Driving> drivings = new ArrayList<>();
    private static Bus bus = new Bus();
    private static String routeNumber;
    private static Long routeId;

    @Autowired
    private StationDao stationDao;

    @Autowired
    private DrivingDao drivingDao;

    @Autowired
    private BusDao busDao;

    @Autowired
    private RouteDao routeDao;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView createInit(@ModelAttribute("departureStation") String departureStation,
            @ModelAttribute("arrivalStation") String arrivalStation, @ModelAttribute("arrivalTime") String arrivalTime,
            @ModelAttribute("arrivalDate") String arrivalDate, @ModelAttribute("departureTime") String departureTime,
            @ModelAttribute("departureDate") String departureDate, @ModelAttribute("price") String price,
            @ModelAttribute("selectedBus") String bus, @ModelAttribute("routeNumber") String routeNumber,
            @ModelAttribute("id") String id, BindingResult result, Model model) {

        System.out.println("bus + = " + bus);

        System.out.println("dr some " + drivings);

        System.out.println(id);
        if (id != null && !id.equals("")) {
            Long routeToUpdateId = null;
            try {
                routeToUpdateId = Long.parseLong(id);
            } catch (NumberFormatException e) {
                return new ModelAndView("redirect:/routeManagement", "message", "error while parsing id");
            }
            UpdateRoute.routeId = routeToUpdateId;
            System.out.println("drivings = " + drivingDao.findByRoute(routeDao.findById(routeToUpdateId)));
            UpdateRoute.drivings = drivingDao.findByRoute(routeDao.findById(routeToUpdateId));
            UpdateRoute.routeNumber = routeDao.findById(routeToUpdateId).getRouteNumber();
            UpdateRoute.bus = routeDao.findById(routeToUpdateId).getBus();
        }

        if (bus != null && !bus.equals("")) {
            System.out.println("Change bus " + busDao.findByNumber(bus));
            UpdateRoute.bus = busDao.findByNumber(bus);
        }

        ModelAndView modelAndView = new ModelAndView("updateRoute", "", "");
        modelAndView.addObject("stations", stationDao.findAll());
        modelAndView.addObject("driving", new Driving());
        modelAndView.addObject("busses", busDao.findAll());
        System.out.println("route number = " + UpdateRoute.routeNumber);
        modelAndView.addObject("routeNumber", UpdateRoute.routeNumber);
        modelAndView.addObject("bus", UpdateRoute.bus.getBusNumber());

        for (int i = 0; i < UpdateRoute.drivings.size(); i++) {
            UpdateRoute.drivings.get(i).setUniqueRouteIdentifier((long) i);
        }
        modelAndView.addObject("drivings", UpdateRoute.drivings);
        System.out.println("drivings = " + UpdateRoute.drivings);

        Driving drivingToAdd = new Driving();

        try {
            drivingToAdd.setArrivalDate(Date.valueOf(arrivalDate));
            drivingToAdd.setDepartureDate(Date.valueOf(departureDate));
            drivingToAdd.setArrivalTime(Time.valueOf(arrivalTime));
            drivingToAdd.setDepartureTime(Time.valueOf(departureTime));
            drivingToAdd.setPrice((Double.parseDouble(price)));
        } catch (IllegalArgumentException e) {
            System.out.println("parse exception");
            System.out.println(modelAndView.getModel().get("drivings"));
            return modelAndView;
        }

        Station arrivalSt = stationDao.findByTitle(arrivalStation);
        Station departureSt = stationDao.findByTitle(departureStation);

        drivingToAdd.setArrivalStation(arrivalSt);
        drivingToAdd.setDepartureStation(departureSt);
        drivings.add(drivingToAdd);

        for (int i = 0; i < UpdateRoute.drivings.size(); i++) {
            UpdateRoute.drivings.get(i).setUniqueRouteIdentifier((long) i);
        }
        modelAndView.addObject("drivings", UpdateRoute.drivings);

        System.out.println("2 " + drivings);

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createInsert(@ModelAttribute("selectedBus") String bus) {
        System.out.println("bus  " + bus);
        if (bus != null && !bus.equals("")) {
            UpdateRoute.bus = busDao.findByNumber(bus);
        }
        Route route = new Route();
        route.setBus(UpdateRoute.bus);
        System.out.println(UpdateRoute.routeNumber);
        route.setRouteNumber(UpdateRoute.routeNumber);

        System.out.println(route);

        route.setId(routeId);

        routeDao.update(route);

        for (Driving driving : drivings) {
            driving.setRoute(route);
            drivingDao.create(driving);
        }

        System.out.println(">>>>");
        System.out.println(routeDao.findAll());
        System.out.println(drivingDao.findAll());
        return "redirect:/routeManagement";
    }

}
