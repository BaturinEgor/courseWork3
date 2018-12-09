package ua.khpi.baturin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ua.khpi.baturin.dto.Converter;
import ua.khpi.baturin.dto.UserDto;
import ua.khpi.baturin.entity.User;
import ua.khpi.baturin.service.contract.UserService;

@Controller
@RequestMapping("/create")
public class CreateController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView createInit() {
        UserDto userDto = new UserDto();
        return new ModelAndView("createUser", "user", userDto);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createInsert(@Valid @ModelAttribute("user") UserDto user,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "createUser";
        }
        User userToCreate = Converter.userDtoToUser(user);
        userToCreate.setPassword(
                new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.create(userToCreate);
        model.addAttribute("message", "User successfully created");
        return "redirect:/admin";
    }
}
