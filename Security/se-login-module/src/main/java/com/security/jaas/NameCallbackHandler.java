package com.security.jaas;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NameCallbackHandler implements CallbackHandler {
    public void handle(Callback[] callbacks) throws IOException {
        for (Callback callback : callbacks) {
            if (callback instanceof NameCallback) {
                NameCallback castedCallback = (NameCallback) callback;
                System.out.println(castedCallback.getPrompt());
                castedCallback.setName(new BufferedReader(new InputStreamReader(System.in)).readLine());
            }
        }
    }
}
