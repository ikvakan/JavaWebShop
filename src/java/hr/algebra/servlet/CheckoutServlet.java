/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.servlet;

import com.paypal.base.rest.PayPalRESTException;
import hr.algebra.dal.repository.dao.OrderDAO;
import hr.algebra.model.CartItem;
import hr.algebra.model.OrderModel;
import hr.algebra.model.User;
import hr.algebra.services.payment.PaymentServices;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author IgorKvakan
 */
@WebServlet(urlPatterns = {"/cashOnDelivery","/payPal"})
public class CheckoutServlet extends HttpServlet {

    OrderDAO orderRepo;

    public CheckoutServlet() {
        orderRepo = new OrderDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        switch (action) {
            case "/cashOnDelivery":
                cashOnDelivery(request, response);
                break;
            case "/payPal":
                payPal(request,response);
                break;
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void cashOnDelivery(HttpServletRequest request, HttpServletResponse response)  {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("productsInCart_session");
        BigDecimal totalPrice = (BigDecimal) session.getAttribute("totalPrice");
        String paymentMethod="Pouzece";
        
        
        OrderModel order= new OrderModel(user, LocalDate.now(), totalPrice, paymentMethod, cartItems);
        
       
        //provjera kod usera !!!!! TODO
        int idOrder=orderRepo.insertOrder(order);
        
        for (CartItem cartItem : cartItems) {
            try {
                orderRepo.insertIntoOrderDetails(idOrder, cartItem);
                cartItems=null;
                session.setAttribute("productsInCart_session", cartItems);
                        
                
            } catch (SQLException ex) {
                Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }

       
           RequestDispatcher rd = request.getRequestDispatcher("paymentSucces.jsp");
           
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }

    private void payPal(HttpServletRequest request, HttpServletResponse response) {
        
         HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("productsInCart_session");
        BigDecimal totalPrice = (BigDecimal) session.getAttribute("totalPrice");
        String paymentMethod="PayPal";
        
        
        OrderModel order= new OrderModel(user, LocalDate.now(), totalPrice, paymentMethod, cartItems);
        
        PaymentServices paymentServices= new PaymentServices();
        
        String errorPath="http://localhost:8084/JavaShoppingApp/paymentError.jsp";
        String successPath="http://localhost:8084/JavaShoppingApp/paymentSucces.jsp";
        
        try {
            String approvalLink=paymentServices.authorizePayment(order, errorPath,successPath);
            
            response.sendRedirect(approvalLink);
            
            int idOrder=orderRepo.insertOrder(order);
            
            for (CartItem cartItem : cartItems) {
            
                orderRepo.insertIntoOrderDetails(idOrder, cartItem);
                cartItems=null;
                session.setAttribute("productsInCart_session", cartItems);
                        
                
        }

            
            
        } catch (PayPalRESTException ex) {
            Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
        
    }

}
