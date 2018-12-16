package ua.khpi.baturin.dao.contract;

import java.util.List;

import ua.khpi.baturin.entity.Bus;
import ua.khpi.baturin.entity.Carrier;

public interface BusDao {
    void create(Bus bus);

    void update(Bus bus);

    void remove(Bus bus);

    Bus findById(Long id);

    List<Bus> findAll();

    Bus findByNumber(String number);

    List<Bus> findByCarrier(Carrier carrier);
}
