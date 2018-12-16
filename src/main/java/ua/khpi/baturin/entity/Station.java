package ua.khpi.baturin.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "station")
public class Station implements Serializable {

    private static final long serialVersionUID = -26860472794155605L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "arrivalStation")
    private List<Driving> drivings;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departureStation")
    private List<Driving> drivings2;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "arrivalStation")
    private List<Ticket> tickets;

    public Station() {

    }

    public Station(Long id, String title) {
        super();
        this.id = id;
        this.title = title;
    }

    public List<Driving> getDrivings2() {
        return drivings2;
    }

    public void setDrivings2(List<Driving> drivings2) {
        this.drivings2 = drivings2;
    }

    public List<Driving> getDrivings() {
        return drivings;
    }

    public void setDrivings(List<Driving> drivings) {
        this.drivings = drivings;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
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
        Station other = (Station) obj;
        if (title == null) {
            if (other.title != null) {
                return false;
            }
        } else if (!title.equals(other.title)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Station [id=" + id + ", title=" + title + "]";
    }

}
