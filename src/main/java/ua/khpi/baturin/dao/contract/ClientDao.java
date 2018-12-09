package ua.khpi.baturin.dao.contract;

import java.util.List;

import ua.khpi.baturin.entity.Client;

public interface ClientDao {
    void create(Client client);

    void update(Client client);

    void remove(Client client);

    List<Client> findAll();

    Client findById(Long id);

    Client findByLogin(String login);
}
