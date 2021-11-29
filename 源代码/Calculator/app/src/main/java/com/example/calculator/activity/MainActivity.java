package com.example.calculator.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculator.R;
import com.example.calculator.adapter.MyPagerAdapter;
import com.example.calculator.fragment.MyFragment;
import com.example.calculator.util.ActivityCollector;
import com.google.android.material.tabs.TabLayout;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public String expression = "";
    private ArrayList<Double> operands = new ArrayList<>();
    private ArrayList<String> operators = new ArrayList<>();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyFragment firstFragment;
    private MyPagerAdapter adapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(MainActivity.this);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
            actionBar.setTitle("");

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            tabLayout = findViewById(R.id.tabLayout);
            viewPager = findViewById(R.id.viewPager);
            fragments.add(new MyFragment(R.layout.fragment_first));
            fragments.add(new MyFragment(R.layout.fragment_second));
            fragments.add(new MyFragment(R.layout.fragment_third));
            firstFragment = (MyFragment) fragments.get(0);
            adapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
            viewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);
        }
        else {
            GridLayout keyboard = findViewById(R.id.keyboard);
            int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
            int columnCount = keyboard.getColumnCount();
            for(int i = 0; i < keyboard.getChildCount(); i ++) {
                Button button = (Button) keyboard.getChildAt(i);
                button.setWidth(screenWidth / columnCount);
            }
            textView = findViewById(R.id.textView);
            Intent intent = getIntent();
            if(intent.getStringExtra("expression") != null) {
                expression = intent.getStringExtra("expression");
                textView.setText(expression);
            }
        }
    }

    public ViewPager viewPager() {
        return viewPager;
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.finish();
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.putExtra("expression", expression);
        startActivity(intent);
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
                Toast.makeText(MainActivity.this, "这是帮助", Toast.LENGTH_SHORT).show();
                break;
            case R.id.history:
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.exit:
                ActivityCollector.finishAll();
        }
        return true;
    }

    public void setText(String text) {
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            firstFragment.setText(text);
        else
            textView.setText(text);
    }

    public void onClickNumber() {
        if(!expression.equals("")) {
            char last = expression.charAt(expression.length() - 1);
            if(last == '%' || last == '!' || last == ')')
                expression += getResources().getString(R.string.multi);
        }
    }

    public void onClickOne(View view) {
        onClickNumber();
        expression += "1";
        setText(expression);
    }

    public void onClickTwo(View view) {
        onClickNumber();
        expression += "2";
        setText(expression);
    }

    public void onClickThree(View view) {
        onClickNumber();
        expression += "3";
        setText(expression);
    }

    public void onClickFour(View view) {
        onClickNumber();
        expression += "4";
        setText(expression);
    }

    public void onClickFive(View view) {
        onClickNumber();
        expression += "5";
        setText(expression);
    }

    public void onClickSix(View view) {
        onClickNumber();
        expression += "6";
        setText(expression);
    }

    public void onClickSeven(View view) {
        onClickNumber();
        expression += "7";
        setText(expression);
    }

    public void onClickEight(View view) {
        onClickNumber();
        expression += "8";
        setText(expression);
    }

    public void onClickNine(View view) {
        onClickNumber();
        expression += "9";
        setText(expression);
    }

    public void onClickZero(View view) {
        onClickNumber();
        expression += "0";
        setText(expression);
    }

    public void onClickDoubleZero(View view) {
        onClickNumber();
        if(expression.equals(""))
            expression += "0";
        else {
            if(isNumber(String.valueOf(expression.charAt(expression.length() - 1))))
                expression += "00";
            else
                expression += "0";
        }
        setText(expression);
    }

    public void onClickPoint(View view) {
        onClickNumber();
        if(expression.equals(""))
            expression += "0.";
        else {
            char c = expression.charAt(expression.length() - 1);
            if(c != '.') {
                if(isNumber(String.valueOf(c)))
                    expression += ".";
                else
                    expression += "0.";
            }
        }
        setText(expression);
    }

    public void onClickPI(View view) {
        expression += getResources().getString(R.string.PI);
        setText(expression);
    }

    public void onClickE(View view) {
        expression += "e";
        setText(expression);
    }

    public void onClickPercent(View view) {
        if(!expression.equals("")) {
            char c = expression.charAt(expression.length() - 1);
            if(c == ')' || isNumber(String.valueOf(c)) || isIrrational(String.valueOf(c)))
                expression += "%";
        }
        setText(expression);
    }

    public void onClickLeftParentheses(View view) {
        if(expression.equals(""))
            expression += "(";
        else {
            char c = expression.charAt(expression.length() - 1);
            if(isOperator(String.valueOf(c)) || isIrrational(String.valueOf(c)))
                expression += "(";
        }
        setText(expression);
    }

    public void onClickRightParentheses(View view) {
        if(expression.equals(""))
            expression += ")";
        else {
            if(!isOperator(String.valueOf(expression.charAt(expression.length() - 1))))
                expression += ")";
        }
        setText(expression);
    }

    public void onClickAdd(View view) {
        if(! expression.equals("")) {
            if(expression.charAt(expression.length() - 1) != '+' && expression.charAt(expression.length() - 1) != '-')
                expression += "+";
        }
        setText(expression);
    }

    public void onClickSub(View view) {
        if(expression.equals(""))
            expression += "0-";
        else {
            if(expression.charAt(expression.length() - 1) == '+')
                expression = expression.substring(0, expression.length() - 1) + "-";
            else if(expression.charAt(expression.length() - 1) == '-')
                expression = expression.substring(0, expression.length() - 1) + "+";
            else
                expression += "-";
        }
        setText(expression);
    }

    public void onClickMulti(View view) {
        if(!expression.equals("")) {
            char c = expression.charAt(expression.length() - 1);
            if(c >= '0' && c <= '9' || c == ')' || c == '%' || c == '!' || isIrrational(String.valueOf(c)))
                expression += getResources().getString(R.string.multi);
        }
        setText(expression);
    }

    public void onClickDivide(View view) {
        if(!expression.equals("")) {
            char c = expression.charAt(expression.length() - 1);
            if (c >= '0' && c <= '9' || c == ')' || c == '%' || c == '!' || isIrrational(String.valueOf(c)))
                expression += getResources().getString(R.string.divide);
        }
        setText(expression);
    }

    public void onClickSin(View view) {
        if(expression.equals(""))
            expression += "sin";
        else {
            char c = expression.charAt(expression.length() - 1);
            if(isOperator(String.valueOf(c)) || isIrrational(String.valueOf(c)))
                expression += "sin";
        }
        setText(expression);
    }

    public void onClickCos(View view) {
        if(expression.equals(""))
            expression += "cos";
        else {
            char c = expression.charAt(expression.length() - 1);
            if(isOperator(String.valueOf(c)) || isIrrational(String.valueOf(c)))
                expression += "cos";
        }
        setText(expression);
    }

    public void onClickTan(View view) {
        if(expression.equals(""))
            expression += "tan";
        else {
            char c = expression.charAt(expression.length() - 1);
            if(isOperator(String.valueOf(c)) || isIrrational(String.valueOf(c)))
                expression += "tan";
        }
        setText(expression);
    }

    public void onClickLog(View view) {
        if(expression.equals(""))
            expression += "log";
        else {
            char c = expression.charAt(expression.length() - 1);
            if(isOperator(String.valueOf(c)) || isIrrational(String.valueOf(c)))
                expression += "log";
        }
        setText(expression);
    }

    public void onClickLn(View view) {
        if(expression.equals(""))
            expression += "ln";
        else {
            char c = expression.charAt(expression.length() - 1);
            if(isOperator(String.valueOf(c)) || isIrrational(String.valueOf(c)))
                expression += "ln";
        }
        setText(expression);
    }

    public void onClickFactorial(View view) {
        if(!expression.equals("")) {
            char c = expression.charAt(expression.length() - 1);
            if(isNumber(String.valueOf(c)) || c == ')' || isIrrational(String.valueOf(c)))
                expression += "!";
        }
        setText(expression);
    }

    public void onClickPower(View view) {
        if(!expression.equals("")) {
            char c = expression.charAt(expression.length() - 1);
            if(isNumber(String.valueOf(c))|| c == ')' || isIrrational(String.valueOf(c)))
                expression += "^";
        }
        setText(expression);
    }

    public void onClickRadical(View view) {
        if(expression.equals(""))
            expression += getResources().getString(R.string.radical);
        else {
            char c = expression.charAt(expression.length() - 1);
            if(isOperator(String.valueOf(c)) || isIrrational(String.valueOf(c)))
                expression += getResources().getString(R.string.radical);
        }
        setText(expression);
    }

    public void onClickClearAll(View view) {
        expression = "";
        setText(expression);
    }

    public void onClickClearTail(View view) {
        if(!expression.equals("")) {
            expression = expression.substring(0, expression.length() - 1);
            setText(expression);
        }
    }

    public void onClickEqual(View view) {
        try {
            expression += "#";    // 表达式
            operators.add("#");    // 符号栈
            int sign = 0;    // 读取的下标
            String s = String.valueOf(expression.charAt(sign ++));    // 单个字符

            // 循环遍历表达式的每个字符
            while(!s.equals("#") || !operators.get(operators.size() - 1).equals("#")) {
                if(isNumber(s)) {
                    int integerPart[] = new int[50];
                    int decimalPart[] = new int[50];
                    int m = 0;
                    int n = 0;
                    boolean isInteger = true;
                    double result = 0;

                    integerPart[m ++] = Integer.parseInt(s);
                    s = String.valueOf(expression.charAt(sign ++));

                    while(isNumber(s)) {
                        if(s.equals("."))
                            isInteger = false;    // 整数标志为，初始为true，出现小数为false
                        else {
                            if(isInteger)
                                integerPart[m ++] = Integer.parseInt(s);    // 整数部分，m初始为0
                            else
                                decimalPart[n ++] = Integer.parseInt(s);    // 小数部分，n初始为0
                        }
                        s = String.valueOf(expression.charAt(sign ++));
                    }

                    // 循环求值，整数部分加上小数部分即为该数的值
                    for(int i = 0; i < m; i ++)
                        result = result * 10 + integerPart[i];
                    for(int i = 0; i < n; i ++)
                        result += decimalPart[i] / Math.pow(10, i + 1);

                    operands.add(result);
                }

                else if(isIrrational(s)) {
                    int back = 0;
                    String next = String.valueOf(expression.charAt(sign));
                    if(sign > 1) {
                        String last = String.valueOf(expression.charAt(sign - 2));

                        if(isOperator(last) || isIrrational(last)) {
                            if(s.equals(getResources().getString(R.string.PI)))
                                operands.add(Math.PI);
                            else
                                operands.add(Math.E);
                            s = String.valueOf(expression.charAt(sign ++));
                            back = 1;
                        }
                        else {
                            if(s.equals(getResources().getString(R.string.PI)))
                                operands.add(Math.PI);
                            else
                                operands.add(Math.E);
                            s = "**";
                        }
                    }
                    else {
                        if(s.equals(getResources().getString(R.string.PI)))
                            operands.add(Math.PI);
                        else
                            operands.add(Math.E);
                        s = String.valueOf(expression.charAt(sign ++));
                        back += 1;
                    }

                    if(!isEnd(next)) {
                        s = "**";
                        sign -= back;
                    }
                }

                else {
                    switch(getPriority(operators.get(operators.size() - 1), s)) {
                        case '<':
                            if(s.equals("l") && expression.charAt(sign) == 'n') {    // 遇到ln函数
                                operators.add("L");
                                sign += 1;    // 下标后移一位
                            }
                            else {
                                operators.add(s);
                                if(isFunction(s))    // 遇到三位的函数，如sin
                                    sign += 2;    // 下标后移两位
                            }
                            s = String.valueOf(expression.charAt(sign ++));
                            break;
                        case '=':
                            operators.remove(operators.size() - 1);
                            s = String.valueOf(expression.charAt(sign ++));
                            break;
                        case '>':
                            String operator = operators.get(operators.size() - 1);
                            operators.remove(operators.size() - 1);
                            if(isSingleOperator(operator)) {
                                double num = operands.get(operands.size() - 1);
                                operands.remove(operands.size() - 1);
                                operands.add(complexOperate(num, operator));
                            }
                            else {
                                double secondNum = operands.get(operands.size() - 1);
                                operands.remove(operands.size() - 1);
                                double firstNum = operands.get(operands.size() - 1);
                                operands.remove(operands.size() - 1);
                                operands.add(simpleOperate(firstNum, operator, secondNum));
                            }
                            break;
                    }
                }
            }

            expression = expression.split("#")[0];
            expression += "=" + operands.get(operands.size() - 1);
            if(expression.charAt(expression.length() - 1) == '0' && expression.charAt(expression.length() - 2) == '.')
                expression = expression.substring(0, expression.length() - 2);
            setText(expression);
            SharedPreferences.Editor editor = getSharedPreferences("history", MODE_PRIVATE).edit();
            String history = getSharedPreferences("history", MODE_PRIVATE).getString("history", "");
            editor.putString("history", history + "\n" + expression);
            editor.apply();
            expression = "";
            operands.clear();
            operators.clear();
        } catch(Exception e) {
            Toast.makeText(MainActivity.this, "输入格式有误", Toast.LENGTH_SHORT).show();
            if(expression.equals("#"))
                expression = "";
            else
                expression = expression.split("#")[0];
        }
    }

    public boolean isNumber(String s) {
        char c = s.charAt(0);
        if(c >= '0' && c <= '9' || c == '.')
            return true;
        else
            return false;
    }

    public boolean isOperator(String s) {
        if(s.equals(getResources().getString(R.string.multi)))
            s = "*";
        if(s.equals(getResources().getString(R.string.divide)))
            s = "/";
        if(s.equals(getResources().getString(R.string.radical)))
            s = "√";
        char c = s.charAt(0);
        if(c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '(' || c == 'n' || c == 's' || c == 'g' || c == '√')
            return true;
        else
            return false;
    }

    public boolean isSingleOperator(String s) {
        if(s.equals("%") || s.equals("!") || s.equals(getResources().getString(R.string.radical)) || isFunction(s))
            return true;
        else
            return false;
    }

    public boolean isFunction(String s) {
        if(s.equals("s") || s.equals("c") || s.equals("t") || s.equals("l") || s.equals("L"))
            return true;
        else
            return false;
    }

    public boolean isIrrational(String s) {
        if(s.equals(getResources().getString(R.string.PI)) || s.equals("e"))
            return true;
        else
            return false;
    }

    public boolean isEnd(String s) {
        if(s.equals("+") || s.equals("-") || s.equals(getResources().getString(R.string.multi)) || s.equals(getResources().getString(R.string.divide)) || s.equals("^") || s.equals("%") || s.equals("!") || s.equals(")") || s.equals("#"))
            return true;
        else
            return false;
    }

    public char getPriority(String s1, String s2) {
        if(s1.equals(getResources().getString(R.string.multi)))
            s1 = "*";
        if(s2.equals(getResources().getString(R.string.multi)))
            s2 = "*";
        if(s1.equals(getResources().getString(R.string.divide)))
            s1 = "/";
        if(s2.equals(getResources().getString(R.string.divide)))
            s2 = "/";
        if(s1.equals(getResources().getString(R.string.radical)))
            s1 = "√";
        if(s2.equals(getResources().getString(R.string.radical)))
            s2 = "√";

        if(s1.equals("+") || s1.equals("-")) {
            if(s2.equals("+") || s2.equals("-") || s2.equals(")") || s2.equals("#"))
                return '>';
            else if(s2.equals("*") || s2.equals("/") || s2.equals("^") || s2.equals("(") || s2.equals("%") || s2.equals("!") || s2.equals("s") || s2.equals("c") || s2.equals("t") || s2.equals("l") || s2.equals("L") || s2.equals("√") || s2.equals("**"))
                return '<';
        }

        else if(s1.equals("*") || s1.equals("/") || s1.equals("^") || s1.equals("**")) {
            if(s2.equals("+") || s2.equals("-") || s2.equals("*") || s2.equals("/") || s2.equals(")") || s2.equals("#"))
                return '>';
            else if(s2.equals("^") || s2.equals("(") || s2.equals("%") || s2.equals("!") || s2.equals("s") || s2.equals("c") || s2.equals("t") || s2.equals("l") || s2.equals("L") || s2.equals("√") || s2.equals("**"))
                return '<';
        }

        else if(s1.equals("(")) {
            if(s2.equals(")"))
                return '=';
            else if(s2.equals("+") || s2.equals("-") || s2.equals("*") || s2.equals("/") || s2.equals("^") || s2.equals("(") || s2.equals("%") || s2.equals("!") || s2.equals("s") || s2.equals("c") || s2.equals("t") || s2.equals("l") || s2.equals("L") || s2.equals("√") || s2.equals("**"))
                return '<';
        }

        else if(s1.equals(")")) {
            if(s2.equals("+") || s2.equals("-") || s2.equals("*") || s2.equals("/") || s2.equals("^") || s2.equals(")") || s2.equals("#") || s2.equals("%") || s2.equals("!") || s2.equals("s") || s2.equals("c") || s2.equals("t") || s2.equals("l") || s2.equals("L") || s2.equals("√") || s2.equals("**"))
                return '>';
            else if(s2.equals("("))
                return '=';
        }

        else if(s1.equals("s") || s1.equals("c") || s1.equals("t") || s1.equals("l") || s1.equals("L") || s1.equals("√")) {
            if(s2.equals("+") || s2.equals("-") || s2.equals("*") || s2.equals("/") || s2.equals("^") || s2.equals(")") || s2.equals("#"))
                return '>';
            else if(s2.equals("(") || s2.equals("%") || s2.equals("!") || s2.equals("s") || s2.equals("c") || s2.equals("t") || s2.equals("l") || s2.equals("L") || s2.equals("√") || s2.equals("**"))
                return '<';
        }

        else if(s1.equals("%") || s1.equals("!"))
            return '>';

        else if(s1.equals("#")) {
            if(s2.equals("#"))
                return '=';
            else if(s2.equals("+") || s2.equals("-") || s2.equals("*") || s2.equals("/") || s2.equals("^") || s2.equals("(") || s2.equals(")") || s2.equals("%") || s2.equals("!") || s2.equals("s") || s2.equals("c") || s2.equals("t") || s2.equals("l") || s2.equals("L") || s2.equals("√") || s2.equals("**"))
                return '<';
        }

        return ' ';
    }

    public double simpleOperate(double m, String operator, double n) {
        BigDecimal b1 = new BigDecimal(Double.toString(m));
        BigDecimal b2 = new BigDecimal(Double.toString(n));
        if(operator.equals("+"))
            return b1.add(b2).doubleValue();
        else if(operator.equals("-"))
            return b1.subtract(b2).doubleValue();
        else if(operator.equals(getResources().getString(R.string.multi)) || operator.equals("**"))
            return b1.multiply(b2).doubleValue();
        else if(operator.equals(getResources().getString(R.string.divide)))
            return b1.divide(b2).doubleValue();
        else if(operator.equals("^"))
            return Math.pow(m, n);

        return 0;
    }

    public double complexOperate(double num, String type) {
        BigDecimal b = new BigDecimal(Double.toString(num));
        // type为函数首字符
        if(type.equals("s"))
            return Math.sin(num);
        else if(type.equals("c"))
            return Math.cos(num);
        else if(type.equals("t"))
            return Math.tan(num);
        else if(type.equals("l"))
            return Math.log10(num);
        else if(type.equals("L"))
            return Math.log(num);
        else if(type.equals("%"))
            return b.divide(new BigDecimal(Double.toString(100))).doubleValue();
        else if(type.equals("!"))
            return gamma(num);
        else if(type.equals(getResources().getString(R.string.radical)))
            return Math.sqrt(num);
        return 0;
    }

    public double gamma(double num) {
        double array[] = { 0.99999999999980993, 676.5203681218851, -1259.1392167224028,
                771.32342877765313, -176.61502916214059, 12.507343278686905,
                -0.13857109526572012, 9.9843695780195716e-6, 1.5056327351493116e-7 };
        int n = 7;

        if(num < 0.5)
            return Math.PI / (Math.sin(Math.PI * num) * gamma(1 - num));
        else {
            double x = array[0];
            for(int i = 1; i < (n + 2); i ++)
                x += array[i] / (num + i);
            double y = num + n + 0.5;
            return Math.sqrt(2 * Math.PI) * Math.pow(y, num + 0.5) * Math.exp(-y) * x;
        }
    }
}