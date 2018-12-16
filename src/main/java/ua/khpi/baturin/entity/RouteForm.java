package ua.khpi.baturin.entity;

import java.util.List;

public class RouteForm {
    private Route rout;
    private List<Driving> drivings;

    public RouteForm() {

    }

    public Route getRout() {
        return rout;
    }

    public void setRout(Route rout) {
        this.rout = rout;
    }

    public List<Driving> getDrivings() {
        return drivings;
    }

    public void setDrivings(List<Driving> drivings) {
        this.drivings = drivings;
    }

}
