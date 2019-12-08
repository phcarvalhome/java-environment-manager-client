package com.phcarvalho.model;

import com.phcarvalho.controller.EnvironmentController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.entity.Environment;
import com.phcarvalho.model.service.EntityRegistryService;
import com.phcarvalho.model.service.EnvironmentService;
import com.phcarvalho.model.vo.EntityType;

import javax.swing.*;
import java.util.List;

public class EnvironmentModel {

    private EnvironmentController controller;
    private DefaultListModel<Environment> list;
    private EnvironmentService service;
    private EntityRegistryService entityRegistryService;

    public EnvironmentModel(EnvironmentController controller) {
        this.controller = controller;
        list = new DefaultListModel();
        service = DependencyFactory.getSingleton().get(EnvironmentService.class);
        entityRegistryService = DependencyFactory.getSingleton().get(EntityRegistryService.class);
    }

    public void add(Environment environment) {
        Integer nextId = entityRegistryService.generateNextId(EntityType.ENVIRONMENT);

        environment.id = nextId;
        service.put(environment);
        list.addElement(environment);
    }

    //TODO refactor this to user and device also...
//    private void displayAll(){
//        List<Environment> environmentList = findAll();
//
//        list.clear();
//        environmentList.forEach(environment -> list.addElement(environment));
//    }

    public List<Environment> findAll(){
        List<Environment> environmentList = service.findAll(new Environment());

        list.clear();
        environmentList.forEach(environment -> list.addElement(environment));

        return environmentList;
    }

//    public void listAssociation(Environment selectedEnvironment) {
//        userModel.findByEnvironmentId(selectedEnvironment);
//        deviceModel.findByEnvironmentId(selectedEnvironment);
//    }

    public DefaultListModel<Environment> getList() {
        return list;
    }
}
