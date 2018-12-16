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

import ua.khpi.baturin.dao.contract.AdminDao;
import ua.khpi.baturin.entity.Admin;

@Repository("adminDao")
public class HibernateAdminDao implements AdminDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public void create(Admin admin) {
        if (admin == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().save(admin);
    }

    @Transactional
    @Override
    public void update(Admin admin) {
        if (admin == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().update(admin);
    }

    @Transactional
    @Override
    public void remove(Admin admin) {
        if (admin == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().delete(admin);
    }

    @Transactional
    @Override
    public List<Admin> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Admin> query = builder.createQuery(Admin.class);
        Root<Admin> root = query.from(Admin.class);
        query.select(root);
        Query<Admin> q = session.createQuery(query);
        List<Admin> admins = q.getResultList();
        return admins;
    }

    @Transactional
    @Override
    public Admin findById(Long id) {
        if (id == null) {
            throw new NullPointerException();
        }
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Admin> query = builder.createQuery(Admin.class);
        Root<Admin> root = query.from(Admin.class);
        query.select(root).where(builder.equal(root.get("id"), id));
        Query<Admin> q = session.createQuery(query);
        Admin driving = q.getSingleResult();
        return driving;
    }

    @Transactional
    @Override
    public Admin findByLogin(String login) {
        if (login == null) {
            throw new NullPointerException();
        }
        Admin client = null;
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Admin> query = builder.createQuery(Admin.class);
        Root<Admin> root = query.from(Admin.class);
        query.select(root).where(builder.equal(root.get("login"), login));
        Query<Admin> q = session.createQuery(query);
        List<Admin> drivings = q.getResultList();
        if (drivings.size() != 0) {
            client = drivings.get(0);
        }
        return client;
    }
}
