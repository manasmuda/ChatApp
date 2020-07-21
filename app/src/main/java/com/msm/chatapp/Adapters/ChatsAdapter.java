package com.msm.chatapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.msm.chatapp.DataModels.ChatModel;
import com.msm.chatapp.R;
import com.msm.chatapp.ViewHolders.CallViewHolder;
import com.msm.chatapp.ViewHolders.ChatViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ChatsAdapter extends RecyclerView.Adapter<ChatViewHolder> {

    private List<ChatModel> chatModelList;
    private List<ChatModel> queryList;
    private Context mContext;
    private String query;

    public ChatsAdapter(Context context){
        mContext=context;
        query="";
        chatModelList=new ArrayList<>();
        queryList=new ArrayList<>();
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.chat_item, parent, false);
        return new ChatViewHolder(contactView,mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        holder.bindView(queryList.get(position));
    }

    @Override
    public int getItemCount() {
        return queryList.size();
    }

    public void setChatModelList(List<ChatModel> chatModelList){
        this.chatModelList.addAll(chatModelList);
        createQueryList();
        notifyDataSetChanged();
    }

    public void setQuery(String query){
        this.query=query;
        createQueryList();
        notifyDataSetChanged();
    }

    private void createQueryList(){
        if(query.equals("")){
            queryList=new ArrayList<>();
            queryList.addAll(chatModelList);
        }
        else {
            queryList=new ArrayList<>();
            for (int i=0;i<chatModelList.size();i++){
                if(chatModelList.get(i).getName().contains(query)){
                    queryList.add(chatModelList.get(i));
                }
            }
        }
    }

    public int getSize(){
        return chatModelList.size();
    }

}
