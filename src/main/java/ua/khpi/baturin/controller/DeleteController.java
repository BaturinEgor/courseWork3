package ua.khpi.baturin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.khpi.baturin.entity.User;
import ua.khpi.baturin.service.contract.UserService;

@Controller
@RequestMapping("/delete")
public class DeleteController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(value = "id") String id) {
        Long idUserToDelete = null;
        try {
            idUserToDelete = Long.parseLong(id);
        } catch (Exception e) {
            return new ModelAndView("redirect:/admin", "message", "User to delete is not found");
        }
        User user = userService.findById(idUserToDelete);
        userService.remove(user);
        return new ModelAndView("redirect:/admin", "message", "User successfully deleted");
    }
}
