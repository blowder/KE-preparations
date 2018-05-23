package com.security.jaas;

import com.security.jaas.module.NamePrincipal;
import com.security.jaas.module.RolesPrincipal;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.security.Principal;

public class Client {
    public static void main(String[] args) {
        try {
            LoginContext ctx = new LoginContext("ExampleCtx", new NameCallbackHandler());
            ctx.login();
            System.out.print("Logged user: ");
            for (Principal principal : ctx.getSubject().getPrincipals()) {
                if (principal instanceof NamePrincipal)
                    System.out.print(principal.getName() + ":");
                if (principal instanceof RolesPrincipal)
                    System.out.println(((RolesPrincipal) principal).getRoles());
            }
            ctx.logout();
        } catch (LoginException e) {
            System.err.println("Something went wrong. Message is: " + e.getMessage());
        }
    }
}
