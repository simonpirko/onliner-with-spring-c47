package by.fakeonliner.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DescriptionFeatureValueDao {

    public List getByDescriptionFeatureId(long descriptionFeatureId);
}
