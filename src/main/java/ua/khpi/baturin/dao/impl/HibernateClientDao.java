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

import ua.khpi.baturin.dao.contract.ClientDao;
import ua.khpi.baturin.entity.Client;

@Repository("clientDao")
public class HibernateClientDao implements ClientDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public void create(Client client) {
        if (client == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().save(client);
    }

    @Transactional
    @Override
    public void update(Client client) {
        if (client == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().update(client);
    }

    @Transactional
    @Override
    public void remove(Client client) {
        if (client == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().delete(client);
    }

    @Transactional
    @Override
    public List<Client> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Client> query = builder.createQuery(Client.class);
        Root<Client> root = query.from(Client.class);
        query.select(root);
        Query<Client> q = session.createQuery(query);
        List<Client> drivings = q.getResultList();
        return drivings;
    }

    @Transactional
    @Override
    public Client findById(Long id) {
        if (id == null) {
            throw new NullPointerException();
        }
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Client> query = builder.createQuery(Client.class);
        Root<Client> root = query.from(Client.class);
        query.select(root).where(builder.equal(root.get("id"), id));
        Query<Client> q = session.createQuery(query);
        Client driving = q.getSingleResult();
        return driving;
    }

    @Transactional
    @Override
    public Client findByLogin(String login) {
        if (login == null) {
            throw new NullPointerException();
        }
        Client client = null;
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Client> query = builder.createQuery(Client.class);
        Root<Client> root = query.from(Client.class);
        query.select(root).where(builder.equal(root.get("login"), login));
        Query<Client> q = session.createQuery(query);
        List<Client> drivings = q.getResultList();
        if (drivings.size() != 0) {
            client = drivings.get(0);
        }
        return client;
    }

}
