package ua.khpi.baturin.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ticket")
public class Ticket implements Serializable {

	private static final long serialVersionUID = -5955089102744520756L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "departureTime")
	private Time departureTime;

	@Column(name = "departureDate")
	private String departureDate;

	@ManyToOne()
	private Station departureStation;

	@Column(name = "arrivalTime")
	private Time arrivalTime;

	@Column(name = "arrivalDate")
	private String arrivalDate;

	@ManyToOne()
	private Station arrivalStation;

	@Column(name = "price")
	private Double price;

	@ManyToOne()
	private Route route;

	@ManyToOne()
	private User user;

	@Column(name = "seat")
	private Integer seat;

	public Ticket() {

	}

	public Ticket(Long id, Time departureTime, Date departureDate, Station departureStation, Time arrivalTime,
			Date arrivalDate, Station arrivalStation, Double price, Route route, User user) {
		super();
		this.id = id;
		this.departureTime = departureTime;
		this.departureStation = departureStation;
		this.arrivalTime = arrivalTime;
		this.arrivalStation = arrivalStation;
		this.price = price;
		this.route = route;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getSeat() {
		return seat;
	}

	public void setSeat(Integer seat) {
		this.seat = seat;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Time getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Station getDepartureStation() {
		return departureStation;
	}

	public void setDepartureStation(Station departureStation) {
		this.departureStation = departureStation;
	}

	public Time getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public Station getArrivalStation() {
		return arrivalStation;
	}

	public void setArrivalStation(Station arrivalStation) {
		this.arrivalStation = arrivalStation;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrivalDate == null) ? 0 : arrivalDate.hashCode());
		result = prime * result + ((arrivalStation == null) ? 0 : arrivalStation.hashCode());
		result = prime * result + ((arrivalTime == null) ? 0 : arrivalTime.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((departureDate == null) ? 0 : departureDate.hashCode());
		result = prime * result + ((departureStation == null) ? 0 : departureStation.hashCode());
		result = prime * result + ((departureTime == null) ? 0 : departureTime.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((route == null) ? 0 : route.hashCode());
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
		Ticket other = (Ticket) obj;
		if (arrivalDate == null) {
			if (other.arrivalDate != null) {
				return false;
			}
		} else if (!arrivalDate.equals(other.arrivalDate)) {
			return false;
		}
		if (arrivalStation == null) {
			if (other.arrivalStation != null) {
				return false;
			}
		} else if (!arrivalStation.equals(other.arrivalStation)) {
			return false;
		}
		if (arrivalTime == null) {
			if (other.arrivalTime != null) {
				return false;
			}
		} else if (!arrivalTime.equals(other.arrivalTime)) {
			return false;
		}
		if (departureDate == null) {
			if (other.departureDate != null) {
				return false;
			}
		} else if (!departureDate.equals(other.departureDate)) {
			return false;
		}
		if (departureStation == null) {
			if (other.departureStation != null) {
				return false;
			}
		} else if (!departureStation.equals(other.departureStation)) {
			return false;
		}
		if (departureTime == null) {
			if (other.departureTime != null) {
				return false;
			}
		} else if (!departureTime.equals(other.departureTime)) {
			return false;
		}
		if (price == null) {
			if (other.price != null) {
				return false;
			}
		} else if (!price.equals(other.price)) {
			return false;
		}
		if (route == null) {
			if (other.route != null) {
				return false;
			}
		} else if (!route.equals(other.route)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", departureTime=" + departureTime + ", departureDate=" + departureDate
				+ ", departureStation=" + departureStation + ", arrivalTime=" + arrivalTime + ", arrivalDate="
				+ arrivalDate + ", arrivalStation=" + arrivalStation + ", price=" + price + ", route=" + route
				+ ", client=" + user + "]";
	}

}
