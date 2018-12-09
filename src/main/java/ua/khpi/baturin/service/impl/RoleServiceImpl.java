package ua.khpi.baturin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.khpi.baturin.dao.contract.RoleDao;
import ua.khpi.baturin.entity.Role;
import ua.khpi.baturin.service.contract.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void create(Role role) {
        roleDao.create(role);
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
    }

    @Override
    public void remove(Role role) {
        roleDao.remove(role);
    }

    @Override
    public Role findByName(String name) {
        return roleDao.findByName(name);
    }

    @Override
    public Role findById(Long id) {
        return roleDao.findById(id);
    }

}
