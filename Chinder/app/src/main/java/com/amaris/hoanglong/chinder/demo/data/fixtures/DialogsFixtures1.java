package com.amaris.hoanglong.chinder.demo.data.fixtures;



import com.amaris.hoanglong.chinder.demo.data.model.Dialog;
import com.amaris.hoanglong.chinder.demo.data.model.Message;
import com.amaris.hoanglong.chinder.demo.data.model.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/*
 * Created by Anton Bevza on 07.09.16.
 */
public final class DialogsFixtures1 extends FixturesData1 {
    private DialogsFixtures1() {
        throw new AssertionError();
    }

    public static ArrayList<Dialog> getDialogs() {
        ArrayList<Dialog> chats = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -(i * i));
            calendar.add(Calendar.MINUTE, -(i * i));

            chats.add(getDialog(i, calendar.getTime()));
        }

        return chats;
    }

    private static Dialog getDialog(int i, Date lastMessageCreatedAt) {
        ArrayList<User> users = getUsers(i);
        return new Dialog(
                getRandomId(),
                users.size() > 1 ? groupChatTitles.get(users.size() - 2) : users.get(0).getName(),
                users.size() > 1 ? groupChatImages.get(users.size() - 2) : getRandomAvatar(i),
                users,
                getMessage(lastMessageCreatedAt),
                i < 3 ? 3 - i : 0);
    }

    private static ArrayList<User> getUsers(int count) {
        ArrayList<User> users = new ArrayList<>();
        int usersCount = 1;

        for (int i = 0; i < usersCount; i++) {
            users.add(getUser(count));
        }

        return users;
    }

    private static User getUser(int count) {
        return new User(
                getRandomId(),
                getRandomName(count),
                getRandomAvatar(count),
                getRandomBoolean());
    }

    private static Message getMessage(final Date date) {
        return new Message(
                getRandomId(),
                getUser(1),
                getRandomMessage(),
                date);
    }
}
