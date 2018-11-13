package com.amaris.hoanglong.chinder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.amaris.hoanglong.chinder.POJO.Image;
import com.amaris.hoanglong.chinder.POJO.TinderDirectionalCard;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipeDirectionalView;
import com.mindorks.placeholderview.listeners.ItemRemovedListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ActivityTinder";

    @BindView(R.id.swipeView)
    SwipeDirectionalView mSwipeView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    Drawer result;
    AccountHeader headerResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        final List<Image> listImg = Utils.loadImages(getBaseContext());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_history:

                        return true;
                    case R.id.navigation_about:
                        Intent myIntent = new Intent(getApplicationContext(), LoadMoreActivity.class);
                        getApplicationContext().startActivity(myIntent);
//                      getSupportActionBar().setTitle("Favorite");
                        return true;
                }
                return false;
            }
        });

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        //Setup for account display on drawer
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.blur)
                .withSelectionListEnabledForSingleProfile(false)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        IProfile profile = new ProfileDrawerItem().withName("Long Pham").withEmail("phoanglong@amaris.com").withIcon(R.drawable.avatar).withTextColor(Color.BLACK);
        headerResult.addProfile(profile, 0);


        //Create item for drawer
        PrimaryDrawerItem browsing = new PrimaryDrawerItem().withIdentifier(0).withName("Browsing").withIcon(R.drawable.ic_favorite).withSelectedTextColor(getResources().getColor(R.color.amaris)).withSelectedIconColor(getResources().getColor(R.color.amarisLight)).withIconTintingEnabled(true);
        PrimaryDrawerItem group = new PrimaryDrawerItem().withIdentifier(1).withName("Group").withIcon(R.drawable.ic_group).withSelectedTextColor(getResources().getColor(R.color.amaris)).withSelectedIconColor(getResources().getColor(R.color.amarisLight)).withIconTintingEnabled(true);
        SecondaryDrawerItem setting = new SecondaryDrawerItem().withIdentifier(2).withName("Settings").withIcon(R.drawable.ic_settings).withSelectedTextColor(getResources().getColor(R.color.amaris)).withSelectedIconColor(getResources().getColor(R.color.amarisLight)).withIconTintingEnabled(true);
        SecondaryDrawerItem logout = new SecondaryDrawerItem().withIdentifier(3).withName("Logout").withIcon(R.drawable.ic_power).withSelectedTextColor(getResources().getColor(R.color.amaris)).withSelectedIconColor(getResources().getColor(R.color.amarisLight)).withIconTintingEnabled(true);


        //Setup drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .addDrawerItems(browsing,
                        group,
                        new DividerDrawerItem(),
                        setting,
                        logout)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {
                            case 1: {
//                                //Adjust the size of content and tablayout back to normal
//                                tabLayout.setVisibility(View.VISIBLE);
//
////                                params.height = height - (actionBarHeight * 4 / 3 + actionBarHeight * 2 / 19);
////                                layout.setLayoutParams(params);
//
//                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, HomeFragment.newInstance("Home")).commit();
//                                result.closeDrawer();
//                                TabLayout.Tab tab = tabLayout.getTabAt(0);
//                                tab.select();
//                                toolbar.setTitle("Missing Residents");
//                                btnSearch.setVisibility(View.VISIBLE);
                            }
                            break;
                            case 2: {
                                //Hide tablayout and increase height of content
//                                tabLayout.setVisibility(View.GONE);
//
////                                params.height = height + (actionBarHeight * 4 / 3 + actionBarHeight * 2 / 19);
////                                layout.setLayoutParams(params);
//
//                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FAQFragment.newInstance("FAQ")).commit();
//                                result.closeDrawer();
//                                toolbar.setTitle("FAQ");
//                                btnSearch.setVisibility(View.GONE);
                            }
                            break;
                            case 4: {
//                                params.height = height + (actionBarHeight * 4 / 3 + actionBarHeight * 2 / 19);
//                                layout.setLayoutParams(params);
//                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, AboutFragment.newInstance("About")).commit();
//                                result.closeDrawer();
//                                toolbar.setTitle("About");
//                                tabLayout.setVisibility(View.GONE);

//                                Intent intent = new Intent(getBaseContext(), AboutUsActivity.class);
//                                intent.putExtra("fromWhat", "home");
//                                startActivity(intent);
//                                finish();
//                                btnSearch.setVisibility(View.GONE);
                            }
                            break;
                            case 5: {
//                                Intent intent = new Intent(getBaseContext(), SettingActivity.class);
//                                intent.putExtra("fromWhat", "home");
//                                startActivity(intent);
//                                finish();
                            }
                            break;

                        }
                        return true;
                    }
                })
                .build();

        mSwipeView.addItemRemoveListener(new ItemRemovedListener() {

            @Override
            public void onItemRemoved(int count) {
                Log.d(TAG, "onItemRemoved: " + count);
                if (count == 0) {
                    mSwipeView.addView(new TinderDirectionalCard(getApplicationContext(), listImg.get(0)))
                            .addView(new TinderDirectionalCard(getApplicationContext(), listImg.get(1)))
                            .addView(new TinderDirectionalCard(getApplicationContext(), listImg.get(2)))
                            .addView(new TinderDirectionalCard(getApplicationContext(), listImg.get(3)))
                            .addView(new TinderDirectionalCard(getApplicationContext(), listImg.get(4)))
                            .addView(new TinderDirectionalCard(getApplicationContext(), listImg.get(5)))
                            .addView(new TinderDirectionalCard(getApplicationContext(), listImg.get(6)))
                            .addView(new TinderDirectionalCard(getApplicationContext(), listImg.get(7)));
                }
            }
        });

        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setIsUndoEnabled(true)
                .setSwipeVerticalThreshold(Utils.dpToPx(50))
                .setSwipeHorizontalThreshold(Utils.dpToPx(50))
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.tinder_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.tinder_swipe_out_msg_view));


        mSwipeView.addView(new TinderDirectionalCard(getApplicationContext(), listImg.get(0)))
                .addView(new TinderDirectionalCard(getApplicationContext(), listImg.get(1)))
                .addView(new TinderDirectionalCard(getApplicationContext(), listImg.get(2)))
                .addView(new TinderDirectionalCard(getApplicationContext(), listImg.get(3)))
                .addView(new TinderDirectionalCard(getApplicationContext(), listImg.get(4)))
                .addView(new TinderDirectionalCard(getApplicationContext(), listImg.get(5)))
                .addView(new TinderDirectionalCard(getApplicationContext(), listImg.get(6)))
                .addView(new TinderDirectionalCard(getApplicationContext(), listImg.get(7)));
    }

    @OnClick(R.id.rejectBtn)
    void onRejectClick() {
        mSwipeView.doSwipe(false);
    }

    @OnClick(R.id.acceptBtn)
    void onAcceptClick() {
        mSwipeView.doSwipe(true);
    }


}