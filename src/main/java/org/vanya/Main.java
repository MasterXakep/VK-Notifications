package org.vanya;

import java.io.IOException;

/**
 * Created by vanya on 16.02.15.
 */
public class Main{


    public static void main(String[] args) throws InterruptedException, IOException {

        Runnable r = new ListenerTread();
        Thread vkListener = new Thread(r);
        vkListener.start();

        SystemTrayListener r1 = new SystemTrayListener();
        Thread tray = new Thread(r1);
        tray.start();


    }
}
