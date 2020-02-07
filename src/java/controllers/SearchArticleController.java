/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.ArticleDAO;
import entities.Article;
import entities.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author tuannnh
 */
public class SearchArticleController extends HttpServlet {
    static Logger log = Logger.getLogger(SearchArticleController.class.getName());

    private static final String USER = "index.jsp";
    private static final String ADMIN = "admin.jsp";

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
        String url = USER;
        try {
            //Seperate function by Searching Role
            HttpSession session = request.getSession();
            String role = "";
            User user = (User) session.getAttribute("USER");
            if (user != null) {
                role = user.getRole();
            }
            String searchKeyword = request.getParameter("txtSearchKeyword");
            if(searchKeyword == null){
                searchKeyword = "";
            }
            String pageIndex = request.getParameter("txtPageId");
            String status = request.getParameter("txtStatus");
            System.out.println(status);
            System.out.println(role);
            ArticleDAO dao = new ArticleDAO();
            List<Article> articleList;
            double totalPage;
            if (!role.equals("Admin") || status == null) {
                totalPage = dao.searchByContent(searchKeyword);
                if (pageIndex == null) {

                    articleList = dao.searchByContentOnPage(searchKeyword, 1);
                    request.setAttribute("PAGEID", 1);
                } else {
                    articleList = dao.searchByContentOnPage(searchKeyword, Integer.parseInt(pageIndex));
                    request.setAttribute("PAGEID", Integer.parseInt(pageIndex));

                }

                totalPage = Math.ceil(totalPage / 20.0);

                request.setAttribute("TOTALPAGE", totalPage);
                request.setAttribute("ARTICLES", articleList);

            } else {
                totalPage = dao.searchByContent(searchKeyword, status);
                if (pageIndex == null) {
                    articleList = dao.searchByContentOnPage(searchKeyword, status, 1);
                    request.setAttribute("PAGEID", 1);
                } else {
                    articleList = dao.searchByContentOnPage(searchKeyword, status, Integer.parseInt(pageIndex));
                    request.setAttribute("PAGEID", Integer.parseInt(pageIndex));

                }
                totalPage = Math.ceil(totalPage / 20.0);

                request.setAttribute("TOTALPAGE", (int) totalPage);
                request.setAttribute("ARTICLES", articleList);
                request.setAttribute("STATUS", status);
                url = ADMIN;
            }
            request.setAttribute("SEARCH", searchKeyword);
        } catch (Exception e) {
            log.info("Error at Search Article Controller: " + e.getMessage());
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
