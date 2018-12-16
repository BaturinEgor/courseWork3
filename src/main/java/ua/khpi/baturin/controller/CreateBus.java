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
        System.out.println(bus);
        System.out.println(carrier);
        bus.setCarrier(carrierDao.findByTitle(carrier));
        System.out.println(bus);
        busDao.create(bus);
        model.addAttribute("message", "Bus successfully created");
        return "redirect:/busManagement";
    }
}