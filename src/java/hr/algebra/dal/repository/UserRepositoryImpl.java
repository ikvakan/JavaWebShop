/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal.repository;

import hr.algebra.dal.interfaces.UserRepository;
import hr.algebra.dal.sql.DataSourceSingleton;
import hr.algebra.model.Category;
import hr.algebra.model.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author IgorKvakan
 */
public class UserRepositoryImpl implements UserRepository<User> {

    private static final String ID_USER = "IdUser";
    private static final String USER_NAME = "UserName";
    private static final String NAME = "Name";
    private static final String SURNAME = "Surname";
    private static final String PASSWORD = "Password";
    private static final String EMAIL = "Email";
    private static final String ADRESS = "Adress";
    private static final String CITY = "City";
    private static final String REG_DATE = "RegDate";
    private static final String IP_ADRESS = "IPAdress";

    private static final String ADD_USER = "INSERT INTO USERS (UserName,Name,Surname,Password,Email,Adress,City,RegDate,IPAdress) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String SELECT_USERS = "SELECT * FROM USERS";
    private static final String DELETE_USERS = "DELETE FROM USERS WHERE IdUser=?";
    private static final String UPDATE_USERS = "UPDATE USERS SET UserName=?,Name=?,Surname=?,Password=?,Email=?,Aress=?,City=?,RegDate=?,IPAdress=? WHERE IdUser=? ";
    private static final String SELECT_USERS_BY_ID = "SELECT * FROM USERS WHERE IdUser=";

    @Override
    public void addEntity(User user) throws Exception {

        DataSource dataSource = DataSourceSingleton.getInstance();

        try (Connection con = dataSource.getConnection()) {

            try (PreparedStatement addUserStmt = con.prepareStatement(ADD_USER)) {
                addUserStmt.setString(1, user.getUserName());
                addUserStmt.setString(2, user.getName());
                addUserStmt.setString(3, user.getSurname());
                addUserStmt.setString(4, user.getPassword());
                addUserStmt.setString(5, user.getEmail());
                addUserStmt.setString(6, user.getAdress());
                addUserStmt.setString(7, user.getCity());
                addUserStmt.setDate(8, Date.valueOf(user.getRegDate()));
                addUserStmt.setString(9, user.getIpAdress());

                addUserStmt.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getEntity() throws Exception {

        List<User> users = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SELECT_USERS)) {
            while (rs.next()) {
                users.add(new User(
                        rs.getInt(ID_USER),
                        rs.getString(USER_NAME),
                        rs.getString(NAME),
                        rs.getString(SURNAME),
                        rs.getString(PASSWORD),
                        rs.getString(EMAIL),
                        rs.getString(ADRESS),
                        rs.getString(CITY),
                        rs.getDate(REG_DATE).toLocalDate(),
                        rs.getString(IP_ADRESS)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;

    }

    @Override
    public void updateEntity(User user) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();

        try (Connection con = dataSource.getConnection()) {

            try (PreparedStatement stmt = con.prepareStatement(UPDATE_USERS)) {
                stmt.setString(1, user.getUserName());
                stmt.setString(2, user.getName());
                stmt.setString(3, user.getSurname());
                stmt.setString(4, user.getPassword());
                stmt.setString(5, user.getEmail());
                stmt.setString(6, user.getAdress());
                stmt.setString(7, user.getCity());
                stmt.setDate(8, Date.valueOf(user.getRegDate()));
                stmt.setString(9, user.getIpAdress());
                stmt.setInt(10, user.getIdUser());

                stmt.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEntity(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();

        try (Connection con = dataSource.getConnection()) {

            try (PreparedStatement stmt = con.prepareStatement(DELETE_USERS)) {
                stmt.setInt(1, id);
                stmt.execute();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getEntityById(int id) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();

        try (Connection con = dataSource.getConnection();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SELECT_USERS_BY_ID + id)) {
            if (rs.next()) {
                return new User(
                        id,
                        rs.getString(USER_NAME),
                        rs.getString(NAME),
                        rs.getString(SURNAME),
                        rs.getString(PASSWORD),
                        rs.getString(EMAIL),
                        rs.getString(ADRESS),
                        rs.getString(CITY),
                        rs.getDate(REG_DATE).toLocalDate(),
                        rs.getString(IP_ADRESS));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
