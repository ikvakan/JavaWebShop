/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author IgorKvakan
 */
public class User implements Serializable{

    private static final long serialVersionUID = 3L;
    private int idUser;
    private String userName;
    private String name;
    private String surname;
    private String password;
    private String email;
    private String adress;
    private String city;
    private LocalDate regDate;
    private String ipAdress;
    private String fullName;
    //private boolean isAdmin;

    public User() {
    }

    
    
    public User(int idUser, String userName, String name, String surname, String password, String email, String adress, String city, LocalDate regDate, String ipAdress) {
        this.idUser = idUser;
        this.userName = userName;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.adress = adress;
        this.city = city;
        this.regDate = regDate;
        this.ipAdress = ipAdress;
    }
    
     public User( String userName, String name, String surname, String password, String email, String adress, String city, LocalDate regDate, String ipAdress) {
        this.userName = userName;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.adress = adress;
        this.city = city;
        this.regDate = regDate;
        this.ipAdress = ipAdress;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public String getFullName() {
        this.fullName=this.name + " " + this.surname;
        return fullName;
    }

   


}
