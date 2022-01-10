package by.fakeonliner.dao.hibernate;

import by.fakeonliner.entity.product.DescriptionFeature;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class DescriptionFeatureValueDao implements by.fakeonliner.dao.DescriptionFeatureValueDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List getByDescriptionFeatureId(long descriptionFeatureId) {
        try (Session session = sessionFactory.openSession()) {
            List resultList = session.createQuery("FROM DescriptionFeatureValue where descriptionFeature.id=:desc")
                    .setParameter("desc", descriptionFeatureId)
                    .getResultList();
            return resultList;
        } catch (NoResultException e) {

        }
        return null;
    }

}
