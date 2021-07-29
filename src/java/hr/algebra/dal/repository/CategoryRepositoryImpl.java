/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal.repository;

import hr.algebra.dal.interfaces.CategoryRepository;
import hr.algebra.dal.sql.DataSourceSingleton;
import hr.algebra.model.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author IgorKvakan
 */
public class CategoryRepositoryImpl implements CategoryRepository<Category> {

    private static final String ID_CATEGORY = "IdCategory";
    private static final String CATEGORY = "Category";
    
    
    private static final String ADD_CATEGORY = "INSERT INTO CATEGORY (Category) VALUES (?)";
    private static final String SELECT_CATEGORY = "SELECT * FROM Category";
    private static final String DELETE_CATEGORY = "DELETE FROM CATEGORY WHERE IdCategory=?";
    private static final String UPDATE_CATEGORY = "UPDATE CATEGORY SET Category=? WHERE IdCategory=? ";
    private static final String SELECT_CATEGORY_BY_ID = "SELECT * FROM Category WHERE IdCategory=";

    @Override
    public List<Category> getEntity() throws Exception {
        List<Category> category = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SELECT_CATEGORY)) {
            while (rs.next()) {
                category.add(
                        new Category(
                                rs.getInt(ID_CATEGORY),
                                rs.getString(CATEGORY)));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }

    @Override
    public void addEntity(Category category) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();

        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(ADD_CATEGORY)) {
                stmt.setString(1, category.getName());

                stmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateEntity(Category category) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();

        try (Connection con = dataSource.getConnection()) {

            try (PreparedStatement stmt = con.prepareStatement(UPDATE_CATEGORY)) {
                stmt.setString(1, category.getName());
                stmt.setInt(2, category.getIdCategory());

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

            try (PreparedStatement stmt = con.prepareStatement(DELETE_CATEGORY)) {
                stmt.setInt(1, id);
                stmt.execute();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Category getEntityById(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
       

        try (Connection con = dataSource.getConnection();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SELECT_CATEGORY_BY_ID+id)) {
            if (rs.next()) {
                return new Category(id, rs.getString(CATEGORY));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
