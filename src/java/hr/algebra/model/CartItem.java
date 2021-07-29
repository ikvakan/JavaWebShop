/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.math.BigDecimal;

/**
 *
 * @author IgorKvakan
 */
public class CartItem {

    private int idCartItem;
    private Product product;
    private int quantity;
    private BigDecimal price;

    public CartItem() {
        product = new Product();
    }

    public CartItem(int idCartItem, Product product, int quantity, BigDecimal price) {
        this.idCartItem = idCartItem;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public CartItem(Product product, int quantity, BigDecimal price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public int getIdCartItem() {
        return idCartItem;
    }

    public void setIdCartItem(int idCartItem) {
        this.idCartItem = idCartItem;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
