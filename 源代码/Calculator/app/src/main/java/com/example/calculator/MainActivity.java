package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    TextView textView;
    String expression = "";
    ArrayList<Double> operands = new ArrayList<>();
    ArrayList<String> operators = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        int columnCount = gridLayout.getColumnCount();
        int screenWidth = this.getWindowManager().getDefaultDisplay().getWidth();
        for(int i = 0; i < gridLayout.getChildCount(); i ++) {
            Button button = (Button) gridLayout.getChildAt(i);
            button.setWidth(screenWidth / columnCount);
        }

        textView = findViewById(R.id.textView);
    }

    public void onClickOne(View view) {
        expression += "1";
        textView.setText(expression);
    }

    public void onClickTwo(View view) {
        expression += "2";
        textView.setText(expression);
    }

    public void onClickThree(View view) {
        expression += "3";
        textView.setText(expression);
    }

    public void onClickFour(View view) {
        expression += "4";
        textView.setText(expression);
    }

    public void onClickFive(View view) {
        expression += "5";
        textView.setText(expression);
    }

    public void onClickSix(View view) {
        expression += "6";
        textView.setText(expression);
    }

    public void onClickSeven(View view) {
        expression += "7";
        textView.setText(expression);
    }

    public void onClickEight(View view) {
        expression += "8";
        textView.setText(expression);
    }

    public void onClickNine(View view) {
        expression += "9";
        textView.setText(expression);
    }

    public void onClickZero(View view) {
        expression += "0";
        textView.setText(expression);
    }

    public void onClickDoubleZero(View view) {
        if(expression.equals(""))
            expression += "0";
        else {
            if(isNumber(String.valueOf(expression.charAt(expression.length() - 1))))
                expression += "00";
            else
                expression += "0";
        }
        textView.setText(expression);
    }

    public void onClickPoint(View view) {
        if(expression.equals(""))
            expression += "0.";
        else {
            if(expression.charAt(expression.length() - 1) != '.') {
                if(isNumber(String.valueOf(expression.charAt(expression.length() - 1))))
                    expression += ".";
                else
                    expression += "0.";
            }
        }
        textView.setText(expression);
    }

    public void onClickPercent(View view) {
        if(!expression.equals(""))
            if(expression.charAt(expression.length() - 1) >= '0' && expression.charAt(expression.length() - 1) <= '9')
                expression += "%";
        textView.setText(expression);
    }

    public void onClickAdd(View view) {
        expression += "+";
        textView.setText(expression);
    }

    public void onClickSub(View view) {
        expression += "-";
        textView.setText(expression);
    }

    public void onClickMulti(View view) {
        expression += getResources().getString(R.string.multi);
        textView.setText(expression);
    }

    public void onClickDivide(View view) {
        expression += getResources().getString(R.string.divide);
        textView.setText(expression);
    }

    public void onClickClearAll(View view) {
        expression = "";
        textView.setText(expression);
    }

    public void onClickClearOne(View view) {
        if(!expression.equals("")) {
            expression = expression.substring(0, expression.length() - 1);
            textView.setText(expression);
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
                    if(s.equals(".")) {
                        isInteger = false;
                        s = String.valueOf(expression.charAt(sign ++));
                    }

                    if(isInteger)
                        integerPart[m ++] = Integer.parseInt(s);
                    else
                        decimalPart[n ++] = Integer.parseInt(s);

                    s = String.valueOf(expression.charAt(sign ++));
                }

                for(int i = 0; i < m; i ++)
                    result = result * 10 + integerPart[i];
                for(int i = 0; i < n; i ++)
                    result += decimalPart[i] / Math.pow(10, i + 1);

                operands.add(result);
            }

            else {
                switch(getPriority(operators.get(operators.size() - 1), s)) {
                    case '<':
                        operators.add(s);
                        s = String.valueOf(expression.charAt(sign ++));
                        break;
                    case '=':
                        operators.remove(operators.size() - 1);
                        s = String.valueOf(expression.charAt(sign ++));
                        break;
                    case '>':
                        String operator = operators.get(operators.size() - 1);
                        operators.remove(operators.size() - 1);
                        if(operator.equals("%")) {
                            double num = operands.get(operands.size() - 1);
                            operands.remove(operands.size() - 1);
                            operands.add(num / 100);
                        }
                        else {
                            double secondNum = operands.get(operands.size() - 1);
                            operands.remove(operands.size() - 1);
                            double firstNum = operands.get(operands.size() - 1);
                            operands.remove(operands.size() - 1);
                            operands.add(operate(firstNum, operator, secondNum));
                        }
                        break;
                }
            }
        }

        expression = expression.split("#")[0];
        expression += "=" + operands.get(operands.size() - 1);
        if(expression.charAt(expression.length() - 1) == '0' && expression.charAt(expression.length() - 2) == '.')
            expression = expression.substring(0, expression.length() - 2);
        textView.setText(expression);
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

    public char getPriority(String s1, String s2) {
        // 返回两个操作符的优先级
        if(s1.equals(getResources().getString(R.string.multi)))
            s1 = "*";
        if(s1.equals(getResources().getString(R.string.divide)))
            s1 = "/";
        if(s2.equals(getResources().getString(R.string.multi)))
            s2 = "*";
        if(s2.equals(getResources().getString(R.string.divide)))
            s2 = "/";

        if(s1.equals("+") || s1.equals("-")) {
            if(s2.equals("+") || s2.equals("-") || s2.equals(")") || s2.equals("#"))
                return '>';
            else if(s2.equals("*") || s2.equals("/") || s2.equals("(") || s2.equals("%"))
                return '<';
        }

        else if(s1.equals("*") || s1.equals("/")) {
            if(s2.equals("+") || s2.equals("-") || s2.equals("*") || s2.equals("/") || s2.equals(")") || s2.equals("#"))
                return '>';
            else if(s2.equals("(") || s2.equals("%"))
                return '<';
        }

        else if(s1.equals("(")) {
            if(s2.equals(")"))
                return '=';
            else if(s2.equals("+") || s2.equals("-") || s2.equals("*") || s2.equals("/") || s2.equals("(") || s2.equals("%"))
                return '<';
        }

        else if(s1.equals(")")) {
            if(s2.equals("+") || s2.equals("-") || s2.equals("*") || s2.equals("/") || s2.equals(")") || s2.equals("#") || s2.equals("%"))
                return '>';
            else if(s2.equals("("))
                return '=';
        }

        else if(s1.equals("#")) {
            if(s2.equals("#"))
                return '=';
            else if(s2.equals("+") || s2.equals("-") || s2.equals("*") || s2.equals("/") || s2.equals("(") || s2.equals("%"))
                return '<';
        }

        else if(s1.equals("%")) {
            return '>';
        }

        return ' ';
    }

    public double operate(double a, String operator, double b) {
        if(operator.equals("+"))
            return a + b;
        else if(operator.equals("-"))
            return a - b;
        else if(operator.equals(getResources().getString(R.string.multi)))
            return a * b;
        else if(operator.equals(getResources().getString(R.string.divide)))
            return a / b;

        return 0;
    }
}