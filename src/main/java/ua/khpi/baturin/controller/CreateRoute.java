package ua.khpi.baturin.controller;

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
@RequestMapping("/createRoute")
public class CreateRoute {

	public static List<Driving> drivings = new ArrayList<>();
	private static Bus bus = new Bus();
	private static String routeNumber;

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
			BindingResult result, Model model) {

		List<String> daysOfWeek = new ArrayList<>();
		daysOfWeek.add("Понедельник");
		daysOfWeek.add("Вторник");
		daysOfWeek.add("Среда");
		daysOfWeek.add("Четверг");
		daysOfWeek.add("Пятница");
		daysOfWeek.add("Суббота");
		daysOfWeek.add("Воскресенье");

		if (bus != null && !bus.equals("")) {
			CreateRoute.bus = busDao.findByNumber(bus);
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("days", daysOfWeek);
		modelAndView.addObject("drivings", drivings);
		modelAndView.addObject("stations", stationDao.findAll());
		modelAndView.addObject("driving", new Driving());
		modelAndView.addObject("busses", busDao.findAll());
		modelAndView.addObject("routeNumber", routeNumber);
		modelAndView.addObject("bus", CreateRoute.bus.getBusNumber());

		CreateRoute.routeNumber = routeNumber;
		System.out.println("Static carrier: " + CreateRoute.routeNumber);

		Driving drivingToAdd = new Driving();

		if (!arrivalTime.equals("")) {
			System.out.println("ds " + departureStation);
			System.out.println("as " + arrivalStation);
			if (!departureStation.equalsIgnoreCase("Станция отправления")
					&& !arrivalStation.equalsIgnoreCase("Станция прибытия")) {
				try {
					System.out.println(arrivalDate);
					drivingToAdd.setArrivalDate(arrivalDate);
					System.out.println(departureDate);
					drivingToAdd.setDepartureDate(departureDate);
					System.out.println(arrivalTime);
					drivingToAdd.setArrivalTime(Time.valueOf(arrivalTime));
					System.out.println(departureTime);
					drivingToAdd.setDepartureTime(Time.valueOf(departureTime));
					System.out.println(price);
					Double pc = Double.parseDouble(price);
					if(pc <= 0) {
						modelAndView.addObject("message", "Введена некорректная цена");
						return modelAndView;
					}
					drivingToAdd.setPrice(pc);
				} catch (IllegalArgumentException e) {
					System.out.println("parse exception");
					modelAndView.addObject("message", "Введены некорректные данные");
					return modelAndView;
				}
			} else {
				modelAndView.addObject("message", "Выберите станцию отправления и прибытия");
				return modelAndView;
			}
		} else {
			return modelAndView;
		}

		Station arrivalSt = stationDao.findByTitle(arrivalStation);
		Station departureSt = stationDao.findByTitle(departureStation);

		drivingToAdd.setArrivalStation(arrivalSt);
		drivingToAdd.setDepartureStation(departureSt);
		drivings.add(drivingToAdd);
		drivingToAdd.setUniqueRouteIdentifier(Long.valueOf(drivings.size()));

		System.out.println("d " + drivingToAdd);
		System.out.println(drivings);

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String createInsert() {
		Route route = new Route();
		route.setBus(CreateRoute.bus);
		route.setRouteNumber(CreateRoute.routeNumber);

		System.out.println(route);
		if (route.getRouteNumber().equals("")) {
			return "redirect:/routeManagement";
		}
		routeDao.create(route);

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
