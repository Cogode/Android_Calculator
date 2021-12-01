package com.example.calculator.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculator.R;
import com.example.calculator.util.ActivityCollector;

public class ConversionActivity extends AppCompatActivity {
    private int layout;
    private TextView input;
    private TextView output;
    private TextView inputUnitTextView;
    private TextView outputUnitTextView;
    private EditText inputEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(ConversionActivity.this);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        layout = intent.getIntExtra("layout", R.layout.activity_conversion_length);
        setContentView(layout);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if(layout == R.layout.activity_conversion_length) {
            if(actionBar != null)
                actionBar.setTitle("长度换算");
        }
        else if(layout == R.layout.activity_conversion_volume) {
            if(actionBar != null)
                actionBar.setTitle("体积换算");
        }
        else if(layout == R.layout.activity_conversion_scale) {
            if(actionBar != null)
                actionBar.setTitle("进制换算");
        }
        output = findViewById(R.id.output_textView);
        inputUnitTextView = findViewById(R.id.input_unit_textView);
        outputUnitTextView = findViewById(R.id.output_unit_textView);

        if(layout == R.layout.activity_conversion_length || layout == R.layout.activity_conversion_volume) {
            GridLayout keyboard = findViewById(R.id.keyboard);
            int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
            int columnCount = keyboard.getColumnCount();
            for(int i = 0; i < keyboard.getChildCount(); i++) {
                Button button = (Button) keyboard.getChildAt(i);
                button.setWidth(screenWidth / columnCount);
            }
            input = findViewById(R.id.input_textView);
        }
        else if(layout == R.layout.activity_conversion_scale) {
            inputEdit = findViewById(R.id.input_editText);
            Button calculateBtn = findViewById(R.id.calculate_btn);
            calculateBtn.setOnClickListener(view -> refreshOutput());
        }

        String inputUnit = intent.getStringExtra("inputUnit");
        String outputUnit = intent.getStringExtra("outputUnit");
        String inputNum = intent.getStringExtra("inputNum");
        if(inputUnit != null && outputUnit != null && inputNum != null) {
            inputUnitTextView.setText(inputUnit);
            outputUnitTextView.setText(outputUnit);
            if(input != null)
                input.setText(inputNum);
            else if(inputEdit != null)
                inputEdit.setText(inputNum);
            refreshOutput();
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
            case android.R.id.home:
                this.finish();
                break;
            case R.id.help:
                Toast.makeText(ConversionActivity.this, "这是帮助", Toast.LENGTH_SHORT).show();
                break;
            case R.id.history:
                Intent intent = new Intent(ConversionActivity.this, HistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.exit:
                ActivityCollector.finishAll();
        }
        return super.onContextItemSelected(item);
    }

    public double toStandard(String type, String unit, double input) {
        if(type.equals("length")) {
            if(unit.equals("pm"))
                return input / Math.pow(10, 12);
            else if(unit.equals("nm"))
                return input / Math.pow(10, 9);
            else if(unit.equals("um"))
                return input / Math.pow(10, 6);
            else if(unit.equals("mm"))
                return input / Math.pow(10, 3);
            else if(unit.equals("cm"))
                return input / Math.pow(10, 2);
            else if(unit.equals("in"))
                return input * 0.025400000025908;
            else if(unit.equals("dm"))
                return input * Math.pow(10, 1);
            else if(unit.equals("ft"))
                return input * 0.304799999536704;
            else if(unit.equals("yd"))
                return input * 0.914399998610112;
            else if(unit.equals("m"))
                return input;
            else if(unit.equals("fm"))
                return input * 1.828800164445711;
            else if(unit.equals("fur"))
                return input * 201.166767250050292;
            else if(unit.equals("km"))
                return input * Math.pow(10, 3);
            else if(unit.equals("mi"))
                return input * 1609.269391696169939;
            else if(unit.equals("nmi"))
                return input * 1851.851851851851852;
            else if(unit.equals("ld"))
                return input * 38400983.065166468261587;
            else if(unit.equals("AU"))
                return input * 149597582503.066750441313868;
            else if(unit.equals("ly"))
                return input * 9460737937559129.612109744560076;
            else if(unit.equals("pc"))
                return input * 30856864263739790.235036735096906;
        }

        else if(type.equals("volume")) {
            if(unit.equals("mm3"))
                return input / Math.pow(10, 9);
            else if(unit.equals("cm3") || unit.equals("ml"))
                return input / Math.pow(10, 6);
            else if(unit.equals("cl"))
                return input / Math.pow(10, 5);
            else if(unit.equals("in3"))
                return input * 0.000016387037037;
            else if(unit.equals("floz"))
                return input * 0.00002841;
            else if(unit.equals("dl"))
                return input / Math.pow(10, 4);
            else if(unit.equals("dm3") || unit.equals("l"))
                return input / Math.pow(10, 3);
            else if(unit.equals("US gal"))
                return input * 0.0037854117834;
            else if(unit.equals("UK gal"))
                return input * 0.004546091880673;
            else if(unit.equals("ft3"))
                return input * 0.028316800022182;
            else if(unit.equals("hl"))
                return input / 10;
            else if(unit.equals("yd3"))
                return input * 0.764553583279152;
            else if(unit.equals("m3"))
                return input;
            else if(unit.equals("af3"))
                return input * 1233.501911927963488;
        }
        return 0;
    }

    public double getConversion(String type, String unit, double input) {
        if(type.equals("length")) {
            if(unit.equals("pm"))
                return input * Math.pow(10, 12);
            else if(unit.equals("nm"))
                return input * Math.pow(10, 9);
            else if(unit.equals("um"))
                return input * Math.pow(10, 6);
            else if(unit.equals("mm"))
                return input * Math.pow(10, 3);
            else if(unit.equals("cm"))
                return input * Math.pow(10, 2);
            else if(unit.equals("in"))
                return input * 39.3700787;
            else if(unit.equals("dm"))
                return input * Math.pow(10, 1);
            else if(unit.equals("ft"))
                return input * 3.2808399;
            else if(unit.equals("yd"))
                return input * 1.0936133;
            else if(unit.equals("m"))
                return input;
            else if(unit.equals("fm"))
                return input * 0.5468066;
            else if(unit.equals("fur"))
                return input * 0.004971;
            else if(unit.equals("km"))
                return input * Math.pow(10, 3);
            else if(unit.equals("mi"))
                return input * 0.0006214;
            else if(unit.equals("nmi"))
                return input * 0.00054;
            else if(unit.equals("ld"))
                return input * 2.6041E-8;
            else if(unit.equals("AU"))
                return input * 6.6846E-12;
            else if(unit.equals("ly"))
                return input * 1.057E-16;
            else if(unit.equals("pc"))
                return input * 3.24077E-17;
        }
        
        else if(type.equals("volume")) {
            if(unit.equals("mm3"))
                return input * Math.pow(10, 9);
            else if(unit.equals("cm3") || unit.equals("ml"))
                return input * Math.pow(10, 6);
            else if(unit.equals("cl"))
                return input * Math.pow(10, 5);
            else if(unit.equals("in3"))
                return input * 61023.8445022;
            else if(unit.equals("floz"))
                return input * 35198.873636;
            else if(unit.equals("dl"))
                return input * Math.pow(10, 4);
            else if(unit.equals("dm3") || unit.equals("l"))
                return input * Math.pow(10, 3);
            else if(unit.equals("US gal"))
                return input * 264.1720524;
            else if(unit.equals("UK gal"))
                return input * 219.9691573;
            else if(unit.equals("ft3"))
                return input * 35.3147248;
            else if(unit.equals("hl"))
                return input * 10;
            else if(unit.equals("yd3"))
                return input * 1.3079528;
            else if(unit.equals("m3"))
                return input;
            else if(unit.equals("af3"))
                return input * 0.0008107;
        }
        return 0;
    }

    private void joinToInput(String inputNum) {
        if(input.getText().toString().equals("0"))
            input.setText(inputNum);
        else
            input.setText(input.getText().toString() + inputNum);
    }

    private void refreshOutput() {
        if(layout == R.layout.activity_conversion_length || layout == R.layout.activity_conversion_volume) {
            String inputNum = input.getText().toString();
            double result = 0;
            if(!inputNum.equals("0")) {
                int integerPart[] = new int[50];
                int decimalPart[] = new int[50];
                int sign = 0;
                int m = 0;
                int n = 0;
                boolean isInteger = true;
                while(sign < inputNum.length()) {
                    String s = String.valueOf(inputNum.charAt(sign ++));
                    if(s.equals("."))
                        isInteger = false;
                    else {
                        if(isInteger)
                            integerPart[m ++] = Integer.parseInt(s);
                        else
                            decimalPart[n ++] = Integer.parseInt(s);
                    }
                }
                for(int i = 0; i < m; i++)
                    result = result * 10 + integerPart[i];
                for(int i = 0; i < n; i++)
                    result += decimalPart[i] / Math.pow(10, i + 1);
                String type = "";
                if(layout == R.layout.activity_conversion_length)
                    type = "length";
                else if(layout == R.layout.activity_conversion_volume)
                    type = "volume";
                else if(layout == R.layout.activity_conversion_scale)
                    type = "scale";
                result = getConversion(type, outputUnitTextView.getText().toString().split("\n")[1],
                        toStandard(type, inputUnitTextView.getText().toString().split("\n")[1], result));
            }
            String temp = String.valueOf(result);
            if(temp.charAt(temp.length() - 1) == '0' && temp.charAt(temp.length() - 2) == '.')
                temp = temp.substring(0, temp.length() - 2);
            output.setText(temp);
        }
        else if(layout == R.layout.activity_conversion_scale) {
            String inputUnit = inputUnitTextView.getText().toString().split("\n")[1];
            String outputUnit = outputUnitTextView.getText().toString().split("\n")[1];
            String inputNum = inputEdit.getText().toString();
            try {
                output.setText(scaleTransform(inputUnit, outputUnit, inputNum));
            } catch(Exception e) {
                Toast.makeText(ConversionActivity.this, "请输入相应格式数字", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public String scaleTransform(String inputUnit, String outputUnit, String inputNum) {
        int temp = 0;

        if(inputUnit.equals("binary"))
            temp = Integer.parseInt(inputNum, 2);
        else if(inputUnit.equals("decimal"))
            temp = Integer.parseInt(inputNum, 10);
        else if(inputUnit.equals("hex"))
            temp = Integer.parseInt(inputNum, 16);

        if(outputUnit.equals("binary"))
            return Integer.toBinaryString(temp);
        else if(outputUnit.equals("decimal"))
            return String.valueOf(temp);
        else if(outputUnit.equals("hex"))
            return Integer.toHexString(temp);

        return "0";
    }

    public void onClickOne(View view) {
        joinToInput("1");
        refreshOutput();
    }

    public void onClickTwo(View view) {
        joinToInput("2");
        refreshOutput();
    }

    public void onClickThree(View view) {
        joinToInput("3");
        refreshOutput();
    }

    public void onClickFour(View view) {
        joinToInput("4");
        refreshOutput();
    }

    public void onClickFive(View view) {
        joinToInput("5");
        refreshOutput();
    }

    public void onClickSix(View view) {
        joinToInput("6");
        refreshOutput();
    }

    public void onClickSeven(View view) {
        joinToInput("7");
        refreshOutput();
    }

    public void onClickEight(View view) {
        joinToInput("8");
        refreshOutput();
    }

    public void onClickNine(View view) {
        joinToInput("9");
        refreshOutput();
    }

    public void onClickZero(View view) {
        joinToInput("0");
        refreshOutput();
    }

    public void onClickDoubleZero(View view) {
        if(! input.getText().toString().equals("0"))
            joinToInput("00");
        refreshOutput();
    }

    public void onClickPoint(View view) {
        String inputNum = input.getText().toString();
        if(inputNum.equals("0"))
            joinToInput(".");
        else {
            char c = inputNum.charAt(inputNum.length() - 1);
            if(c != '.')
                joinToInput(".");
        }
        refreshOutput();
    }

    public void onClickClearTail(View view) {
        String inputNum = input.getText().toString();
        if(! inputNum.equals("0"))
            input.setText(inputNum.substring(0, inputNum.length() - 1));
        refreshOutput();
    }

    public void onClickClearAll(View view) {
        input.setText("0");
        refreshOutput();
    }

    public void onClickSelection(View view) {
        Intent intent = new Intent(ConversionActivity.this, SelectionActivity.class);
        if(layout == R.layout.activity_conversion_length)
            intent.putExtra("selection", "length");
        else if(layout == R.layout.activity_conversion_volume)
            intent.putExtra("selection", "volume");
        else if(layout == R.layout.activity_conversion_scale)
            intent.putExtra("selection", "scale");

        if(view.getId() == R.id.input_view) {
            intent.putExtra("type", "input");
            if(layout == R.layout.activity_conversion_length || layout == R.layout.activity_conversion_volume)
                intent.putExtra("inputNum", ((TextView) view.findViewById(R.id.input_textView)).getText().toString());
            else if(layout == R.layout.activity_conversion_scale)
                intent.putExtra("inputNum", ((EditText) view.findViewById(R.id.input_editText)).getText().toString());
            intent.putExtra("outputUnit",
                    ((TextView) ((View) view.getParent()).findViewById(R.id.output_view).findViewById(R.id.output_unit_textView)).getText().toString());
        }
        else if(view.getId() == R.id.output_view) {
            intent.putExtra("type", "output");
            if(layout == R.layout.activity_conversion_length || layout == R.layout.activity_conversion_volume)
                intent.putExtra("inputNum",
                        ((TextView) ((View) view.getParent()).findViewById(R.id.input_view).findViewById(R.id.input_textView)).getText().toString());
            else if(layout == R.layout.activity_conversion_scale)
                intent.putExtra("inputNum",
                        ((EditText) ((View) view.getParent()).findViewById(R.id.input_view).findViewById(R.id.input_editText)).getText().toString());
            intent.putExtra("inputUnit",
                    ((TextView) ((View) view.getParent()).findViewById(R.id.input_view).findViewById(R.id.input_unit_textView)).getText().toString());
        }
        startActivity(intent);
        this.finish();
    }
}