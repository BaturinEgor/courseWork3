package ua.khpi.baturin.controller;

import java.io.IOException;

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
import org.springframework.web.servlet.ModelAndView;

import ua.khpi.baturin.dto.Converter;
import ua.khpi.baturin.dto.UserDto;
import ua.khpi.baturin.entity.User;
import ua.khpi.baturin.service.contract.UserService;
import ua.khpi.baturin.validator.VerifyRecaptcha;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView createInit() {
        UserDto userDto = new UserDto();
        return new ModelAndView("registrationPage", "user", userDto);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processRegistration(
            @Valid @ModelAttribute("user") UserDto user, BindingResult result,
            Model model,
            @RequestParam("g-recaptcha-response") String captchaResponse) {
        try {
            boolean verify = VerifyRecaptcha.verify(captchaResponse);
            if (!verify) {
                model.addAttribute("message", "Fail while captcha validation");
                return "registrationPage";
            }
        } catch (IOException e) {
            model.addAttribute("message", "Fail while captcha validation");
            return "registrationPage";
        }
        System.out.println("there4");

        if (result.hasErrors()) {
            return "registrationPage";
        }

        User userToCreate = Converter.userDtoToUser(user);
        userToCreate.setPassword(
                new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.create(userToCreate);

        model.addAttribute("message", "User successfully registered");
        return "indexPage";
    }
}
