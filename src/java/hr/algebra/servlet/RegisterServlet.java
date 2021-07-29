/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.servlet;

import hr.algebra.dal.interfaces.Repository;
import hr.algebra.dal.interfaces.UserRepository;
import hr.algebra.dal.repository.UserRepositoryImpl;
import hr.algebra.model.User;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author IgorKvakan
 */
public class RegisterServlet extends HttpServlet {

    UserRepository<User> userRepo;

    public RegisterServlet() {
        userRepo = new UserRepositoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("userName");
        String name = request.getParameter("name");
        String surName = request.getParameter("surname");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String adress = request.getParameter("adress");
        String city = request.getParameter("city");

        LocalDate regDate = LocalDate.now();
        String ipAddress = request.getRemoteAddr();

        User user = new User(userName, name, surName, password, email, adress, city, regDate, ipAddress);

        try {
            userRepo.addEntity(user);

            response.sendRedirect("home.jsp");
        } catch (Exception ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
