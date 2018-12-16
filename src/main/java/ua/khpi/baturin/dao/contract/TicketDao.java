package ua.khpi.baturin.dao.contract;

import java.util.List;

import ua.khpi.baturin.entity.Route;
import ua.khpi.baturin.entity.Ticket;
import ua.khpi.baturin.entity.User;

public interface TicketDao {
    void create(Ticket ticket);

    void update(Ticket ticket);

    void remove(Ticket ticket);

    List<Ticket> findByClient(User user);

    List<Ticket> findByRoute(Route route);

    Ticket findById(Long id);
}
