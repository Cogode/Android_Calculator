package com.example.calculator.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.calculator.R;
import com.example.calculator.adapter.HistoryRecyclerViewAdapter;
import com.example.calculator.util.ActivityCollector;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        init();
    }

    private void init() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle("计算历史");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        SharedPreferences sharedPreferences = getSharedPreferences("history", MODE_PRIVATE);
        ArrayList<String> historyList = getHistoryList(sharedPreferences);
        HistoryRecyclerViewAdapter adapter = new HistoryRecyclerViewAdapter(historyList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(HistoryActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        adapter.notifyDataSetChanged();
        recyclerView.scrollToPosition(0);

        Button clearBtn = findViewById(R.id.clear_btn);
        clearBtn.setOnClickListener(view -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("history");
            editor.apply();
            adapter.refreshHistory(getHistoryList(sharedPreferences));
            adapter.notifyDataSetChanged();
            recyclerView.scrollToPosition(0);
        });
    }

    private ArrayList<String> getHistoryList(SharedPreferences sharedPreferences) {
        ArrayList<String> historyList = new ArrayList<>();
        String history = sharedPreferences.getString("history", "");
        String[] historys = history.split("\n");
        for(int i = 0; i < historys.length; i ++) {
            if(! historys[i].equals(""))
                historyList.add(historys[i]);
        }
        return historyList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.help:
                Toast.makeText(HistoryActivity.this, "这是帮助", Toast.LENGTH_SHORT).show();
                break;
            case R.id.history:
                Intent intent = new Intent(HistoryActivity.this, HistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.exit:
                ActivityCollector.finishAll();
        }
        return true;
    }
}