package Repository;

import Data.User;
import config.DatabaseConnection;

import java.sql.*;

public class UserRepository {

   /*public void createTable() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "CREATE TABLE user_tbl (idUser int primary key not null ," +
                "username varchar(30),nationalCode varchar(10)," +
                "birthday date,password varchar(16))";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeQuery();
        connection.close();
    }*/


    public void signUp(User user) throws SQLException {

        Connection connection = DatabaseConnection.getConnection();
        String sql = "insert into user_tbl  (username,nationalCode,birthady,password)values" +
                "(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getNationalCode());
        preparedStatement.setDate(3, user.getBirthday());
        preparedStatement.setString(4, user.getNationalCode());
        preparedStatement.executeUpdate();
        connection.close();
    }
    public User matchPassword(String userName , String password) throws SQLException {

        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM user_tbl where usernam = ?" +
                " && password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,userName);
        preparedStatement.setString(2,password);
        ResultSet resultSet = preparedStatement.executeQuery();
        connection.close();
        while (resultSet.next()) {
            return new User();//constructor user
        }
        return null;
    }
    /*private User buildUser(ResultSet resultSet) throws SQLException {

        while (resultSet.next()) {
            User user = new User();
            String username = resultSet.getString("username");
            String nationalCode = resultSet.getString("nationalCode");
            Date birthday = resultSet.getDate("birthday");
            String passWord = resultSet.getString("passWord");

            user.setUsername(username);
            user.setNationalCode(nationalCode);
            user.setBirthday(birthday);
            user.setPassword(passWord);
            return user;
        }
        return null;
    }*/
}
