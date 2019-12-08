package com.phcarvalho.model.service;

import com.phcarvalho.model.entity.Device;
import com.phcarvalho.model.entity.Environment;

import java.util.List;
import java.util.stream.Collectors;

public class DeviceService extends AbstractEntityService<Device> {

    public List<Device> findByEnvironmentId(Environment environment){
        List<Device> userList = findAll(new Device());

        return userList.stream()
                .filter(device -> device.environmentId == environment.id)
                .collect(Collectors.toList());
    }
}
