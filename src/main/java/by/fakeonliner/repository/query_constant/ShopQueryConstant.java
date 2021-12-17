package by.fakeonliner.repository.query_constant;

public class ShopQueryConstant {
    public static final String ADD_SHOP_QUERY = "INSERT INTO shop_list (shop_name, password," +
            " phone_number, email) VALUES(?, ?, ?, ?)";
    public static final String CHANGE_SHOP_BY_ID_QUERY = "UPDATE shop_list SET shop_name=?, email=?, password=?," +
            " phone_number=?, contact_address=?, description=?  WHERE id=?";
    public static final String EXIST_BY_EMAIL_QUERY = "SELECT email FROM shop_list WHERE email=?";
    public static final String GET_SHOP_BY_EMAIL_QUERY = "SELECT * FROM shop_list WHERE email=?";
    public static final String DELETE_SHOP_QUERY = "DELETE FROM shop_list WHERE id=?";
    public static final String GET_SHOP_LIST_QUERY = "SELECT * FROM shop_list";
}
