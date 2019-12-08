package com.phcarvalho.view;

import com.phcarvalho.controller.ConnectionController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.entity.Environment;
import com.phcarvalho.model.entity.User;
import com.phcarvalho.view.util.DialogUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.Random;

public class ConnectionView extends JPanel {

    private static final String DEFAULT_USER_NAME = "User ";
    private static final String CONNECT_TO_SERVER = "Connect To Server";
    private static final String EMPTY_LABEL = "-";

    private ConnectionController controller;
    private MainView mainView;
    private DialogUtil dialogUtil;
    private JButton connectToServerButton;
//    private JLabel clientIdLabel;
//    private JLabel clientIdValueLabel;

    public ConnectionView(ConnectionController controller) {
        super(new GridBagLayout());
        this.controller = controller;
        dialogUtil = DependencyFactory.getSingleton().get(DialogUtil.class);
        connectToServerButton = new JButton(CONNECT_TO_SERVER);
//        clientIdLabel = new JLabel("User name:");
//        clientIdValueLabel = new JLabel(EMPTY_LABEL);
        initialize();
    }

    private void initialize() {
        TitledBorder titledBorder = new TitledBorder("Connection");

        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        setBorder(titledBorder);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        connectToServerButton.addActionListener(actionEvent -> connectToServer());
        connectToServerButton.setPreferredSize(new Dimension(160, 30));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        add(connectToServerButton, gridBagConstraints);

//        gridBagConstraints.gridx = 1;
//        gridBagConstraints.gridy = 1;
//        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
//        add(clientIdLabel, gridBagConstraints);
//
//        clientIdValueLabel.setPreferredSize(new Dimension(480, 40));
//        gridBagConstraints.gridx = 2;
//        gridBagConstraints.gridy = 1;
//        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
//        add(clientIdValueLabel, gridBagConstraints);
    }

    private void connectToServer(){
        String userName = getUserName();

        if(userName != null) {
            Environment selectedEnvironment = mainView.getEnvironmentView().getSelectedEnvironment();
            Integer environmentId = selectedEnvironment != null ? selectedEnvironment.id : null;

//            if (selectedEnvironment == null){
//                dialogUtil.showInformation("Select an environment in the environment list!", CONNECT_TO_SERVER);
//
//                return;
//            }

            User user = new User(userName, environmentId);

            controller.connectToServer(user);
            connectToServerByCallback();
        }
    }

    private String getUserName() {
        String userName = dialogUtil.showInput("What is the user name?", CONNECT_TO_SERVER);

        if(userName == null)
            return null;

        if(userName.isEmpty())
            return DEFAULT_USER_NAME + new Random().nextInt();

        return userName;
    }

    public void connectToServerByCallback() {
//        User localUser = Configuration.getSingleton().getLocalUser();

//        clientIdValueLabel.setText(localUser.id);
        connectToServerButton.setEnabled(false);
//        mainView.getSharedFileView().getSelectSharedFileButton().setEnabled(true);
//        mainView.getSharedFileView().getSearchSharedFileField().setEnabled(true);
//        mainView.getSharedFileView().getSelectSharedFileButton().setEnabled(true);
        mainView.getEnvironmentView().getAddButton().setEnabled(true);
//        mainView.getEnvironmentView().getDeleteButton().setEnabled(true);
//        mainView.getEnvironmentView().getListButton().setEnabled(true);
        mainView.getUserView().getAddButton().setEnabled(true);
        mainView.getUserView().getEditButton().setEnabled(true);
        mainView.getDeviceView().getAddButton().setEnabled(true);
        mainView.getDeviceView().getEditButton().setEnabled(true);
//        dialogUtil.showInformation("The server is connected!", SERVER_CONNECTION);
    }

    public void disconnect() {

        try {
            controller.disconnect();
        } catch (RemoteException e) {
            //TODO handle it
            e.printStackTrace();
        }
    }

//    public void clear() {
//        clientIdValueLabel.setText(EMPTY_LABEL);
//    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }
}
