package by.fakeonliner.repository.query_constant;

public class ProductQueryConstant {
    public final static String FIND_BY_MODEL = "SELECT * FROM product_list WHERE model LIKE ?";
    public final static String FIND_BY_BRAND = "SELECT * FROM product_list WHERE brand=? AND category=?";
    public final static String FIND_BY_PRICE = "SELECT * FROM product_list WHERE price>? AND price<? AND category=?";
    public static final String DELETE_PRODUCT_BY_ID = "DELETE FROM product_list WHERE id=?";
    public static final String ALL_FROM_CATEGORY = "SELECT * FROM product_list WHERE category=?";
    public static final String GET_ALL_PRODUCTS_HOME_QUERY = "SELECT * FROM product_list";
    public final static String FIND_BY_ID = "SELECT * FROM product_list WHERE id=?";
}
