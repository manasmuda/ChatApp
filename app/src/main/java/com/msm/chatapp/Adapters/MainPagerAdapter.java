package com.msm.chatapp.Adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.msm.chatapp.CallBacks.LoadingCallBack;
import com.msm.chatapp.Fragments.CallsFragment;
import com.msm.chatapp.Fragments.ChatsFragment;

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private Context mContext;
    private LoadingCallBack lcb;


    public MainPagerAdapter(FragmentManager fm, Context context,LoadingCallBack lcb) {
        super(fm);
        mContext=context;
        this.lcb=lcb;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return ChatsFragment.getInstance(mContext,lcb);
            case 1:
                return CallsFragment.getInstance(mContext,lcb);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
