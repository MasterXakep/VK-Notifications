package org.vanya;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.URL;

/**
 * Created by vanya on 16.02.15.
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        AccessConf accessToken = (AccessConf) context.getBean("accessTokenBean");
        //getLongPollServer

        URL url = new URL("https://api.vk.com/method/messages.getLongPollServer?use_ssl=0&need_pts=0&access_token=" + accessToken.getAccesToken());
        System.out.println(url);

        ObjectMapper mapper = new ObjectMapper();
        LongPollServer longPollServer = mapper.readValue(url, LongPollServer.class);

        System.out.println(longPollServer.getResponse().getTs());

        url = new URL("http://" + longPollServer.getResponse().getServer() + "?act=a_check&key=" + longPollServer.getResponse().getKey() + "&ts=" + longPollServer.getResponse().getTs() + "&wait=25&mode=2");

        while (true) {
            Updates updates = mapper.readValue(url, Updates.class);
            //System.out.println(url);
            System.out.println(updates.getUpdates().toString());
            url = new URL("http://" + longPollServer.getResponse().getServer() + "?act=a_check&key=" + longPollServer.getResponse().getKey() + "&ts=" + updates.getTs() + "&wait=25&mode=2");

        }
    }


}
