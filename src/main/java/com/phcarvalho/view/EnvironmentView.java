package com.phcarvalho.view;

import com.phcarvalho.controller.EnvironmentController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.entity.Environment;
import com.phcarvalho.view.listener.GenericMouseAdapter;
import com.phcarvalho.view.util.DialogUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Random;

public class EnvironmentView extends JPanel {

    private static final String TITLE = "Environment";
//    private static final String EMPTY_LABEL = "-";
    private static final int WIDTH = 270;
    private static final int HEIGHT = 270;
    private static final String ADD = "Add";
    private static final String DELETE = "Delete";
    private static final String DEFAULT_ENVIRONMENT_NAME = "Environment ";
    private static final String ADD_ENVIRONMENT = "Add Environment";
    private static final String LIST = "List";

    private EnvironmentController controller;
    private MainView mainView;
    private DialogUtil dialogUtil;
    private JList<Environment> list;
    private JPanel bottomPanel;
    private JButton addButton;
    private JButton deleteButton;
//    private JButton listButton;

    public EnvironmentView(EnvironmentController controller) {
        super(new GridBagLayout());
        this.controller = controller;
        dialogUtil = DependencyFactory.getSingleton().get(DialogUtil.class);
        list = new JList();
        bottomPanel = new JPanel(new GridBagLayout());
        addButton = new JButton(ADD);
        deleteButton = new JButton(DELETE);
//        listButton = new JButton(LIST);
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
        list.addMouseListener(new GenericMouseAdapter(() -> listAssociation()));
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

        deleteButton.setEnabled(false);
        deleteButton.addActionListener(actionEvent -> delete());
        deleteButton.setPreferredSize(new Dimension(120, 30));
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        bottomPanel.add(deleteButton, gridBagConstraints);

//        listButton.setEnabled(false);
//        listButton.addActionListener(actionEvent -> listAssociation());
//        listButton.setPreferredSize(new Dimension(120, 30));
//        gridBagConstraints.gridx = 2;
//        gridBagConstraints.gridy = 0;
//        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
//        bottomPanel.add(listButton, gridBagConstraints);
    }

    private void listAssociation() {
        Environment selectedEnvironment = list.getSelectedValue();

        if(selectedEnvironment != null)
            controller.listAssociation(selectedEnvironment);
    }

    private void add(){
        String environmentName = getEnvironmentName();

        if(environmentName != null) {
            Environment environment = new Environment(environmentName);

            controller.add(environment);
        }
    }

    private String getEnvironmentName() {
        String environmentName = dialogUtil.showInput("What is the environment name?", ADD_ENVIRONMENT);

        if(environmentName == null)
            return null;

        if(environmentName.isEmpty())
            return DEFAULT_ENVIRONMENT_NAME + new Random().nextInt();

        return environmentName;
    }

    public void delete(){

    }

    public Environment getSelectedEnvironment(){
        return list.getSelectedValue();
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

//    public JButton getListButton() {
//        return listButton;
//    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public JList getList() {
        return list;
    }
}
