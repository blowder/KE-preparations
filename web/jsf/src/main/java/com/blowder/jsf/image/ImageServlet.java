package com.blowder.jsf.image;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

@WebServlet("/images")
public class ImageServlet extends HttpServlet {
    private static final long serialVersionUID = -6247270176734420071L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imageName = req.getParameter("name");
        if (!hasValidName(imageName)) {
            resp.setStatus(404);
            System.err.println("Image name is empty or invalid");
            return;
        }
        File imageFile = resolveImage(imageName);
        if (!imageFile.exists()) {
            resp.setStatus(404);
            System.err.println("Image with name=" + imageName + " does not exist");
            return;
        }
        resp.setStatus(200);
        resp.addHeader("Content-Type", "image/png");
        try (InputStream is = new FileInputStream(imageFile); OutputStream os = resp.getOutputStream()) {
            IOUtils.copy(is, os);
        }
    }

    private File resolveImage(String name) {
        return new File(System.getProperty("java.io.tmpdir") + File.separator + "images" + File.separator + name);
    }

    private boolean hasValidName(String name) {
        return name != null && !name.isEmpty() && name.toLowerCase().endsWith(".png");
    }
}