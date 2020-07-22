package com.msm.chatapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.msm.chatapp.DataModels.MessageModel;
import com.msm.chatapp.R;
import com.msm.chatapp.ViewHolders.MessageViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private List<MessageModel> messageModelList;
    private Context mContext;

    public MessagesAdapter(Context context){
        messageModelList=new ArrayList<>();
        mContext=context;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.message_item, parent, false);
        return new MessageViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        holder.bind(messageModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return messageModelList.size();
    }

    public void setList(List<MessageModel> list){
        this.messageModelList=list;
        notifyDataSetChanged();
    }
}
