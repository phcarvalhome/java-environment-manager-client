package com.phcarvalho.model.configuration;

import com.phcarvalho.model.entity.User;

public class Configuration {

    private static Configuration singleton;

    public static Configuration getSingleton(){

        if(singleton == null)
            singleton = new Configuration();

        return singleton;
    }

    private User user;
    private boolean serverConnected;

    private Configuration() {
        serverConnected = false;
    }

    public User getUser() {

//        if(localUser == null)
//            throw new RuntimeException("The localUser field is null!");

        return user;
    }

    public void setUser(User localUser) {
        this.user = localUser;
    }

    public boolean isServerConnected() {
        return serverConnected;
    }

    public void setServerConnected(boolean serverConnected) {
        this.serverConnected = serverConnected;
    }

}
