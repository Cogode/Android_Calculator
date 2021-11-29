package com.example.calculator.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calculator.R;

import java.util.ArrayList;

public class SelectionRecyclerViewAdapter extends RecyclerView.Adapter<SelectionRecyclerViewAdapter.ViewHolder> {
    private ArrayList<String> selections;
    private OnClickListener clickListener;

    public interface OnClickListener {
        void onClick(View view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final View item;
        private final TextView textView;
        private final RadioButton radioButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            textView = itemView.findViewById(R.id.selection_textView);
            radioButton = itemView.findViewById(R.id.radioButton);
        }
    }

    public SelectionRecyclerViewAdapter(ArrayList<String> selections, OnClickListener clickListener) {
        this.selections = selections;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.selection_row_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(selections.get(position));
        holder.item.setOnClickListener(view -> clickListener.onClick(view));
        holder.radioButton.setOnClickListener(view -> clickListener.onClick(view));
    }

    @Override
    public int getItemCount() {
        return selections.size();
    }
}
