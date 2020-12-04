/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieudn.controllers;

import hieudn.blos.AccountBLO;
import hieudn.encryptings.SHA256;
import hieudn.entitites.Users;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hp
 */
public class LoginController extends HttpServlet {

    private static final String INVALID = "login.jsp";
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
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            boolean valid = true;
            String encrypted = null;
            if (!username.matches("[a-zA-Z0-9]{3,50}@[a-zA-Z0-9]{3,50}[.][a-zA-Z0-9]{2,7}([.][a-zA-Z]{1,2})?")) {
                valid = false;
            }
            if (valid) {
                try {
                    encrypted = SHA256.toHexString(SHA256.getSHA(password));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                AccountBLO blo = new AccountBLO();
                boolean result = blo.checkLogin(username, encrypted);
                if (!result) {
                    url = INVALID;
                    request.setAttribute("INVALID1", "Invalid Email Or Password!");
                } else {
                    url = SUCCESS;
                    HttpSession session = request.getSession();
                    Users user = new Users();
                    user = blo.loginPage(username);
                    if (user.getRole().equals("admin") || user.getRole().equals("customer")) {
                        session.setAttribute("ROLE", user.getRole());
                        session.setAttribute("ACCOUNT", user);
                        session.setAttribute("USERNAME", user.getUserName());
                        session.setAttribute("UID", user.getUserId());
                    }
                }
            } else {
                url = INVALID;
                request.setAttribute("INVALIDDD", "wrong format of email");
                request.setAttribute("IDUSERNAME", username);
            }
        } catch (Exception e) {
            log("Error at Login Controller: " + e.getMessage());
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
