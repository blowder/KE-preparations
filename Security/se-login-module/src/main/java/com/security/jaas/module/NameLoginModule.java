package com.security.jaas.module;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class NameLoginModule implements LoginModule {
    private Subject subject;
    private CallbackHandler callbackHandler;
    private List<Principal> principals = new ArrayList<>();

    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> map, Map<String, ?> map1) {
        System.out.println("LoginModule.initialize()");
        if (callbackHandler == null)
            throw new NullPointerException();

        this.subject = subject;
        this.callbackHandler = callbackHandler;
    }

    public boolean login() throws LoginException {
        System.out.println("LoginModule.login()");

        try {
            NameCallback nameCallback = new NameCallback("Enter your name:");
            callbackHandler.handle(new Callback[]{nameCallback});

            if (nameCallback.getName().equals("Bob")) {
                principals.add(new NamePrincipal("Bob"));
                principals.add(new RolesPrincipal(Arrays.asList("User", "Human")));
                return true;
            } else {
                System.err.println("Login failed, try enter username \"Bob\"");
                return false;
            }
        } catch (IOException | UnsupportedCallbackException e) {
            throw new LoginException(e.getMessage());
        }
    }

    public boolean commit() {
        System.out.println("LoginModule.commit()");
        subject.getPrincipals().addAll(principals);
        return true;
    }

    public boolean abort() {
        System.out.println("LoginModule.abort()");
        for (Principal principal : principals) {
            subject.getPrincipals().remove(principal);
        }
        principals.clear();
        subject = null;
        return true;
    }

    public boolean logout() {
        System.out.println("LoginModule.logout()");
        for (Principal principal : principals) {
            subject.getPrincipals().remove(principal);
        }
        principals.clear();
        subject = null;
        return true;
    }
}
