package ua.khpi.baturin.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/home")
@SessionAttributes("SPRING_SECURITY_CONTEXT")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String homeManagement(
            @ModelAttribute("SPRING_SECURITY_CONTEXT") SecurityContextImpl context) {
        UserDetails user = (UserDetails) context.getAuthentication()
                .getPrincipal();
        for (GrantedAuthority auth : user.getAuthorities()) {
            if (auth.getAuthority().equals("ADMIN")) {
                return "redirect:/admin";
            } else {
                return "redirect:/user";
            }
        }
        return "index";
    }
}
