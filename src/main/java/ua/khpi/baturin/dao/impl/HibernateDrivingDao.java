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

import ua.khpi.baturin.dao.contract.DrivingDao;
import ua.khpi.baturin.entity.Driving;
import ua.khpi.baturin.entity.Route;

@Repository("drivingDao")
public class HibernateDrivingDao implements DrivingDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public void create(Driving driving) {
        if (driving == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().save(driving);
    }

    @Transactional
    @Override
    public void update(Driving driving) {
        if (driving == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().update(driving);
    }

    @Transactional
    @Override
    public void remove(Driving driving) {
        if (driving == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().delete(driving);
    }

    @Transactional
    @Override
    public List<Driving> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Driving> query = builder.createQuery(Driving.class);
        Root<Driving> root = query.from(Driving.class);
        query.select(root);
        Query<Driving> q = session.createQuery(query);
        List<Driving> drivings = q.getResultList();
        return drivings;
    }

    @Transactional
    @Override
    public List<Driving> findByRoute(Route route) {
        if (route == null) {
            throw new NullPointerException();
        }
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Driving> query = builder.createQuery(Driving.class);
        Root<Driving> root = query.from(Driving.class);
        query.select(root).where(builder.equal(root.get("route"), route));
        Query<Driving> q = session.createQuery(query);
        List<Driving> drivings = q.getResultList();
        return drivings;
    }

    @Transactional
    @Override
    public Driving findById(Long id) {
        if (id == null) {
            throw new NullPointerException();
        }
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Driving> query = builder.createQuery(Driving.class);
        Root<Driving> root = query.from(Driving.class);
        query.select(root).where(builder.equal(root.get("id"), id));
        Query<Driving> q = session.createQuery(query);
        Driving driving = q.getSingleResult();
        return driving;
    }
}
