/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package Controller;

import Model.FileManager;
import Model.Singleton;
import View.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class FileMenuController implements ActionListener {

    private MainView view;
    private JFileChooser fileChooser;

    private String workingPath;

    public FileMenuController(MainView view) {

        this.view = view;
        this.fileChooser = new JFileChooser();
        this.workingPath = "";
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        Actions action = Actions.valueOf(e.getActionCommand());

        try {

            switch (action) {

                case OPEN_FILE:

                    open();

                    break;

                case SAVE_FILE:

                    save();

                    break;

                case SAVE_AS_FILE:

                    saveAs();
                    save();

                    break;

                case ABOUT:

                    break;
            }

            JOptionPane.showMessageDialog(
                    null,
                    "Operation done successfully!"
            );

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Operation failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void open() throws IOException {

        fileChooser.setDialogTitle("Specify a file to open");
        int userSelection = fileChooser.showOpenDialog(view);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            workingPath = fileToSave.getAbsolutePath();
        }

        FileManager.openGraph(workingPath, false);
    }

    private void saveAs() {

        fileChooser.setDialogTitle("Specify a file to save");
        int userSelection = fileChooser.showSaveDialog(view);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            workingPath = fileToSave.getAbsolutePath();
        }
    }

    private void save() throws IOException {

        if (workingPath.isEmpty()) {

            saveAs();
        }

        Singleton singleton = Singleton.getInstance();

        FileManager.saveGraph(workingPath, singleton.getCities(), singleton.getConnections());
    }
}
