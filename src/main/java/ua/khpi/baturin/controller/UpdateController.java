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
		model.addAttribute("user", user);
		model.addAttribute("roles", roleService.findAll());
		return "updateUser";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String updateProcess(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
		model.addAttribute("roles", roleService.findAll());
		model.addAttribute("role", user.getRole());
		model.addAttribute("user", user);
		System.out.println("User to update >>>>>>> " + user);
		if (!ua.khpi.baturin.util.Validator.nameCheck(user.getFirstName())) {
			return "redirect:/updateUser";
		}
		BCryptPasswordEncoder end = new BCryptPasswordEncoder();
		user.setPassword(end.encode(user.getPassword()));
		user.setRole(roleService.findByName(user.getRole().getName()));
		if (user.getRole() != null) {
			userService.update(user);
		}
		model.addAttribute("message", "Пользователь успешно изменён");
		return "redirect:/usersManagement";
	}
}
