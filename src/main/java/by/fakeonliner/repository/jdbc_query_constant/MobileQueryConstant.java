package by.fakeonliner.repository.jdbc_query_constant;

public class MobileQueryConstant {
    public static final String ADD_MOBILE_QUERY = "INSERT INTO mobile (id, type, processor, OS," +
            " display_size, frequency, ram, display_type) VALUES (?,?,?,?,?,?,?,?)";
    public static final String GET_MOBILE_BY_ID_QUERY = "SELECT * FROM mobile WHERE mobile.id =?";
}
