package ua.khpi.baturin.dao.contract;

import java.util.List;

import ua.khpi.baturin.entity.Driving;
import ua.khpi.baturin.entity.Route;

public interface DrivingDao {
    void create(Driving driving);

    void update(Driving driving);

    void remove(Driving driving);

    List<Driving> findAll();

    List<Driving> findByRoute(Route route);

    Driving findById(Long id);
}
