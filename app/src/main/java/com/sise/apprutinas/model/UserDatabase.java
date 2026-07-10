package com.sise.apprutinas.model;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase {

    public static List<User> users = new ArrayList<>();

    static {
        users.add(new User("Admin", "premium@test.com", "123456", "PREMIUM"));
        users.add(new User("Usuario", "gratuito@test.com", "123456", "GRATUITO"));
    }

    public static class User {
        public String name, email, password, type;

        public User(String name, String email, String password, String type) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.type = type;
        }
    }
}
