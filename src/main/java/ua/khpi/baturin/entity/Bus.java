package ua.khpi.baturin.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "bus")
public class Bus implements Serializable {

    private static final long serialVersionUID = 3256387274926808043L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "bus_number")
    private String busNumber;

    @Pattern(regexp = "[0-9]*", message = "пароль должен состоять только из латинских букв и иметь длинну 8-32 символов")
    @Column(name = "seats")
    private int seats;

    @ManyToOne()
    @JoinColumn(name = "carrier_id")
    private Carrier carrier;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bus")
    private List<Route> routes;

    public Bus() {

    }

    public Bus(Long id, String busNumber, int seats, Carrier carrier) {
        super();
        this.id = id;
        this.busNumber = busNumber;
        this.seats = seats;
        this.carrier = carrier;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((busNumber == null) ? 0 : busNumber.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Bus other = (Bus) obj;
        if (busNumber == null) {
            if (other.busNumber != null) {
                return false;
            }
        } else if (!busNumber.equals(other.busNumber)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Номер: " + busNumber + ", Мест: " + seats + ", Перевозчик: " + carrier.getTitle();
    }

}
