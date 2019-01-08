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
import ua.khpi.baturin.util.Validator;

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
        if (carrier.getTitle() != null) {
            if (!Validator.someCheck(carrier.getTitle())) {
                model.addAttribute("message", "Неверное название перевозчика");
                return "redirect:/carrierManagement";
            }
        }
        System.out.println(carrier);
        carrierDao.update(carrier);
        model.addAttribute("message", "Перевозчик успешно изменён");
        return "redirect:/carrierManagement";
    }
}
