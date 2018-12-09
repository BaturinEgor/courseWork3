package com.nixsolutions.Integration;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
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
import ua.khpi.baturin.dao.contract.UserDao;
import ua.khpi.baturin.dao.impl.HibernateUserDao;
import ua.khpi.baturin.entity.Role;
import ua.khpi.baturin.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { HibernateConfiguration.class,
        HibernateUserDao.class }, loader = AnnotationConfigWebContextLoader.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class UserDaoTest {

    @Autowired
    IDatabaseTester databaseTester;
    @Autowired
    UserDao userDao;

    @Test
    @DatabaseSetup({ "classpath:datasets/user/user-init-dataset.xml" })
    @ExpectedDatabase("classpath:datasets/user/create-user-dataset.xml")
    public void testShouldBeEqualAfterInsert() throws Exception {
        User user = new User();
        user.setId(3l);
        user.setLogin("userLogin2");
        user.setPassword("$2a$10$wM9PFLlfl3Jf5hUPpvRuBOHpCDVPJ5FDfAuUU2DCBiXWNa.7JWBhS");
        user.setEmail("user2@ukr.net");
        user.setFirstName("Pavel");
        user.setLastName("Pavlov");
        user.setBirthday(Date.valueOf("2003-03-03"));
        user.setRole(new Role(2l, "USER"));
        userDao.create(user);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateNullShouldThrowNPE() {
        userDao.create(null);
    }

    @Test
    @DatabaseSetup({ "classpath:datasets/user/user-init-dataset.xml" })
    @ExpectedDatabase("classpath:datasets/user/update-user-dataset.xml")
    public void testShouldBeEqualAfterUpdate() throws Exception {
        User user = new User();
        user.setId(2l);
        user.setLogin("userLogin");
        user.setPassword("$2a$10$wM9PFLlfl3Jf5hUPpvRuBOHpCDVPJ5FDfAuUU2DCBiXWNa.7JWBhS");
        user.setEmail("user@gmail.com");
        user.setFirstName("Pavel");
        user.setLastName("Pavlov");
        user.setBirthday(Date.valueOf("1992-02-02"));
        user.setRole(new Role(1l, "ADMIN"));
        userDao.update(user);
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateNullShouldThrowNPE() {
        userDao.update(null);
    }

    @Test
    @DatabaseSetup({ "classpath:datasets/user/user-init-dataset.xml" })
    @ExpectedDatabase("classpath:datasets/user/remove-user-dataset.xml")
    public void testShouldBeEqualAfterRemove() throws Exception {
        User user = new User();
        user.setId(2l);
        user.setLogin("userLogin");
        user.setPassword("$2a$10$wM9PFLlfl3Jf5hUPpvRuBOHpCDVPJ5FDfAuUU2DCBiXWNa.7JWBhS");
        user.setEmail("user@ukr.net");
        user.setFirstName("Petr");
        user.setLastName("Petrov");
        user.setBirthday(Date.valueOf("2002-02-02"));
        user.setRole(new Role(2l, "USER"));
        userDao.remove(user);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveNullShouldThrowNPE() {
        userDao.remove(null);
    }

    @Test
    @DatabaseSetup({ "classpath:datasets/user/user-init-dataset.xml" })
    public void testShouldBeEqualAfterFindAll() throws Exception {
        User admin = new User(1l, "adminLogin", "$2a$10$4CPYaLutcgdl6/ayVI7Ge.tkOIJrJwJW1L.XvTlinehut4a5Ss.Fu",
                "admin@ukr.net", "Ivan", "Ivanov", Date.valueOf("2001-01-01"), new Role(1l, "ADMIN"));
        User user = new User(2l, "userLogin", "$2a$10$wM9PFLlfl3Jf5hUPpvRuBOHpCDVPJ5FDfAuUU2DCBiXWNa.7JWBhS",
                "user@ukr.net", "Petr", "Petrov", Date.valueOf("2002-02-02"), new Role(1l, "USER"));
        List<User> usersToCompare = new ArrayList<>();
        usersToCompare.add(admin);
        usersToCompare.add(user);

        List<User> usersFromDb = userDao.findAll();

        assertEquals(usersToCompare, usersFromDb);
    }

    @Test
    @DatabaseSetup({ "classpath:datasets/user/user-init-dataset.xml" })
    public void testShouldBeEqualAfterFindByLogin() throws Exception {
        User userToCompare = new User(1l, "adminLogin", "$2a$10$4CPYaLutcgdl6/ayVI7Ge.tkOIJrJwJW1L.XvTlinehut4a5Ss.Fu",
                "admin@ukr.net", "Ivan", "Ivanov", Date.valueOf("2001-01-01"), new Role(1l, "ADMIN"));

        User userFromDb = userDao.findByLogin("adminLogin");

        assertEquals(userToCompare, userFromDb);
    }

    @Test(expected = NullPointerException.class)
    public void testFindByLoginWithNullShouldThrowNPE() {
        userDao.findByLogin(null);
    }

    @Test
    @DatabaseSetup({ "classpath:datasets/user/user-init-dataset.xml" })
    public void testShouldBeEqualAfterFindByEmail() throws Exception {
        User userToCompare = new User(1l, "adminLogin", "$2a$10$4CPYaLutcgdl6/ayVI7Ge.tkOIJrJwJW1L.XvTlinehut4a5Ss.Fu",
                "admin@ukr.net", "Ivan", "Ivanov", Date.valueOf("2001-01-01"), new Role(1l, "ADMIN"));

        User userFromDb = userDao.findByEmail("admin@ukr.net");
        assertEquals(userToCompare, userFromDb);
    }

    @Test(expected = NullPointerException.class)
    public void testFindByEmailWithNullShouldThrowNPE() {
        userDao.findByEmail(null);
    }

    @Test
    @DatabaseSetup({ "classpath:datasets/user/user-init-dataset.xml" })
    public void testShouldBeEqualAfterFindById() throws Exception {
        User userToCompare = new User(1l, "adminLogin", "$2a$10$4CPYaLutcgdl6/ayVI7Ge.tkOIJrJwJW1L.XvTlinehut4a5Ss.Fu",
                "admin@ukr.net", "Ivan", "Ivanov", Date.valueOf("2001-01-01"), new Role(1l, "ADMIN"));

        User userFromDb = userDao.findById(1l);
        assertEquals(userToCompare, userFromDb);
    }

    @Test(expected = NullPointerException.class)
    public void testFindByIdWithNullShouldThrowNPE() {
        userDao.findById(null);
    }

}
