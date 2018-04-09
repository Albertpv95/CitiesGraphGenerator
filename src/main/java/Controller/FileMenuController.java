package Controller;

import Model.FileManager;
import Model.Singleton;
import View.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * <p>An instance of this class will be able to open and save files
 * as well as remember the working path so the user won't need to
 * put it every time since it is saved.</p>
 *
 *
 * @author Albertpv
 * @version 1.0
 */
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

        Singleton singleton = Singleton.getInstance();

        if (singleton.getCities().isEmpty())
            FileManager.openGraph(workingPath, false);
        else {
            Object []options = {"Yes", "No"};

            int decision = JOptionPane.showOptionDialog(fileChooser,
                    "Would you like to keep the current results obtained?",
                            "Keep current results or override",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]
                    );
            if (decision == 0) FileManager.openGraph(workingPath, true);
            else FileManager.openGraph(workingPath, false);
        }
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
