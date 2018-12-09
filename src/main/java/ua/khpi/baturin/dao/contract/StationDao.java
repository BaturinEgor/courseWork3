package ua.khpi.baturin.dao.contract;

import java.util.List;

import ua.khpi.baturin.entity.Station;

public interface StationDao {
    void create(Station station);

    void update(Station station);

    void remove(Station station);

    List<Station> findAll();

    Station findByTitle(String title);
}
