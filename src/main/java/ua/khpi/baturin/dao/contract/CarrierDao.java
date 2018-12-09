package ua.khpi.baturin.dao.contract;

import java.util.List;

import ua.khpi.baturin.entity.Carrier;

public interface CarrierDao {
    void create(Carrier carrier);

    void update(Carrier carrier);

    void remove(Carrier carrier);

    List<Carrier> findAll();

    Carrier findByTitle(String title);

    Carrier findById(Long id);
}
