/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieudn.controllers;

import hieudn.blos.AccountBLO;
import hieudn.encryptings.SHA256;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hp
 */
public class RegisterController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "index.jsp";
    private static final String INVALID = "register.jsp";

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
            String userId = request.getParameter("txtMemberId");
            boolean valid = true;
            if (!userId.matches("[a-zA-Z0-9]{3,50}@[a-zA-Z0-9]{3,50}[.][a-zA-Z0-9]{2,7}([.][a-zA-Z]{1,2})?")) {
                valid = false;
                request.setAttribute("INVALID", "wrong format of email");
            }
            String fullname = request.getParameter("txtFullname");
            String password = request.getParameter("txtPassword");
            String confirmPassword = request.getParameter("txtConfirmPass");
            if (!confirmPassword.equals(password)) {
                valid = false;
                request.setAttribute("CONFIRM", "confirm correctly your above password!");
            }
            String address  = request.getParameter("txtAddress");
            Date date = new Date();
            java.sql.Date d = new java.sql.Date(date.getTime());
            if (valid) {
                String encrypted = null;
                try {
                    encrypted = SHA256.toHexString(SHA256.getSHA(password));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                AccountBLO blo = new AccountBLO();
                boolean result = blo.insertAccount(userId, fullname, address, encrypted, d, "customer", "active");
                if (result) {
                    url = SUCCESS;
                } else {
                    url = ERROR;
                }
            } else {
                url = INVALID;
                request.setAttribute("MAIL", userId);
                request.setAttribute("FN", fullname);
            }
        } catch (Exception e) {
            log("error at RegisterController: " + e.getMessage());
            if (e.getMessage().contains("duplicate")) {
                url = INVALID;
                request.setAttribute("DUP", "Existed username!");
            }
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
