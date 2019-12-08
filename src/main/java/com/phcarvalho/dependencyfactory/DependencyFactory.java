package com.phcarvalho.dependencyfactory;

import com.phcarvalho.controller.*;
import com.phcarvalho.model.*;
import com.phcarvalho.model.service.DeviceService;
import com.phcarvalho.model.service.EntityRegistryService;
import com.phcarvalho.model.service.EnvironmentService;
import com.phcarvalho.model.service.UserService;
import com.phcarvalho.model.service.delegate.JavaSpaceDelegate;
import com.phcarvalho.view.*;
import com.phcarvalho.view.util.DialogUtil;

import java.util.HashMap;
import java.util.Map;

public class DependencyFactory {

    private static DependencyFactory singleton;

    public static DependencyFactory getSingleton(){

        if(singleton == null)
            singleton = new DependencyFactory();

        return singleton;
    }

//    private ICommunicationStrategy communicationStrategyFactory;
    private Map<Class<?>, Object> dependencyMap;

    private DependencyFactory() {
        dependencyMap = new HashMap<>();
    }

    public void build(){
        dependencyMap.put(DialogUtil.class, new DialogUtil());
        dependencyMap.put(JavaSpaceDelegate.class, new JavaSpaceDelegate());
        dependencyMap.put(EnvironmentService.class, new EnvironmentService());
        dependencyMap.put(UserService.class, new UserService());
        dependencyMap.put(DeviceService.class, new DeviceService());
        dependencyMap.put(EntityRegistryService.class, new EntityRegistryService());
//        dependencyMap.put(SocketConnectionStrategy.class, new SocketConnectionStrategy());
//        dependencyMap.put(IConnectionStrategy.class, communicationStrategyFactory.buildConnectionStrategy());
//        dependencyMap.put(ICommandTemplateFactory.class, communicationStrategyFactory.buildCommandTemplateFactory());
//        dependencyMap.put(StartingPositionConfigurationRegistry.class, new StartingPositionConfigurationRegistry());

//        buildBoardMVC();
        buildEnvironmentMVC();
        buildUserMVC();
        buildDeviceMVC();
        buildConnectionMVC();
//        buildSharedFileMVC();
//        buildGameMVC();
//        buildChatMVC();
        buildMainMVC();

//        get(CommandInvoker.class).buildCommandObserverMap();
////        get(IConnectionStrategy.class).setCommandInvoker(get(CommandInvoker.class));
//
//        dependencyMap.put(BoardPositionTransferHandler.class, new BoardPositionTransferHandler());
    }

//    private void buildBoardMVC() {
//        BoardController boardController = new BoardController();
//        BoardView boardView = new BoardView(boardController);
//        BoardModel boardModel = new BoardModel(boardController);
//
//        boardController.setView(boardView);
//        boardController.setModel(boardModel);
//        dependencyMap.put(BoardView.class, boardView);
//        dependencyMap.put(BoardModel.class, boardModel);

//    }

    private void buildConnectionMVC() {
        ConnectionController connectionController = new ConnectionController();
        ConnectionView connectionView = new ConnectionView(connectionController);
        ConnectionModel connectionModel = new ConnectionModel(connectionController);

        connectionController.setView(connectionView);
        connectionController.setModel(connectionModel);
        dependencyMap.put(ConnectionView.class, connectionView);
        dependencyMap.put(ConnectionModel.class, connectionModel);
    //        get(IConnectionHandlerStrategy.class).setConnectionModel(connectionModel);
    }

    private void buildEnvironmentMVC() {
        EnvironmentController environmentController = new EnvironmentController();
        EnvironmentView environmentView = new EnvironmentView(environmentController);
        EnvironmentModel environmentModel = new EnvironmentModel(environmentController);

        environmentController.setView(environmentView);
        environmentController.setModel(environmentModel);
        environmentController.initializeList();
        dependencyMap.put(EnvironmentView.class, environmentView);
        dependencyMap.put(EnvironmentModel.class, environmentModel);
//        get(IConnectionHandlerStrategy.class).setDownloadedFileModel(downloadedFileModel);
    }

    private void buildUserMVC() {
        UserController userController = new UserController();
        UserView userView = new UserView(userController);
        UserModel userModel = new UserModel(userController);

        userController.setView(userView);
        userController.setModel(userModel);
        userController.initializeList();
        dependencyMap.put(UserView.class, userView);
        dependencyMap.put(UserModel.class, userModel);
//        get(IConnectionHandlerStrategy.class).setDownloadedFileModel(downloadedFileModel);
    }

    private void buildDeviceMVC() {
        DeviceController deviceController = new DeviceController();
        DeviceView deviceView = new DeviceView(deviceController);
        DeviceModel deviceModel = new DeviceModel(deviceController);

        deviceController.setView(deviceView);
        deviceController.setModel(deviceModel);
        deviceController.initializeList();
        dependencyMap.put(DeviceView.class, deviceView);
        dependencyMap.put(DeviceModel.class, deviceModel);
//        get(IConnectionHandlerStrategy.class).setDownloadedFileModel(downloadedFileModel);
    }

//    private void buildSharedFileMVC() {
//        SharedFileController sharedFileController = new SharedFileController();
//        SharedFileView sharedFileView = new SharedFileView(sharedFileController);
//        SharedFileModel sharedFileModel = new SharedFileModel(sharedFileController);
//
//        sharedFileController.setView(sharedFileView);
//        sharedFileController.setModel(sharedFileModel);
//        sharedFileController.initializeList();
//        dependencyMap.put(SharedFileView.class, sharedFileView);
//        dependencyMap.put(SharedFileModel.class, sharedFileModel);
////        get(IConnectionHandlerStrategy.class).setSharedFileModel(sharedFileModel);
//    }
//    private void buildGameMVC() {
//        GameController gameController = new GameController();
//        GameView gameView = new GameView(gameController);
//        GameModel gameModel = new GameModel(gameController);
//
//        gameController.setView(gameView);
//        gameController.setModel(gameModel);
//        gameController.initializeList();
//        dependencyMap.put(GameView.class, gameView);
//        dependencyMap.put(GameModel.class, gameModel);
////        get(IConnectionHandlerStrategy.class).setGameModel(gameModel);

//    }

//    private void buildChatMVC() {
//        ChatController chatController = new ChatController();
//        ChatView chatView = new ChatView(chatController);
//        ChatModel chatModel = new ChatModel(chatController);
//
//        chatController.setView(chatView);
//        chatController.setModel(chatModel);
//        dependencyMap.put(ChatView.class, chatView);
//        dependencyMap.put(ChatModel.class, chatModel);
//    }

    private void buildMainMVC() {
        MainController mainController = new MainController();
        MainView mainView = new MainView(mainController);
        MainModel mainModel = new MainModel(mainController);

        mainController.setView(mainView);
        mainController.setModel(mainModel);
        dependencyMap.put(MainView.class, mainView);
        dependencyMap.put(MainModel.class, mainModel);
        get(DialogUtil.class).setMainView(mainView);
//        get(BoardView.class).setMainView(mainView);
//        get(SharedFileView.class).setMainView(mainView);
        get(EnvironmentView.class).setMainView(mainView);
        get(UserView.class).setMainView(mainView);
        get(DeviceView.class).setMainView(mainView);
//        get(GameView.class).setMainView(mainView);
        get(ConnectionView.class).setMainView(mainView);
//        get(ChatView.class).setMainView(mainView);
////        get(IConnectionStrategy.class).setMainModel(mainModel);
//        //TODO talvez fazer o set de cada model que foi colocado lá...
    }

    public <T> T get(Class<T> classType){
        Object dependency = dependencyMap.get(classType);

        if(dependency == null)
            throw new RuntimeException("The dependency is null! Type: " + classType);

        return (T) dependency;
    }

//    public void setCommunicationStrategyFactory(ICommunicationStrategy communicationStrategyFactory) {
//        this.communicationStrategyFactory = communicationStrategyFactory;
//    }
}
