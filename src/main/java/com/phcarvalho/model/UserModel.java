package com.phcarvalho.model;

import com.phcarvalho.controller.UserController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.entity.Environment;
import com.phcarvalho.model.entity.User;
import com.phcarvalho.model.service.EntityRegistryService;
import com.phcarvalho.model.service.UserService;
import com.phcarvalho.model.vo.EntityType;
import net.jini.core.entry.Entry;

import javax.swing.*;
import java.util.List;

public class UserModel {

    private UserController controller;
    private DefaultListModel<User> list;
    private UserService service;
    private EntityRegistryService entityRegistryService;
    private EnvironmentModel environmentModel;

    public UserModel(UserController controller) {
        this.controller = controller;
        list = new DefaultListModel();
        service = DependencyFactory.getSingleton().get(UserService.class);
        entityRegistryService = DependencyFactory.getSingleton().get(EntityRegistryService.class);
        environmentModel = DependencyFactory.getSingleton().get(EnvironmentModel.class);
    }

    public void add(User user) {
        Integer nextId = entityRegistryService.generateNextId(EntityType.USER);

        user.id = nextId;
        service.put(user);
        list.addElement(user);
    }

    public List<User> findAll(){
        List<User> userList = service.findAll(new User());

        list.clear();
        userList.forEach(user -> list.addElement(user));

        return userList;
    }

    public List<Environment> findEnvironmentList() {
        return environmentModel.findAll();
    }

    public void update(User oldUser, User newUser) {
        service.update(oldUser, newUser);
        findAll();
    }

    public List<User> findByEnvironmentId(Environment environment) {
        List<User> userList = service.findByEnvironmentId(environment);

        list.clear();
        userList.forEach(user -> list.addElement(user));

        return userList;
    }

    public DefaultListModel<User> getList() {
        return list;
    }
}
