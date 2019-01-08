package ua.khpi.baturin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ua.khpi.baturin.entity.User;
import ua.khpi.baturin.service.contract.RoleService;
import ua.khpi.baturin.service.contract.UserService;

@Controller
@RequestMapping("/create")
public class CreateController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView createInit() {
		return new ModelAndView("createUser", "user", new User());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String createInsert(@ModelAttribute("user") User user, BindingResult result, Model model) {
		System.out.println("User = " + user);
		System.out.println(result);
		if (result.hasErrors()) {
			return "createUser";
		}
		user.setRole(roleService.findByName(user.getRole().getName()));
		BCryptPasswordEncoder end = new BCryptPasswordEncoder();
		user.setPassword(end.encode(user.getPassword()));
		userService.create(user);
		model.addAttribute("message", "Пользователь успешно добавлен");
		return "redirect:/usersManagement";
	}
}
