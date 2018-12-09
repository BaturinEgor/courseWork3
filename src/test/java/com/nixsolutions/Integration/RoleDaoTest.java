package com.nixsolutions.Integration;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.dbunit.IDatabaseTester;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;

import ua.khpi.baturin.configuration.HibernateConfiguration;
import ua.khpi.baturin.dao.contract.RoleDao;
import ua.khpi.baturin.dao.impl.HibernateRoleDao;
import ua.khpi.baturin.entity.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { HibernateConfiguration.class,
        HibernateRoleDao.class }, loader = AnnotationConfigWebContextLoader.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class RoleDaoTest {

    @Autowired
    IDatabaseTester databaseTester;
    @Autowired
    private RoleDao roleDao;

    @Test
    @DatabaseSetup({ "classpath:datasets/role/role-init-dataset.xml" })
    @ExpectedDatabase("classpath:datasets/role/create-role-dataset.xml")
    public void testShouldBeEqualAfterInsert() throws Exception {
        Role role = new Role(3l, "MODERATOR");
        roleDao.create(role);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateNullShouldThrowNPE() {
        roleDao.create(null);
    }

    @Test
    @DatabaseSetup({ "classpath:datasets/role/role-init-dataset.xml" })
    @ExpectedDatabase("classpath:datasets/role/update-role-dataset.xml")
    public void testShouldBeEqualAfterUpdate() throws Exception {
        Role role = new Role(2l, "MODERATOR");
        roleDao.update(role);
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateNullShouldThrowNPE() {
        roleDao.update(null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveNullShouldThrowNPE() {
        roleDao.remove(null);
    }

    @Test
    @DatabaseSetup({ "classpath:datasets/role/role-init-dataset.xml" })
    public void testFindAllShouldBeEqual() throws Exception {
        List<Role> roles = roleDao.findAll();
        Role adminRole = new Role(1l, "ADMIN");
        Role userRole = new Role(2l, "USER");
        List<Role> rolesToCompare = new ArrayList<>();
        rolesToCompare.add(adminRole);
        rolesToCompare.add(userRole);
        assertEquals(roles, rolesToCompare);
    }

    @Test
    @DatabaseSetup({ "classpath:datasets/role/role-init-dataset.xml" })
    public void testFindByNameShouldBeEqual() throws Exception {
        Role role = roleDao.findByName("ADMIN");
        Role roleToCompare = new Role(1l, "ADMIN");
        assertEquals(role, roleToCompare);
    }

    @Test(expected = NullPointerException.class)
    public void testfindByNameWithNullShouldThrowNPE() {
        roleDao.findByName(null);
    }

    @Test
    @DatabaseSetup({ "classpath:datasets/role/role-init-dataset.xml" })
    public void testFindByIdShouldBeEqual() throws Exception {
        Role role = roleDao.findById(1l);
        Role roleToCompare = new Role(1l, "ADMIN");
        assertEquals(role, roleToCompare);
    }

    @Test(expected = NullPointerException.class)
    public void testfindByIdWithNullShouldThrowNPE() {
        roleDao.findById(null);
    }
}
