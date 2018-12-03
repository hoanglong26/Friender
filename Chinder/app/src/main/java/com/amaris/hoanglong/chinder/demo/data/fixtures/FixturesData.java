package com.amaris.hoanglong.chinder.demo.data.fixtures;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.UUID;

/*
 * Created by Anton Bevza on 1/13/17.
 */
abstract class FixturesData {

    static SecureRandom rnd = new SecureRandom();

    static ArrayList<String> avatars = new ArrayList<String>() {
        {
            add("https://i.imgur.com/k04SAaH.jpg");
            add("https://i.imgur.com/sFH5yVi.jpg");
            add("https://i.imgur.com/xVcEdjc.jpg");
            add("https://i.imgur.com/ZAk4xr9.jpg");
        }
    };

    static final ArrayList<String> groupChatImages = new ArrayList<String>() {
        {
            add("https://i.imgur.com/2w8rPB8.png");
            add("https://i.imgur.com/L51Nuyh.png");
            add("https://i.imgur.com/Io2VlhM.png");
        }
    };

    static final ArrayList<String> groupChatTitles = new ArrayList<String>() {
        {
            add("PM group");
            add("Milk tea group");
            add("Board game club");
        }
    };

    static final ArrayList<String> names = new ArrayList<String>() {
        {
            add("PHAM Hoang Long");
            add("TRAN Ngoc Kim Anh");
            add("Zoe Milton");
            add("NGO Gia An");
            add("NGUYEN Le Khanh Phuong");
            add("TONG Trong Nhan");
            add("Kyle Oswald");
            add("Abigail Stevenson");
            add("Olivier Brohant");
            add("Jordan Gill");
            add("NGUYEN Hoai Minh Tam");
        }
    };

    static final ArrayList<String> messages = new ArrayList<String>() {
        {
            add("Hello!");
            add("Good to hear");
            add("Goodbye");
            add("Hey guys! Check out this video");
            add("Hello! No problem. I can today at 2 pm. And after we can go to the office.");
            add("Have you finish?");
            add("Great to hear that!");
            add("Meet me at Room 19");
            add("Wow, amazing!");
            add("So saying he unbuckled his baldric with the bugle");
            add("I just received a call from ODE. I think he gonna visit us in next month");
        }
    };

    static final ArrayList<String> images = new ArrayList<String>() {
        {
            add("https://habrastorage.org/getpro/habr/post_images/e4b/067/b17/e4b067b17a3e414083f7420351db272b.jpg");
            add("https://cdn.pixabay.com/photo/2017/12/25/17/48/waters-3038803_1280.jpg");
        }
    };

    static String getRandomId() {
        return Long.toString(UUID.randomUUID().getLeastSignificantBits());
    }

    static String getRandomAvatar(int count) {
        return avatars.get(count);
    }

    static String getRandomGroupChatImage() {
        return groupChatImages.get(rnd.nextInt(groupChatImages.size()));
    }

    static String getRandomGroupChatTitle() {
        return groupChatTitles.get(rnd.nextInt(groupChatTitles.size()));
    }

    static String getRandomName(int count) {
        return names.get(count);
    }

    static String getRandomMessage() {
        return messages.get(rnd.nextInt(messages.size()));
    }

    static String getRandomImage() {
        return images.get(rnd.nextInt(images.size()));
    }

    static boolean getRandomBoolean() {
        return rnd.nextBoolean();
    }
}
