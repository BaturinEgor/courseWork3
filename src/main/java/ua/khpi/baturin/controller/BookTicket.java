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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ua.khpi.baturin.dao.contract.DrivingDao;
import ua.khpi.baturin.dao.contract.RouteDao;
import ua.khpi.baturin.dao.contract.TicketDao;
import ua.khpi.baturin.entity.Driving;
import ua.khpi.baturin.entity.Route;
import ua.khpi.baturin.entity.Ticket;
import ua.khpi.baturin.entity.User;

@Controller
@RequestMapping("/bookTicket")
@SessionAttributes({ "user" })
public class BookTicket {

	@Autowired
	RouteDao routeDao;

	@Autowired
	TicketDao ticketDao;

	@Autowired
	DrivingDao drivingDao;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView initUpdating(@RequestParam(value = "id") Long id, @RequestParam(value = "date") String date,
			Model model) {
		System.out.println("date = " + date);
		Route route = routeDao.findById(id);
		List<Integer> seats = new ArrayList<>();
		List<String> seats2 = new ArrayList<>();
		for (int i = 1; i <= route.getBus().getSeats(); i++) {
			seats.add(i);
		}
		for (Ticket ticket : ticketDao.findByRoute(route)) {
			if (ticket.getDepartureDate().equals(date)) {
				seats2.add("~" + ticket.getSeat() + "~");
				// seats.remove(new Integer(ticket.getSeat()));
			}
		}

		System.out.println(seats);

		ModelAndView mav = new ModelAndView("bookTicket", "seats", seats);
		mav.addObject("seats2", seats2);
		mav.addObject("routeNumber", route.getRouteNumber());
		mav.addObject("routeId", id);
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView updateProcess(@SessionAttribute("user") User user, @ModelAttribute("routeId") String routeId,
			@ModelAttribute("seat") String seat, BindingResult result, Model model) {
		Route route = routeDao.findById(Long.parseLong(routeId));
		List<Driving> drivings = drivingDao.findByRoute(route);
		Ticket ticket = new Ticket();

		for (Driving driving : drivings) {
			if (driving.getArrivalStation().equals(FindTicketController.arrivalStation)) {
				ticket.setArrivalDate(FindTicketController.arrivalDate);
				ticket.setArrivalTime(driving.getArrivalTime());
				ticket.setArrivalStation(driving.getArrivalStation());
			}
			if (driving.getDepartureStation().equals(FindTicketController.departureStation)) {
				ticket.setDepartureDate(FindTicketController.departureDate);
				ticket.setDepartureTime(driving.getDepartureTime());
				ticket.setDepartureStation(driving.getDepartureStation());
			}
		}

		Double price = 0.0;

		boolean start = false;
		boolean finish = false;
		for (Driving driving : drivingDao.findByRoute(route)) {
			if (driving.getDepartureStation().equals(FindTicketController.departureStation)) {
				start = true;
			}
			System.out.println(driving);
			System.out.println("start " + start);
			System.out.println("finish " + finish);
			if (start == true && finish == false) {
				System.out.println("add price");
				price += driving.getPrice();
			}
			if (driving.getArrivalStation().equals(FindTicketController.arrivalStation)) {
				finish = true;
			}
		}

		ticket.setPrice(price);

		ticket.setRoute(route);
		ticket.setSeat(Integer.parseInt(seat));
		ticket.setUser(user);

		System.out.println("booked ticket = " + ticket);

		ticketDao.create(ticket);

		System.out.println("route id " + routeId);
		System.out.println("seat " + seat);

		return new ModelAndView("showTicket", "ticket", ticket);
	}
}
