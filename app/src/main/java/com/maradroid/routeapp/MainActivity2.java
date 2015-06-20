package com.maradroid.routeapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;


public class MainActivity2 extends ActionBarActivity implements NeewsFeedAdapter.ClickListener,SetAdapterCallback{


    //private ArrayList<RouteObject> newsFeedObjects;
    private RecyclerView mRecycler;
    private RecyclerView.LayoutManager mLayoutManager;
    private NeewsFeedAdapter mAdapter;


    private AsyncDownload asyncDownload;
    private ArrayList<RouteObject> routeObjectArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        routeObjectArrayList = new ArrayList<RouteObject>();

        mRecycler = (RecyclerView) findViewById(R.id.recycler_view);
        mRecycler.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(mLayoutManager);
        mAdapter = new NeewsFeedAdapter(routeObjectArrayList);
        mAdapter.setClickListener(this);
        mRecycler.setAdapter(mAdapter);

        getNewsFeed("http://dravabicycleroutes.eu/cms-admin/api_v2/routes");


    }


    @Override
    public void onClick(View v, int position) {

        Log.e("fbdadfrgdfgdfgadfgadf",""+routeObjectArrayList.get(position).getArrayList());
        Intent intent = new Intent(this,MapsActivity.class);
        intent.putStringArrayListExtra("koordinate",routeObjectArrayList.get(position).getArrayList());
        startActivity(intent);

    }

    @Override
    public void onRequestCompleted(Object objects) {
        ArrayList<RouteObject> io = new ArrayList<RouteObject>();
        io = (ArrayList<RouteObject>)objects;
        for(int i=0;i<io.size();i++){
            mAdapter.addItem(io.get(i));
        }

        Log.e("dfasdfadfadfa",""+io.get(0).getArrayList().get(100));

    }

    public void getNewsFeed(String url){
        asyncDownload = new AsyncDownload();
        asyncDownload.setCallback(this);
        asyncDownload.execute(url);

    }
}
