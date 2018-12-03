package com.amaris.hoanglong.chinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import butterknife.ButterKnife;

public class QuestionActivity extends AppCompatActivity implements CardPresenter.CardCallback {

    SwipePlaceHolderView mCardsContainer;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ButterKnife.bind(this);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mCardsContainer = findViewById(R.id.cards_container);

        mCardsContainer.getBuilder()
//                .setDisplayViewCount(1)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f));

        mCardsContainer.addView(new CardPresenter(this, "Question 1", "Do you sing in the shower? ", "Yes", "No"));
        mCardsContainer.addView(new CardPresenter(this, "Question 2", "Do you enjoy cooking?", "Yes", "No"));
        mCardsContainer.addView(new CardPresenter(this, "Question 3", "Do you believe in Horoscope?", "Yes", "No"));
        mCardsContainer.addView(new CardPresenter(this, "Question 4", "Are you a dog person or a cat person?", "Meow Meow", "Woof Woof"));
        mCardsContainer.addView(new CardPresenter(this, "Question 5", "Would you like to be Smart or be Pretty/Handsome?", "Smart", "Pretty/Handsome"));


    }

    @Override
    public void onSwiping() {

    }

    @Override
    public void onSwipingEnd() {
        count++;
        final String type = getIntent().getStringExtra("type");

        if (count == 5) {
            Intent myIntent = new Intent(getApplicationContext(), ResultActivity.class);
            myIntent.putExtra("type", type);
            getApplicationContext().startActivity(myIntent);

        }

    }
}
