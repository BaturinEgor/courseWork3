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
import org.springframework.web.bind.annotation.RequestParam;

import ua.khpi.baturin.dto.Converter;
import ua.khpi.baturin.dto.UserDto;
import ua.khpi.baturin.entity.User;
import ua.khpi.baturin.service.contract.RoleService;
import ua.khpi.baturin.service.contract.UserService;

@Controller
@RequestMapping("/updateUser")
public class UpdateController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    public String initUpdating(@RequestParam(value = "id") Long id, Model model) {
        User user = userService.findById(id);
        user.setPassword("");
        model.addAttribute("role", user.getRole().getName());
        model.addAttribute("user", Converter.userToUserDto(user));
        model.addAttribute("roles", roleService.findAll());
        return "updateUser";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String updateProcess(@Valid @ModelAttribute("user") UserDto user, BindingResult result, Model model) {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("role", user.getRole());
        model.addAttribute("user", user);
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("error", "Password are not the same");
            return "updateUser";
        }
        if (result.hasErrors()) {
            return "updateUser";
        }
        User userToUpdate = Converter.userDtoToUser(user);
        userToUpdate.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.update(userToUpdate);

        model.addAttribute("message", "User successfully updated");
        return "redirect:/admin";
    }
}
