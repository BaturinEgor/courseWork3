package ua.khpi.baturin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ua.khpi.baturin.dao.contract.CarrierDao;
import ua.khpi.baturin.entity.Carrier;

@Controller
@RequestMapping("/createCarrier")
public class CreateCarrier {

    @Autowired
    private CarrierDao carrierDao;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView createInit() {
        Carrier carrier = new Carrier();
        return new ModelAndView("createCarrier", "carrier", carrier);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createInsert(@Valid @ModelAttribute("carrier") Carrier carrier, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "createCarrier";
        }
        carrierDao.create(carrier);
        model.addAttribute("message", "Carrier successfully creaated");
        return "redirect:/carrierManagement";
    }
}
