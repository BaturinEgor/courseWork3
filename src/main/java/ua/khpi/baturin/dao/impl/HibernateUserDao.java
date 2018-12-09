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

import ua.khpi.baturin.dao.contract.UserDao;
import ua.khpi.baturin.entity.User;

@Repository("userDao")
public class HibernateUserDao implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public void create(User user) {
        if (user == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().save(user);
    }

    @Transactional
    @Override
    public void update(User user) {
        if (user == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().update(user);
    }

    @Transactional
    @Override
    public void remove(User user) {
        if (user == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().delete(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);
        Query<User> q = session.createQuery(query);
        List<User> users = q.getResultList();
        return users;
    }

    /**
     * Find first user that match by login. Use criteria getResultList() and return
     * first element of the list. Important not to use getSingleResult() because it
     * will fail if database have several users with the same logins.
     */
    @Transactional
    @Override
    public User findByLogin(String login) {
        if (login == null) {
            throw new NullPointerException();
        }
        Session session = getSession();
        User user = null;
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(builder.equal(root.get("login"), login));
        Query<User> q = session.createQuery(query);
        List<User> users = q.getResultList();
        if (users.size() != 0) {
            user = users.get(0);
        }
        return user;
    }

    /**
     * Find first user that match by email. Use criteria getResultList() and return
     * first element of the list. Important not to use getSingleResult() because it
     * will fail if database have several users with the same email.
     */
    @Transactional(readOnly = true)
    @Override
    public User findByEmail(String email) {
        if (email == null) {
            throw new NullPointerException();
        }
        User user = null;
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(builder.equal(root.get("email"), email));
        Query<User> q = session.createQuery(query);
        List<User> users = q.getResultList();
        if (users.size() != 0) {
            user = users.get(0);
        }
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(Long id) {
        if (id == null) {
            throw new NullPointerException();
        }
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(builder.equal(root.get("id"), id));
        Query<User> q = session.createQuery(query);
        User user = q.getSingleResult();
        return user;
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