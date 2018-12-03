package com.amaris.hoanglong.chinder;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.suke.widget.SwitchButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserSetting extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    Drawer result;

    @BindView(R.id.switch_button2)
    SwitchButton switchButton2;

    @BindView(R.id.next)
    Button next;

    @BindView(R.id.interestChoice)
    ImageView choice;

    @BindView(R.id.choiceTxt)
    TextView choiceTxt;

    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);
        ButterKnife.bind(this);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), Filter.class);
                getApplicationContext().startActivity(myIntent);
            }
        });

        switchButton2.setChecked(true);

//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//
//        result = new DrawerBuilder()
//                .withActivity(this)
//                .withTranslucentStatusBar(true)
//                .withToolbar(toolbar)
//                .build();

        listItems = getResources().getStringArray(R.array.interest_item);
        checkedItems = new boolean[listItems.length];
        checkedItems[0]= true;
        mUserItems.add(0);
        checkedItems[1]= true;
        mUserItems.add(1);
        checkedItems[4]= true;
        mUserItems.add(4);

        choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(UserSetting.this,  R.style.MyAlertDialogTheme);
                mBuilder.setTitle("Interests");
                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if(isChecked){
                            mUserItems.add(position);
                        }else{
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


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.origin, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
}
