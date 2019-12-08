package com.phcarvalho.view;

import com.phcarvalho.controller.MainController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.view.listener.MainWindowAdapter;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    private static final String TITLE = "File Sharing - Client";

    private MainController controller;
    private JPanel mainPanel;
    private JPanel centerPanel;
    private EnvironmentView environmentView;
    private UserView userView;
    private DeviceView deviceView;
    private ConnectionView connectionView;

    public MainView(MainController controller) {
        this.controller = controller;
        mainPanel = new JPanel(new GridBagLayout());
        centerPanel = new JPanel(new GridBagLayout());
        environmentView = DependencyFactory.getSingleton().get(EnvironmentView.class);
        userView = DependencyFactory.getSingleton().get(UserView.class);
        deviceView = DependencyFactory.getSingleton().get(DeviceView.class);
        connectionView = DependencyFactory.getSingleton().get(ConnectionView.class);
        initialize();
    }

    private void initialize() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        mainPanel.add(centerPanel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        centerPanel.add(environmentView, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        centerPanel.add(userView, gridBagConstraints);

        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        centerPanel.add(deviceView, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        mainPanel.add(connectionView, gridBagConstraints);

        addWindowListener(new MainWindowAdapter(() -> disconnect()));
        setTitle(TITLE);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    private void disconnect() {
        connectionView.disconnect();
    }

    public EnvironmentView getEnvironmentView() {
        return environmentView;
    }

    public UserView getUserView() {
        return userView;
    }

    public DeviceView getDeviceView() {
        return deviceView;
    }

    public ConnectionView getConnectionView() {
        return connectionView;
    }
}
