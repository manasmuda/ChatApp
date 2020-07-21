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

import com.msm.chatapp.Adapters.CallsAdapter;
import com.msm.chatapp.Adapters.ChatsAdapter;
import com.msm.chatapp.CallBacks.LoadingCallBack;
import com.msm.chatapp.DataBase.CallDB;
import com.msm.chatapp.DataBase.ChatDB;
import com.msm.chatapp.R;

public class CallsFragment extends Fragment {

    private static CallsFragment callsFragment;
    private static Context mContext;
    private Activity activity;

    private static LoadingCallBack lcb;

    private RecyclerView callList;
    private LinearLayoutManager layoutManager;

    public static CallsAdapter callsAdapter;

    private boolean scroll=true;

    public CallsFragment() {
        // Required empty public constructor
    }

    public static CallsFragment getInstance(Context context,LoadingCallBack lcb) {
        if(callsFragment==null) {
            callsFragment = new CallsFragment();
            mContext=context;
            CallsFragment.lcb=lcb;
        }
        return callsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.calls_fragment,container,false);
        callList=view.findViewById(R.id.call_list);

        layoutManager=new LinearLayoutManager(mContext);
        callList.setLayoutManager(layoutManager);

        callsAdapter=new CallsAdapter(mContext);
        callList.setAdapter(callsAdapter);

        callsAdapter.setCallModelList(CallDB.getPage(callsAdapter.getSize()));

        callList.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                                callsAdapter.setCallModelList(CallDB.getPage(callsAdapter.getSize()));
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
