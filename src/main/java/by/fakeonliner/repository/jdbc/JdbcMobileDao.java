package by.fakeonliner.repository.jdbc;

import by.fakeonliner.entity.product.Mobile;
import by.fakeonliner.repository.CategoryProductDao;
import by.fakeonliner.repository.configuration.JdbcConnection;
import by.fakeonliner.repository.query_constant.LaptopQueryConstant;
import by.fakeonliner.repository.query_constant.MobileQueryConstant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcMobileDao implements CategoryProductDao {

    @Override
    public boolean save(Object object) {
        Mobile mobile = (Mobile) object;
        try (Connection con = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(MobileQueryConstant.ADD_MOBILE_QUERY)) {
            preparedStatement.setInt(1, (int) mobile.getId());
            preparedStatement.setString(2, mobile.getMobileType());
            preparedStatement.setString(3, mobile.getProcessor());
            preparedStatement.setString(4, mobile.getOperationSystem());
            preparedStatement.setDouble(5, mobile.getDisplaySize());
            preparedStatement.setDouble(6, mobile.getFrequency());
            preparedStatement.setInt(7, mobile.getRam());
            preparedStatement.setString(8, mobile.getDisplayType());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Mobile getProductByIdAndType(long id) {
        try (Connection con = JdbcConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(LaptopQueryConstant.ADD_LAPTOP_QUERY)) {
            preparedStatement.setInt(1, (int) id);
            ResultSet resultSet = preparedStatement.executeQuery();

            return getMobile(resultSet);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    private Mobile getMobile(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return (new Mobile(rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getDouble(5), rs.getDouble(6),
                    rs.getInt(7), rs.getString(8)));
        }
        return null;
    }
}
