package com.msm.chatapp.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.msm.chatapp.Adapters.ChatsAdapter;
import com.msm.chatapp.CallBacks.LoadingCallBack;
import com.msm.chatapp.DataBase.ChatDB;
import com.msm.chatapp.R;

public class ChatsFragment extends Fragment {

    private static ChatsFragment chatsFragment;
    private static Context mContext;
    private Activity activity;

    private static LoadingCallBack lcb;

    private RecyclerView chatList;
    private LinearLayoutManager layoutManager;

    public static ChatsAdapter chatsAdapter;

    private boolean scroll=true;

    public ChatsFragment() {
        // Required empty public constructor
    }

    public static ChatsFragment getInstance(Context context,LoadingCallBack lcb1) {
        if(chatsFragment==null) {
            chatsFragment = new ChatsFragment();
            mContext=context;
            lcb=lcb1;
        }
        return chatsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.chats_fragment,container,false);
        chatList=view.findViewById(R.id.chat_list);

        layoutManager=new LinearLayoutManager(mContext);
        chatList.setLayoutManager(layoutManager);

        chatsAdapter=new ChatsAdapter(mContext);
        chatList.setAdapter(chatsAdapter);

        chatsAdapter.setChatModelList(ChatDB.getPage(chatsAdapter.getSize()));

        chatList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                Log.i("abc","vic"+String.valueOf(visibleItemCount));
                Log.i("abc","tic"+String.valueOf(totalItemCount));
                Log.i("abc","fvip"+String.valueOf(firstVisibleItemPosition));

                if(scroll) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= 8) {
                        Log.i("abc", "addItems");
                        lcb.loading(true);
                        final Handler handler = new Handler();
                        scroll=false;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // Do something after 5s = 5000ms
                                chatsAdapter.setChatModelList(ChatDB.getPage(chatsAdapter.getSize()));
                                lcb.loading(false);
                                scroll=true;
                            }
                        }, 1000);

                    }
                }
            }
        });


        return view;
    }

}
