package ua.khpi.baturin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ua.khpi.baturin.dao.contract.BusDao;
import ua.khpi.baturin.dao.contract.CarrierDao;
import ua.khpi.baturin.entity.Bus;
import ua.khpi.baturin.entity.Carrier;
import ua.khpi.baturin.util.Validator;

@Controller
@RequestMapping("/createBus")
public class CreateBus {

	@Autowired
	private BusDao busDao;

	@Autowired
	private CarrierDao carrierDao;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView createInit(Model model) {
		model.addAttribute("carriers", carrierDao.findAll());
		Bus bus = new Bus();
		Carrier carrier = new Carrier();
		model.addAttribute("carrier", carrier);
		return new ModelAndView("createBus", "bus", bus);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String createInsert(@ModelAttribute("bus") Bus bus, BindingResult result, Model model,
			@ModelAttribute("carrier") String carrier) {
		System.out.println("bus>>>>> " + bus);
		if (bus.getBusNumber() != null) {
			if (!Validator.busNumberCheck(bus.getBusNumber())) {
				model.addAttribute("message", "Неверный номер автобуа");
				return "redirect:/busManagement";
			}
			if (bus.getSeats() <= 0) {
				model.addAttribute("message", "Ошибка при вводе количества мест");
				return "redirect:/busManagement";
			}
		}
		bus.setCarrier(carrierDao.findByTitle(carrier));
		System.out.println("bus>>>>> " + bus);
		busDao.create(bus);
		model.addAttribute("message", "Автобус успешно добавлен");
		return "redirect:/busManagement";
	}
}
