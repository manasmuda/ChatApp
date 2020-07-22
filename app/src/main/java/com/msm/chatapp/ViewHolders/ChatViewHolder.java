package com.msm.chatapp.ViewHolders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.msm.chatapp.CallBacks.OnChatClick;
import com.msm.chatapp.DataModels.ChatModel;
import com.msm.chatapp.R;

import java.text.SimpleDateFormat;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatViewHolder extends RecyclerView.ViewHolder {

    private TextView name;
    private TextView lastmsg;
    private TextView date;
    private CircleImageView imageView;
    private Context context;
    private RelativeLayout chatItemLayout;

    private ChatModel chatModel;

    private int[] resourses=new int[]{R.drawable.download,R.drawable.download1};

    public ChatViewHolder(View itemView, Context context){
        super(itemView);
        name=itemView.findViewById(R.id.chat_name);
        lastmsg=itemView.findViewById(R.id.chat_lmsg);
        date=itemView.findViewById(R.id.chat_date);
        imageView=itemView.findViewById(R.id.chat_image);
        chatItemLayout=itemView.findViewById(R.id.chat_item_layout);
        this.context=context;
    }

    public void bindView(ChatModel model, final OnChatClick onChatClick){
        chatModel=model;
        Random random = new Random();
        int randomNumber = random.nextInt(2);
        chatItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChatClick.onClick(chatModel);
            }
        });
        imageView.setImageDrawable(context.getResources().getDrawable(resourses[randomNumber]));
        name.setText(model.getName());
        lastmsg.setText(model.getMessages().get(model.getMessages().size()-1).getMsg());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("hh:mm");
        date.setText(simpleDateFormat.format(model.getMessages().get(model.getMessages().size()-1).getDate()));
    }

}

