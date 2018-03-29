/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package Controller;

import View.MainView;

import javax.swing.*;

public class Main {

    public static void main(String []args) {


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                MainView view = new MainView();
                CitiesController citiesController = new CitiesController(view);
                ConnectionsController connectionsController = new ConnectionsController(view);

                view.addCityPanelListeners(citiesController);
                view.addConnectionsPanelListeners(connectionsController, connectionsController);
            }
        });

    }
}
