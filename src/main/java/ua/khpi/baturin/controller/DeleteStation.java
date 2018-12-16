package ua.khpi.baturin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.khpi.baturin.dao.contract.StationDao;
import ua.khpi.baturin.entity.Station;

@Controller
@RequestMapping("/deleteStation")
public class DeleteStation {

    @Autowired
    private StationDao busDao;

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(value = "id") String id) {
        Long idUserToDelete = null;
        try {
            idUserToDelete = Long.parseLong(id);
        } catch (Exception e) {
            return new ModelAndView("redirect:/stationManagement", "message", "Error while station deleting");
        }
        Station user = busDao.findById(idUserToDelete);
        busDao.remove(user);
        return new ModelAndView("redirect:/stationManagement", "message", "Station successfuly deleted");
    }
}
