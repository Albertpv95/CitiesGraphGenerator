
package View;

import Controller.Actions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class KeyDialog extends JDialog {

    private static final int DIM_WIDTH  = 350;
    private static final int DIM_HEIGHT = 80;

    private JTextField keyField;

    public KeyDialog(JFrame owner, ActionListener listener) {
        super(owner);

        this.setTitle("Set the API Key");
        this.setLayout(new BorderLayout());

        keyField = new JTextField();
        this.add(keyField, BorderLayout.CENTER);

        JPanel jpButtons = new JPanel();
        jpButtons.setLayout(new GridLayout(1, 1));

        JButton jbAccept = new JButton("Accept");
        jbAccept.addActionListener(listener);
        jbAccept.setActionCommand(Actions.UPDATE_KEY.toString());

        JButton jbCancel = new JButton("Cancel");
        jbCancel.addActionListener(listener);
        jbCancel.setActionCommand(Actions.CANCEL_KEY.toString());

        jpButtons.add(jbCancel);
        jpButtons.add(jbAccept);
        this.add(jpButtons, BorderLayout.SOUTH);

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(DIM_WIDTH, DIM_HEIGHT);
        this.setResizable(false);
    }

    public String getKeyText() {

        return keyField.getText();
    }
}
