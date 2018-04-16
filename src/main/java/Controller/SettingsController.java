package Controller;

import Network.WSGoogleMaps;
import View.KeyDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsController implements ActionListener {

    private KeyDialog view;
    private JFrame ownerView;

    public SettingsController(JFrame ownerDialogView) {

        this.ownerView  = ownerDialogView;
        this.view       = null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Actions action = Actions.valueOf(e.getActionCommand());

        switch (action) {

            case SET_KEY:

                if (view == null)
                    view = new KeyDialog(ownerView, this);

                view.setVisible(true);
                break;

            case UPDATE_KEY:

                if (view == null) {
                    System.err.println("Trying to do an action " + action.toString() + " on a non existing view (KeyDialog)");
                    return;
                }

                WSGoogleMaps.setApiKey(view.getKeyText());
                view.dispose();
                view = null;

            case CANCEL_KEY:

                if (view == null) {
                    System.err.println("Trying to do an action " + action.toString() + " on a non existing view (KeyDialog)");
                    return;
                }

                view.dispose();
                view = null;
                break;
        }
    }

    public void openKeyDialog() {

        if (view != null) view.setVisible(true);
        else view = new KeyDialog(ownerView, this);

    }
}
