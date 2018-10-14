package com.blowder.jsf.image;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

@ManagedBean
public class ImageLoader {
    private Part image;

    public Part getImage() {
        return this.image;
    }

    public void setImage(Part image) {
        this.image = image;
    }

    public String load() {
        if (image == null) {
            return "";
        }
        try (InputStream is = image.getInputStream()) {
            IOUtils.copy(is, new FileOutputStream(resolveImage(UUID.randomUUID().toString() + ".png")));
            return "/images/list";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public List<String> list() {
        File imageFolder = new File(System.getProperty("java.io.tmpdir") + File.separator + "images");
        String[] images = imageFolder.list((file, name) -> name.endsWith(".png"));
        return Optional.ofNullable(images).map(Arrays::asList).orElse(new ArrayList<String>());
    }

    public String getRoot() {
        return System.getProperty("java.io.tmpdir") + File.separator + "images" + File.separator;
    }

    private File resolveImage(String name) {
        return new File(getRoot() + name);
    }
}