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
import java.math.BigDecimal;
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
@WebServlet(urlPatterns = {"/addProduct", "/deleteProduct", "/updateProduct", "/listProduct", "/showEditProduct", "/showProduct"})

public class ProductServlet extends HttpServlet {

    ProductRepository<Product> productRepo;
    CategoryRepository<Category> categoryRepo;

    public ProductServlet() {
        productRepo = new ProductRepositoryImpl();
        categoryRepo = new CategoryRepositoryImpl();

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        switch (action) {
            case "/addProduct":
                addProduct(request, response);
                break;
            case "/deleteProduct":
                deleteProduct(request, response);
                break;
            case "/updateProduct":
                updateProduct(request, response);
                break;
            case "/showEditProduct":
                showEditProduct(request, response);
                break;
            case "/showProduct":
                showProduct(request, response);
            default:
                showProductsList(request, response);
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

    private void addProduct(HttpServletRequest request, HttpServletResponse response) {

        try {

            String name = request.getParameter("productName");
            BigDecimal price = new BigDecimal(request.getParameter("productPrice"));
            String desc = request.getParameter("productDesc");
            int categoryId = Integer.parseInt(request.getParameter("category"));

            Category category = new Category();
            List<Category> list = categoryRepo.getEntity();
            for (Category item : list) {
                if (item.getIdCategory() == categoryId) {
                    category = item;
                    break;
                }
            }

            productRepo.addEntity(new Product(name, price.setScale(2,BigDecimal.ROUND_HALF_UP), desc, category));

            List<Category> categories = categoryRepo.getEntity();
            request.setAttribute("category_list", categories);
            RequestDispatcher rd = request.getRequestDispatcher("Admin/addProduct.jsp");
            rd.forward(request, response);

            //response.sendRedirect("Admin/addProduct.jsp");
        } catch (ServletException ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {

        int idProduct = Integer.parseInt(request.getParameter("id"));

        try {
            productRepo.deleteEntity(idProduct);
            //response.sendRedirect("Product");
            
            
            List<Product> products = productRepo.getEntity();
            request.setAttribute("product_list", products);

            RequestDispatcher rd = request.getRequestDispatcher("Admin/productsAdmin.jsp");
            rd.forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) {

        int productId=Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("productName");
        BigDecimal price = new BigDecimal(request.getParameter("productPrice"));
        String desc = request.getParameter("productDesc");
        int categoryId = Integer.parseInt(request.getParameter("category"));
        
        
        try {
            
            productRepo.updateEntity(new Product(productId,name,price,desc,categoryRepo.getEntityById(categoryId)));
            
            response.sendRedirect("Product");
        } catch (IOException ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    private void showEditProduct(HttpServletRequest request, HttpServletResponse response) {
        int idProduct = Integer.parseInt(request.getParameter("id"));

        try {

            Product product = productRepo.getEntityById(idProduct);
            List<Category> category = categoryRepo.getEntity();

            request.setAttribute("product", product);
            request.setAttribute("category_list", category);

            RequestDispatcher rd = request.getRequestDispatcher("Admin/addProduct.jsp");
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void showProductsList(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Product> products = productRepo.getEntity();
            request.setAttribute("product_list", products);

            RequestDispatcher rd = request.getRequestDispatcher("Admin/productsAdmin.jsp");
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showProduct(HttpServletRequest request, HttpServletResponse response) {

        try {

            List<Category> categories = categoryRepo.getEntity();
            request.setAttribute("category_list", categories);

            RequestDispatcher rd = request.getRequestDispatcher("Admin/addProduct.jsp");
            rd.forward(request, response);

        } catch (ServletException ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
