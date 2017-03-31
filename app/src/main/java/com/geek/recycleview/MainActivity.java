package com.geek.recycleview;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.geek.recycleview.Constant.Code;
import com.geek.recycleview.adapter.AdapterForMainActivity;
import com.geek.recycleview.database.DatabaseOpenHelper;
import com.geek.recycleview.model.Person;

public class MainActivity extends AppCompatActivity {

    private AdapterForMainActivity adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Activity activity = this;
    private DatabaseOpenHelper utils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        utils = new DatabaseOpenHelper(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyler_main_activity);

        //improve performance when change context not change size in recycler view
        recyclerView.setHasFixedSize(true);

        // init default list
        boolean isInit = false;
        SharedPreferences init_data = getSharedPreferences(Code.PREF_DATA, MODE_PRIVATE);
        isInit = init_data.getBoolean(Code.INIT_DATA, false);
        if (!isInit) {
            for (int i = 1; i < 10; i++) {
                Person person = new Person();
                person.setName("Demo " + i);
                person.setPhone("124567878");
                person.setAddress("Demo");
                person.setAge(18);
                person.setGender("Male");
                person.setMail("demo@mail.com");
                utils.addNewPerson(person);
            }
            init_data = getSharedPreferences(Code.PREF_DATA, MODE_PRIVATE);
            SharedPreferences.Editor editor = init_data.edit();
            editor.putBoolean(Code.INIT_DATA, true);
            editor.commit();
        }
        setupRecyclerView();
    }

    public void setupRecyclerView() {
        layoutManager = new LinearLayoutManager(this);
        adapter = new AdapterForMainActivity(activity, utils);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void addNewPerson(View view) {
        Intent addIntent = new Intent(this, ActionActivity.class);
        addIntent.putExtra(Code.FUNCTION_ADD_NEW, Code.FUNCTION_ADD_NEW);
        startActivityForResult(addIntent, Code.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Code.REQUEST_CODE) {
            if (resultCode == Code.REQUEST_CODE) {
                String functionEdit = data.getStringExtra(Code.FUNCTION_ADD_NEW);
                String functionDelete = data.getStringExtra(Code.FUNCTION_UPDATE);
                Person person = (Person) data.getSerializableExtra(Code.PERSON_DATA);
                if (functionEdit != null && functionDelete == null) {
                    adapter.add(person);
                } else if (functionDelete != null && functionEdit == null) {
                    adapter.update(person);
                }
                setupRecyclerView();
            }
        }
    }
}
