package com.amaris.hoanglong.chinder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.amaris.hoanglong.chinder.demo.DemoMessagesActivity;
import com.amaris.hoanglong.chinder.demo.data.fixtures.MessagesFixtures;
import com.amaris.hoanglong.chinder.demo.data.model.Message;
import com.amaris.hoanglong.chinder.demo.data.model.User;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;
import com.stfalcon.chatkit.utils.DateFormatter;

import java.util.Date;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChattingActivity extends DemoMessagesActivity
        implements MessageInput.InputListener,
        MessageInput.AttachmentsListener,
        DateFormatter.Formatter {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbarTxt;

    Drawer result;
    AccountHeader headerResult;

    private MessagesList messagesList;

    private int count = 0;

    public static void open(Context context) {
        context.startActivity(new Intent(context, ChattingActivity.class));
    }

    private void initAdapter() {
        super.messagesAdapter = new MessagesListAdapter<>(super.senderId, super.imageLoader);
        super.messagesAdapter.enableSelectionMode(this);
        super.messagesAdapter.setLoadMoreListener(this);
        super.messagesAdapter.setDateHeadersFormatter(this);

        messagesList.setAdapter(super.messagesAdapter);
        final String type = getIntent().getStringExtra("type");
//        final String type = "safe";

        User anonymous = null;

        if (type.equals("wild")) {
            anonymous = new User(
                    "1",
                    "Anonymous Penguin",
                    "https://i.imgur.com/z4FmZ3e.png",
                    true);
            messagesAdapter.addToStart(new Message(getRandomId(), anonymous, "Nice to meet you!"), true);
                toolbarTxt.setText("Anonymous Penguin");
        } else {
            anonymous = new User(
                    "3",
                    "TRAN Ngoc Kim Anh",
                    "https://i.imgur.com/hNjfgd9.jpg",
                    true);
            messagesAdapter.addToStart(new Message(getRandomId(), anonymous, "Hello, it's me Kim Anh!"), true);
            toolbarTxt.setText("TRAN Ngoc Kim Anh");

        }
        count++;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);
        ButterKnife.bind(this);

        messagesList = (MessagesList) findViewById(R.id.messagesList);
        initAdapter();

        MessageInput input = (MessageInput) findViewById(R.id.input);
        input.setInputListener(this);
        input.setAttachmentsListener(this);

        super.TOTAL_MESSAGES_COUNT = 1;
        super.isAnonymous = true;

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Setup for account display on drawer
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.game_start)
                .withSelectionListEnabledForSingleProfile(false)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        IProfile profile = new ProfileDrawerItem().withName("PHAM Hoang Long").withEmail("phoanglong@amaris.com").withIcon(R.drawable.avatar).withTextColor(Color.WHITE);
        headerResult.addProfile(profile, 0);
        //Create item for drawer
        PrimaryDrawerItem browsing = new PrimaryDrawerItem().withIdentifier(0).withName("Match").withIcon(R.drawable.card).withSelectedTextColor(getResources().getColor(R.color.amaris)).withSelectedIconColor(getResources().getColor(R.color.amarisLight)).withIconTintingEnabled(true);
        PrimaryDrawerItem group = new PrimaryDrawerItem().withIdentifier(1).withName("Group").withIcon(R.drawable.ic_group).withSelectedTextColor(getResources().getColor(R.color.amaris)).withSelectedIconColor(getResources().getColor(R.color.amarisLight)).withIconTintingEnabled(true);
        PrimaryDrawerItem setting = new PrimaryDrawerItem().withIdentifier(2).withName("Messages").withIcon(R.drawable.ic_chat).withSelectedTextColor(getResources().getColor(R.color.amaris)).withSelectedIconColor(getResources().getColor(R.color.amarisLight)).withIconTintingEnabled(true);
        PrimaryDrawerItem logout = new PrimaryDrawerItem().withIdentifier(3).withName("Settings").withIcon(R.drawable.ic_settings).withSelectedTextColor(getResources().getColor(R.color.amaris)).withSelectedIconColor(getResources().getColor(R.color.amarisLight)).withIconTintingEnabled(true);

        //Setup drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .addDrawerItems(browsing,
                        group,
                        setting,
                        logout)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {
                            case 1: {
                                Intent intent = new Intent(getBaseContext(), SafeOrWildActivity.class);
                                startActivity(intent);
                            }
                            break;
                            case 2: {
                                Intent intent = new Intent(getBaseContext(), GroupsActivity.class);
                                startActivity(intent);
                            }
                            break;
                            case 3: {
//                                Intent intent = new Intent(getBaseContext(), MessagesActivity.class);
//                                startActivity(intent);
                            }
                            break;
                            case 4: {
                                Intent intent = new Intent(getBaseContext(), SettingActivity.class);
                                startActivity(intent);
                            }
                            break;

                        }
                        return true;
                    }
                })
                .build();

        result.setSelectionAtPosition(3);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_message);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_play:
                        Intent myIntent = new Intent(getApplicationContext(), SafeOrWildActivity.class);
                        getApplicationContext().startActivity(myIntent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        return true;
                    case R.id.navigation_group:
                        myIntent = new Intent(getApplicationContext(), GroupsActivity.class);
                        getApplicationContext().startActivity(myIntent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        return true;
                    case R.id.navigation_message:
                        myIntent = new Intent(getApplicationContext(), MessagesActivity.class);
                        getApplicationContext().startActivity(myIntent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        return true;
                    case R.id.navigation_setting:
                        myIntent = new Intent(getApplicationContext(), SettingActivity.class);
                        getApplicationContext().startActivity(myIntent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        return true;
                }
                return false;
            }
        });

        navigation.setVisibility(View.GONE);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.origin, menu);
        MenuItem item = menu.findItem(R.id.userDisplay);

        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return false;
            }
        });

//        ImageView reveal = findViewById(R.id.reveal);
//        reveal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "haha", Toast.LENGTH_SHORT).show();
//
//            }
//        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onSubmit(CharSequence input) {
        messagesAdapter.addToStart(
                MessagesFixtures.getTextMessage(input.toString(), true), true);
        return true;
    }

    @Override
    public void onAddAttachments() {
        User anonymous = new User(
                "1",
                "Anonymous Penguin",
                "https://i.imgur.com/z4FmZ3e.png",
                true);

        User bot = new User(
                "2",
                "Bot",
                "https://static.thenounproject.com/png/815603-200.png",
                true);

        Message aMessage = new Message(getRandomId(), anonymous, ".");

        switch (count) {
            case 1:
                aMessage = new Message(getRandomId(), anonymous, "Which Amaris office do you work?");
                //Vietnam
                messagesAdapter.addToStart(
                        aMessage, true);
                count++;
                break;
            case 2:
                aMessage = new Message(getRandomId(), anonymous, "What do you think about our CTO?");
                //he good, but kinda strict
                messagesAdapter.addToStart(
                        aMessage, true);
                count++;
                break;
            case 3:
                aMessage = new Message(getRandomId(), anonymous, "Do you want to reveal ourselves? ");
                //yes
                messagesAdapter.addToStart(
                        aMessage, true);
                count++;
                break;
            case 4:

                aMessage = new Message(getRandomId(), bot, "aBuddy BOT:\n" +
                        "---------------------------- \n" +
                        "\nYou just sent REVEAL IDENTITY request to ANONYMOUS PENGUIN. \n " +
                        "\nWait ANONYMOUS PENGUIN accept to show both you guys identity.");
                messagesAdapter.addToStart(
                        aMessage, true);

                aMessage = new Message(getRandomId(), bot, "...");
                messagesAdapter.addToStart(
                        aMessage, true);
                //Y
                count++;
                break;

            case 5:
                aMessage = new Message(getRandomId(), anonymous, "/Y");
                messagesAdapter.addToStart(
                        aMessage, true);
                count++;
                break;
            case 6:
                aMessage = new Message(getRandomId(), bot, null);
                aMessage.setImage(new Message.Image("https://pbs.twimg.com/media/B446N8RIcAAeguN.jpg"));
                messagesAdapter.addToStart(
                        aMessage, true);
                String htmlString = "aBuddy BOT" +
                        "\n ---------------------------- \n" +
                        "\nUsername: DELATTRE Olivier\n" +
                        "\nOffice: AMACONS\n" +
                        "\nFunction: Chief Information Officer\n" +
                        "\nInterests: Travel, Dog, Cat\n";

                aMessage = new Message(getRandomId(), bot, htmlString);
                messagesAdapter.addToStart(
                        aMessage, true);
                //Y
                count++;
                break;
            case 7:
                User ode = new User(
                        "1",
                        "ODE",
                        "https://i.imgur.com/H8Mf5Pk.jpg",
                        true);
                aMessage = new Message(getRandomId(), ode, "Hi :)");
                messagesAdapter.addToStart(
                        aMessage, true);
                //Y
                count++;
                break;
        }

    }

    @Override
    public String format(Date date) {
        if (DateFormatter.isToday(date)) {
            return getString(R.string.date_header_today);
        } else if (DateFormatter.isYesterday(date)) {
            return getString(R.string.date_header_yesterday);
        } else {
            return DateFormatter.format(date, DateFormatter.Template.STRING_DAY_MONTH_YEAR);
        }
    }

    static String getRandomId() {
        return Long.toString(UUID.randomUUID().getLeastSignificantBits());
    }

}
