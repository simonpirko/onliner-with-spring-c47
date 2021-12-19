package by.fakeonliner.repository.hibernate_query_constant;

public class HBasketQueryConstant {

    public static final String GET_BASKET_PRODUCTS_QUERY = "from BasketProductDto where user.id = :userId";
    public static final String GET_PRODUCT_AMOUNT_QUERY = "from BasketProductDto where " +
            "user.id = :userId and product.id = :productId";
    public static final String EXIST_BY_PRODUCT_DTO_QUERY = "select 1 from BasketProductDto " +
            "where user.id = :userId and product.id = :productId";
}
