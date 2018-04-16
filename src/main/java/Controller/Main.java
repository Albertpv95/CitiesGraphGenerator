package Controller;

import View.MainView;

import javax.swing.*;

/**
 * @author Albertpv
 * @version 1.0
 */
public class Main {

    public static void main(String []args) {


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                MainView view = new MainView();
                CitiesController citiesController = new CitiesController(view);
                ConnectionsController connectionsController = new ConnectionsController(view);
                FileMenuController menuController = new FileMenuController(view, connectionsController);

                view.addCityPanelListeners(citiesController);
                view.addConnectionsPanelListeners(connectionsController, connectionsController);
                view.addMenuListeners(menuController);
            }
        });

    }
}
