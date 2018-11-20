/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.NotesDBException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.AccountService;

/**
 *
 * @author 743953
 */
public class ResetPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uuid = request.getParameter("uuid");

        if (uuid == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("hiddenuuid", request.getParameter("uuid"));

            getServletContext().getRequestDispatcher("/WEB-INF/resetNewPassword.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String url = request.getRequestURL().toString();
        String path = getServletContext().getRealPath("/WEB-INF");
        String action = request.getParameter("action");

        AccountService accountservice = new AccountService();

        if (action != null) {
            if (action.equals("change")) {
                HttpSession session = request.getSession();
                String uuid = (String) session.getAttribute("hiddenuuid");
                String newpassword = request.getParameter("newpassword");
                try {
                    if (accountservice.changePassword(uuid, newpassword)) {
                        request.setAttribute("message", "Password changed successfully.");
                        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                    }
                } catch (NotesDBException ex) {
                    Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            try {
                if (accountservice.resetPassword(email, path, url)) {
                    request.setAttribute("message", "Email sent.");
                    request.setAttribute("readonly", "readonly");
                }
            } catch (Exception ex) {
                Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
    }

}
