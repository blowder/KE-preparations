package com.security.jaas.module;

import java.security.Principal;
import java.util.Objects;

public class NamePrincipal implements Principal {
    private final String name;

    public NamePrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NamePrincipal that = (NamePrincipal) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
