package org.vanya;

import java.io.IOException;

/**
 * Created by vanya on 13.03.15.
 */
public class Notification {

    private String title;
    private String text;

    public Notification(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean send(){

        if(exec("notify-send -i empathy -u critical " + "\"" + title + "\" \"" + text + "\"")) return true;
        else return false;
    }
    private static boolean exec(String command) {

        String[] cmd = {"/bin/sh", "-c", command};

        try {
            Process p = Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
