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

import ua.khpi.baturin.dao.contract.RoleDao;
import ua.khpi.baturin.entity.Role;

@Repository("roleDao")
public class HibernateRoleDao implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public void create(Role role) {
        if (role == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().save(role);
    }

    @Transactional
    @Override
    public void update(Role role) {
        if (role == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().update(role);
    }

    @Transactional
    @Override
    public void remove(Role role) {
        if (role == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().delete(role);
    }

    /**
     * Find first role that match by name. Use criteria getResultList() and
     * return
     * first element of the list. Important not to use getSingleResult() because
     * it
     * will fail if database have several roles with the same names.
     */
    @Transactional(readOnly = true)
    @Override
    public Role findByName(String name) {
        if (name == null) {
            throw new NullPointerException();
        }
        Role role = null;
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Role> query = builder.createQuery(Role.class);
        Root<Role> root = query.from(Role.class);
        query.select(root).where(builder.equal(root.get("name"), name));
        Query<Role> q = session.createQuery(query);
        List<Role> roles = q.getResultList();
        if (roles.size() != 0) {
            role = roles.get(0);
        }
        return role;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Role> query = builder.createQuery(Role.class);
        Root<Role> root = query.from(Role.class);
        query.select(root);
        Query<Role> q = session.createQuery(query);
        List<Role> roles = q.getResultList();
        return roles;
    }

    @Transactional(readOnly = true)
    @Override
    public Role findById(Long id) {
        if (id == null) {
            throw new NullPointerException();
        }
        Role role = null;
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Role> query = builder.createQuery(Role.class);
        Root<Role> root = query.from(Role.class);
        query.select(root).where(builder.equal(root.get("id"), id));
        Query<Role> q = session.createQuery(query);
        List<Role> roles = q.getResultList();
        if (roles.size() != 0) {
            role = roles.get(0);
        }
        return role;
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