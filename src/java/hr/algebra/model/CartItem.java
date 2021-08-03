/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author IgorKvakan
 */
public class CartItem implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private int idCartItem;
    private int quantity;
    private BigDecimal total;
    private Product product;

    public CartItem() {
    }

    public CartItem(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
        this.total=calculateTotal();
    }

    
    
    public CartItem(int idCartItem, int quantity, BigDecimal total, Product product) {
        this.idCartItem = idCartItem;
        this.quantity = quantity;
        this.total = calculateTotal();
        this.product = product;
    }

    public CartItem(int quantity, BigDecimal total, Product product) {
        this.quantity = quantity;
        this.total = total;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public int getIdCartItem() {
        return idCartItem;
    }

    public void setIdCartItem(int idCartItem) {
        this.idCartItem = idCartItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total =total;
    }
    
    private  BigDecimal calculateTotal(){
        
       BigDecimal subTotal=BigDecimal.valueOf(this.quantity).multiply(this.product.getPrice());
       return subTotal;
    }
    
}
