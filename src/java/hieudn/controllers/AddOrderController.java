/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieudn.controllers;

import CartObj.RoomsCart;
import hieudn.blos.BookingBLO;
import hieudn.entitites.Booking;
import hieudn.entitites.BookingDetails;
import hieudn.entitites.Coupon;
import hieudn.entitites.Rooms;
import hieudn.entitites.Users;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class AddOrderController extends HttpServlet {

    private static final String INVALID = "viewcart.jsp";
    private static final String SUCCESS = "SearchController";

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
        String url = INVALID;
        try {
            boolean valid = true;
            HttpSession session = request.getSession();
            String uId = (String) session.getAttribute("UID");
            java.util.Date d = new java.util.Date();
            Date d1 = new Date(d.getTime());
            double totalPrice = Double.parseDouble(session.getAttribute("TOTAL").toString());
            String status = "active";
            RoomsCart cart = (RoomsCart) session.getAttribute("CART");
            Users u = new Users(uId);
            List<BookingDetails> orderDetails = new ArrayList();
            String checkin = request.getParameter("txtCheckInDate");
            String checkout = request.getParameter("txtCheckOutDate");
            Date checkInDate = new SimpleDateFormat("yyyy-MM-dd").parse(checkin);
            Date checkOutDate = new SimpleDateFormat("yyyy-MM-dd").parse(checkout);
            Date getDateNow = new SimpleDateFormat("yyyy-MM-dd").parse(new Date().toString());
            if (checkInDate.after(checkOutDate) || checkInDate.after(getDateNow) || checkOutDate.after(getDateNow)) {
                valid = false;

            }
            if (!valid) {
                request.setAttribute("WRONG", "Checkin date must be before Check Out Date");
            } else {
                url = SUCCESS;
                BookingBLO blo = new BookingBLO();
                String couponId = request.getParameter("txtCoupon");
                System.out.println("ci: " + couponId);
                Coupon c = null;
                System.out.println(couponId == null);
                if (couponId != null && !couponId.isEmpty()) {
                    c = blo.getCoupon(couponId);
                    totalPrice = c.getDiscountValue() * Float.parseFloat(session.getAttribute("TOTAL").toString());
                }
                if (cart != null) {
                    orderDetails = new ArrayList();
                    Booking booking = new Booking(null, d1, totalPrice, status, c, u);
                    for (Map.Entry<Rooms, Integer> entry : cart.getItems().entrySet()) {
                        BookingDetails details = new BookingDetails(null, entry.getValue(), entry.getKey().getPrice() * entry.getValue(), checkInDate, checkOutDate);
                        details.setBookId(booking);
                        details.setHotelId(entry.getKey().getHotelId());
                        details.setRoomId(entry.getKey());
                        orderDetails.add(details);
                    }
                    booking.setBookingDetailsList(orderDetails);
                    blo.insert(booking);
                }
            }

        } catch (Exception e) {
            log("Error at Add order Controller: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
