package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.calculator.adapter.MyPageAdapter;
import com.example.calculator.fragment.MyFragment;
import com.google.android.material.tabs.TabLayout;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    String expression = "";
    ArrayList<Double> operands = new ArrayList<>();
    ArrayList<String> operators = new ArrayList<>();
    TabLayout tabLayout;
    ViewPager viewPager;
    MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new MyFragment(R.layout.fragment_first));
        fragmentList.add(new MyFragment(R.layout.fragment_first));
        fragmentList.add(new MyFragment(R.layout.fragment_first));
        myFragment = (MyFragment) fragmentList.get(0);

        viewPager.setAdapter(new MyPageAdapter(getSupportFragmentManager(), fragmentList));
        tabLayout.setupWithViewPager(viewPager);
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
                Toast.makeText(this, "这是帮助", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "这是设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit:
                System.exit(0);
        }
        return true;
    }

    public void setText(String text) {
        myFragment.setText(text);
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
        expression += "+";
        setText(expression);
    }

    public void onClickSub(View view) {
        expression += "-";
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

    public void onClickClearOne(View view) {
        if(!expression.equals("")) {
            expression = expression.substring(0, expression.length() - 1);
            setText(expression);
        }
    }

    public void onClickEqual(View view) {
        expression += "#";
        operators.add("#");
        int sign = 0;
        String s = String.valueOf(expression.charAt(sign ++));

        while(!s.equals("#") || !operators.get(operators.size() - 1).equals("#")) {
            if(isNumber(s)) {
                double result = 0;
                int integerPart[] = new int[20];
                int decimalPart[] = new int[20];
                int m = 0;
                int n = 0;
                boolean isInteger = true;

                integerPart[m ++] = Integer.parseInt(s);
                s = String.valueOf(expression.charAt(sign ++));

                while(isNumber(s)) {
                    if(s.equals("."))
                        isInteger = false;
                    else {
                        if(isInteger)
                            integerPart[m++] = Integer.parseInt(s);
                        else
                            decimalPart[n++] = Integer.parseInt(s);
                    }

                    s = String.valueOf(expression.charAt(sign ++));
                }

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
                        if(s.equals("l") && expression.charAt(sign) == 'n') {
                            operators.add("L");
                            sign += 1;
                        }
                        else {
                            operators.add(s);
                            if(isFunction(s))
                                sign += 2;
                        }
                        s = String.valueOf(expression.charAt(sign++));
                        break;
                    case '=':
                        operators.remove(operators.size() - 1);
                        s = String.valueOf(expression.charAt(sign++));
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

        if(!expression.equals("#")) {
            expression = expression.split("#")[0];
            expression += "=" + operands.get(operands.size() - 1);
            if(expression.charAt(expression.length() - 1) == '0' && expression.charAt(expression.length() - 2) == '.')
                expression = expression.substring(0, expression.length() - 2);
            setText(expression);
        }
        expression = "";
        operands.clear();
        operators.clear();
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