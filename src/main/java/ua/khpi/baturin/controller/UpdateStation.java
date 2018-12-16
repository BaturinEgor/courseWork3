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
        busDao.update(station);
        model.addAttribute("message", "Station successfuly updated");
        return "redirect:/stationManagement";
    }
}
