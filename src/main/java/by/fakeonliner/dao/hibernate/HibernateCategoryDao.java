package by.fakeonliner.dao.hibernate;

import by.fakeonliner.dao.CategoryDao;
import by.fakeonliner.entity.product.Category;
import by.fakeonliner.entity.product.DescriptionFeature;
import by.fakeonliner.entity.product.DescriptionFeatureValue;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class HibernateCategoryDao implements CategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Category category) {
        try (Session session = sessionFactory.openSession()) {
            session.save(category);
        } catch (NoResultException e) {
            return;
        }
    }

    @Override
    public void saveDescriptionFeature(DescriptionFeature descriptionFeature) {
        try (Session session = sessionFactory.openSession()) {
            session.save(descriptionFeature);
        } catch (NoResultException e) {
            return;
        }
    }

    @Override
    public void saveDescriptionFeatureValue(DescriptionFeatureValue descriptionFeatureValue) {
        try (Session session = sessionFactory.openSession()) {
            session.save(descriptionFeatureValue);
        } catch (NoResultException e) {
            return;
        }
    }

    @Override
    public List<Category> getCategory() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select c from Category c", Category.class).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<DescriptionFeature> getDescriptionFeature(long categoryId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select c from DescriptionFeature c where c.categoryId =:categoryId"
                            , DescriptionFeature.class).setParameter("categoryId", categoryId)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<DescriptionFeatureValue> getDescriptionFeatureValue() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select c from DescriptionFeature c where c.categoryId =:categoryId"
                            , DescriptionFeatureValue.class)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
