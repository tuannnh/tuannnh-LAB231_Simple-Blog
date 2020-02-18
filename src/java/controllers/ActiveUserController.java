/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.UserDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author tuannnh
 */
public class ActiveUserController extends HttpServlet {

    static Logger log = Logger.getLogger(ActiveUserController.class);

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "success.jsp";

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
        String url = ERROR;
        try {
            String token = request.getParameter("txtToken");
            String email = request.getParameter("txtEmail");
            UserDAO dao = new UserDAO();
            String status = dao.checkStatus(email);
            if (status.equals("New")) {
                if (dao.activeUser(token)) {
                    request.setAttribute("SUCCESS_TYPE", "Active User");
                    request.setAttribute("SUCCESS_MESSAGE", "Your account: <strong>" + email + "</strong> has been activated! You can post article and comment now. Have fun!");
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR_MESSAGE", "The account <strong>" + email + "</strong> is deleted!");
                }
            } else if (status.equals("Deleted")) {
                request.setAttribute("ERROR_MESSAGE", "The token is not available");
            } else {
                request.setAttribute("ERROR_MESSAGE", "The account <strong>" + email + "</strong> is already actived");
            }
        } catch (Exception e) {
            log.info("Error at Active User Controller: " + e.getMessage());
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
