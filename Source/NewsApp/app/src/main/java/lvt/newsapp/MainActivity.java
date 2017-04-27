/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package lvt.newsapp;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import org.java_websocket.drafts.Draft_10;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import lvt.newsapp.R;

/**
 * TODO
 */
public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, View.OnClickListener {

    private DrawerLayout mDrawerLayout;
    private RecyclerView rvNews;
    private ArrayList<News> newsList;
    private FloatingActionButton fabNext;
    private FloatingActionButton fabPre;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView.OnScrollListener mListener;
    ClientConnectB client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowTitleEnabled(false);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        rvNews = (RecyclerView) findViewById(R.id.rvNews);
        fabNext = (FloatingActionButton) findViewById(R.id.fabNext);

        fabPre = (FloatingActionButton) findViewById(R.id.fabPre);
        fabNext.setVisibility(View.INVISIBLE);
        fabPre.setVisibility(View.INVISIBLE);
        fabNext.setOnClickListener(this);
        fabPre.setOnClickListener(this);
        newsList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            newsList.add(new News("Ahihi" + i));
        }

        NewsAdapter adapter = new NewsAdapter(newsList, this);
        rvNews.setAdapter(adapter);

        mLayoutManager = new LinearLayoutManager(this);
        rvNews.setLayoutManager(mLayoutManager);
        rvNews.setHasFixedSize(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
        inti();
        rvNews.addOnScrollListener(mListener);

//        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
//        if (viewPager != null) {
//            setupViewPager(viewPager);
//        }


//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);
    }

    public void inti() {
        mListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int visibleItemCount = mLayoutManager.getChildCount();
                    int totalItemCount = mLayoutManager.getItemCount();
                    int pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        fabNext.setVisibility(View.VISIBLE);
                        fabPre.setVisibility(View.VISIBLE);
                    }
                } else {
                    fabNext.setVisibility(View.INVISIBLE);
                    fabPre.setVisibility(View.INVISIBLE);
                }
            }
        };
    }

    @Override
    protected void onResume() {
        String uri = "ws://ec2-54-169-80-188.ap-southeast-1.compute.amazonaws.com:6969";
        try {
            client = new ClientConnectB(new URI(uri), new Draft_10()) {
                @Override
                public void get_News_Android(String source, List<String> result) {
                    Log.d("TEST", "CALL BACK");
                    if (result == null) {
                        Log.d("TEST", "NULL");
                    } else {
                        for (String item : result) {
                            Log.d("TEST", item);
                        }
                    }
                }
            };
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        client.connect();

        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(
                new ComponentName(this, MainActivity.class)));
        searchView.setIconifiedByDefault(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    private void setupViewPager(ViewPager viewPager) {
//        AdapterTab adapter = new AdapterTab(getSupportFragmentManager());
//        adapter.addFragment(new CheeseListFragment(), "Tab 1");
//        adapter.addFragment(new CheeseListFragment(), "Tab 2");
//        adapter.addFragment(new CheeseListFragment(), "Tab 3");
//        viewPager.setAdapter(adapter);
//    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }


    @Override
    public boolean onQueryTextSubmit(final String query) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        GetTask task = new GetTask();
        task.execute(query);
        return false;
    }

    public class GetTask extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... params) {
            try {
                client.SendtoServer("ALL", "HASHTAG", params[0]);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fabNext:
                Snackbar.make(view, "Here's a next", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
            case R.id.fabPre:
                Snackbar.make(view, "Here's a previous", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
        }
    }
}
