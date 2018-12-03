package com.amaris.hoanglong.chinder.demo.custom.holder.holders.messages;

import android.view.View;

import com.amaris.hoanglong.chinder.demo.data.model.Message;
import com.stfalcon.chatkit.messages.MessageHolders;


public class CustomOutcomingTextMessageViewHolder
        extends MessageHolders.OutcomingTextMessageViewHolder<Message> {

    public CustomOutcomingTextMessageViewHolder(View itemView, Object payload) {
        super(itemView, payload);
    }

    @Override
    public void onBind(Message message) {
        super.onBind(message);

        time.setText(message.getStatus() + " " + time.getText());
    }
}
