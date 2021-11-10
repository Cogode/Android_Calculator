package com.example.calculator.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.calculator.R;

public class MyFragment extends Fragment {
    private final int layout;
    TextView textView;

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
            for (int i = 0; i < keyboard.getChildCount(); i++) {
                Button button = (Button) keyboard.getChildAt(i);
                button.setWidth(screenWidth / columnCount);
            }
            textView = view.findViewById(R.id.textView);
        }
    }

    public void setText(String text) {
        textView.setText(text);
    }
}
