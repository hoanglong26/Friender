package com.amaris.hoanglong.chinder;

import android.widget.Button;
import android.widget.TextView;

import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;

@NonReusable
@Layout(R.layout.card_layout)
public class CardPresenter {
    @View(R.id.question_number)
    TextView questionNoTxt;


    @View(R.id.question_caption)
    TextView mTextView;

    @View(R.id.question_ans)
    TextView ansTxt;

    @View(R.id.btn_answer1)
    Button btn_ans1;

    @View(R.id.btn_answer2)
    Button btn_ans2;

    String questionNo;
    String question;
    String ans1;
    String ans2;

    private CardCallback callback;

    public CardPresenter(CardCallback callback) {
        this.callback = callback;
    }

    @SwipeOut
    public void onSwipedOut() {
        callback.onSwipingEnd();
    }

    @SwipeCancelState
    public void onSwipeCancelState() {
        callback.onSwipingEnd();
    }

    @SwipeIn
    public void onSwipeIn() {
        callback.onSwipingEnd();
    }

    @SwipeInState
    public void onSwipeInState() {
        callback.onSwiping();
    }

    @SwipeOutState
    public void onSwipeOutState() {
        callback.onSwiping();
    }

    public CardPresenter(String question) {
        this.question = question;
    }

    public CardPresenter(CardCallback callback, String questionNo, String question, String ans1, String ans2) {
        this.callback = callback;
        this.questionNo = questionNo;
        this.question = question;
        this.ans1 = ans1;
        this.ans2 = ans2;

   }

    @Click(R.id.btn_answer1)
    public void onClickQ1() {
        String message = String.format(
                "Your answer is: %s.",
                this.ans1);
        ansTxt.setText(message);
    }

    @Click(R.id.btn_answer2)
    public void onClickQ2() {
        String message = String.format(
                "Your answer is: %s.",
                this.ans2);
        ansTxt.setText(message);
    }

    @Resolve
    public void onResolved() {
        questionNoTxt.setText(this.questionNo);
        mTextView.setText(this.question);
        btn_ans1.setText(ans1);
        btn_ans2.setText(ans2);
    }

    public interface CardCallback{
        void onSwiping();
        void onSwipingEnd();
    }
}
