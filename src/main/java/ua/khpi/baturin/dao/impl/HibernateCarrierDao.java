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

import ua.khpi.baturin.dao.contract.CarrierDao;
import ua.khpi.baturin.entity.Carrier;

@Repository("carrierDao")
public class HibernateCarrierDao implements CarrierDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public void create(Carrier carrier) {
        if (carrier == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().save(carrier);
    }

    @Transactional
    @Override
    public void update(Carrier carrier) {
        if (carrier == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().update(carrier);
    }

    @Transactional
    @Override
    public void remove(Carrier carrier) {
        if (carrier == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().delete(carrier);
    }

    @Transactional
    @Override
    public List<Carrier> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Carrier> query = builder.createQuery(Carrier.class);
        Root<Carrier> root = query.from(Carrier.class);
        query.select(root);
        Query<Carrier> q = session.createQuery(query);
        List<Carrier> users = q.getResultList();
        return users;
    }

    @Transactional
    @Override
    public Carrier findByTitle(String title) {
        if (title == null) {
            throw new NullPointerException();
        }
        Session session = getSession();
        Carrier carrier = null;
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Carrier> query = builder.createQuery(Carrier.class);
        Root<Carrier> root = query.from(Carrier.class);
        query.select(root).where(builder.equal(root.get("title"), title));
        Query<Carrier> q = session.createQuery(query);
        List<Carrier> carriers = q.getResultList();
        if (carriers.size() != 0) {
            carrier = carriers.get(0);
        }
        return carrier;
    }

    @Transactional
    @Override
    public Carrier findById(Long id) {
        if (id == null) {
            throw new NullPointerException();
        }
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Carrier> query = builder.createQuery(Carrier.class);
        Root<Carrier> root = query.from(Carrier.class);
        query.select(root).where(builder.equal(root.get("id"), id));
        Query<Carrier> q = session.createQuery(query);
        Carrier carrier = q.getSingleResult();
        return carrier;
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

}
