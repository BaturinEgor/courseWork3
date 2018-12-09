package ua.khpi.baturin.service.contract;

import java.util.List;

import ua.khpi.baturin.entity.Role;

public interface RoleService {

    void create(Role role);

    void update(Role role);

    void remove(Role role);

    Role findByName(String name);

    List<Role> findAll();

    Role findById(Long id);
}
