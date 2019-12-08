package com.phcarvalho.model.service;

import com.phcarvalho.model.entity.Environment;
import com.phcarvalho.model.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserService extends AbstractEntityService<User> {

    public List<User> findByEnvironmentId(Environment environment){
        List<User> userList = findAll(new User());

        return userList.stream()
                .filter(user -> user.environmentId == environment.id)
                .collect(Collectors.toList());
    }
}
