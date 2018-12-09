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
import ua.khpi.baturin.dao.contract.CarrierDao;
import ua.khpi.baturin.dao.impl.HibernateCarrierDao;
import ua.khpi.baturin.entity.Carrier;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { HibernateConfiguration.class,
        HibernateCarrierDao.class }, loader = AnnotationConfigWebContextLoader.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class CarrierDaoTest {

    @Autowired
    IDatabaseTester databaseTester;
    @Autowired
    CarrierDao carrierDao;

    @Test
    @DatabaseSetup({ "classpath:datasets/carrier/carrier-init-dataset.xml" })
    @ExpectedDatabase("classpath:datasets/carrier/create-carrier-dataset.xml")
    public void testShouldBeEqualAfterInsert() throws Exception {
        Carrier carrier = new Carrier(3l, "someTitle3");
        carrierDao.create(carrier);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateNullShouldThrowNPE() {
        carrierDao.create(null);
    }

    @Test
    @DatabaseSetup({ "classpath:datasets/carrier/carrier-init-dataset.xml" })
    @ExpectedDatabase("classpath:datasets/carrier/update-carrier-dataset.xml")
    public void testShouldBeEqualAfterUpdate() throws Exception {
        Carrier carrier = new Carrier(2l, "someTitle4");
        carrierDao.update(carrier);
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateNullShouldThrowNPE() {
        carrierDao.update(null);
    }

    @Test
    @DatabaseSetup({ "classpath:datasets/carrier/carrier-init-dataset.xml" })
    @ExpectedDatabase("classpath:datasets/carrier/remove-carrier-dataset.xml")
    public void testShouldBeEqualAfterRemove() throws Exception {
        Carrier carrier = new Carrier(2l, "someTitle2");
        carrierDao.remove(carrier);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveNullShouldThrowNPE() {
        carrierDao.remove(null);
    }

    @Test
    @DatabaseSetup({ "classpath:datasets/carrier/carrier-init-dataset.xml" })
    public void testShouldBeEqualAfterFindAll() throws Exception {
        Carrier carrier1 = new Carrier(1l, "someTitle1");
        Carrier carrier2 = new Carrier(2l, "someTitle2");
        List<Carrier> carriersToCompare = new ArrayList<>();
        carriersToCompare.add(carrier1);
        carriersToCompare.add(carrier2);

        List<Carrier> carriersFromDb = carrierDao.findAll();

        assertEquals(carriersToCompare, carriersFromDb);
    }

    @Test
    @DatabaseSetup({ "classpath:datasets/carrier/carrier-init-dataset.xml" })
    public void testShouldBeEqualAfterFindByLogin() throws Exception {
        Carrier carrierToCompare = new Carrier(1l, "someTitle1");
        Carrier carrierFromDb = carrierDao.findByTitle("someTitle1");

        assertEquals(carrierToCompare, carrierFromDb);
    }

    @Test(expected = NullPointerException.class)
    public void testFindByLoginWithNullShouldThrowNPE() {
        carrierDao.findByTitle(null);
    }
}
