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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ua.khpi.baturin.dao.contract.CarrierDao;
import ua.khpi.baturin.entity.Carrier;

@Controller
@RequestMapping("/carrierManagement")
public class CarrierManagementController {

	@Autowired
	private CarrierDao carrierDao;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getManageAdminCabinet(@RequestParam(value = "message", required = false) String message,
			@ModelAttribute("title") String title, BindingResult result, Model model) {
		if (!title.equals("")) {
			Carrier carrier = carrierDao.findByTitle(title);
			if (carrier != null) {
				List<Carrier> carrierToReturn = new ArrayList<>();
				carrierToReturn.add(carrier);
				ModelAndView mv = new ModelAndView("carrier", "carriers", carrierToReturn);
				mv.addObject("message", message);
				return mv;
			} else {
				ModelAndView mv = new ModelAndView("carrier", "carriers", carrierDao.findAll());
				mv.addObject("message", "Перевозчик с указанным названием не найден");
				return mv;
			}
		}
		ModelAndView mv = new ModelAndView("carrier", "carriers", carrierDao.findAll());
		mv.addObject("message", message);
		return mv;
	}
}
