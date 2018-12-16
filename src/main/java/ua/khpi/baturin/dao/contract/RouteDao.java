package ua.khpi.baturin.dao.contract;

import java.util.List;

import ua.khpi.baturin.entity.Route;

public interface RouteDao {
    void create(Route route);

    void update(Route route);

    void remove(Route route);

    List<Route> findAll();

    Route findByNumber(String number);

    Route findById(Long id);
}
