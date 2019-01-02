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
import org.springframework.web.bind.annotation.RequestParam;

import ua.khpi.baturin.dao.contract.DrivingDao;
import ua.khpi.baturin.dao.contract.StationDao;
import ua.khpi.baturin.entity.Driving;

@Controller
@RequestMapping("/updateDriving")
public class UpdateDrivingUpdate {

    @Autowired
    private StationDao stationDao;

    @Autowired
    private DrivingDao drivingDao;

    @RequestMapping(method = RequestMethod.GET)
    public String initUpdating(@RequestParam(value = "uniqueRouteIdentifier") Long uniqueRouteIdentifier, Model model) {
        System.out.println("identifier = " + uniqueRouteIdentifier);
        Driving drivingToUpdate = null;
        for (Driving driving : UpdateRoute.drivings) {
            if (driving.getUniqueRouteIdentifier() == uniqueRouteIdentifier) {
                drivingToUpdate = driving;
            }
        }
        System.out.println("dr to updt = " + drivingToUpdate);
        model.addAttribute("driving", drivingToUpdate);
        model.addAttribute("stations", stationDao.findAll());

        List<String> days = new ArrayList<>();
        days.add("Понедельник");
        days.add("Вторник");
        days.add("Среда");
        days.add("Четверг");
        days.add("Пятница");
        days.add("Суббота");
        days.add("Воскресенье");
        model.addAttribute("days", days);
        return "updateDriving";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String updateProcess(@ModelAttribute("departureStation") String departureStation,
            @ModelAttribute("arrivalStation") String arrivalStation, @ModelAttribute("driving") Driving driving,
            BindingResult result, Model model) {

        System.out.println("departure station " + departureStation);
        System.out.println("arrival station " + arrivalStation);
        System.out.println("updated driving = " + driving);

        driving.setArrivalStation(stationDao.findByTitle(arrivalStation));
        driving.setDepartureStation(stationDao.findByTitle(departureStation));

        for (int i = 0; i < UpdateRoute.drivings.size(); i++) {
            if (UpdateRoute.drivings.get(i).getUniqueRouteIdentifier() == driving.getUniqueRouteIdentifier()) {
                drivingDao.remove(UpdateRoute.drivings.get(i));
                UpdateRoute.drivings.set(i, driving);
            }
        }

        model.addAttribute("message", "Переезд успешно изменен");
        return "redirect:/updateRoute";
    }
}
