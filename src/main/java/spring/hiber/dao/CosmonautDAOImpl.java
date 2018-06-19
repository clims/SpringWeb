package spring.hiber.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.hiber.model.Cosmonaut;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class CosmonautDAOImpl implements CosmonautDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public CosmonautDAOImpl(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int addCosmonaut(Cosmonaut cosmonaut) {
        return  (int) sessionFactory.getCurrentSession().save(cosmonaut);
    }

    @Override
    public Cosmonaut getCosmonautById(int id) {
        return sessionFactory.getCurrentSession().get(Cosmonaut.class, id);
    }

    @Override
    public List<Cosmonaut> getAllCosmonauts() {
        return sessionFactory.getCurrentSession().createQuery("from Cosmonaut").list();
    }

    @Override
    public List<Cosmonaut> getCosmonautsByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = builder.createQuery(Cosmonaut.class);
        Root<Cosmonaut> root = criteriaQuery.from(Cosmonaut.class);
        criteriaQuery.select(root).where(builder.equal(root.get("name"), name));
        Query<Cosmonaut> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void updateCosmonaut(Cosmonaut cosmonaut) {
        sessionFactory.getCurrentSession().update(cosmonaut);
    }

    @Override
    public long getNumberOfCosmonauts() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = builder.createQuery(Cosmonaut.class);
        Root<Cosmonaut> root = criteriaQuery.from(Cosmonaut.class);
        criteriaQuery.select(builder.count(root));
        Query<Long> query = session.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public Cosmonaut getOldestCosmonaut() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = builder.createQuery(Cosmonaut.class);
        Root<Cosmonaut> root = criteriaQuery.from(Cosmonaut.class);
        criteriaQuery.select(builder.max(root.get("age")));
        int age = (int) session.createQuery(criteriaQuery).getSingleResult();
        criteriaQuery.select(root).where(builder.equal(root.get("age"), age));
        Query<Cosmonaut> query = session.createQuery(criteriaQuery);
        return query.getResultList().get(0);
    }

    @Override
    public void removeCosmonaut(Cosmonaut cosmonaut) {
        sessionFactory.getCurrentSession().delete(cosmonaut);
    }
}
