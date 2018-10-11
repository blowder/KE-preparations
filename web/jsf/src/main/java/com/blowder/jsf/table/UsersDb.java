package com.blowder.jsf.table;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "usersDb")
public class UsersDb {
    private List<User> users;

    public UsersDb() {
        this.users = new ArrayList<>();
        this.users.add(new User("John", "Malkovich", "Actor"));
        this.users.add(new User("Ivan", "Ivanov", "Builder"));
        this.users.add(new User("Jane", "Philds", "Teacher"));
    }

    public List<User> getUsers() {
        return this.users;
    }
}