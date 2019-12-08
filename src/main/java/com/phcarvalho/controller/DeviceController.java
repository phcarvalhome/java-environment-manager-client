package com.phcarvalho.controller;

import com.phcarvalho.model.DeviceModel;
import com.phcarvalho.model.entity.Device;
import com.phcarvalho.model.entity.Environment;
import com.phcarvalho.model.entity.User;
import com.phcarvalho.view.DeviceView;

import java.util.List;

public class DeviceController {

    private DeviceView view;
    private DeviceModel model;

    public DeviceController() {
    }

    public void initializeList() {
        view.getList().setModel(model.getList());
    }

//    public void add(Player player) {
//        model.add(player);
//    }
//
//    public void removeByCallback(Player player) {
//        view.removeByCallback(player);
//    }
//
//    public int getPlayerIndex(Player player) {
//        return model.getPlayerIndex(player);
//    }
//
//    public void clear() {
//        model.clear();
//    }

    public void add(Device device) {
        model.add(device);
    }

    public List<Environment> findEnvironmentList() {
        return model.findEnvironmentList();
    }

    public void update(Device oldDevice, Device newDevice) {
        model.update(oldDevice, newDevice);
    }

    public void findByEnvironmentId(Environment environment) {
        model.findByEnvironmentId(environment);
    }

    public void setView(DeviceView view) {
        this.view = view;
    }

    public void setModel(DeviceModel model) {
        this.model = model;
    }
}
