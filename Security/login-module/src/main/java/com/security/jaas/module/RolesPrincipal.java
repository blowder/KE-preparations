package com.security.jaas.module;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

public class RolesPrincipal implements Principal {
    private final List<String> roles;

    public RolesPrincipal(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String getName() {
        return "roles";
    }

    public List<String> getRoles() {
        return roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolesPrincipal that = (RolesPrincipal) o;
        return Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {

        return Objects.hash(roles);
    }
}
