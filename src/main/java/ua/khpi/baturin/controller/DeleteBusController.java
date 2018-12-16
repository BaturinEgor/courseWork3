package ua.khpi.baturin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.khpi.baturin.dao.contract.BusDao;
import ua.khpi.baturin.entity.Bus;

@Controller
@RequestMapping("/deleteBus")
public class DeleteBusController {

    @Autowired
    private BusDao busDao;

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(value = "id") String id) {
        Long idUserToDelete = null;
        try {
            idUserToDelete = Long.parseLong(id);
        } catch (Exception e) {
            return new ModelAndView("redirect:/busManagement", "message", "Error while bus deleting");
        }
        Bus user = busDao.findById(idUserToDelete);
        busDao.remove(user);
        return new ModelAndView("redirect:/busManagement", "message", "Bus successfuly deleted");
    }
}
