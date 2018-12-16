package ua.khpi.baturin.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.khpi.baturin.dao.contract.StationDao;
import ua.khpi.baturin.entity.Station;

@Repository("stationDao")
public class HibernateStationDaoImpl implements StationDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public void create(Station station) {
        if (station == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().save(station);
    }

    @Transactional
    @Override
    public void update(Station station) {
        if (station == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().update(station);
    }

    @Transactional
    @Override
    public void remove(Station station) {
        if (station == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().delete(station);
    }

    @Transactional
    @Override
    public List<Station> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Station> query = builder.createQuery(Station.class);
        Root<Station> root = query.from(Station.class);
        query.select(root);
        Query<Station> q = session.createQuery(query);
        List<Station> stattions = q.getResultList();
        return stattions;
    }

    @Transactional
    @Override
    public Station findByTitle(String title) {
        if (title == null) {
            throw new NullPointerException();
        }
        Session session = sessionFactory.getCurrentSession();
        Station station = null;
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Station> query = builder.createQuery(Station.class);
        Root<Station> root = query.from(Station.class);
        query.select(root).where(builder.equal(root.get("title"), title));
        Query<Station> q = session.createQuery(query);
        List<Station> stations = q.getResultList();
        if (stations.size() != 0) {
            station = stations.get(0);
        }
        return station;
    }

    public Session getSession() {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    @Transactional(readOnly = true)
    @Override
    public Station findById(Long id) {
        if (id == null) {
            throw new NullPointerException();
        }
        Station role = null;
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Station> query = builder.createQuery(Station.class);
        Root<Station> root = query.from(Station.class);
        query.select(root).where(builder.equal(root.get("id"), id));
        Query<Station> q = session.createQuery(query);
        List<Station> roles = q.getResultList();
        if (roles.size() != 0) {
            role = roles.get(0);
        }
        return role;
    }

}
