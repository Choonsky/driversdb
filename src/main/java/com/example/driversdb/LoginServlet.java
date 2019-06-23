package com.example.driversdb;

import com.example.driversdb.dao.UserDAO;
import com.example.driversdb.entity.User;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        try {

            User user = new User();
            user.setUsername(request.getParameter("u"));
            user.setPassword(request.getParameter("p"));

            user = UserDAO.login(user);

            if (user.isValid()) {

                HttpSession oldSession = request.getSession(false);
                if (oldSession != null) {
                    oldSession.invalidate();
                }
                HttpSession newSession = request.getSession(true);
                newSession.setAttribute("currentUser", user);
                // 30 min session expiration
                newSession.setMaxInactiveInterval(30*60);
                if (request.getParameter("rememberMe") == "true") {
                    Cookie userName = new Cookie("user", user.getUsername());
                    // 30 days cookie expiration
                    userName.setMaxAge(30*60*60*24);
                    response.addCookie(userName);
                }
                response.sendRedirect("index.jsp"); //logged-in page
            } else {
                response.sendRedirect("login.jsp?failed=true");
            }
        } catch (Throwable e) {
            System.out.println("EXCEPTION::" + e);
        }
    }
}
