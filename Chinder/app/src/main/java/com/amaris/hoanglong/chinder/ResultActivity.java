package com.amaris.hoanglong.chinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultActivity extends AppCompatActivity {

    @BindView(R.id.chatNow)
    Button chatNow;

    @BindView(R.id.back)
    Button back;

    @BindView(R.id.result_wild)
    ImageView result_wild;

    @BindView(R.id.result_safe)
    ImageView result_safe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        final String type = getIntent().getStringExtra("type");

        if (type.equals("wild")) {
            result_wild.setVisibility(View.VISIBLE);
            result_safe.setVisibility(View.INVISIBLE);
            chatNow.setVisibility(View.VISIBLE);
            back.setVisibility(View.VISIBLE);
        } else {
            result_wild.setVisibility(View.INVISIBLE);
            result_safe.setVisibility(View.VISIBLE);
            chatNow.setVisibility(View.VISIBLE);
            back.setVisibility(View.VISIBLE);
        }

        chatNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(getApplicationContext(), ChattingActivity.class);
                myIntent.putExtra("type", type);
                getApplicationContext().startActivity(myIntent);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), SafeOrWildActivity.class);
                myIntent.putExtra("type", type);
                getApplicationContext().startActivity(myIntent);
            }
        });


    }
}
