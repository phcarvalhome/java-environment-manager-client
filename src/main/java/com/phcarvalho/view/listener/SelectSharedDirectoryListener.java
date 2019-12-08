package com.phcarvalho.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectSharedDirectoryListener implements ActionListener {

    private Runnable actionPerformedRunnable;

    public SelectSharedDirectoryListener(Runnable actionPerformedRunnable) {
        this.actionPerformedRunnable = actionPerformedRunnable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actionPerformedRunnable.run();
    }
}
