package by.fakeonliner.repository.jdbc_query_constant;

public class LaptopQueryConstant {
    public static final String ADD_LAPTOP_QUERY = "INSERT INTO laptop (id, type, video_card, processor," +
            " OS, display_size, frequency, ram, rom) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String GET_LAPTOP_BY_ID_QUERY = "SELECT * FROM laptop WHERE laptop.id =?";
}
