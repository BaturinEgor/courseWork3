package ua.khpi.baturin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "route")
public class Route implements Serializable {

    private static final long serialVersionUID = -1489896849297801816L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "routeNumber")
    private String routeNumber;

    @OneToOne()
    @JoinColumn(name = "bus_id")
    private Bus bus;

    public Route() {

    }

    public Route(Long id, String routeNumber, Bus bus) {
        super();
        this.id = id;
        this.routeNumber = routeNumber;
        this.bus = bus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public String getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(String routeNumber) {
        this.routeNumber = routeNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bus == null) ? 0 : bus.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((routeNumber == null) ? 0 : routeNumber.hashCode());
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
        Route other = (Route) obj;
        if (bus == null) {
            if (other.bus != null) {
                return false;
            }
        } else if (!bus.equals(other.bus)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (routeNumber == null) {
            if (other.routeNumber != null) {
                return false;
            }
        } else if (!routeNumber.equals(other.routeNumber)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Route [id=" + id + ", routeNumber=" + routeNumber + ", bus=" + bus + "]";
    }

}
