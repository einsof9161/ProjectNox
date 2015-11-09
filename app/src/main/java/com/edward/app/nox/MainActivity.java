package com.edward.app.nox;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.edward.app.nox.adapter.FeedAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity {

    private final String tag = "Edward";
    @InjectView(R.id.toolbar)
    Toolbar toolbar;


    private MenuItem inboxMenuItem;

    private FeedAdapter feedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setupToolbar();
        setupFeed();
    }


    /*
    * 配置上方Action Bar,並配置Menu Icon
    *
    * */
    private void setupToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white);
    }

    /*
       * Inflater右側的Menu bar
       *
       * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        inboxMenuItem = menu.findItem(R.id.action_inbox);
        inboxMenuItem.setActionView(R.layout.menu_item_view);
        return true;
    }


    @InjectView(R.id.rvFeed)
    RecyclerView rvFeedRecycleView;


    private void setupFeed() {
        Log.d(tag, "########1setupFeed#######setupFeed");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        Log.d(tag, "########2linearLayoutManager#######setupFeed");


        try {
            rvFeedRecycleView.setLayoutManager(linearLayoutManager);

            Log.d(tag, "########s3etLayoutManager#######setupFeed");
            feedAdapter = new FeedAdapter(this);
            Log.d(tag, "########4setLayoutManager#######new FeedAdapter(");

            rvFeedRecycleView.setAdapter(feedAdapter);
            Log.d(tag, "########s5etLayoutManager#######rvFeedRecycleView.setAdapter(feedAdapter)");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
