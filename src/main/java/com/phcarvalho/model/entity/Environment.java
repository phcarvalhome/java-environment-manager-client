package com.phcarvalho.model.entity;

public class Environment extends Entity {

    public String name;

    public Environment() {
    }

    public Environment(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + name;
    }
}
