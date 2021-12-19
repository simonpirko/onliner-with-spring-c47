package by.fakeonliner.repository.jdbc;

import by.fakeonliner.entity.product.Laptop;
import by.fakeonliner.repository.CategoryProductDao;
import by.fakeonliner.repository.configuration.JdbcConnection;
import by.fakeonliner.repository.jdbc_query_constant.LaptopQueryConstant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcLaptopDao implements CategoryProductDao {

    @Override
    public boolean save(Object object) {
        Laptop laptop = (Laptop) object;
        try (Connection con = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(LaptopQueryConstant.ADD_LAPTOP_QUERY)) {
            preparedStatement.setInt(1, (int) laptop.getId());
            preparedStatement.setString(2, laptop.getType());
            preparedStatement.setString(3, laptop.getVideoCard());
            preparedStatement.setString(4, laptop.getProcessor());
            preparedStatement.setString(5, laptop.getOperationSystem());
            preparedStatement.setDouble(6, laptop.getDisplaySize());
            preparedStatement.setDouble(7, laptop.getFrequency());
            preparedStatement.setInt(8, laptop.getRam());
            preparedStatement.setInt(9, laptop.getRom());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Laptop getProductByIdAndType(long id) {
        try (Connection con = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(LaptopQueryConstant.GET_LAPTOP_BY_ID_QUERY)) {
            preparedStatement.setInt(1, (int) id);
            ResultSet resultSet = preparedStatement.executeQuery();

            return getLaptop(resultSet);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    private Laptop getLaptop(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return (new Laptop(rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getDouble(6),
                    rs.getDouble(7), rs.getInt(8), rs.getInt(9)));
        }
        return null;
    }
}
