package com.amaris.hoanglong.chinder;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mikepenz.materialdrawer.Drawer;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Filter extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    Drawer result;

    @BindView(R.id.next)
    Button next;

    @BindView(R.id.choiceTxt)
    TextView choiceTxt;

    @BindView(R.id.interestChoice)
    ImageView choice;

    @BindView(R.id.sameLikeMe)
    Button sameLikeme;

    @BindView(R.id.custom)
    Button custom;

    @BindView(R.id.filterChoice)
    RelativeLayout filterChoiceLayout;

    @BindView(R.id.filterSetting)
    RelativeLayout filterSettingLayout;

    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);

        sameLikeme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), SafeOrWildActivity.class);
                getApplicationContext().startActivity(myIntent);
            }
        });

        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterChoiceLayout.setVisibility(View.INVISIBLE);

//                AlphaAnimation animate = new AlphaAnimation(0,1);
//                animate.setDuration(1000);
//                filterSettingLayout.startAnimation(animate);

                Animation fadeIn = new AlphaAnimation(0, 1);
                fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
                fadeIn.setDuration(700);

                AnimationSet animation = new AnimationSet(false); //change to false
                animation.addAnimation(fadeIn);
                filterSettingLayout.setAnimation(animation);
                filterSettingLayout.setVisibility(View.VISIBLE);

            }
        });


        listItems = getResources().getStringArray(R.array.interest_item);
        checkedItems = new boolean[listItems.length];
        checkedItems[0] = true;
        mUserItems.add(0);
        checkedItems[1] = true;
        mUserItems.add(1);

        choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Filter.this, R.style.MyAlertDialogTheme);
                mBuilder.setTitle("Interests");
                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if (isChecked) {
                            mUserItems.add(position);
                        } else {
                            mUserItems.remove((Integer.valueOf(position)));
                        }
                    }
                });

                mBuilder.setCancelable(false);

                mBuilder.setPositiveButton(Html.fromHtml("<font color='#f56d2f'>OK</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String item = "";
                        for (int i = 0; i < mUserItems.size(); i++) {
                            item = item + listItems[mUserItems.get(i)];
                            if (i != mUserItems.size() - 1) {
                                item = item + ", ";
                            }
                        }
                        choiceTxt.setText(item);
                    }
                });

                mBuilder.setNegativeButton(Html.fromHtml("<font color='#ff864b'>Cancel</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), SafeOrWildActivity.class);
                getApplicationContext().startActivity(myIntent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        });
    }
}
