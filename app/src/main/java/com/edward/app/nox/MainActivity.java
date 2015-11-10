package com.edward.app.nox;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageButton;

import com.edward.app.nox.adapter.FeedAdapter;
import com.edward.app.nox.utility.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity {

    private final String tag = "Edward";
    @InjectView(R.id.toolbar)
    Toolbar toolbar;


    @InjectView(R.id.btnCreate)
    ImageButton btnCreate;
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
    private static final int ANIM_DURATION_TOOLBAR = 300;

    private void startIntroAnimation() {
        btnCreate.setTranslationY(2 * getResources().getDimensionPixelOffset(R.dimen.btn_fab_size));
        int actionbarSize = Utils.dpToPx(56);
        toolbar.setTranslationY(-actionbarSize);
        inboxMenuItem.getActionView().setTranslationY(-actionbarSize);

        toolbar.animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(300);
        inboxMenuItem.getActionView().animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        startContentAnimation();
                    }
                })
                .start();
    }

    private static final int ANIM_DURATION_FAB = 400;

    private void startContentAnimation() {
        btnCreate.animate()
                .translationY(0)
                .setInterpolator(new OvershootInterpolator(1.f))
                .setStartDelay(300)
                .setDuration(ANIM_DURATION_FAB)
                .start();
        feedAdapter.updateItems();
    }

}
