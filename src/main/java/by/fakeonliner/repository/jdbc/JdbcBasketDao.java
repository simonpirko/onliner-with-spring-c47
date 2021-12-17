package by.fakeonliner.repository.jdbc;

import by.fakeonliner.dto.BasketProductDto;
import by.fakeonliner.repository.BasketDao;
import by.fakeonliner.repository.configuration.JdbcConnection;
import by.fakeonliner.repository.query_constant.BasketQueryConstant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class JdbcBasketDao implements BasketDao {

    private final static String ID = "id";
    private final static String BRAND = "brand";
    private final static String PRICE = "price";
    private final static String MODEL = "model";
    private final static String MARKET_LAUNCH_DATE = "market_launch_date";
    private final static String RATING = "rating";
    private final static String IMAGE_LINK = "product_link_image";
    private final static String DESCRIPTION = "description";
    private final static String AMOUNT = "amount";


    @Override
    public boolean addProductDb(long id, long productId, int amount) {
        try (Connection con = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(BasketQueryConstant.ADD_PRODUCT_QUERY)) {
            preparedStatement.setInt(1, (int) id);
            preparedStatement.setInt(2, (int) productId);
            preparedStatement.setInt(3, amount);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public int getProductAmount(long productId, long userId) {
        try (Connection con = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(BasketQueryConstant.GET_PRODUCT_AMOUNT_QUERY)) {
            preparedStatement.setInt(1, (int) userId);
            preparedStatement.setInt(2, (int) productId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public void changeProductAmount(long productId, long userId, int amount) {
        try (Connection con = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(BasketQueryConstant.CHANGE_PRODUCT_AMOUNT_QUERY)) {
            preparedStatement.setInt(1, amount);
            preparedStatement.setInt(2, (int) userId);
            preparedStatement.setInt(3, (int) productId);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<BasketProductDto> getBasketFromDb(long userId) {
        try (Connection con = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(BasketQueryConstant.GET_PRODUCTS_QUERY)) {
            preparedStatement.setInt(1, (int) userId);
            ResultSet rs = preparedStatement.executeQuery();
            return getBasketList(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteProduct(long productId, long userId) {
        try (Connection con = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(BasketQueryConstant.DELETE_PRODUCT_QUERY)) {
            preparedStatement.setInt(1, (int) userId);
            preparedStatement.setInt(2, (int) productId);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<BasketProductDto> getBasket() {
        return null;
    }


    @Override
    public void addProduct(BasketProductDto product) {

    }

    private LinkedList<BasketProductDto> getBasketList(ResultSet resultSet) throws SQLException {
        LinkedList<BasketProductDto> list = new LinkedList<>();
        while (resultSet.next()) {
            BasketProductDto product = new BasketProductDto();
            product.setId(resultSet.getInt(ID));
            product.setBrand(resultSet.getString(BRAND));
            product.setPrice(resultSet.getInt(PRICE));
            product.setModel(resultSet.getString(MODEL));
            product.setMarketLaunchDate(resultSet.getInt(MARKET_LAUNCH_DATE));
            product.setAverageRating(resultSet.getDouble(RATING));
            product.setImage(resultSet.getString(IMAGE_LINK));
            product.setDescription(resultSet.getString(DESCRIPTION));
            product.setAmount(resultSet.getInt(AMOUNT));
            list.addFirst(product);
        }
        return list;
    }
}
