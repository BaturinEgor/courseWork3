package ua.khpi.baturin.controller;

import java.text.ParseException;
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
import ua.khpi.baturin.entity.Ticket;
import ua.khpi.baturin.entity.TicketToBuyForm;
import ua.khpi.baturin.util.DateParser;

@Controller
@RequestMapping("/findTicket")
public class FindTicketController {

	public static Station arrivalStation;
	public static Station departureStation;

	public static String arrivalDate;
	public static String departureDate;

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
			BindingResult result, Model model) throws ParseException {

		departureDate = driving.getDepartureDate();
		arrivalDate = driving.getArrivalDate();
		try {
			System.out.println("parsed day = " + DateParser.parse(driving.getDepartureDate()));
		} catch (ParseException e) {
			ModelAndView modelAndView = new ModelAndView("user", "message",
					"Введите дату в формете дд-ММ-ггг, например: 08-01-2019");
			modelAndView.addObject("stations", stationDao.findAll());
			return modelAndView;
		}

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

			if (driving != null) {
				for (Driving driving2 : drivingDao.findByRoute(route)) {
					if (driving2.getDepartureStation().getTitle().equals(departureStation)) {
						haveDeparture = true;
					}
					if (driving2.getArrivalStation().getTitle().equals(arrivalStation) && haveDeparture) {
						haveArrival = true;
					}
					System.out.println("1: " + DateParser.parse(driving.getDepartureDate()));
					System.out.println("2: " + driving2.getDepartureDate());
					if (DateParser.parse(driving.getDepartureDate()).equalsIgnoreCase(driving2.getDepartureDate())) {
						haveCorrectDate = true;
					}
				}
				if (haveDeparture && haveArrival && haveCorrectDate) {
					TicketToBuyForm ttb = new TicketToBuyForm();
					ttb.setRoute(route);
					int amountOfSeats = route.getBus().getSeats();
					List<Ticket> tickets = ticketDao.findByRoute(route);
					for (Ticket ticketToBuyForm : tickets) {
						if (ticketToBuyForm.getDepartureDate().equals(driving.getDepartureDate())) {
							amountOfSeats--;
						}
					}
					ttb.setAmountOfSeats(amountOfSeats);
					ttb.setDrivings(drivingDao.findByRoute(route));
					routes.add(ttb);
				}
			}
		}

		System.out.println(routes.size());
		for (TicketToBuyForm ticketToBuyForm : routes) {
			System.out.println(ticketToBuyForm.getRoute());
			System.out.println(ticketToBuyForm.getAmountOfSeats());
		}
		System.out.println("1 " + driving.getDepartureDate());
		ModelAndView modelAndView = new ModelAndView("routesList", "routes", routes);
		modelAndView.addObject("date", DateParser.parse(driving.getDepartureDate()));
		modelAndView.addObject("date2", driving.getDepartureDate());
		modelAndView.addObject("departureStation", departureStation);
		modelAndView.addObject("arrivalStation", arrivalStation);
		return modelAndView;
	}
}