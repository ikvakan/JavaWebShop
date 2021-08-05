/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.servlet;

import hr.algebra.dal.interfaces.UserRepository;
import hr.algebra.dal.repository.UserRepositoryImpl;
import hr.algebra.model.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author IgorKvakan
 */
public class LoginServlet extends HttpServlet {

    UserRepository<User> users;

    public LoginServlet() {
        users = new UserRepositoryImpl();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        User newUser = new User();

        try {
            for (User user : users.getEntity()) {

                if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                    newUser = user;
                    session.setAttribute("user", newUser);

                    break;
                }

            }
            
            if (session.getAttribute("user")==null) {
                response.sendRedirect("userLoginError.jsp");
            }

            RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
            rd.forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
