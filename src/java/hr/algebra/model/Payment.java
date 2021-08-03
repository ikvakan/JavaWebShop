/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

/**
 *
 * @author IgorKvakan
 */
public class Payment {
    
    private int idPayment;
    private String paymentMethod;

    public Payment() {
    }

    
    
    public Payment(int idPayment, String paymentMethod) {
        this.idPayment = idPayment;
        this.paymentMethod = paymentMethod;
    }

    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    
}
