package ua.khpi.baturin.dao.contract;

import java.util.List;

import ua.khpi.baturin.entity.Status;

public interface StatusDao {
    void create(Status status);

    void update(Status status);

    void remove(Status status);

    List<Status> findAll();

    Status findByTitle(String title);
}
