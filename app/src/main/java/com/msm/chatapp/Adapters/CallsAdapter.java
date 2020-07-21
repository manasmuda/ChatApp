package com.msm.chatapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.msm.chatapp.DataModels.CallModel;
import com.msm.chatapp.R;
import com.msm.chatapp.ViewHolders.CallViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CallsAdapter extends RecyclerView.Adapter<CallViewHolder> {

    private List<CallModel> callModelList;
    private List<CallModel> queryList;
    private Context mContext;

    private String query;

    public CallsAdapter(Context context){
        callModelList=new ArrayList<>();
        queryList=new ArrayList<>();
        mContext=context;
        query="";
    }

    @NonNull
    @Override
    public CallViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.call_item, parent, false);
        return new CallViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull CallViewHolder holder, int position) {
        holder.bindView(queryList.get(position));
    }

    @Override
    public int getItemCount() {
        return queryList.size();
    }

    public void setCallModelList(List<CallModel> callModelList){
        this.callModelList=callModelList;
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
            queryList.addAll(callModelList);
        }
        else {
            queryList=new ArrayList<>();
            for (int i=0;i<callModelList.size();i++){
                if(callModelList.get(i).getName().contains(query)){
                    queryList.add(callModelList.get(i));
                }
            }
        }
    }

    public int getSize(){
        return callModelList.size();
    }
}
