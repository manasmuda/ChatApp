package com.msm.chatapp.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.msm.chatapp.DataModels.ChatModel;
import com.msm.chatapp.R;

import java.text.SimpleDateFormat;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatViewHolder extends RecyclerView.ViewHolder {

    private TextView name;
    private TextView lastmsg;
    private TextView date;
    private CircleImageView imageView;

    public ChatViewHolder(View itemView){
        super(itemView);
        name=itemView.findViewById(R.id.chat_name);
        lastmsg=itemView.findViewById(R.id.chat_lmsg);
        date=itemView.findViewById(R.id.chat_date);
        imageView=itemView.findViewById(R.id.chat_image);
    }

    public void bindView(ChatModel model){
        name.setText(model.getName());
        lastmsg.setText(model.getMessages().get(model.getMessages().size()-1).getMsg());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("hh:mm");
        date.setText(simpleDateFormat.format(model.getMessages().get(model.getMessages().size()-1).getDate()));
    }

}

