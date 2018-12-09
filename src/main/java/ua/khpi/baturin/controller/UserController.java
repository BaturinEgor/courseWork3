package ua.khpi.baturin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(method = RequestMethod.POST)
    public String managePostUserCabinet() {
        return "user_cabinet";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String manageGetUserCabinet() {
        return "user_cabinet";
    }

}
