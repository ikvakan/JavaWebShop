/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.servlet;

import hr.algebra.dal.interfaces.CategoryRepository;
import hr.algebra.dal.interfaces.ProductRepository;
import hr.algebra.dal.repository.CategoryRepositoryImpl;
import hr.algebra.dal.repository.ProductRepositoryImpl;
import hr.algebra.model.Category;
import hr.algebra.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(urlPatterns = {"/showAllProducts", "/showProductsByCategory", "/addToCart"})
public class UserProductServlet extends HttpServlet {

    ProductRepository<Product> productRepo;
    CategoryRepository<Category> categoryRepo;

    public UserProductServlet() {
        productRepo = new ProductRepositoryImpl();
        categoryRepo = new CategoryRepositoryImpl();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/showAllProducts":
                showAllProducts(request, response);
                break;
            case "/showProductsByCategory":
                showProductsByCategory(request, response);
                break;
            case "/addToCart":
                addToCart(request, response);
                break;
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

    private void showAllProducts(HttpServletRequest request, HttpServletResponse response) {

        try {
            List<Category> categories = categoryRepo.getEntity();
            request.setAttribute("categories", categories);

            List<Product> products = productRepo.getEntity();
            request.setAttribute("products", products);

            RequestDispatcher rd = request.getRequestDispatcher("/productsUser.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UserProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showProductsByCategory(HttpServletRequest request, HttpServletResponse response) {

        int categoryId = Integer.parseInt(request.getParameter("category"));

        try {
            List<Product> productByCategory = productRepo.getProductByCategory(categoryId);
            request.setAttribute("products", productByCategory);

            List<Category> categories = categoryRepo.getEntity();
            request.setAttribute("categories", categories);

            RequestDispatcher rd = request.getRequestDispatcher("/productsUser.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UserProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) {

        
        int idProduct = Integer.parseInt(request.getParameter("id"));

        HttpSession session = request.getSession();

        try {
            Product product = productRepo.getEntityById(idProduct);
            List<Product> products = new ArrayList<>();

            if (session.getAttribute("productsInCart") == null) {
                products.add(product);
                session.setAttribute("productsInCart", products);
            } else {
                products = (List<Product>) session.getAttribute("productsInCart");
                products.add(product);
                session.setAttribute("productsInCart", products);
            }

            String msg = "Proizvod: " + product.getName() + " je dodan u košaricu";
            request.setAttribute("msg", msg);

            RequestDispatcher rd = request.getRequestDispatcher("showAllProducts");
            rd.forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(UserProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
