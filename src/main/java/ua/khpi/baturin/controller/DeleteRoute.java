package ua.khpi.baturin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.khpi.baturin.dao.contract.RouteDao;
import ua.khpi.baturin.entity.Route;

@Controller
@RequestMapping("/deleteRoute")
public class DeleteRoute {

    @Autowired
    private RouteDao carrierDao;

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(value = "id") String id) {
        Long idUserToDelete = null;
        try {
            idUserToDelete = Long.parseLong(id);
        } catch (Exception e) {
            return new ModelAndView("redirect:/routeManagement", "message", "Ошибка при удалении маршрута");
        }
        Route user = carrierDao.findById(idUserToDelete);
        carrierDao.remove(user);
        return new ModelAndView("redirect:/routeManagement", "message", "Route successfuly deleted");
    }
}
