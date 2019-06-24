package com.example.driversdb;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Logout servlet
 *
 * @author Stanislav Nemirovsky
 */

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 148163813744079388L;
    private final static Logger LOGGER = Logger.getLogger(MyLogger.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MyLogger.init();

        response.setContentType("text/html");
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("JSESSIONID")){
                    break;
                }
            }
        }
        // Закрываем сессию
        HttpSession session = request.getSession(false);
        LOGGER.log( Level.INFO, "Вышел из системы пользователь  " + session.getAttribute("user"));
        if(session != null){
            session.invalidate();
        }
        response.sendRedirect("login.jsp?logout=true");
    }

}