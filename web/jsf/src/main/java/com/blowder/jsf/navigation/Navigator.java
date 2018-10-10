package com.blowder.jsf.navigation;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "navigator")
public class Navigator {

    public String navigate(String ctxPath) {
        return ctxPath + "/second";
    }
}