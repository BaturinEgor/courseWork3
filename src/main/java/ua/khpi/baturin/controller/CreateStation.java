package ua.khpi.baturin.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ua.khpi.baturin.dao.contract.StationDao;
import ua.khpi.baturin.entity.Station;

@Controller
@RequestMapping("/createStation")
public class CreateStation {

    @Autowired
    private StationDao carrierDao;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView createInit(HttpServletRequest req) throws UnsupportedEncodingException {
        Station station = new Station();
        return new ModelAndView("createStation", "station", station);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createInsert(@ModelAttribute("station") Station station, BindingResult result, Model model,
            HttpServletRequest req) throws UnsupportedEncodingException {
        carrierDao.create(station);
        model.addAttribute("message", "Station successfully creaated");
        return "redirect:/stationManagement";
    }
}
