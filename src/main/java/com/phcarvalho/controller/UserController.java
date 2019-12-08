package com.phcarvalho.controller;

import com.phcarvalho.model.UserModel;
import com.phcarvalho.model.entity.Environment;
import com.phcarvalho.model.entity.User;
import com.phcarvalho.view.UserView;

import java.util.List;

public class UserController {

    private UserView view;
    private UserModel model;

    public UserController() {
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

    public void add(User user) {
        model.add(user);
    }

    public List<Environment> findEnvironmentList() {
        return model.findEnvironmentList();
    }

    public void update(User oldUser, User newUser) {
        model.update(oldUser, newUser);
    }

    public void findByEnvironmentId(Environment environment) {
        model.findByEnvironmentId(environment);
    }

    public void setView(UserView view) {
        this.view = view;
    }

    public void setModel(UserModel model) {
        this.model = model;
    }
}
