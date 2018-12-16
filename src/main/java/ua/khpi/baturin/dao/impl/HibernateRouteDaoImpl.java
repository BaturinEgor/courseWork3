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

import ua.khpi.baturin.dao.contract.RouteDao;
import ua.khpi.baturin.entity.Route;

@Repository("routeDao")
public class HibernateRouteDaoImpl implements RouteDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public void create(Route route) {
        if (route == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().save(route);
    }

    @Transactional
    @Override
    public void update(Route route) {
        if (route == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().update(route);
    }

    @Transactional
    @Override
    public void remove(Route route) {
        if (route == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().delete(route);
    }

    @Transactional
    @Override
    public List<Route> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Route> query = builder.createQuery(Route.class);
        Root<Route> root = query.from(Route.class);
        query.select(root);
        Query<Route> q = session.createQuery(query);
        List<Route> routs = q.getResultList();
        return routs;
    }

    @Transactional
    @Override
    public Route findByNumber(String number) {
        if (number == null) {
            throw new NullPointerException();
        }
        Session session = getSession();
        Route route = null;
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Route> query = builder.createQuery(Route.class);
        Root<Route> root = query.from(Route.class);
        query.select(root).where(builder.equal(root.get("routeNumber"), number));
        Query<Route> q = session.createQuery(query);
        List<Route> routs = q.getResultList();
        if (routs.size() != 0) {
            route = routs.get(0);
        }
        return route;
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
    public Route findById(Long id) {
        if (id == null) {
            throw new NullPointerException();
        }
        Route role = null;
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Route> query = builder.createQuery(Route.class);
        Root<Route> root = query.from(Route.class);
        query.select(root).where(builder.equal(root.get("id"), id));
        Query<Route> q = session.createQuery(query);
        List<Route> roles = q.getResultList();
        if (roles.size() != 0) {
            role = roles.get(0);
        }
        return role;
    }

}
