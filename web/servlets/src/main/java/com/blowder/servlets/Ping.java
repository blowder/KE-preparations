package com.blowder.servlets;

import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ping")
public class Ping extends HttpServlet {
    private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String result = "<html><body>";
        
        Enumeration<String> names = req.getParameterNames();
        while (names.hasMoreElements()) {
            String element = (String) names.nextElement();
            result += "<p>" + element + "=" + req.getParameter(element) + "</p>";
        }
        result += "</body></html>";
        try (PrintWriter writer = resp.getWriter()) {
            writer.append(result);
        } catch (Exception e) {
        }
        resp.setStatus(201);

    }
}