/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import daos.UserDAO;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author tuannnh
 */
public class RegisterController extends HttpServlet {

    private static final String ERROR = "register.jsp";
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
        String email = "";
        try {
            email = request.getParameter("txtEmail");
            if (email == null) {
                request.setAttribute("ERROR_MESSAGE", "There is nothing to active");
            } else {
                String name = request.getParameter("txtName");
                String password = DigestUtils.sha256Hex(request.getParameter("txtPassword"));
                String token = UUID.randomUUID().toString().replace("-", "");
                UserDAO dao = new UserDAO();
                boolean result = dao.register(email, name, password, token);
                if (result) {
                    sendMail(email, token, request);
                    url = SUCCESS;
                }
            }

        } catch (Exception e) {
            log("Error at Register Controller :" + e.getMessage());
            if (e.getMessage().contains("Duplicate")) {
                request.setAttribute("ERROR_MESSAGE", "*This email: " + email + "is existed!");
            }
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

    private void sendMail(String receiver, String token, HttpServletRequest request) throws Exception {
        Email from = new Email("system@localhost");
        String subject = "Verification Email";
        Email to = new Email(receiver);
        Content content = new Content("text/html", "This is your verification link to active your account. Please click this link: <a href='http://localhost:9000/LAB231_Blog/MainController?action=Active&txtEmail=" + receiver + "&txtToken=" + token + "'>Verification Link</a>");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request req = new Request();

        req.setMethod(Method.POST);
        req.setEndpoint("mail/send");
        req.setBody(mail.build());
        sg.api(req);
        if (receiver != null) {
            request.setAttribute("SUCCESS_MESSAGE", "The verification email have been sent to your email: " + receiver + ". Please check your inbox and click the supplied link to active your account.");
            
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
