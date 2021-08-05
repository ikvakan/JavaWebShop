/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author IgorKvakan
 */
public class OrderModel implements Serializable{
    
    private static final long serialVersionUID = 5L;
    private int IdOrder;
    private User user;
    private LocalDate buyDate;
    private BigDecimal totalPrice;
    private String paymentMethod;
    private List<CartItem> cartItems;

    public OrderModel() {
        user= new User();
        cartItems= new ArrayList<>();
    }

    public OrderModel(int IdOrder, User user, LocalDate buyDate, BigDecimal totalPrice, String paymentMethod, List<CartItem> cartItems) {
        this.IdOrder = IdOrder;
        this.user = user;
        this.buyDate = buyDate;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.cartItems = cartItems;
    }

    public OrderModel(int IdOrder, LocalDate buyDate, BigDecimal totalPrice, String paymentMethod) {
        this.IdOrder = IdOrder;
        this.buyDate = buyDate;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
    }

    

    public OrderModel(User user, LocalDate buyDate, BigDecimal totalPrice, String paymentMethod, List<CartItem> cartItems) {
        this.user = user;
        this.buyDate = buyDate;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.cartItems = cartItems;
    }

 
 
    
    
   public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }


    public int getIdOrder() {
        return IdOrder;
    }

    public void setIdOrder(int IdOrder) {
        this.IdOrder = IdOrder;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    

   
    
    
    
}
