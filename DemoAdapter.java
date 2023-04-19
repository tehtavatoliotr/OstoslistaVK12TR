package com.example.ostoslistaoliootuomasr;

import java.util.Collections;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.DemoVH> {
    List<String> items;

    public DemoAdapter(List<String> items) {
        this.items = items;
    }

    public void sortAlphabetically() {
        Collections.sort(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DemoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new DemoVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull DemoVH holder, int position) {
        String item = items.get(position);
        String displayText = item;
        if (item.endsWith("*")) {
            displayText = item.substring(0, item.length() - 1);
            holder.starIcon.setVisibility(View.VISIBLE);
        } else {
            holder.starIcon.setVisibility(View.GONE);
        }
        holder.textView.setText(displayText);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    static class DemoVH extends RecyclerView.ViewHolder {
        TextView textView;
        EditText editText;
        Button editButton;
        ImageView starIcon;
        private DemoAdapter adapter;

        public DemoVH(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.text);
            editText = itemView.findViewById(R.id.editText);
            editButton = itemView.findViewById(R.id.editButton);
            starIcon = itemView.findViewById(R.id.star_icon);

            itemView.findViewById(R.id.delete).setOnClickListener(view -> {
                adapter.items.remove(getAdapterPosition());
                adapter.notifyItemRemoved(getAdapterPosition());
            });


            editButton.setOnClickListener(v -> {
                if (editButton.getText().equals("Edit")) {

                    textView.setVisibility(View.GONE);
                    editText.setVisibility(View.VISIBLE);
                    editText.setText(textView.getText());


                    editButton.setText("Save");
                } else {

                    String newText = editText.getText().toString();
                    adapter.items.set(getAdapterPosition(), newText);
                    textView.setText(newText);

                    textView.setVisibility(View.VISIBLE);
                    editText.setVisibility(View.GONE);

                    editButton.setText("Edit");
                }
            });
        }

        public DemoVH linkAdapter(DemoAdapter adapter) {
            this.adapter = adapter;
            return this;
        }
    }
}
