package com.example.calculator.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.calculator.R;
import com.example.calculator.activity.ConversionActivity;
import com.example.calculator.activity.MainActivity;
import com.example.calculator.activity.SelectionActivity;
import com.example.calculator.domain.MoneyInfomation;
import com.example.calculator.domain.MoneyService;
import com.example.calculator.util.ServiceCreator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyFragment extends Fragment {
    private final int layout;
    private TextView textView;
    private TextView input;
    private TextView output;
    private TextView inputUnitTextView;
    private TextView outputUnitTextView;
    private TextView hint;

    public MyFragment() {
        layout = R.layout.fragment_first;
    }

    public MyFragment(int layout) {
        this.layout = layout;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(layout, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if(layout == R.layout.fragment_first) {
            GridLayout keyboard = view.findViewById(R.id.keyboard);
            int screenWidth = getActivity().getWindowManager().getDefaultDisplay().getWidth();
            int columnCount = keyboard.getColumnCount();
            for(int i = 0; i < keyboard.getChildCount(); i ++) {
                Button button = (Button) keyboard.getChildAt(i);
                button.setWidth(screenWidth / columnCount);
            }
            textView = view.findViewById(R.id.textView);
            Intent intent = getActivity().getIntent();
            if(intent.getStringExtra("expression") != null) {
                ((MainActivity) getActivity()).expression = intent.getStringExtra("expression");
                textView.setText(((MainActivity) getActivity()).expression);
            }
        }
        else if(layout == R.layout.fragment_second) {
            input = getActivity().findViewById(R.id.input_textView);
            output = getActivity().findViewById(R.id.output_textView);
            inputUnitTextView = getActivity().findViewById(R.id.input_unit_textView);
            outputUnitTextView = getActivity().findViewById(R.id.output_unit_textView);
            hint = getActivity().findViewById(R.id.hint_textView);
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            hint.setText("数据来自于天行数据，" + sdf.format(now) + "更新");
            Intent intent = getActivity().getIntent();
            String inputUnit = intent.getStringExtra("inputUnit");
            String outputUnit = intent.getStringExtra("outputUnit");
            String inputNum = intent.getStringExtra("inputNum");
            if(inputUnit != null) {
                inputUnitTextView.setText(inputUnit);
                ((MainActivity) getActivity()).viewPager().setCurrentItem(1);
            }
            if(outputUnit != null)
                outputUnitTextView.setText(outputUnit);
            if(inputNum != null) {
                input.setText(inputNum);
                refreshOutput();
            }

            GridLayout keyboard = getActivity().findViewById(R.id.keyboard_fragment_two);
            int screenWidth = getActivity().getWindowManager().getDefaultDisplay().getWidth();
            int columnCount = keyboard.getColumnCount();
            for(int i = 0; i < keyboard.getChildCount(); i++) {
                Button button = (Button) keyboard.getChildAt(i);
                button.setWidth(screenWidth / columnCount);
            }

            View inputView = view.findViewById(R.id.input_view);
            inputView.setOnClickListener(v -> {
                Intent selectIntent = new Intent(getActivity(), SelectionActivity.class);
                selectIntent.putExtra("selection", "rate");
                selectIntent.putExtra("type", "input");
                selectIntent.putExtra("inputNum", ((TextView) v.findViewById(R.id.input_textView)).getText().toString());
                selectIntent.putExtra("outputUnit",
                        ((TextView) ((View) v.getParent()).findViewById(R.id.output_view).findViewById(R.id.output_unit_textView)).getText().toString());
                startActivity(selectIntent);
                getActivity().finish();
            });
            View outputView = view.findViewById(R.id.output_view);
            outputView.setOnClickListener(v -> {
                Intent selectIntent = new Intent(getActivity(), SelectionActivity.class);
                selectIntent.putExtra("selection", "rate");
                selectIntent.putExtra("type", "output");
                selectIntent.putExtra("inputNum",
                        ((TextView) ((View) v.getParent()).findViewById(R.id.input_view).findViewById(R.id.input_textView)).getText().toString());
                selectIntent.putExtra("inputUnit",
                        ((TextView) ((View) v.getParent()).findViewById(R.id.input_view).findViewById(R.id.input_unit_textView)).getText().toString());
                startActivity(selectIntent);
                getActivity().finish();
            });

            Button btnOne = getActivity().findViewById(R.id.btn_one_fragment_two);
            btnOne.setOnClickListener(v -> {
                joinToInput("1");
                refreshOutput();
            });
            Button btnTwo = getActivity().findViewById(R.id.btn_two_fragment_two);
            btnTwo.setOnClickListener(v -> {
                joinToInput("2");
                refreshOutput();
            });
            Button btnThree = getActivity().findViewById(R.id.btn_three_fragment_two);
            btnThree.setOnClickListener(v -> {
                joinToInput("3");
                refreshOutput();
            });
            Button btnFour = getActivity().findViewById(R.id.btn_four_fragment_two);
            btnFour.setOnClickListener(v -> {
                joinToInput("4");
                refreshOutput();
            });
            Button btnFive = getActivity().findViewById(R.id.btn_five_fragment_two);
            btnFive.setOnClickListener(v -> {
                joinToInput("5");
                refreshOutput();
            });
            Button btnSix = getActivity().findViewById(R.id.btn_six_fragment_two);
            btnSix.setOnClickListener(v -> {
                joinToInput("6");
                refreshOutput();
            });
            Button btnSeven = getActivity().findViewById(R.id.btn_seven_fragment_two);
            btnSeven.setOnClickListener(v -> {
                joinToInput("7");
                refreshOutput();
            });
            Button btnEight = getActivity().findViewById(R.id.btn_eight_fragment_two);
            btnEight.setOnClickListener(v -> {
                joinToInput("8");
                refreshOutput();
            });
            Button btnNine = getActivity().findViewById(R.id.btn_nine_fragment_two);
            btnNine.setOnClickListener(v -> {
                joinToInput("9");
                refreshOutput();
            });
            Button btnZero = getActivity().findViewById(R.id.btn_zero_fragment_two);
            btnZero.setOnClickListener(v -> {
                joinToInput("0");
                refreshOutput();
            });
            Button btnDoubleZero = getActivity().findViewById(R.id.btn_doubleZero_fragment_two);
            btnDoubleZero.setOnClickListener(v -> {
                if(! input.getText().toString().equals("0"))
                    joinToInput("00");
                refreshOutput();
            });
            Button btnPoint = getActivity().findViewById(R.id.btn_point_fragment_two);
            btnPoint.setOnClickListener(v -> {
                String num = input.getText().toString();
                if(num.equals("0"))
                    joinToInput(".");
                else {
                    char c = num.charAt(num.length() - 1);
                    if(c != '.')
                        joinToInput(".");
                }
                refreshOutput();
            });
            Button btnClearTail = getActivity().findViewById(R.id.btn_clearTail_fragment_two);
            btnClearTail.setOnClickListener(v -> {
                String num = input.getText().toString();
                if(! num.equals("0"))
                    input.setText(num.substring(0, num.length() - 1));
                refreshOutput();
            });
            Button btnClearAll = getActivity().findViewById(R.id.btn_clearAll_fragment_two);
            btnClearAll.setOnClickListener(v -> {
                input.setText("0");
                refreshOutput();
            });
        }
        else if(layout == R.layout.fragment_third) {
            Button lengthBtn = view.findViewById(R.id.length_button);
            lengthBtn.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), ConversionActivity.class);
                intent.putExtra("layout", R.layout.activity_conversion_length);
                startActivity(intent);
            });

            Button volumeBtn = view.findViewById(R.id.volume_button);
            volumeBtn.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), ConversionActivity.class);
                intent.putExtra("layout", R.layout.activity_conversion_volume);
                startActivity(intent);
            });

            Button scaleBtn = view.findViewById(R.id.scale_button);
            scaleBtn.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), ConversionActivity.class);
                intent.putExtra("layout", R.layout.activity_conversion_scale);
                startActivity(intent);
            });
        }
    }

    public void setText(String text) {
        textView.setText(text);
    }

    private void joinToInput(String inputNum) {
        if(input.getText().toString().equals("0"))
            input.setText(inputNum);
        else
            input.setText(input.getText().toString() + inputNum);
    }

    private void refreshOutput() {
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
            MoneyService service = ServiceCreator.create(MoneyService.class);
            String key = "73796e33c14d95cebfe65179933f9052";
            String fromcoin = inputUnitTextView.getText().toString().split("\n")[1];
            String tocoin = outputUnitTextView.getText().toString().split("\n")[1];
            String money = input.getText().toString();
            service.getMoneyData(key, fromcoin, tocoin, money).enqueue(new Callback<MoneyInfomation>() {
                @Override
                public void onResponse(Call<MoneyInfomation> call, Response<MoneyInfomation> response) {
                    MoneyInfomation infomation = response.body();
                    if(infomation != null) {
                        if(infomation.getCode().equals("200")) {
                            List<MoneyInfomation.Money> newslist = infomation.getNewslist();
                            String temp = newslist.get(0).getMoney();
                            if(temp.charAt(temp.length() - 1) == '0' && temp.charAt(temp.length() - 2) == '.')
                                temp = temp.substring(0, temp.length() - 2);
                            output.setText(temp);
                        }
                    }
                }

                @Override
                public void onFailure(Call<MoneyInfomation> call, Throwable t) {
                    Toast.makeText(getActivity(), "数据获取失败", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
            output.setText("0");
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        hint.setText("数据来自于天行数据，" + sdf.format(now) + "更新");
    }
}
