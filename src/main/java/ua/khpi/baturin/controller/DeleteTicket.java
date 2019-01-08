package ua.khpi.baturin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.khpi.baturin.dao.contract.StationDao;
import ua.khpi.baturin.dao.contract.TicketDao;
import ua.khpi.baturin.entity.Station;
import ua.khpi.baturin.entity.Ticket;

@Controller
@RequestMapping("/deleteTicket")
public class DeleteTicket {

    @Autowired
    private TicketDao busDao;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam(value = "id") String id) {
        Long idUserToDelete = null;
        try {
            idUserToDelete = Long.parseLong(id);
        } catch (Exception e) {
            return new ModelAndView("redirect:/privateabinet", "message", "Ошибка при удалении билета");
        }
        Ticket user = busDao.findById(idUserToDelete);
        busDao.remove(user);
        return new ModelAndView("redirect:/privateCabinet", "message", "Билет успешно удалён");
    }
}
