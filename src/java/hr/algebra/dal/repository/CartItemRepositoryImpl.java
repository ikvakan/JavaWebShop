/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal.repository;

import hr.algebra.dal.interfaces.CartItemRepository;
import hr.algebra.model.CartItem;
import java.util.List;

/**
 *
 * @author IgorKvakan
 */
public class CartItemRepositoryImpl implements CartItemRepository<CartItem>{
    
    private static final String ID_CART_ITEM="IdCartItem";
    private static final String PRODUCT_ID="ProductId";
    private static final String QUANTITY="Quantity";
    private static final String PRICE="Price";
    
    private static final String ADD_CART_ITEM = "INSERT INTO CART_ITEM (Name,Price,Description,CategoryId) VALUES (?,?,?,?)";
    private static final String SELECT_CART_ITEM_BY_ID = "SELECT * FROM CART_ITEM WHERE IdCategory=";
    
    private static final String DELETE_CART_ITEM = "DELETE FROM CART_ITEM WHERE IdProduct=?";  
    private static final String UPDATE_CART_ITEM = "UPDATE CART_ITEM SET Name=?,Price=?,Description=?,CategoryId=? WHERE IdProduct=? ";
    
    @Override
    public List<CartItem> getEntity() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CartItem getEntityById(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addEntity(CartItem entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateEntity(CartItem entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteEntity(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
