package org.vanya;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by vanya on 16.02.15.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        AccessConf accessToken = (AccessConf) context.getBean("accessTokenBean");
        //getLongPollServer

        URL url = new URL("https://api.vk.com/method/messages.getLongPollServer?use_ssl=0&need_pts=0&access_token=" + accessToken.getAccesToken());
        System.out.println(url);

        ObjectMapper mapper = new ObjectMapper();
        LongPollServer longPollServer = mapper.readValue(url, LongPollServer.class);

        System.out.println(longPollServer.getResponse().getTs());

        url = new URL("http://" + longPollServer.getResponse().getServer() + "?act=a_check&key=" + longPollServer.getResponse().getKey() + "&ts=" + longPollServer.getResponse().getTs() + "&wait=25&mode=2");
        List<List<Object>> updatesListList;

        while (true) {
            Updates updates = null;
            try {
                updates = mapper.readValue(url, Updates.class);
            } catch (IOException e) {
                url = new URL("https://api.vk.com/method/messages.getLongPollServer?use_ssl=0&need_pts=0&access_token=" + accessToken.getAccesToken());
                System.out.println(url);

                longPollServer = mapper.readValue(url, LongPollServer.class);
                e.printStackTrace();
            }
            //System.out.println(url);
            updatesListList = updates.getUpdates();
            String message = "";
            for(List<Object> updatesList: updatesListList) {
                int s = (Integer) updatesList.get(0);
                String firstName;
                String lastName;
                String userId;
                UserInfo userInfo;
                switch (s){
                    case (0): message = "удаление сообщения с указанным local_id";
                        break;
                    case (1):
                    message = "замена флагов сообщения (FLAGS:=$flags)";
                        break;
                    case (2):
                    message = "установка флагов сообщения (FLAGS|=$mask)";
                        break;
                    case (3):
                    message = "сброс флагов сообщения (FLAGS&=~$mask)";
                        break;
                    case (4):
                    message = "добавление нового сообщения";
                        break;
                   case (6):
                    message = "прочтение всех входящих сообщений с $peer_id вплоть до $local_id включительно";
                       break;
                   case (7):
                    message = "прочтение всех исходящих сообщений с $peer_id вплоть до $local_id включительно";
                       break;
                   case (8):
                       userId = updatesList.get(1).toString();
                       url = new URL("https://api.vk.com/method/users.get?user_ids=" + userId);
                       userInfo = mapper.readValue(url, UserInfo.class);
                       firstName = userInfo.getResponse().get(0).getFirstName();
                       lastName = userInfo.getResponse().get(0).getLastName();
                       message = "друг " + firstName + " " + lastName + " стал онлайн, $extra не равен 0, если в mode был передан флаг 64, в младшем байте (остаток от деления на 256) числа $extra лежит идентификатор платформы (таблица ниже)";
                       break;
                   case (9):
                       userId = updatesList.get(1).toString().replace("-", "");
                       url = new URL("https://api.vk.com/method/users.get?user_ids=" +userId);
                       userInfo = mapper.readValue(url, UserInfo.class);
                       firstName = userInfo.getResponse().get(0).getFirstName();
                       lastName = userInfo.getResponse().get(0).getLastName();
                       message = "друг " + firstName + " " + lastName + " стал оффлайн ($flags равен 0, если пользователь покинул сайт ";
                       break;
                   case (51):
                    message = "один из параметров (состав, тема) беседы $chat_id были изменены. $self - были ли изменения вызваны самим пользователем";
                       break;
                   case (61):
                       userId = updatesList.get(1).toString().replace("-", "");
                       url = new URL("https://api.vk.com/method/users.get?user_ids=" +userId);
                       userInfo = mapper.readValue(url, UserInfo.class);
                       firstName = userInfo.getResponse().get(0).getFirstName();
                       lastName = userInfo.getResponse().get(0).getLastName();
                       message = "пользователь " + firstName + " " + lastName + " начал набирать текст в диалоге. событие должно приходить раз в ~5 секунд при постоянном наборе текста. $flags = 1";
                       break;
                    case (62):
                    message = "пользователь $user_id начал набирать текст в беседе $chat_id.";
                        break;
                   case (70):
                    message = "пользователь $user_id совершил звонок имеющий идентификатор $call_id";
                       break;
                   case (80):
                    message = "новый счетчик непрочитанных в левом меню стал равен $count";
                       break;
               default:
                    message = "nothing";
                }
                System.out.println(message);
            }
            
            System.out.println(updates.getUpdates().toString());
            url = new URL("http://" + longPollServer.getResponse().getServer() + "?act=a_check&key=" + longPollServer.getResponse().getKey() + "&ts=" + updates.getTs() + "&wait=25&mode=2");

        }
       // URL  url1 = new URL("https://api.vk.com/method/users.get?user_ids=173197559");
       // System.out.println(url);
    }


}
