package ua.khpi.baturin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.khpi.baturin.dao.contract.DrivingDao;
import ua.khpi.baturin.entity.Driving;

@Controller
@RequestMapping("/deleteDrivingUpdate")
public class DeleteDrivingUpdate {

    @Autowired
    private DrivingDao drivingDao;

    @RequestMapping(method = RequestMethod.POST)
    public String delete(@ModelAttribute("uniqueRouteIdentifier") String uniqueRouteIdentifier, BindingResult result,
            Model model) {
        System.out.println("uniqueRouteIdentifier: " + uniqueRouteIdentifier);
        System.out.println("drivings before delete " + UpdateRoute.drivings);
        System.out.println("unique identifier " + uniqueRouteIdentifier);

        List<Driving> localDrivings = new ArrayList<>(UpdateRoute.drivings);

        System.out.println("localDrivings " + localDrivings);

        synchronized (localDrivings) {
            for (int i = 0; i < localDrivings.size(); i++) {
                if (localDrivings.get(i).getUniqueRouteIdentifier() == Long.parseLong(uniqueRouteIdentifier)) {
                    System.out.println("removing " + i + " element");
                    System.out.println(localDrivings.get(i));
                    drivingDao.remove(localDrivings.get(i));
                    localDrivings.remove(i);
                }
            }
        }
        UpdateRoute.drivings = localDrivings;
        System.out.println(UpdateRoute.drivings);
        return "redirect:/updateRoute";
    }
}
