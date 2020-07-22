package com.msm.chatapp.ViewHolders;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.msm.chatapp.DataModels.MessageModel;
import com.msm.chatapp.R;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    private LinearLayout otherLayout;
    private TextView otherMessage;
    private TextView otherTime;
    private LinearLayout myLayout;
    private TextView myMessage;
    private TextView myTime;

    public MessageViewHolder(View itemView){
        super(itemView);
        otherLayout=itemView.findViewById(R.id.otherlayout);
        otherMessage=itemView.findViewById(R.id.othermsg);
        otherTime=itemView.findViewById(R.id.othermsgtime);
        myLayout=itemView.findViewById(R.id.mylayout);
        myMessage=itemView.findViewById(R.id.mymsg);
        myTime=itemView.findViewById(R.id.mymsgtime);
    }

    public void bind(MessageModel model){
        if(model.isMyMsg()){
            myLayout.setVisibility(View.VISIBLE);
            otherLayout.setVisibility(View.GONE);
            myMessage.setText(model.getMsg());
        }
        else {
            myLayout.setVisibility(View.GONE);
            otherLayout.setVisibility(View.VISIBLE);
            otherMessage.setText(model.getMsg());
        }
    }

}
