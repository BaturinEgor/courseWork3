package ua.khpi.baturin.dao.contract;

import java.util.List;

import ua.khpi.baturin.entity.Client;
import ua.khpi.baturin.entity.Route;
import ua.khpi.baturin.entity.Ticket;

public interface TicketDao {
    void create(Ticket ticket);

    void update(Ticket ticket);

    void remove(Ticket ticket);

    List<Ticket> findByClient(Client client);

    List<Ticket> findByRoute(Route route);
}
