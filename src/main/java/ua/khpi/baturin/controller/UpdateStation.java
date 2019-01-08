package ua.khpi.baturin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.khpi.baturin.dao.contract.StationDao;
import ua.khpi.baturin.entity.Station;
import ua.khpi.baturin.util.Validator;

@Controller
@RequestMapping("/updateStation")
public class UpdateStation {

	@Autowired
	StationDao busDao;

	@RequestMapping(method = RequestMethod.GET)
	public String initUpdating(@RequestParam(value = "id") Long id, Model model) {
		Station bus = busDao.findById(id);
		System.out.println(bus);
		model.addAttribute("station", bus);
		return "updateStation";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String updateProcess(@ModelAttribute("station") Station station, BindingResult result, Model model) {
		System.out.println(station);
		if (!Validator.stationTitleCheck(station.getTitle())) {
			model.addAttribute("message", "Некорректное название станции");
			return "redirect:/stationManagement";
		}
		station.setTitle(processTitle(station.getTitle()));
		busDao.update(station);
		model.addAttribute("message", "Станция успешно изменена");
		return "redirect:/stationManagement";
	}

	private String processTitle(String title) {
		String[] words = title.split(" ");
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			String firstCapitalLetter = word.substring(0, 1).toUpperCase();
			result.append(firstCapitalLetter);
			result.append(word.substring(1, word.length()));
			if(i < words.length - 1) {
				result.append(" ");
			}
		}
		return result.toString();
	}
}
