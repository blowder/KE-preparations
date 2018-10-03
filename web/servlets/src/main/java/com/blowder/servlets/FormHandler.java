package com.blowder.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/form-handler")
public class FormHandler extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = Optional.ofNullable(req.getParameter("username"))
                .filter(p->!p.isEmpty())
                .orElse("Anonymous");

        String result = "<html><body>Your name is: " + username + "<html/><body/>";
        try (PrintWriter writer = resp.getWriter()) {
            writer.append(result);
        } catch (Exception ignored) {
        }

    }


}
