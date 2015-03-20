package org.vanya;

import java.io.IOException;

/**
 * Created by vanya on 16.02.15.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        Runnable r = new ListenerTread();
        Thread t = new Thread(r);
        t.start();

    }
}
