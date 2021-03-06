package com.amaris.hoanglong.chinder.POJO;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.amaris.hoanglong.chinder.R;
import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.SwipeDirection;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeInDirectional;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutDirectional;
import com.mindorks.placeholderview.annotations.swipe.SwipeTouch;
import com.mindorks.placeholderview.annotations.swipe.SwipingDirection;

/**
 * Created by janisharali on 09/08/17.
 */

@NonReusable
@Layout(R.layout.tinder_card_view)
public class TinderDirectionalCard {
    @View(R.id.profileImageView)
    ImageView profileImageView;

    @View(R.id.nameAgeTxt)
    TextView nameAgeTxt;

    @View(R.id.locationNameTxt)
    TextView locationNameTxt;

    @View(R.id.deptTxt)
    TextView deptTxt;

    @View(R.id.language1)
    TextView languageTxt;

    @Click(R.id.profileImageView)
    public void onClick() {
        Log.d("DEBUG", "profileImageView");
    }

    Context context;
    Image img;
    String name;
    String location;
    String dept;
    String language;

    public TinderDirectionalCard(Context context, Image img) {
        this.context = context;
        this.img = img;
    }

    public TinderDirectionalCard(Context context, Image img, String name, String location, String dept, String language) {
        this.context = context;
        this.img = img;
        this.name = name;
        this.location = location;
        this.dept = dept;
        this.language = language;
    }

    @Resolve
    public void onResolve() {
        nameAgeTxt.setText(this.name);
        locationNameTxt.setText(this.location);
        deptTxt.setText(this.dept);
        languageTxt.setText(this.language);
        Glide.with(context).load(img.getUrl()).into(profileImageView);
    }

    @SwipeOutDirectional
    public void onSwipeOutDirectional(SwipeDirection direction) {
//        Log.d("DEBUG", "SwipeOutDirectional " + direction.name());
    }

    @SwipeCancelState
    public void onSwipeCancelState() {
        Log.d("DEBUG", "onSwipeCancelState");
    }

    @SwipeInDirectional
    public void onSwipeInDirectional(SwipeDirection direction) {
//        Log.d("DEBUG", "SwipeInDirectional " + direction.name());
    }

    @SwipingDirection
    public void onSwipingDirection(SwipeDirection direction) {
//        Log.d("DEBUG", "SwipingDirection " + direction.name());
    }

    @SwipeTouch
    public void onSwipeTouch(float xStart, float yStart, float xCurrent, float yCurrent) {
//        Log.d("DEBUG", "onSwipeTouch "
//                + " xStart : " + xStart
//                + " yStart : " + yStart
//                + " xCurrent : " + xCurrent
//                + " yCurrent : " + yCurrent
//                + " distance : "
//                + Math.sqrt(Math.pow(xCurrent - xStart, 2) + (Math.pow(yCurrent - yStart, 2)))
//        );
    }
}
