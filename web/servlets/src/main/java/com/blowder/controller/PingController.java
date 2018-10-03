package com.blowder.controller;

import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("pingController")
@RequestScoped
public class PingController {
    public String ping() {
        return "Ping Controller execute ping on " + new Date();
    }
}