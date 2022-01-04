package by.fakeonliner.dao;

import by.fakeonliner.entity.product.Category;
import by.fakeonliner.entity.product.DescriptionFeature;
import by.fakeonliner.entity.product.DescriptionFeatureValue;
<<<<<<< ONL-24

import java.util.List;

@Repository
public interface CategoryDao {

=======
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao {
>>>>>>> main
    void save(Category category);

    void saveDescriptionFeature(DescriptionFeature descriptionFeature);

    void saveDescriptionFeatureValue(DescriptionFeatureValue descriptionFeatureValue);

    List<Category> getCategory();

    List<DescriptionFeature> getDescriptionFeature(long categoryId);

    List<DescriptionFeatureValue> getDescriptionFeatureValue();
}
