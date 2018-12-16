package ua.khpi.baturin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.khpi.baturin.dao.contract.BusDao;
import ua.khpi.baturin.dao.contract.CarrierDao;
import ua.khpi.baturin.entity.Bus;

@Controller
@RequestMapping("/updateBus")
public class UpdateBusController {

    @Autowired
    BusDao busDao;

    @Autowired
    CarrierDao carrierDao;

    @RequestMapping(method = RequestMethod.GET)
    public String initUpdating(@RequestParam(value = "id") Long id, Model model) {
        Bus bus = busDao.findById(id);
        System.out.println(bus);
        model.addAttribute("bus", bus);
        model.addAttribute("carriers", carrierDao.findAll());
        return "updateBus";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String updateProcess(@ModelAttribute("bus") Bus bus, BindingResult result, Model model,
            @ModelAttribute("carrier") String carrier) {
        System.out.println(carrier);
        System.out.println(bus);
        bus.setCarrier(carrierDao.findByTitle(carrier));
        System.out.println(bus);
        busDao.update(bus);
        model.addAttribute("message", "Bus successfuly updated");
        return "redirect:/busManagement";
    }
}
