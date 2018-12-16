package ua.khpi.baturin.entity;

import java.util.List;

public class TicketToBuyForm {
    private Route route;
    private int amountOfSeats;
    private List<Driving> drivings;

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public int getAmountOfSeats() {
        return amountOfSeats;
    }

    public void setAmountOfSeats(int amountOfSeats) {
        this.amountOfSeats = amountOfSeats;
    }

    public List<Driving> getDrivings() {
        return drivings;
    }

    public void setDrivings(List<Driving> drivings) {
        this.drivings = drivings;
    }

}
