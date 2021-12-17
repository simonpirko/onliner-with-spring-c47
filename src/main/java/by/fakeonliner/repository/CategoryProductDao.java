package by.fakeonliner.repository;

public interface CategoryProductDao {

    boolean save(Object object);
    Object getProductByIdAndType(long id);
}
