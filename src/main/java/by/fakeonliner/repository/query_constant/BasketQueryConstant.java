package by.fakeonliner.repository.query_constant;

public class BasketQueryConstant {
    public static final String ADD_PRODUCT_QUERY = "INSERT INTO basket (user_id, product_id, amount) VALUES (?, ?, ?)";
    public static final String GET_PRODUCTS_QUERY = "SELECT * FROM basket LEFT JOIN" +
            " product_list ON basket.product_id = product_list.id WHERE basket.user_id =?";
    public static final String DELETE_PRODUCT_QUERY = "DELETE FROM basket WHERE basket.user_id =? AND basket.product_id =?";
    public static final String GET_PRODUCT_AMOUNT_QUERY = "SELECT amount FROM basket WHERE" +
            " basket.user_id =? AND basket.product_id =?";
    public static final String CHANGE_PRODUCT_AMOUNT_QUERY = "UPDATE basket SET basket.amount =? " +
            "WHERE basket.user_id =? AND basket.product_id =?";

}
