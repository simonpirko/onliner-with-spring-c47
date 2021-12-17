package by.fakeonliner.repository.jdbc;

import by.fakeonliner.entity.shop.Shop;
import by.fakeonliner.repository.ShopDao;
import by.fakeonliner.repository.configuration.JdbcConnection;
import by.fakeonliner.repository.query_constant.ShopQueryConstant;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class JdbcShopDao implements ShopDao {

    @Override
    public void save(Shop shop) {
        try (Connection con = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ShopQueryConstant.ADD_SHOP_QUERY)) {
            preparedStatement.setString(1, shop.getName());
            preparedStatement.setString(2, shop.getPassword());
            preparedStatement.setString(3, shop.getPhoneNumber());
            preparedStatement.setString(4, shop.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean existByEmail(String email) {
        try (Connection con = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ShopQueryConstant.EXIST_BY_EMAIL_QUERY)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Shop getShopByEmail(String email) {
        try (Connection con = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ShopQueryConstant.GET_SHOP_BY_EMAIL_QUERY)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            return getShop(resultSet);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Shop> getShopList() {
        try (Connection con = JdbcConnection.getConnection();
             Statement statement = con.createStatement()) {
            ResultSet resultSet = statement.executeQuery(ShopQueryConstant.GET_SHOP_LIST_QUERY);

            return getShopList(resultSet);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void edit(Shop shop) {
        try (Connection con = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ShopQueryConstant.CHANGE_SHOP_BY_ID_QUERY)) {
            preparedStatement.setString(1, shop.getName());
            preparedStatement.setString(2, shop.getEmail());
            preparedStatement.setString(3, shop.getEmail());
            preparedStatement.setString(4, shop.getEmail());
            preparedStatement.setString(5, shop.getEmail());
            preparedStatement.setString(6, shop.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Shop shop) {
        try (Connection con = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ShopQueryConstant.DELETE_SHOP_QUERY)) {
            preparedStatement.setInt(1, (int) shop.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private List<Shop> getShopList(ResultSet rs) throws SQLException {
        LinkedList<Shop> shops = new LinkedList<>();
        while (rs.next()) {
            Shop shop = new Shop();
            setShopFields(shop, rs);
            shops.addFirst(shop);
        }
        return shops;
    }

    private void setShopFields(Shop shop, ResultSet resultSet) throws SQLException {
        shop.setId(resultSet.getInt(1));
        shop.setName(resultSet.getString(2));
        shop.setEmail(resultSet.getString(3));
        shop.setPassword(resultSet.getString(4));
        shop.setPhoneNumber(resultSet.getString(5));
        shop.setContactAddress(resultSet.getString(6));
        shop.setDescription(resultSet.getString(7));
        shop.setRating(resultSet.getString(8));
        shop.setCountRating(resultSet.getInt(9));
    }

    private Shop getShop(ResultSet rs) throws SQLException {
        Shop shop = new Shop();
        if(rs.next()) {
            setShopFields(shop, rs);
            return shop;
        }
        return null;
    }
}
