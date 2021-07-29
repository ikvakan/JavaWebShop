/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal.repository;

import hr.algebra.dal.interfaces.ProductRepository;
import hr.algebra.dal.sql.DataSourceSingleton;
import hr.algebra.model.Category;
import hr.algebra.model.Product;
import java.math.RoundingMode;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author IgorKvakan
 */
public class ProductRepositoryImpl implements ProductRepository<Product> {

    private static final String ID_PRODUCT = "IdProduct";
    private static final String NAME = "Name";
    private static final String PRICE = "Price";
    private static final String DESCRIPTION = "Description";
    private static final String ID_CATEGORY = "IdCategory";
    private static final String CATEGORY = "Category";

    private static final String ADD_PRODUCT = "INSERT INTO PRODUCTS (Name,Price,Description,CategoryId) VALUES (?,?,?,?)";
    private static final String SELECT_PRODUCT_CATEGORY = "{ CALL getProductWithCategory }";
 
    private static final String SELECT_PRODUCT_BY_ID = "{ CALL getProductById (?)  }";
    private static final String SELECT_PRODUCT_BY_CATEGORY = "{ CALL getProductsByCategory (?)  }";

    private static final String DELETE_PRODUCT = "DELETE FROM PRODUCTS WHERE IdProduct=?";  //PROCEDURA ????
    private static final String UPDATE_PRODUCT = "UPDATE PRODUCTS SET Name=?,Price=?,Description=?,CategoryId=? WHERE IdProduct=? ";

    @Override
    public List<Product> getEntity() throws Exception {
        List<Product> products = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_PRODUCT_CATEGORY);) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setIdProduct(rs.getInt(ID_PRODUCT));
                product.setName(rs.getString(NAME));
                product.setPrice(rs.getBigDecimal(PRICE).setScale(2, RoundingMode.HALF_UP));
                product.setDescription(rs.getString(DESCRIPTION));
                product.setCategory(new Category(rs.getInt(ID_CATEGORY), rs.getString(CATEGORY)));

                products.add(product);

            }

        }

        return products;
    }

    @Override
    public void addEntity(Product product) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();

        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(ADD_PRODUCT)) {
                stmt.setString(1, product.getName());
                stmt.setBigDecimal(2, product.getPrice());
                stmt.setString(3, product.getDescription());
                stmt.setInt(4, product.getCategory().getIdCategory());

                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateEntity(Product product) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();

        try (Connection con = dataSource.getConnection()) {

            try (PreparedStatement stmt = con.prepareStatement(UPDATE_PRODUCT)) {
                stmt.setString(1, product.getName());
                stmt.setBigDecimal(2, product.getPrice());
                stmt.setString(3, product.getDescription());
                stmt.setInt(4, product.getCategory().getIdCategory());
                stmt.setInt(5, product.getIdProduct());

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

            try (PreparedStatement stmt = con.prepareStatement(DELETE_PRODUCT)) {
                stmt.setInt(1, id);
                stmt.execute();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product getEntityById(int id) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();

        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_PRODUCT_BY_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    Category category= new Category(rs.getInt(ID_CATEGORY), rs.getString(CATEGORY));
                    return new Product(rs.getInt(ID_PRODUCT),
                            rs.getString(NAME),
                            rs.getBigDecimal(PRICE).setScale(2,RoundingMode.HALF_UP),
                            rs.getString(DESCRIPTION),
                            category);
                }
            }

            return null;
        }

    }

    @Override
    public List<Product> getProductByCategory(int id) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        List<Product> products= new ArrayList<>();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_PRODUCT_BY_CATEGORY)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Category category= new Category(rs.getInt(ID_CATEGORY), rs.getString(CATEGORY));
                    products.add( new Product(rs.getInt(ID_PRODUCT),
                            rs.getString(NAME),
                            rs.getBigDecimal(PRICE).setScale(2,RoundingMode.HALF_UP),
                            rs.getString(DESCRIPTION),
                            category)
                    );}
            }

            return products;
        }
    }
}
