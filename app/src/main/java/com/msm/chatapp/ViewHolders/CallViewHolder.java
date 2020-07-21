package com.msm.chatapp.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.msm.chatapp.DataModels.CallModel;
import com.msm.chatapp.R;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class CallViewHolder extends RecyclerView.ViewHolder {

    private TextView name;
    private TextView date;
    private CircleImageView imageView;
    private ImageView callType;

    public CallViewHolder(View itemView){
        super(itemView);
        name=itemView.findViewById(R.id.call_name);
        callType=itemView.findViewById(R.id.call_type_image);
        date=itemView.findViewById(R.id.call_date);
        imageView=itemView.findViewById(R.id.call_image);
    }

    public void bindView(CallModel model){
        name.setText(model.getName());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd MMMM  hh:mm");
        date.setText(simpleDateFormat.format(model.getDate()));

    }

}
