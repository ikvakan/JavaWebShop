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

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author IgorKvakan
 */
@WebServlet(urlPatterns = {"/addCategory", "/deleteCategory", "/updateCategory", "/listCategory", "/showEditCategory"})

public class CategoryServlet extends HttpServlet {

    CategoryRepository<Category> categoryRepo;
    ProductRepository<Product> productRepo;

    public CategoryServlet() {
        categoryRepo = new CategoryRepositoryImpl();
        productRepo = new ProductRepositoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        switch (action) {
            case "/addCategory":
                addCategory(request, response);
                break;
            case "/deleteCategory":
                deleteCategory(request, response);
                break;
            case "/updateCategory":
                updateCategory(request, response);
                break;
            case "/showEditCategory":
                showEditCategory(request, response);
//            case "/listCategory":
//                showCategory(request, response);
//                break;
            default:
                showCategory(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

    }

    private void addCategory(HttpServletRequest request, HttpServletResponse response) {

        try {

            String categoryName = request.getParameter("category");

            Category category = new Category(categoryName);
            try {
                categoryRepo.addEntity(category);
                List<Category> list = categoryRepo.getEntity();
                request.setAttribute("category_list", list);
            } catch (Exception ex) {
                Logger.getLogger(CategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            RequestDispatcher dispacher = request.getRequestDispatcher("Admin/addCategory.jsp");
            dispacher.forward(request, response);

        } catch (ServletException ex) {
            Logger.getLogger(CategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            for (Product product : productRepo.getEntity()) {
                if (product.getCategory().getIdCategory() == id) {
                    productRepo.deleteEntity(product.getIdProduct());
                    categoryRepo.deleteEntity(id);

                }

            }

            categoryRepo.deleteEntity(id);

            List<Category> list = categoryRepo.getEntity();
            request.setAttribute("category_list", list);

            RequestDispatcher dispacher = request.getRequestDispatcher("Admin/addCategory.jsp");
            dispacher.forward(request, response);

        } catch (ServletException ex) {
            Logger.getLogger(CategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void showCategory(HttpServletRequest request, HttpServletResponse response) {
        try {

            try {

                List<Category> list = categoryRepo.getEntity();
                request.setAttribute("category_list", list);
            } catch (Exception ex) {
                Logger.getLogger(CategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            RequestDispatcher dispacher = request.getRequestDispatcher("Admin/addCategory.jsp");
            dispacher.forward(request, response);

        } catch (ServletException ex) {
            Logger.getLogger(CategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateCategory(HttpServletRequest request, HttpServletResponse response) {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("category");

            categoryRepo.updateEntity(new Category(id, name));

            response.sendRedirect("Category");


        } catch (Exception ex) {
            Logger.getLogger(CategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void showEditCategory(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));

       
        try {

            Category category = categoryRepo.getEntityById(id);

            request.setAttribute("category", category);
            RequestDispatcher dispacher = request.getRequestDispatcher("Admin/addCategory.jsp");
            dispacher.forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(CategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
