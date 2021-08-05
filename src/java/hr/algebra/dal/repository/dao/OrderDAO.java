/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal.repository.dao;

import hr.algebra.dal.interfaces.CategoryRepository;
import hr.algebra.dal.repository.CategoryRepositoryImpl;
import hr.algebra.dal.sql.DataSourceSingleton;
import hr.algebra.model.CartItem;
import hr.algebra.model.Category;
import hr.algebra.model.Order;
import hr.algebra.model.Product;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author IgorKvakan
 */
public class OrderDAO {

    private CategoryRepository<Category> categoryRepo;

    public OrderDAO() {
        categoryRepo=new CategoryRepositoryImpl();
    }
    
    
    
    private static final String ID_ORDER="IdOrder";
    private static final String BUY_DATE="BuyDate";
    private static final String TOTAL_PRICE="Total";
    private static final String PAYMENT_METHOD="PaymentMethod";
    
    private static final String PRODUCT_QUANTITY="ProductQuantity";
    private static final String PRODUCT_TOTAL_PRICE="ProductTotalPrice";
    private static final String ID_PRODUCT="IdProduct";
    private static final String PRODUCT_NAME="Name";
    private static final String PRODUCT_PRICE="Price";
    private static final String PRODUCT_DESCRIPTION="Description";
    private static final String CATEGORY_ID="CategoryId";
            
    
    private static final String INSERT_ORDER = "{ CALL insertOrder (?,?,?,?,?)}";
    private static final String INSERT_INTO_ORDER_DETAILS = "{ CALL insertIntoOrderDetails  (?,?,?,?) }";
    private static final String SELECT_ORDERS_BY_USER_ID = "{ CALL getOrdersByUserId (?) }";
    private static final String SELECT_CART_ITEMS_BY_ORDER_ID="{ CALL getCartItemsByOrderID (?)}";

    public int insertOrder(Order order)   {

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(INSERT_ORDER)) {
            stmt.setInt(1, order.getUser().getIdUser());
            stmt.setDate(2, Date.valueOf(order.getBuyDate()));
            stmt.setBigDecimal(3, order.getTotalPrice());
            stmt.setString(4, order.getPaymentMethod());
            stmt.registerOutParameter(5, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(5);

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public void insertIntoOrderDetails(int idOrder, CartItem cartItem) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();

        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement stmt = conn.prepareCall(INSERT_INTO_ORDER_DETAILS)) {
                stmt.setInt(1, idOrder);
                stmt.setInt(2, cartItem.getProduct().getIdProduct());
                stmt.setInt(3, cartItem.getQuantity());
                stmt.setBigDecimal(4, cartItem.getTotal());

                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Order> getOrdersByUserId(int id) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        List<Order> orders = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ORDERS_BY_USER_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Order order= new Order();
                    order.setIdOrder(rs.getInt(ID_ORDER));
                    order.setBuyDate(rs.getDate(BUY_DATE).toLocalDate());
                    order.setTotalPrice(rs.getBigDecimal(TOTAL_PRICE).setScale(2,RoundingMode.HALF_UP));
                    order.setPaymentMethod(rs.getString(PAYMENT_METHOD));
                    orders.add(order);
                    
                  
                }
            }

        }

        
        for (Order order : orders) {
            
            order.setCartItems(getCartItemsByOrderID(order.getIdOrder()));
        }
        
        return orders;

    }
    
     public List<CartItem> getCartItemsByOrderID(int id) throws SQLException {
       DataSource dataSource = DataSourceSingleton.getInstance();
        List<CartItem> cartItems = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_CART_ITEMS_BY_ORDER_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    cartItems.add(new CartItem(
                            rs.getInt(PRODUCT_QUANTITY),
                            rs.getBigDecimal(PRODUCT_TOTAL_PRICE).setScale(2,RoundingMode.HALF_UP),
                            new Product(
                                    rs.getInt(ID_PRODUCT),
                                    rs.getString(PRODUCT_NAME),
                                    rs.getBigDecimal(PRODUCT_PRICE).setScale(2,RoundingMode.HALF_UP),
                                    rs.getString(PRODUCT_DESCRIPTION),
                                    categoryRepo.getEntityById(rs.getInt(CATEGORY_ID)))));
                    
                  
                }
            } catch (Exception ex) {
               Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
           }

        }
      
        return cartItems;

     }

     public List<Order> getOrdersForUserByDate(int userId,LocalDate date) throws SQLException{
         
         List<Order> orders=getOrdersByUserId(userId);
         List<Order> filteredOrdersByDate=new ArrayList<>();
         
         for (Order order : orders) {
             if (order.getBuyDate().equals(date)) {
                 
                 filteredOrdersByDate.add(order);
                 
             }
         }
         
         return filteredOrdersByDate;
     }
     
    
}
