package org.vanya;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by vanya on 16.02.15.
 */
public class SystemTrayThread {

    static SystemTray tray;
    static Image image;
    static PopupMenu popup;
    static TrayIcon trayIcon;
    final static Executor ex = Executors.newSingleThreadExecutor();
    final static Runnable r = new VkListenerThread();

    public SystemTrayThread() {

    }

    public static void main(String[] args) throws InterruptedException {

        ex.execute(r);

        tray = SystemTray.getSystemTray();
        image = Toolkit.getDefaultToolkit().getImage("src/main/resources/normal.png");
        popup = new PopupMenu();
        trayIcon = new TrayIcon(image, null, popup);

        if (SystemTray.isSupported()) {


            MouseListener mouseListener = new MouseListener() {

                public void mouseClicked(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse clicked!");
                    changeIcon(3);
                }

                public void mouseEntered(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse entered!");
                }

                public void mouseExited(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse exited!");
                }

                public void mousePressed(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse pressed!");
                }

                public void mouseReleased(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse released!");
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
/*                    trayIcon.displayMessage("Action Event",
                            "An Action Event Has Been Performed!",
                            TrayIcon.MessageType.INFO);*/
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

    public static void changeIcon(int n) {
        System.out.println("change icon");
        switch (n) {
            case 1:
                image = Toolkit.getDefaultToolkit().getImage("src/main/resources/print.png");
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

    /**
     * Restart vkListener thread if it's needed (502 error, time out, etc.)
     */
    static void restartVkListener(){
        ex.execute(r);
    }

}
