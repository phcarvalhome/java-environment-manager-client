package com.phcarvalho.model;

import com.phcarvalho.controller.ConnectionController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.configuration.Configuration;
import com.phcarvalho.model.entity.Device;
import com.phcarvalho.model.entity.Environment;
import com.phcarvalho.model.entity.User;
import com.phcarvalho.model.service.UserService;
import com.phcarvalho.model.service.delegate.JavaSpaceDelegate;

import java.rmi.RemoteException;

public class ConnectionModel {

    private ConnectionController controller;
    public JavaSpaceDelegate javaSpaceDelegate;
    private EnvironmentModel environmentModel;
    private UserModel userModel;
    private DeviceModel deviceModel;

    public ConnectionModel(ConnectionController controller) {
        this.controller = controller;
        javaSpaceDelegate = DependencyFactory.getSingleton().get(JavaSpaceDelegate.class);
        environmentModel = DependencyFactory.getSingleton().get(EnvironmentModel.class);
        userModel = DependencyFactory.getSingleton().get(UserModel.class);
        deviceModel = DependencyFactory.getSingleton().get(DeviceModel.class);
    }

    public void connectToServer(User user) {
        Configuration.getSingleton().setUser(user);
        javaSpaceDelegate.locateService();
//        userModel.add(user);
        environmentModel.findAll();
//        userModel.findAll();
//        deviceModel.findAll();
    }

    public void disconnect() throws RemoteException {
//        Game gameSelected = Configuration.getSingleton().getGameSelected();
//
//        if(gameSelected != null){
//            Player player = Configuration.getSingleton().getPlayer();
//
//            commandTemplateFactory.getMain().addPlayer(new AddPlayerCommand(player, gameSelected, true));
//        }
////        else
////        commandTemplateFactory.getConnection().disconnect(new DisconnectCommand()); //TODO precisa disso mesmo aqui (disconnect por fechar frame do jogo)...
    }

//    public void disconnectByCallback(DisconnectCommand disconnectCommand) {
//        controller.disconnectByCallback(disconnectCommand);
//    }
}
