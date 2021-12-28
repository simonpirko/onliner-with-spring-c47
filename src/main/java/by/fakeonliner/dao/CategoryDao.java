package by.fakeonliner.dao;

import by.fakeonliner.entity.product.Category;
import by.fakeonliner.entity.product.DescriptionFeature;
import by.fakeonliner.entity.product.DescriptionFeatureValue;

import java.util.List;

public interface CategoryDao {

    void save(Category category);

    void saveDescriptionFeature(DescriptionFeature descriptionFeature);

    void saveDescriptionFeatureValue(DescriptionFeatureValue descriptionFeatureValue);

    List<Category> getCategory();

    List<DescriptionFeature> getDescriptionFeature(long categoryId);

    List<DescriptionFeatureValue> getDescriptionFeatureValue();
}
