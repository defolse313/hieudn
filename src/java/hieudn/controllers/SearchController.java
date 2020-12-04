/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieudn.controllers;

import hieudn.blos.BookingBLO;
import hieudn.entitites.BookingDetails;
import hieudn.entitites.Hotel;
import hieudn.entitites.Rooms;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hp
 */
public class SearchController extends HttpServlet {

    private static final String SUCCESS = "index.jsp";
    private static final String ERROR = "error.jsp";

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
        String url = ERROR;
        try {
            int currentPage;
            String search = request.getParameter("txtSearch");
            if (search == null) {
                search = "";
            }
            if (request.getParameter("pageIDPaging") == null) {
                currentPage = 1;
            } else {
                currentPage = Integer.parseInt(request.getParameter("pageIDPaging"));
            }
            int pageMaxSize = 1;
            BookingBLO blo = new BookingBLO();
            String checkin = request.getParameter("txtCheckin");
            String checkout = request.getParameter("txtCheckout");
            String amount = request.getParameter("txtAmount");
            List<Hotel> result = blo.searchNameAndArea(search, currentPage, pageMaxSize);
            System.out.println(result);
            List finalResult = null;
            List<BookingDetails> bookedResult;
            if (checkin != null && checkout != null) {
                Date inDate = new SimpleDateFormat("yyyyy-MM-dd").parse(checkin);
                Date outDate = new SimpleDateFormat("yyyy-MM-dd").parse(checkout);
                finalResult = new ArrayList();
                for (int i = 0; i < result.size(); i++) {
                    System.out.println("Hihi " + i);
                    if (Integer.parseInt(amount) <= blo.getAmountRoomsHotel(result.get(i))) {
                        for (int j = 0; j < result.get(i).getRoomsList().size(); j++) {
                            bookedResult = blo.getBookedAmount(result.get(i).getRoomsList().get(j));
                            if (bookedResult == null) {
                                finalResult.add(result.get(i));
                            } else {
                                for (int k = 0; k < bookedResult.size(); k++) {
                                    if (bookedResult.get(k).getCheckIn().after(outDate) && bookedResult.get(k).getCheckOut().before(inDate)) {
                                        finalResult.add(result.get(i));
                                    } else {
                                        if (Integer.parseInt(amount) <= (blo.getAmountRoomsHotel(result.get(i)) - blo.getAmountBookedRoomsHotel(result.get(i)))) {
                                            finalResult.add(result.get(i));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (finalResult != null) {
                    request.setAttribute("INFO", result);
                    request.setAttribute("CURRENT_PAGE", currentPage);
                    request.setAttribute("HOTEL_COUNT", blo.getAmountHotelSearch(search, pageMaxSize));
                    url = SUCCESS;
                }
            } else {
//                Date startDate = new SimpleDateFormat("YYYY/MM/dd").parse("1999/01/01");
//                Date endDate = new SimpleDateFormat("YYYY/MM/dd").parse("1999/01/02");
                if (result != null) {
                    request.setAttribute("INFO", result);
                    request.setAttribute("CURRENT_PAGE", currentPage);
                    request.setAttribute("HOTEL_COUNT", blo.getAmountHotelSearch(search, pageMaxSize));
                    url = SUCCESS;
                }
            }

//            String checkin;
//            if (request.getParameter("txtCheckin") != null) {
//                checkin = request.getParameter("txtCheckin");
//            } else {
//                java.util.Date date1 = Calendar.getInstance().getTime();
//                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
//                checkin = dateFormat.format(date1);
//            }
//            String checkout;
//            if (request.getParameter("txtCheckout") != null) {
//                checkout = request.getParameter("txtCheckout");
//            } else {
//                java.util.Date date1 = Calendar.getInstance().getTime();
//                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
//                checkout = dateFormat.format(date1);
//            }
//            java.util.Date checkinDate = new SimpleDateFormat("YYYY/MM/dd").parse(checkin);
            //            java.util.Date checkoutDate = new SimpleDateFormat("YYYY/MM/dd").parse(checkout)
//            System.out.println(blo.findRoom(startDate, endDate, 2));
//            for (int i = 0; i < result.size(); i++) {
//                for (int j = 0; j < result.get(i).getRoomsList().size(); j++) {
//                    if ((result.get(i).getRoomsList().get(j).getCheckinDate().compareTo(checkinDate) >= 0 && result.get(i).getRoomsList().get(j).getCheckinDate().compareTo(checkinDate) >= 0) || (result.get(i).getRoomsList().get(j).getCheckoutDate().compareTo(checkoutDate) < 0 && result.get(i).getRoomsList().get(j).getCheckoutDate().compareTo(checkoutDate) < 0)) {
//                        request.setAttribute("DATE", resultRoom.add(result.get(i).getRoomsList().get(j)));
//                    }
//                }
//            }
//            List<Rooms> list = new BookingBLO.findByHotelID(string hotelID
//            );
//            List<Rooms> amounts;
//            List<Rooms> dato;
//            for (Hotel hotel : result) {
//
//            }
            request.setAttribute("PAGEID", 1);

        } catch (Exception e) {
            log("Error at Search Controller: " + e.getMessage());
            e.printStackTrace();
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
