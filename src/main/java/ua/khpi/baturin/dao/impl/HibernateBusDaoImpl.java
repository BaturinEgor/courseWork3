package ua.khpi.baturin.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.khpi.baturin.dao.contract.BusDao;
import ua.khpi.baturin.entity.Bus;
import ua.khpi.baturin.entity.Carrier;

@Repository("busDao")
public class HibernateBusDaoImpl implements BusDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public void create(Bus bus) {
        if (bus == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().save(bus);
    }

    @Transactional
    @Override
    public void update(Bus bus) {
        if (bus == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().update(bus);
    }

    @Transactional
    @Override
    public void remove(Bus bus) {
        if (bus == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().delete(bus);
    }

    @Transactional
    @Override
    public List<Bus> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Bus> query = builder.createQuery(Bus.class);
        Root<Bus> root = query.from(Bus.class);
        query.select(root);
        Query<Bus> q = session.createQuery(query);
        List<Bus> busses = q.getResultList();
        return busses;
    }

    @Transactional
    @Override
    public Bus findByNumber(String number) {
        if (number == null) {
            throw new NullPointerException();
        }
        Session session = sessionFactory.getCurrentSession();
        Bus bus = null;
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Bus> query = builder.createQuery(Bus.class);
        Root<Bus> root = query.from(Bus.class);
        query.select(root).where(builder.equal(root.get("busNumber"), number));
        Query<Bus> q = session.createQuery(query);
        List<Bus> carriers = q.getResultList();
        if (carriers.size() != 0) {
            bus = carriers.get(0);
        }
        return bus;
    }

    @Transactional
    @Override
    public List<Bus> findByCarrier(Carrier carrier) {
        if (carrier == null) {
            throw new NullPointerException();
        }
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Bus> query = builder.createQuery(Bus.class);
        Root<Bus> root = query.from(Bus.class);
        query.select(root).where(builder.equal(root.get("carrier"), carrier));
        Query<Bus> q = session.createQuery(query);
        List<Bus> busses = q.getResultList();
        return busses;
    }

}
