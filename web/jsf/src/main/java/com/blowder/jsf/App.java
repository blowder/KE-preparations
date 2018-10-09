package com.blowder.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "helloWorld")
@RequestScoped
public class App {

    @ManagedProperty(value = "#{messageHolder}")
    private MessageHolder messageHolder;

    public void setMessageHolder(MessageHolder holder) {
        this.messageHolder = holder;
    }

    public String getMessage() {
        return messageHolder != null 
        ? messageHolder.getMessage() 
        : "DI does not work";
    }
}
