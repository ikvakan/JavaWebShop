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
public class Product implements Serializable{

    private static final long serialVersionUID = 2L;
    private int IdProduct;
    private String name;
    private BigDecimal price;
    private String description;
    //private int CategoryID;
    private Category category;

    public Product() {
        category=new Category();
    }

    
    public Product(int IdProduct, String name, BigDecimal price, String description, Category category) {
        this.IdProduct = IdProduct;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public Product(String name, BigDecimal price, String description, Category category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    
    
//    
//    public Product(int IdProduct, String name, BigDecimal price, String description,int CategoryID) {
//        this.IdProduct = IdProduct;
//        this.name = name;
//        this.price = price;
//        this.description = description;
//        this.CategoryID = CategoryID;
//    }
//
//    public Product( String name, BigDecimal price, String description, int CategoryID) {
//        this.name = name;
//        this.price = price;
//        this.description = description;
//        this.CategoryID = CategoryID;
//    }
//    
    

    public int getIdProduct() {
        return IdProduct;
    }

    public void setIdProduct(int IdProduct) {
        this.IdProduct = IdProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public int getCategoryID() {
//        return CategoryID;
//    }
//
//    public void setCategoryID(int CategoryID) {
//        this.CategoryID = CategoryID;
//    }
}
