package org.vanya;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by vanya on 20.03.15.
 */
public class SystemTrayListener implements Runnable {
    static SystemTray tray;
    static Image image;
    static PopupMenu popup;
    static TrayIcon trayIcon;

    public SystemTrayListener() {
        tray = SystemTray.getSystemTray();
        image = Toolkit.getDefaultToolkit().getImage("src/main/resources/normal.png");
        popup = new PopupMenu();
        trayIcon = new TrayIcon(image, "Tray Demo", popup);
    }

    public static void changeIcon(int n) {
        System.out.println("change icon");
        switch (n) {
            case 1:
                image = Toolkit.getDefaultToolkit().getImage("src/main/resources/print1.png");
                trayIcon.setImage(image);
                break;
            case 2:
                image = Toolkit.getDefaultToolkit().getImage("src/main/resources/incom.png");
                trayIcon.setImage(image);
                break;
            case 3:
                image = Toolkit.getDefaultToolkit().getImage("src/main/resources/normal.png");
                trayIcon.setImage(image);
                break;
            default:
                image = Toolkit.getDefaultToolkit().getImage("src/main/resources/normal.png");
                trayIcon.setImage(image);
        }
    }

    @Override
    public void run() {


        if (SystemTray.isSupported()) {


            MouseListener mouseListener = new MouseListener() {

                public void mouseClicked(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse clicked!");
                }

                public void mouseEntered(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse entered!");
                }

                public void mouseExited(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse exited!");
                }

                public void mousePressed(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse pressed!");
                    changeIcon(1);
                }

                public void mouseReleased(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse released!");
                    changeIcon(2);
                }
            };

            ActionListener exitListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
            };


            MenuItem defaultItem = new MenuItem("Exit");
            defaultItem.addActionListener(exitListener);
            popup.add(defaultItem);


            ActionListener actionListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    trayIcon.displayMessage("Action Event",
                            "An Action Event Has Been Performed!",
                            TrayIcon.MessageType.INFO);
                }
            };

            trayIcon.setImageAutoSize(true);
            trayIcon.addActionListener(actionListener);
            trayIcon.addMouseListener(mouseListener);

            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println("TrayIcon could not be added.");
            }

        } else {

            System.out.println("System Tray is not supported");

        }
    }
}
