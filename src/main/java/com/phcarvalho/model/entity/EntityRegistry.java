package com.phcarvalho.model.entity;

public class EntityRegistry extends Entity {

    public String entityType;
    public Integer counter;

    public EntityRegistry() {
    }

    public EntityRegistry(String entityType, Integer counter) {
        this.entityType = entityType;
        this.counter = counter;
    }

    @Override
    public String toString() {
        return "EntityRegistry{" +
                "entityType='" + entityType + '\'' +
                ", counter=" + counter +
                '}';
    }
}

