package com.msm.chatapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.tabs.TabLayout;
import com.msm.chatapp.Adapters.MainPagerAdapter;
import com.msm.chatapp.CallBacks.LoadingCallBack;
import com.msm.chatapp.DataBase.CallDB;
import com.msm.chatapp.DataBase.ChatDB;
import com.msm.chatapp.Fragments.ChatsFragment;
import com.msm.chatapp.R;

public class MainActivity extends AppCompatActivity {

    private ViewPager mainPager;
    private TabLayout mainTabs;

    private RelativeLayout progressLayout;

    private SearchView searchView;
    private MenuItem menuItemSearch;

    private MainPagerAdapter pagerAdapter;

    private LoadingCallBack lcb;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.searchmenu,menu);

        menuItemSearch=menu.findItem(R.id.action_search);
        //menuItemSearch.collapseActionView();
        searchView=(SearchView)menuItemSearch.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ChatsFragment.chatsAdapter.setQuery(newText);
                return false;
            }
        });

        menuItemSearch.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                mainTabs.setVisibility(View.GONE);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                mainTabs.setVisibility(View.VISIBLE);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTabs=findViewById(R.id.main_tabs);
        mainPager=findViewById(R.id.main_pager);
        progressLayout=findViewById(R.id.loadingLayout);

        progressLayout.setVisibility(View.GONE);

        ChatDB.InitiateDB();
        CallDB.InitiateDB();

        lcb=new LoadingCallBack() {
            @Override
            public void loading(boolean x) {
                if(x)
                    progressLayout.setVisibility(View.VISIBLE);
                else
                    progressLayout.setVisibility(View.GONE);
            }
        };

        pagerAdapter=new MainPagerAdapter(getSupportFragmentManager(),this,lcb);
        mainPager.setAdapter(pagerAdapter);

        mainTabs.addTab(mainTabs.newTab().setText("CHATS"));
        mainTabs.addTab(mainTabs.newTab().setText("CALLS"));

        mainPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mainTabs));


        mainTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mainPager.setCurrentItem(tab.getPosition());
                menuItemSearch.collapseActionView();
                searchView.setQuery("",false);
                searchView.clearFocus();
                progressLayout.setVisibility(View.GONE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
