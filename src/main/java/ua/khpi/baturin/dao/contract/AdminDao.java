package ua.khpi.baturin.dao.contract;

import java.util.List;

import ua.khpi.baturin.entity.Admin;

public interface AdminDao {
    void create(Admin admin);

    void update(Admin admin);

    void remove(Admin admin);

    List<Admin> findAll();

    Admin findById(Long id);

    Admin findByLogin(String login);
}
