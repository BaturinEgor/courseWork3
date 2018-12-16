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

import ua.khpi.baturin.dao.contract.TicketDao;
import ua.khpi.baturin.entity.Route;
import ua.khpi.baturin.entity.Station;
import ua.khpi.baturin.entity.Ticket;
import ua.khpi.baturin.entity.User;

@Repository("ticketDao")
public class HibernateTicketDao implements TicketDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public void create(Ticket ticket) {
        if (ticket == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().save(ticket);
    }

    @Transactional
    @Override
    public void update(Ticket ticket) {
        if (ticket == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().update(ticket);
    }

    @Transactional
    @Override
    public void remove(Ticket ticket) {
        if (ticket == null) {
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().delete(ticket);
    }

    @Transactional
    @Override
    public List<Ticket> findByClient(User user) {
        if (user == null) {
            throw new NullPointerException();
        }
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Ticket> query = builder.createQuery(Ticket.class);
        Root<Ticket> root = query.from(Ticket.class);
        query.select(root).where(builder.equal(root.get("user"), user));
        Query<Ticket> q = session.createQuery(query);
        List<Ticket> driving = q.getResultList();
        return driving;
    }

    @Transactional
    @Override
    public List<Ticket> findByRoute(Route route) {
        if (route == null) {
            throw new NullPointerException();
        }
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Ticket> query = builder.createQuery(Ticket.class);
        Root<Ticket> root = query.from(Ticket.class);
        query.select(root).where(builder.equal(root.get("route"), route));
        Query<Ticket> q = session.createQuery(query);
        List<Ticket> driving = q.getResultList();
        return driving;
    }

    @Transactional
    @Override
    public Ticket findById(Long id) {
        if (id == null) {
            throw new NullPointerException();
        }
        Ticket role = null;
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Ticket> query = builder.createQuery(Ticket.class);
        Root<Ticket> root = query.from(Ticket.class);
        query.select(root).where(builder.equal(root.get("id"), id));
        Query<Ticket> q = session.createQuery(query);
        List<Ticket> roles = q.getResultList();
        if (roles.size() != 0) {
            role = roles.get(0);
        }
        return role;
    }
}
