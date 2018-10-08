package com.blowder.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("pingController")
@RequestScoped
public class PingController {
    public String ping() {
        return "Ping Controller execute ping on " + new Date();
    }

    public List<Integer> getNumbers() {
        return Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
    }
}