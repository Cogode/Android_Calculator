package com.example.calculator.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculator.R;
import com.example.calculator.adapter.SelectionRecyclerViewAdapter;
import com.example.calculator.util.ActivityCollector;

import java.util.ArrayList;

public class SelectionActivity extends AppCompatActivity {
    private String selection;
    private RecyclerView iRecyclerView;
    private RecyclerView eRecyclerView;
    private SelectionRecyclerViewAdapter iAdapter;
    private SelectionRecyclerViewAdapter eAdapter;
    private ArrayList<String> iSelections = new ArrayList<>();
    private ArrayList<String> eSelections = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(SelectionActivity.this);
        setContentView(R.layout.activity_selection);
        init();
    }

    private void init() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle("选择单位");
        }

        Intent intent = getIntent();
        selection = intent.getStringExtra("selection");
        String type = intent.getStringExtra("type");
        String inputNum = intent.getStringExtra("inputNum");
        iRecyclerView = findViewById(R.id.international);
        eRecyclerView = findViewById(R.id.English);
        if(selection.equals("length")) {
            iSelections.add("皮米\npm");
            iSelections.add("纳米\nnm");
            iSelections.add("微米\num");
            iSelections.add("毫米\nmm");
            iSelections.add("厘米\ncm");
            iSelections.add("分米\ndm");
            iSelections.add("米\nm");
            iSelections.add("公里\nkm");
            iSelections.add("月地距离\nld");
            iSelections.add("天文单位\nAU");
            iSelections.add("光年\nly");
            iSelections.add("秒差距\npc");
            iAdapter = new SelectionRecyclerViewAdapter(iSelections, view -> {
                Intent returnIntent = new Intent(SelectionActivity.this, ConversionActivity.class);
                returnIntent.putExtra("layout", R.layout.activity_conversion_length);
                returnIntent.putExtra("inputNum", inputNum);
                if(type.equals("input")) {
                    returnIntent.putExtra("outputUnit", intent.getStringExtra("outputUnit"));
                    if(view.findViewById(R.id.selection_textView) != null)
                        returnIntent.putExtra("inputUnit", ((TextView) view.findViewById(R.id.selection_textView)).getText().toString());
                    else
                        returnIntent.putExtra("inputUnit", ((TextView) ((View) view.getParent()).findViewById(R.id.selection_textView)).getText().toString());
                }
                else if(type.equals("output")) {
                    returnIntent.putExtra("inputUnit", intent.getStringExtra("inputUnit"));
                    if(view.findViewById(R.id.selection_textView) != null)
                        returnIntent.putExtra("outputUnit", ((TextView) view.findViewById(R.id.selection_textView)).getText().toString());
                    else
                        returnIntent.putExtra("outputUnit", ((TextView) ((View) view.getParent()).findViewById(R.id.selection_textView)).getText().toString());
                }
                startActivity(returnIntent);
                this.finish();
            });

            eSelections.add("英寸\nin");
            eSelections.add("英尺\nft");
            eSelections.add("码\nyd");
            eSelections.add("英寻\nfm");
            eSelections.add("弗隆\nfur");
            eSelections.add("英里\nmi");
            eSelections.add("海里\nnmi");
            eAdapter = new SelectionRecyclerViewAdapter(eSelections, view -> {
                Intent returnIntent = new Intent(SelectionActivity.this, ConversionActivity.class);
                returnIntent.putExtra("layout", R.layout.activity_conversion_length);
                returnIntent.putExtra("inputNum", inputNum);
                if(type.equals("input")) {
                    returnIntent.putExtra("outputUnit", intent.getStringExtra("outputUnit"));
                    if(view.findViewById(R.id.selection_textView) != null)
                        returnIntent.putExtra("inputUnit", ((TextView) view.findViewById(R.id.selection_textView)).getText().toString());
                    else
                        returnIntent.putExtra("inputUnit", ((TextView) ((View) view.getParent()).findViewById(R.id.selection_textView)).getText().toString());
                }
                else if(type.equals("output")) {
                    returnIntent.putExtra("inputUnit", intent.getStringExtra("inputUnit"));
                    if(view.findViewById(R.id.selection_textView) != null)
                        returnIntent.putExtra("outputUnit", ((TextView) view.findViewById(R.id.selection_textView)).getText().toString());
                    else
                        returnIntent.putExtra("outputUnit", ((TextView) ((View) view.getParent()).findViewById(R.id.selection_textView)).getText().toString());
                }
                startActivity(returnIntent);
                this.finish();
            });
        }
        else if(selection.equals("volume")) {
            iSelections.add("立方毫米\nmm3");
            iSelections.add("立方厘米\ncm3");
            iSelections.add("毫升\nml");
            iSelections.add("厘升\ncl");
            iSelections.add("分升\ndl");
            iSelections.add("立方分米\ndm3");
            iSelections.add("升\nl");
            iSelections.add("公石\nhl");
            iSelections.add("立方米\nm3");
            iAdapter = new SelectionRecyclerViewAdapter(iSelections, view -> {
                Intent returnIntent = new Intent(SelectionActivity.this, ConversionActivity.class);
                returnIntent.putExtra("layout", R.layout.activity_conversion_volume);
                returnIntent.putExtra("inputNum", inputNum);
                if(type.equals("input")) {
                    returnIntent.putExtra("outputUnit", intent.getStringExtra("outputUnit"));
                    if(view.findViewById(R.id.selection_textView) != null)
                        returnIntent.putExtra("inputUnit", ((TextView) view.findViewById(R.id.selection_textView)).getText().toString());
                    else
                        returnIntent.putExtra("inputUnit", ((TextView) ((View) view.getParent()).findViewById(R.id.selection_textView)).getText().toString());
                }
                else if(type.equals("output")) {
                    returnIntent.putExtra("inputUnit", intent.getStringExtra("inputUnit"));
                    if(view.findViewById(R.id.selection_textView) != null)
                        returnIntent.putExtra("outputUnit", ((TextView) view.findViewById(R.id.selection_textView)).getText().toString());
                    else
                        returnIntent.putExtra("outputUnit", ((TextView) ((View) view.getParent()).findViewById(R.id.selection_textView)).getText().toString());
                }
                startActivity(returnIntent);
                this.finish();
            });
            eSelections.add("立方英寸\nin3");
            eSelections.add("英制液量盎司\nfloz");
            eSelections.add("英制加仑\nUS gal");
            eSelections.add("美制加仑\nUK gal");
            eSelections.add("立方英尺\nft3");
            eSelections.add("立方码\nyd3");
            eSelections.add("亩英尺\naf3");
            eAdapter = new SelectionRecyclerViewAdapter(eSelections, view -> {
                Intent returnIntent = new Intent(SelectionActivity.this, ConversionActivity.class);
                returnIntent.putExtra("layout", R.layout.activity_conversion_volume);
                returnIntent.putExtra("inputNum", inputNum);
                if(type.equals("input")) {
                    returnIntent.putExtra("outputUnit", intent.getStringExtra("outputUnit"));
                    if(view.findViewById(R.id.selection_textView) != null)
                        returnIntent.putExtra("inputUnit", ((TextView) view.findViewById(R.id.selection_textView)).getText().toString());
                    else
                        returnIntent.putExtra("inputUnit", ((TextView) ((View) view.getParent()).findViewById(R.id.selection_textView)).getText().toString());
                }
                else if(type.equals("output")) {
                    returnIntent.putExtra("inputUnit", intent.getStringExtra("inputUnit"));
                    if(view.findViewById(R.id.selection_textView) != null)
                        returnIntent.putExtra("outputUnit", ((TextView) view.findViewById(R.id.selection_textView)).getText().toString());
                    else
                        returnIntent.putExtra("outputUnit", ((TextView) ((View) view.getParent()).findViewById(R.id.selection_textView)).getText().toString());
                }
                startActivity(returnIntent);
                this.finish();
            });
        }
        else if(selection.equals("scale")) {
            iSelections.add("二进制\nbinary");
            iSelections.add("十进制\ndecimal");
            iSelections.add("十六进制\nhex");
            iAdapter = new SelectionRecyclerViewAdapter(iSelections, view -> {
                Intent returnIntent = new Intent(SelectionActivity.this, ConversionActivity.class);
                returnIntent.putExtra("layout", R.layout.activity_conversion_scale);
                returnIntent.putExtra("inputNum", inputNum);
                if(type.equals("input")) {
                    returnIntent.putExtra("outputUnit", intent.getStringExtra("outputUnit"));
                    if(view.findViewById(R.id.selection_textView) != null)
                        returnIntent.putExtra("inputUnit", ((TextView) view.findViewById(R.id.selection_textView)).getText().toString());
                    else
                        returnIntent.putExtra("inputUnit", ((TextView) ((View) view.getParent()).findViewById(R.id.selection_textView)).getText().toString());
                }
                else if(type.equals("output")) {
                    returnIntent.putExtra("inputUnit", intent.getStringExtra("inputUnit"));
                    if(view.findViewById(R.id.selection_textView) != null)
                        returnIntent.putExtra("outputUnit", ((TextView) view.findViewById(R.id.selection_textView)).getText().toString());
                    else
                        returnIntent.putExtra("outputUnit", ((TextView) ((View) view.getParent()).findViewById(R.id.selection_textView)).getText().toString());
                }
                startActivity(returnIntent);
                this.finish();
            });
            TextView internationalTextView = findViewById(R.id.international_textView);
            internationalTextView.setVisibility(View.GONE);
            TextView EnglishTextView = findViewById(R.id.English_textView);
            EnglishTextView.setVisibility(View.GONE);
            View line = findViewById(R.id.line);
            line.setVisibility(View.GONE);
        }
        else if(selection.equals("rate")) {
            iSelections.add("澳门元\nMOP");
            iSelections.add("俄罗斯卢布\nRUB");
            iSelections.add("港元\nHKD");
            iSelections.add("韩元\nKRW");
            iSelections.add("美元\nUSD");
            iSelections.add("欧元\nEUR");
            iSelections.add("人民币\nCNY");
            iSelections.add("日元\nJPY");
            iSelections.add("新加坡元\nSGD");
            iSelections.add("新台币\nTWD");
            iSelections.add("英镑\nGBP");
            iAdapter = new SelectionRecyclerViewAdapter(iSelections, view -> {
                Intent returnIntent = new Intent(SelectionActivity.this, MainActivity.class);
                returnIntent.putExtra("inputNum", inputNum);
                if(type.equals("input")) {
                    returnIntent.putExtra("outputUnit", intent.getStringExtra("outputUnit"));
                    if(view.findViewById(R.id.selection_textView) != null)
                        returnIntent.putExtra("inputUnit", ((TextView) view.findViewById(R.id.selection_textView)).getText().toString());
                    else
                        returnIntent.putExtra("inputUnit", ((TextView) ((View) view.getParent()).findViewById(R.id.selection_textView)).getText().toString());
                }
                else if(type.equals("output")) {
                    returnIntent.putExtra("inputUnit", intent.getStringExtra("inputUnit"));
                    if(view.findViewById(R.id.selection_textView) != null)
                        returnIntent.putExtra("outputUnit", ((TextView) view.findViewById(R.id.selection_textView)).getText().toString());
                    else
                        returnIntent.putExtra("outputUnit", ((TextView) ((View) view.getParent()).findViewById(R.id.selection_textView)).getText().toString());
                }
                startActivity(returnIntent);
                this.finish();
            });
            TextView internationalTextView = findViewById(R.id.international_textView);
            internationalTextView.setVisibility(View.GONE);
            TextView EnglishTextView = findViewById(R.id.English_textView);
            EnglishTextView.setVisibility(View.GONE);
            View line = findViewById(R.id.line);
            line.setVisibility(View.GONE);
        }
        iRecyclerView.setAdapter(iAdapter);
        LinearLayoutManager iLayoutManager = new LinearLayoutManager(SelectionActivity.this);
        iRecyclerView.setLayoutManager(iLayoutManager);
        iAdapter.notifyDataSetChanged();
        iRecyclerView.scrollToPosition(0);
        if(! eSelections.isEmpty()) {
            eRecyclerView.setAdapter(eAdapter);
            LinearLayoutManager eLayoutManager = new LinearLayoutManager(SelectionActivity.this);
            eRecyclerView.setLayoutManager(eLayoutManager);
            eAdapter.notifyDataSetChanged();
            eRecyclerView.scrollToPosition(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.help:
                Toast.makeText(SelectionActivity.this, "这是帮助", Toast.LENGTH_SHORT).show();
                break;
            case R.id.history:
                Intent intent = new Intent(SelectionActivity.this, HistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.exit:
                ActivityCollector.finishAll();
        }
        return super.onContextItemSelected(item);
    }
}