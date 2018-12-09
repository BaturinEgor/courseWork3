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

import ua.khpi.baturin.dao.contract.StatusDao;
import ua.khpi.baturin.entity.Status;

@Repository("statusDao")
public class HibernateStatusDao implements StatusDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public void create(Status status) {
        if (status == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().save(status);
    }

    @Transactional
    @Override
    public void update(Status status) {
        if (status == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().update(status);
    }

    @Transactional
    @Override
    public void remove(Status status) {
        if (status == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().delete(status);
    }

    @Transactional
    @Override
    public List<Status> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Status> query = builder.createQuery(Status.class);
        Root<Status> root = query.from(Status.class);
        query.select(root);
        Query<Status> q = session.createQuery(query);
        List<Status> statuses = q.getResultList();
        return statuses;
    }

    @Transactional
    @Override
    public Status findByTitle(String title) {
        if (title == null) {
            throw new NullPointerException();
        }
        Session session = sessionFactory.getCurrentSession();
        Status status = null;
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Status> query = builder.createQuery(Status.class);
        Root<Status> root = query.from(Status.class);
        query.select(root).where(builder.equal(root.get("title"), title));
        Query<Status> q = session.createQuery(query);
        List<Status> statuses = q.getResultList();
        if (statuses.size() != 0) {
            status = statuses.get(0);
        }
        return status;
    }

}
