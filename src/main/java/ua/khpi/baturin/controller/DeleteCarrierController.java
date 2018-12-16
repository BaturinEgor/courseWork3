package ua.khpi.baturin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.khpi.baturin.dao.contract.CarrierDao;
import ua.khpi.baturin.entity.Carrier;

@Controller
@RequestMapping("/deleteCarrier")
public class DeleteCarrierController {

    @Autowired
    private CarrierDao carrierDao;

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(value = "id") String id) {
        Long idUserToDelete = null;
        try {
            idUserToDelete = Long.parseLong(id);
        } catch (Exception e) {
            return new ModelAndView("redirect:/carrierManagement", "message", "Ошибка при удалении пеервозчика");
        }
        Carrier user = carrierDao.findById(idUserToDelete);
        carrierDao.remove(user);
        return new ModelAndView("redirect:/carrierManagement", "message", "Carrier successfuly deleted");
    }
}
