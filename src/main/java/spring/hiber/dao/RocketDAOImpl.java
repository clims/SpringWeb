package spring.hiber.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.hiber.model.Rocket;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class RocketDAOImpl implements RocketDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public RocketDAOImpl(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public int addRocket(Rocket rocket) {
        return ((int) sessionFactory.getCurrentSession().save(rocket));
    }

    public List<Rocket> getAllRockets() {
        return sessionFactory.getCurrentSession().createQuery("from Rocket").list();
    }

    public Rocket getRocketById(int id) {
        return sessionFactory.getCurrentSession().get(Rocket.class, id);
    }

    public List<Rocket> getRocketByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = builder.createQuery(Rocket.class);
        Root<Rocket> root = criteriaQuery.from(Rocket.class);
        criteriaQuery.select(root).where(builder.equal(root.get("name"), name));
        Query<Rocket> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List<Rocket> getPriceGreather(Double price) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = builder.createQuery(Rocket.class);
        Root<Rocket> root = criteriaQuery.from(Rocket.class);
        criteriaQuery.select(root).where(builder.greaterThan(root.get("price"), price));
        Query<Rocket> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public void updateRocket(Rocket rocket) {
        sessionFactory.getCurrentSession().update(rocket);
    }

    public void deleteRocket(Rocket rocket) {
        sessionFactory.getCurrentSession().delete(rocket);
    }

    @Override
    public long getNumberOfRockets() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = builder.createQuery(Rocket.class);
        Root<Rocket> root = criteriaQuery.from(Rocket.class);
        criteriaQuery.select(builder.count(root));
        Query<Long> query = session.createQuery(criteriaQuery);
        return query.getSingleResult();
    }
}
