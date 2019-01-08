package ua.khpi.baturin.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ua.khpi.baturin.dao.contract.BusDao;
import ua.khpi.baturin.dao.contract.CarrierDao;
import ua.khpi.baturin.entity.Bus;
import ua.khpi.baturin.entity.Carrier;

@Controller
@RequestMapping("/busManagement")
public class BusManagerController {

	@Autowired
	private BusDao busDao;

	@Autowired
	private CarrierDao carrierDao;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getManageAdminCabinet(@RequestParam(value = "message", required = false) String message,
			@ModelAttribute("title") String title, BindingResult result, Model model,
			@ModelAttribute("number") String number) {
		if (!title.equals("")) {
			Carrier carrier = carrierDao.findByTitle(title);
			if (carrier != null) {
				ModelAndView mv = new ModelAndView("bus", "busses", busDao.findByCarrier(carrier));
				mv.addObject("message", message);
				return mv;
			} else {
				ModelAndView mv = new ModelAndView("bus", "busses", busDao.findAll());
				mv.addObject("message", "Перевозчика с данным названием нет в базе");
				return mv;
			}
		}
		if (!number.equals("")) {
			Bus bus = busDao.findByNumber(number);
			if (bus != null) {
				ArrayList<Bus> busToReturn = new ArrayList<Bus>();
				busToReturn.add(bus);
				ModelAndView mv = new ModelAndView("bus", "busses", busToReturn);
				mv.addObject("message", message);
				return mv;
			} else {
				ModelAndView mv = new ModelAndView("bus", "busses", busDao.findAll());
				mv.addObject("message", "Автобус с указанным номером не найден в системе");
				return mv;
			}
		}
		ModelAndView mv = new ModelAndView("bus", "busses", busDao.findAll());
		mv.addObject("message", message);
		return mv;
	}
}
