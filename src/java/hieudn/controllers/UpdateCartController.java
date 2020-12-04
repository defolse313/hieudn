/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieudn.controllers;

import CartObj.RoomsCart;
import hieudn.blos.BookingBLO;
import hieudn.entitites.Rooms;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hp
 */
public class UpdateCartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            RoomsCart cart = (RoomsCart) session.getAttribute("CART");
            String id = request.getParameter("txtIdUpdate");
            int cartValue = Integer.parseInt(request.getParameter("txtCartQuantity"));
//            int roomAmount = Integer.parseInt(request.getParameter("txtRoomQuantity"));
            BookingBLO blo = new BookingBLO();
            Rooms r = blo.getRoomById(id);
            boolean valid = true;
            for (Map.Entry<Rooms, Integer> m : cart.getItems().entrySet()) {
                if (m.getKey().getRoomId().equals(r.getRoomId())) {
                    if (cartValue > r.getAmount() || cartValue <= 0) {
                        request.setAttribute("INVALID", "unsuitable amount of rooms");
                        request.setAttribute("ID", m.getKey().getRoomId());
                        valid = false;
                    }
                }
            }
            if (valid) {
                for (Map.Entry<Rooms, Integer> m : cart.getItems().entrySet()) {
                    if (m.getKey().getRoomId().equals(id)) {
                        cart.updateCart(r, cartValue);
                        request.setAttribute("ID1", m.getKey().getRoomId());
                        request.setAttribute("SUCCESS", "your change is applied");
                    }
                }
                session.setAttribute("CART", cart);
                session.setAttribute("TOTAL", cart.getTotal());
            }
        } catch (Exception e) {
            log("ERROR at UpdateCartController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("viewcart.jsp").forward(request, response);
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

}
