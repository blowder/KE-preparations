package com.blowder.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class MessageHolder {
    public String getMessage() {
        return "Hello World!!!";
    }
}