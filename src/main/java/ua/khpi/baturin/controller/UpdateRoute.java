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

		List<String> days = new ArrayList<>();
		days.add("Понедельник");
		days.add("Вторник");
		days.add("Среда");
		days.add("Четверг");
		days.add("Пятница");
		days.add("Суббота");
		days.add("Воскресенье");

		System.out.println(id);
		if (id != null && !id.equals("")) {
			Long routeToUpdateId = null;
			try {
				routeToUpdateId = Long.parseLong(id);
			} catch (NumberFormatException e) {
				return new ModelAndView("redirect:/routeManagement", "message",
						"ошибка при изменении маршрута, неверный id маршрута");
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
		modelAndView.addObject("days", days);

		for (int i = 0; i < UpdateRoute.drivings.size(); i++) {
			UpdateRoute.drivings.get(i).setUniqueRouteIdentifier((long) i);
		}
		modelAndView.addObject("drivings", UpdateRoute.drivings);
		System.out.println("drivings = " + UpdateRoute.drivings);

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
					if (pc <= 0) {
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

		for (int i = 0; i < UpdateRoute.drivings.size(); i++) {
			UpdateRoute.drivings.get(i).setUniqueRouteIdentifier((long) i);
		}
		modelAndView.addObject("drivings", UpdateRoute.drivings);

		System.out.println("2 " + drivings);

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String createInsert(@ModelAttribute("selectedBus") String bus,
			@ModelAttribute("routeNumber") String number) {
		System.out.println("bus  " + bus);
		System.out.println("number = " + number);
		if (bus != null && !bus.equals("")) {
			UpdateRoute.bus = busDao.findByNumber(bus);
		}
		Route route = new Route();
		route.setBus(UpdateRoute.bus);
		System.out.println(UpdateRoute.routeNumber);
		route.setRouteNumber(UpdateRoute.routeNumber);

		System.out.println(route);

		route.setId(routeId);
		route.setRouteNumber(number);

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
