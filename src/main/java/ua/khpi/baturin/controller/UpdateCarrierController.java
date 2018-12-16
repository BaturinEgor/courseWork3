package ua.khpi.baturin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.khpi.baturin.dao.contract.CarrierDao;
import ua.khpi.baturin.entity.Carrier;

@Controller
@RequestMapping("/updateCarrier")
public class UpdateCarrierController {

    @Autowired
    CarrierDao carrierDao;

    @RequestMapping(method = RequestMethod.GET)
    public String initUpdating(@RequestParam(value = "id") Long id, Model model) {
        Carrier carrier = carrierDao.findById(id);
        System.out.println(carrier);
        model.addAttribute("carrier", carrier);
        return "updateCarrier";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String updateProcess(@ModelAttribute("carrier") Carrier carrier, BindingResult result, Model model) {
        System.out.println(carrier);
        carrierDao.update(carrier);
        model.addAttribute("message", "Carrier successfuly updated");
        return "redirect:/carrierManagement";
    }
}
