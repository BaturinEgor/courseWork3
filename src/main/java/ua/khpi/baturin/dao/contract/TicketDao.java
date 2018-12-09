package ua.khpi.baturin.dao.contract;

import ua.khpi.baturin.entity.Client;
import ua.khpi.baturin.entity.Ticket;

public interface TicketDao {
    void create(Ticket ticket);

    void update(Ticket ticket);

    void remove(Ticket ticket);

    Ticket findByClient(Client client);
}
