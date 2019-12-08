package com.phcarvalho.model.entity;

public class Device extends Entity {

    public String name;
    public Integer environmentId;

    public Device() {
    }

    public Device(String name, Integer environmentId) {
        this.name = name;
        this.environmentId = environmentId;
    }

    public static Device of(Device device, Environment environment){
        Device newDevice = new Device();

        newDevice.id = device.id;
        newDevice.name = device.name;
        newDevice.environmentId = environment.id;

        return newDevice;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + name;
    }
}
