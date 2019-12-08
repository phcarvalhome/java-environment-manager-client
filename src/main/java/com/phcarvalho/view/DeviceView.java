package com.phcarvalho.view;

import com.phcarvalho.controller.DeviceController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.entity.Device;
import com.phcarvalho.model.entity.Environment;
import com.phcarvalho.model.entity.User;
import com.phcarvalho.view.util.DialogUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class DeviceView extends JPanel {

    private static final String TITLE = "Device";
//    private static final String EMPTY_LABEL = "-";
    private static final int WIDTH = 270;
    private static final int HEIGHT = 270;
    private static final String ADD = "Add";
    private static final String DEFAULT_DEVICE_NAME = "Device ";
    private static final String ADD_DEVICE = "Add Device";
    private static final String EDIT = "Edit";
    private static final String EDIT_USER = "Edit User";

    private DeviceController controller;
    private MainView mainView;
    private DialogUtil dialogUtil;
    private JList<Device> list;
    private JPanel bottomPanel;
    private JButton addButton;
    private JButton editButton;

    public DeviceView(DeviceController controller) {
        super(new GridBagLayout());
        this.controller = controller;
        dialogUtil = DependencyFactory.getSingleton().get(DialogUtil.class);
        list = new JList();
        bottomPanel = new JPanel(new GridBagLayout());
        addButton = new JButton(ADD);
        editButton = new JButton(EDIT);
        initialize();
    }

    private void initialize() {
        TitledBorder titledBorder = new TitledBorder(TITLE);

        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        setBorder(titledBorder);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

//        list.setEnabled(false);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(list);

        scrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(scrollPane, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        add(bottomPanel, gridBagConstraints);

        addButton.setEnabled(false);
        addButton.addActionListener(actionEvent -> add());
        addButton.setPreferredSize(new Dimension(120, 30));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        bottomPanel.add(addButton, gridBagConstraints);

        editButton.setEnabled(false);
        editButton.addActionListener(actionEvent -> edit());
        editButton.setPreferredSize(new Dimension(120, 30));
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        bottomPanel.add(editButton, gridBagConstraints);
    }

    private void add(){
        String deviceName = getDeviceName();

        if(deviceName != null) {
            Environment selectedEnvironment = mainView.getEnvironmentView().getSelectedEnvironment();

            if (selectedEnvironment == null){
                dialogUtil.showInformation("Select an environment in the environment list!", ADD_DEVICE);

                return;
            }

            Device device = new Device(deviceName, selectedEnvironment.id);

            controller.add(device);
        }
    }

    private String getDeviceName() {
        String deviceName = dialogUtil.showInput("What is the device name?", ADD_DEVICE);

        if(deviceName == null)
            return null;

        if(deviceName.isEmpty())
            return DEFAULT_DEVICE_NAME + new Random().nextInt();

        return deviceName;
    }

    private void edit() {
        java.util.List<Environment> environmentList = controller.findEnvironmentList();

        if(environmentList.isEmpty()){
            dialogUtil.showInformation("The environment list is empty!", EDIT_USER);

            return;
        }

        Device selectedDevice = list.getSelectedValue();

        if (selectedDevice == null){
            dialogUtil.showInformation("Select a device in the device list!", EDIT_USER);

            return;
        }

        Environment selectedEnvironment = selectEnvironment(environmentList);

        if(selectedEnvironment != null)
            controller.update(selectedDevice, Device.of(selectedDevice, selectedEnvironment));
    }

    private Environment selectEnvironment(List<Environment> environmentList){
        Environment[] environmentArray = environmentList.toArray(new Environment[]{});
        Environment environment = dialogUtil.showInput("What is the environment?", EDIT_USER,
                environmentArray, environmentList.get(0));

        if(environment == null)
            return null;

        return environment;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public JList getList() {
        return list;
    }
}
