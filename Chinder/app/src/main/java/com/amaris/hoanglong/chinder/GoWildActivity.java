package com.amaris.hoanglong.chinder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class GoWildActivity extends AppCompatActivity {

    @BindView(R.id.loading)
    AVLoadingIndicatorView loading;

    @BindView(R.id.loadingLayout)
    RelativeLayout loadingLayout;

    @BindView(R.id.profile_image)
    CircleImageView profileImg;

    @BindView(R.id.matchedChoice)
    RelativeLayout matchedChoiceLayout;

    @BindView(R.id.breakTheIce)
    Button breakTheIce;

    @BindView(R.id.chatting)
    Button chatting;

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    @BindView(R.id.matchedName)
    TextView matchName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_wild);
        ButterKnife.bind(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
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

        final String type = getIntent().getStringExtra("type");

        if (type.equals("wild")) {
            loadingLayout.setVisibility(View.VISIBLE);
            matchedChoiceLayout.setVisibility(View.INVISIBLE);
            loading.show();

            matchName.setText("Anonymous Penguin");
            toolbarTitle.setText("It's an Anonymous!");
            Picasso.with(GoWildActivity.this).load(R.drawable.penguin).into(profileImg);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    Animation fadeOut = new AlphaAnimation(1, 0);
                    fadeOut.setInterpolator(new DecelerateInterpolator()); //add this
                    fadeOut.setDuration(300);

                    AnimationSet animation = new AnimationSet(false); //change to false
                    animation.addAnimation(fadeOut);
                    loadingLayout.setAnimation(animation);
                    loadingLayout.setVisibility(View.INVISIBLE);
                    loading.hide();

                    Animation fadeIn = new AlphaAnimation(0, 1);
                    fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
                    fadeIn.setDuration(300);

                    AnimationSet animation2 = new AnimationSet(false); //change to false
                    animation2.addAnimation(fadeIn);
                    matchedChoiceLayout.setAnimation(animation2);
                    matchedChoiceLayout.setVisibility(View.VISIBLE);

                }
            }, 3000);
        } else {
            loadingLayout.setVisibility(View.INVISIBLE);
            matchedChoiceLayout.setVisibility(View.VISIBLE);
            loading.hide();

            matchName.setText("TRAN Ngoc Kim Anh");
            toolbarTitle.setText("It's a Match!");
            Picasso.with(GoWildActivity.this).load(R.drawable.kim_anh).into(profileImg);

        }


        chatting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equals("wild")) {
                    Intent myIntent = new Intent(getApplicationContext(), ChattingActivity.class);
                    myIntent.putExtra("type", type);
                    getApplicationContext().startActivity(myIntent);
                }

            }
        });

        breakTheIce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), QuestionActivity.class);
                myIntent.putExtra("type", type);
                getApplicationContext().startActivity(myIntent);
            }
        });
    }
}
