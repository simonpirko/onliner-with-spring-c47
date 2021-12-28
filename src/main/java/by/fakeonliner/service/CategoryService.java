package by.fakeonliner.service;

import by.fakeonliner.dao.hibernate.HibernateCategoryDao;
import by.fakeonliner.entity.product.Category;
import by.fakeonliner.entity.product.DescriptionFeature;
import by.fakeonliner.entity.product.DescriptionFeatureValue;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private HibernateCategoryDao hibernateCategoryDao;

    public void saveCategory(Category category) {
        hibernateCategoryDao.save(category);
    }


    public void saveDescriptionFeature(DescriptionFeature descriptionFeature) {
        hibernateCategoryDao.saveDescriptionFeature(descriptionFeature);
    }


    public void saveDescriptionFeatureValue(DescriptionFeatureValue descriptionFeatureValue) {
        hibernateCategoryDao.saveDescriptionFeatureValue(descriptionFeatureValue);
    }


    public List<Category> getCategory() {
        return hibernateCategoryDao.getCategory();
    }


    public List<DescriptionFeature> getDescriptionFeature(long categoryId) {
        return hibernateCategoryDao.getDescriptionFeature(categoryId);
    }


    public List<DescriptionFeatureValue> getDescriptionFeatureValue() {
        return hibernateCategoryDao.getDescriptionFeatureValue();
    }
}
