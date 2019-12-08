package com.phcarvalho.model.entity;

public class User extends Entity {

    public String name;
    public Integer environmentId;

    public User() {
    }

    public User(String name, Integer environmentId) {
        this.name = name;
        this.environmentId = environmentId;
    }

    public static User of(User user, Environment environment){
        User newUser = new User();

        newUser.id = user.id;
        newUser.name = user.name;
        newUser.environmentId = environment.id;

        return newUser;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + name;
    }
}
