/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.servlet;

import hr.algebra.dal.interfaces.UserRepository;
import hr.algebra.dal.repository.UserRepositoryImpl;
import hr.algebra.dal.repository.dao.OrderDAO;
import hr.algebra.model.OrderModel;
import hr.algebra.model.User;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
@WebServlet(urlPatterns = {"/showOrders", "/showOrderByUser", "/showOrdersByDate"})
public class Orders extends HttpServlet {

    UserRepository usersRepository;
    OrderDAO orderRepository;

    public Orders() {
        usersRepository = new UserRepositoryImpl();
        orderRepository = new OrderDAO();

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        switch (action) {
            case "/showOrders":
                showOrders(request, response);
                break;
            case "/showOrderByUser":
                showOrderByUser(request, response);
                break;
            case "/showOrdersByDate":
                showOrdersByDate(request, response);
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

    private void showOrders(HttpServletRequest request, HttpServletResponse response) {

        try {
            List<User> users = usersRepository.getEntity();
            request.setAttribute("users", users);

            RequestDispatcher rd = request.getRequestDispatcher("Admin/ordersAdmin.jsp");
            rd.forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void showOrderByUser(HttpServletRequest request, HttpServletResponse response) {

        int userId = Integer.parseInt(request.getParameter("users"));

        HttpSession session = request.getSession();

        try {
            List<OrderModel> orders = orderRepository.getOrdersByUserId(userId);
            List<User> users = usersRepository.getEntity();

            Set<LocalDate> dates = new HashSet<LocalDate>();

            for (OrderModel order : orders) {
                dates.add(order.getBuyDate());
            }

            //request.setAttribute("selectedUserId", userId);
            session.setAttribute("selectedUserId", userId);
            request.setAttribute("users", users);
            request.setAttribute("orders", orders);
            session.setAttribute("orderDates", dates);

            RequestDispatcher rd = request.getRequestDispatcher("Admin/ordersAdmin.jsp");
            rd.forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void showOrdersByDate(HttpServletRequest request, HttpServletResponse response) {

    

            LocalDate date = LocalDate.parse(request.getParameter("buyDate"));
            HttpSession session = request.getSession();

            int userId = (int) session.getAttribute("selectedUserId");

            try {
                List<OrderModel> ordersByDate = orderRepository.getOrdersForUserByDate(userId, date);
                //List<Order> orders = orderRepository.getOrdersByUserId(userId);
                //request.setAttribute("ordersByDate", ordersByDate);
                request.setAttribute("orders", ordersByDate);

                List<User> users = usersRepository.getEntity();
                request.setAttribute("users", users);
                request.setAttribute("selectedDate", date);

                RequestDispatcher rd = request.getRequestDispatcher("Admin/ordersAdmin.jsp");
                rd.forward(request, response);

            } catch (SQLException ex) {
                Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ServletException ex) {
                Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    

}
