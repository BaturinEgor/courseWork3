package ua.khpi.baturin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.khpi.baturin.entity.Driving;

@Controller
@RequestMapping("/deleteDriving")
public class DeleteDriving {

    @RequestMapping(method = RequestMethod.POST)
    public String delete(@ModelAttribute("uniqueRouteIdentifier") String uniqueRouteIdentifier,
            @ModelAttribute("bus") String bus, BindingResult result, Model model) {
        System.out.println("uniqueRouteIdentifier: " + uniqueRouteIdentifier);
        System.out.println("drivings before delete " + CreateRoute.drivings);

        List<Driving> localDrivings = new ArrayList<>(CreateRoute.drivings);

        System.out.println("localDrivings " + localDrivings);

        synchronized (localDrivings) {
            for (int i = 0; i < localDrivings.size(); i++) {
                if (localDrivings.get(i).getUniqueRouteIdentifier() == Long.parseLong(uniqueRouteIdentifier)) {
                    localDrivings.remove(i);
                }
            }
        }
        CreateRoute.drivings = localDrivings;
        model.addAttribute("bus", bus);
        System.out.println(CreateRoute.drivings);
        return "redirect:/createRoute";
    }
}
