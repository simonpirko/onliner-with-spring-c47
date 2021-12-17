package by.fakeonliner.repository.jdbc;

import by.fakeonliner.dto.ProductDto;
import by.fakeonliner.repository.ProductDao;
import by.fakeonliner.repository.configuration.JdbcConnection;
import by.fakeonliner.repository.query_constant.ProductQueryConstant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcProductDao implements ProductDao {
    private final static String ID = "id";
    private final static String BRAND = "brand";
    private final static String PRICE = "price";
    private final static String MODEL = "model";
    private final static String MARKET_LAUNCH_DATE = "market_launch_date";
    private final static String RATING = "rating";
    private final static String CATEGORY = "category";
    private final static String LINK_PHOTO = "product_link_image";
    private final static String DESCRIPTION = "description";

    @Override
    public void save(Object object) {

    }

    @Override
    public ProductDto findById(int id) {
        try (Connection con = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ProductQueryConstant.FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getProductDto(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean existByModel(String model) {
        try (Connection con = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ProductQueryConstant.FIND_BY_MODEL)) {
            preparedStatement.setString(1, model);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ProductDto> findByModel(String model) {
        try (Connection con = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ProductQueryConstant.FIND_BY_MODEL)) {
            preparedStatement.setString(1, model);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getProductDtoList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void edit(long id, Object object) {

    }

    @Override
    public void delete(long id) {
        try (Connection con = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ProductQueryConstant.DELETE_PRODUCT_BY_ID)) {
            preparedStatement.setInt(1, (int) id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ProductDto> findByBrand(String name, String category) {

        try (Connection con = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ProductQueryConstant.FIND_BY_BRAND)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, category);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getProductDtoList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProductDto> findByPrice(double min, double max, String category) {
        try (Connection con = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ProductQueryConstant.FIND_BY_PRICE)) {
            preparedStatement.setDouble(1, min);
            preparedStatement.setDouble(2, max);
            preparedStatement.setString(3, category);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getProductDtoList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProductDto> findByAllFromCategory(String category) {
        try (Connection con = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ProductQueryConstant.ALL_FROM_CATEGORY)) {
            preparedStatement.setString(1, category);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getProductDtoList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ProductDto> getProductListHome() {
        try (Connection con = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ProductQueryConstant.GET_ALL_PRODUCTS_HOME_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return getProductDtoList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<ProductDto> getProductDtoList(ResultSet resultSet) throws SQLException {
        List<ProductDto> list = new ArrayList<>();
        while (resultSet.next()) {
            ProductDto productDto = new ProductDto();
            productDto.setId(resultSet.getInt(ID));
            productDto.setBrand(resultSet.getString(BRAND));
            productDto.setPrice(resultSet.getInt(PRICE));
            productDto.setModel(resultSet.getString(MODEL));
            productDto.setMarketLaunchDate(resultSet.getInt(MARKET_LAUNCH_DATE));
            productDto.setAverageRating(resultSet.getDouble(RATING));
            productDto.setImage(resultSet.getString(LINK_PHOTO));
            productDto.setDescription(resultSet.getString(DESCRIPTION));
            list.add(productDto);
        }
        return list;
    }

    private ProductDto getProductDto(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            ProductDto productDto = new ProductDto();
            productDto.setId(resultSet.getInt(ID));
            productDto.setBrand(resultSet.getString(BRAND));
            productDto.setPrice(resultSet.getInt(PRICE));
            productDto.setModel(resultSet.getString(MODEL));
            productDto.setMarketLaunchDate(resultSet.getInt(MARKET_LAUNCH_DATE));
            productDto.setAverageRating(resultSet.getDouble(RATING));
            productDto.setImage(resultSet.getString(LINK_PHOTO));
            productDto.setDescription(resultSet.getString(DESCRIPTION));
            return productDto;
        }
        return null;
    }
}
