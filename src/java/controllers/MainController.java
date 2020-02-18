/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
public class MainController extends HttpServlet {

    static Logger log = Logger.getLogger(MainController.class);
    private static final String ERROR = "error.jsp";
    private static final String REGISTER = "RegisterController";
    private static final String LOGIN = "LoginController";
    private static final String ACTIVE_USER = "ActiveUserController";
    private static final String LOGOUT = "LogoutController";
    private static final String VIEW_ARTICLE_DETAIL = "ViewDetailController";
    private static final String CREATE_ARTICLE = "CreateArticleController";
    private static final String CREATE_COMMENT = "CreateCommentController";
    private static final String DELETE_ARTICLE = "DeleteArticleController";
    private static final String APPROVE_ARTICLE = "ApproveArticleController";
    private static final String SEARCH_ARTICLE = "SearchArticleController";

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
        request.setAttribute("ERROR_MESSAGE", "There's something wrong occured!");

        try {

            String action = request.getParameter("action");
            switch (action) {
                case "Register":
                    url = REGISTER;//
                    break;
                case "Login":
                    url = LOGIN;//
                    break;
                case "Logout":
                    url = LOGOUT;
                    break;
                case "Active":
                    url = ACTIVE_USER;//
                    break;
                case "SearchArticle":
                    url = SEARCH_ARTICLE;
                    break;
                case "ViewDetail":
                    url = VIEW_ARTICLE_DETAIL;
                    break;
                case "Post Article":
                    url = CREATE_ARTICLE;
                    break;
                case "Approve":
                    url = APPROVE_ARTICLE;
                    break;
                case "Delete":
                    url = DELETE_ARTICLE;
                    break;
                case "Post Comment":
                    url = CREATE_COMMENT;
                    break;
            }
        } catch (Exception e) {
            log.info("Error at Main Controller :" + e.toString());
            request.setAttribute("ERROR_MESSAGE", "There's something wrong occured!");
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
