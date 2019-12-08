package com.phcarvalho.model;

import com.phcarvalho.controller.DeviceController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.entity.Device;
import com.phcarvalho.model.entity.Environment;
import com.phcarvalho.model.entity.User;
import com.phcarvalho.model.service.DeviceService;
import com.phcarvalho.model.service.EntityRegistryService;
import com.phcarvalho.model.vo.EntityType;
import net.jini.core.entry.Entry;

import javax.swing.*;
import java.util.List;

public class DeviceModel {

    public DeviceController controller;
    public DefaultListModel<Device> list;
    public DeviceService service;
    private EntityRegistryService entityRegistryService;
    private EnvironmentModel environmentModel;

    public DeviceModel(DeviceController controller) {
        this.controller = controller;
        list = new DefaultListModel();
        service = DependencyFactory.getSingleton().get(DeviceService.class);
        entityRegistryService = DependencyFactory.getSingleton().get(EntityRegistryService.class);
        environmentModel = DependencyFactory.getSingleton().get(EnvironmentModel.class);
    }

    public void add(Device device) {
        Integer nextId = entityRegistryService.generateNextId(EntityType.DEVICE);

        device.id = nextId;
        service.put(device);
        list.addElement(device);
    }

    public List<Device> findAll(){
        List<Device> deviceList = service.findAll(new Device());

        list.clear();
        deviceList.forEach(device -> list.addElement(device));

        return deviceList;
    }

    public List<Environment> findEnvironmentList() {
        return environmentModel.findAll();
    }

    public void update(Device oldDevice, Device newDevice) {
        service.update(oldDevice, newDevice);
        findAll();
    }

    public List<Device> findByEnvironmentId(Environment environment) {
        List<Device> deviceList = service.findByEnvironmentId(environment);

        list.clear();
        deviceList.forEach(device -> list.addElement(device));

        return deviceList;
    }

    public DefaultListModel<Device> getList() {
        return list;
    }
}
