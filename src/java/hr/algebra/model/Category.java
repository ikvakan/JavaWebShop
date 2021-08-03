/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.io.Serializable;

/**
 *
 * @author IgorKvakan
 */
public class Category implements Serializable{
    
    private static final long serialVersionUID = 6L;
    private int IdCategory;
    private String Name;

    public Category() {
    }

    
    public Category(int IdCategory, String Name) {
        this.IdCategory = IdCategory;
        this.Name = Name;
    }

    public Category(String Name) {
        this.Name = Name;
    }

    
    public int getIdCategory() {
        return IdCategory;
    }

    public void setIdCategory(int IdCategory) {
        this.IdCategory = IdCategory;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    
    
}
