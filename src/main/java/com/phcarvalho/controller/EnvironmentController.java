package com.phcarvalho.controller;

import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.DeviceModel;
import com.phcarvalho.model.EnvironmentModel;
import com.phcarvalho.model.UserModel;
import com.phcarvalho.model.entity.Environment;
import com.phcarvalho.view.EnvironmentView;

public class EnvironmentController {

    private EnvironmentView view;
    private EnvironmentModel model;
    private UserController userController;
    private DeviceController deviceController;

    public EnvironmentController() {
        userController = DependencyFactory.getSingleton().get(UserController.class);
        deviceController = DependencyFactory.getSingleton().get(DeviceController.class);
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

    public void add(Environment environment) {
        model.add(environment);
    }

    public void listAssociation(Environment selectedEnvironment) {
        userController.findByEnvironmentId(selectedEnvironment);
        deviceController.findByEnvironmentId(selectedEnvironment);
    }

    public void setView(EnvironmentView view) {
        this.view = view;
    }

    public void setModel(EnvironmentModel model) {
        this.model = model;
    }
}
