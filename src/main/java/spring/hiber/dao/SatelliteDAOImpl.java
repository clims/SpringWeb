package spring.hiber.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.hiber.model.Satellite;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class SatelliteDAOImpl implements SatelliteDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public SatelliteDAOImpl(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int addSatellite(Satellite satellite) {
        return ((int) sessionFactory.getCurrentSession().save(satellite));
    }

    @Override
    public List<Satellite> getAllSatellites() {
        return sessionFactory.getCurrentSession().createQuery("from Satellite").getResultList();
    }

    @Override
    public Satellite getSatelliteById(int id) {
        return sessionFactory.getCurrentSession().get(Satellite.class, id);
    }

    @Override
    public List<Satellite> getSatellitesByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Satellite.class);
        Root<Satellite> root = criteriaQuery.from(Satellite.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("name"), name));
        Query<Satellite> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void removeSatellite(Satellite satellite) {
        sessionFactory.getCurrentSession().delete(satellite);
    }

    @Override
    public void updateSatellite(Satellite satellite) {
        sessionFactory.getCurrentSession().update(satellite);
    }

    @Override
    public long getNumberOfSatellites() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = builder.createQuery(Satellite.class);
        Root<Satellite> root = criteriaQuery.from(Satellite.class);
        criteriaQuery.select(builder.count(root));
        Query<Long> query = session.createQuery(criteriaQuery);
        return query.getSingleResult();
    }
}
