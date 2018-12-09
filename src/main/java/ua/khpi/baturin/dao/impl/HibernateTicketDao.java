package ua.khpi.baturin.dao.impl;

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
import ua.khpi.baturin.entity.Client;
import ua.khpi.baturin.entity.Ticket;

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
    public Ticket findByClient(Client client) {
        if (client == null) {
            throw new NullPointerException();
        }
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Ticket> query = builder.createQuery(Ticket.class);
        Root<Ticket> root = query.from(Ticket.class);
        query.select(root).where(builder.equal(root.get("client"), client));
        Query<Ticket> q = session.createQuery(query);
        Ticket driving = q.getSingleResult();
        return driving;
    }

}
