package com.geek.recycleview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.geek.recycleview.adapter.AdapterForActionActivity;

/**
 * Created by PK on 3/30/2017.
 */

public class ActionActivity extends Activity {

    private RecyclerView recyclerView;
    private AdapterForActionActivity adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
        recyclerView = (RecyclerView) findViewById(R.id.recyler_add_new_member);
        setupRecyclerView();
    }

    public void setupRecyclerView() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterForActionActivity(ActionActivity.this);
        recyclerView.setAdapter(adapter);
    }

}
