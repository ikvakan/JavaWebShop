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
import hr.algebra.model.CartItem;
import hr.algebra.model.Category;
import hr.algebra.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
import javax.servlet.jsp.PageContext;

/**
 *
 * @author IgorKvakan
 */
@WebServlet(urlPatterns = {"/showAllProducts", "/showProductsByCategory", "/addToCart", "/showCart", "/removeCartItem", "/updateCart"})
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
            case "/showCart":
                showCart(request, response);
                break;
            case "/removeCartItem":
                removeCartItem(request, response);
                break;
            case "/updateCart":
                updateCartItem(request, response);
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
            
            request.setAttribute("selectedCategory", categoryId);

            RequestDispatcher rd = request.getRequestDispatcher("/productsUser.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UserProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) {

        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int idProduct = Integer.parseInt(request.getParameter("id"));

        
        HttpSession session = request.getSession();

        try {

            Product product = productRepo.getEntityById(idProduct);
            List<CartItem> cartItems = new ArrayList<>();
            CartItem cartItem = new CartItem(quantity, product);

            if (session.getAttribute("productsInCart_session") == null) {

                cartItems.add(cartItem);
                session.setAttribute("productsInCart_session", cartItems);
            } else {
                cartItems = (List<CartItem>) session.getAttribute("productsInCart_session");

                List<CartItem> cartItemsFilter = new ArrayList<>();

                for (CartItem item : cartItems) {
                    if (item.getProduct().getIdProduct() == idProduct) {

                        int quant = item.getQuantity() + quantity;
                        cartItem = new CartItem(quant, product);
                        cartItems.remove(item);
                        cartItemsFilter.add(cartItem);
                        session.setAttribute("productsInCart_session", cartItemsFilter);

                        break;
                    }

                }
                cartItems.add(cartItem);

                session.setAttribute("productsInCart_session", cartItems);

            }

            String msg = "Proizvod: " + product.getName() + " je dodan u košaricu";
            request.setAttribute("productAddedMsg", msg);

            RequestDispatcher rd = request.getRequestDispatcher("showAllProducts");
            rd.forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(UserProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void showCart(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        if (session.getAttribute("productsInCart_session") != null) {
            List<CartItem> cartItems = (List<CartItem>) session.getAttribute("productsInCart_session");

            int totalPrice = 0;
            for (CartItem cartItem : cartItems) {
                totalPrice += cartItem.getTotal().intValue();
            }

            request.setAttribute("cartItems_request", cartItems);
            //request.setAttribute("totalPrice", new BigDecimal(totalPrice));
            session.setAttribute("totalPrice", new BigDecimal(totalPrice));

        }

        try {

            RequestDispatcher rd = request.getRequestDispatcher("/cart.jsp");
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(UserProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void removeCartItem(HttpServletRequest request, HttpServletResponse response) {
        int idProduct = Integer.parseInt(request.getParameter("id"));

        HttpSession session = request.getSession();
        try {

            List<CartItem> listCartItem = (List<CartItem>) session.getAttribute("productsInCart_session");

            for (CartItem item : listCartItem) {
                if (item.getProduct().getIdProduct() == idProduct) {
                    listCartItem.remove(item);
                    break;
                }
            }

            request.setAttribute("productsInCart_session", listCartItem);

            RequestDispatcher rd = request.getRequestDispatcher("showCart");
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(UserProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void updateCartItem(HttpServletRequest request, HttpServletResponse response) {
        int idProduct = Integer.parseInt(request.getParameter("id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        HttpSession session = request.getSession();

        try {

            List<CartItem> cartItems = (List<CartItem>) session.getAttribute("productsInCart_session");

            for (CartItem cartItem : cartItems) {

                if (cartItem.getProduct().getIdProduct() == idProduct) {

                    cartItems.remove(cartItem);
                    CartItem item = new CartItem(quantity, cartItem.getProduct());
                    cartItems.add(item);
                    break;
                }
            }

            request.setAttribute("cartItems_request", cartItems);

            RequestDispatcher rd = request.getRequestDispatcher("showCart");
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(UserProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
