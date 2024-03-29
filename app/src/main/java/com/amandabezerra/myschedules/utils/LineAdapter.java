package com.amandabezerra.myschedules.utils;

import android.content.Intent;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amandabezerra.myschedules.EditTaskActivity;
import com.amandabezerra.myschedules.R;

import java.util.List;

public class LineAdapter extends RecyclerView.Adapter<LineHolder> {

    private final List<Pair<String, String>> dataSet;

    public LineAdapter(List<Pair<String, String>> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public LineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LineHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_line_view, parent, false));
    }

    @Override
    public void onBindViewHolder(final LineHolder holder, int position) {
        holder.title.setText(dataSet.get(position).first);
        holder.itemView.setTag(dataSet.get(position).second);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), EditTaskActivity.class);
                String taskId = holder.itemView.getTag().toString();
                intent.putExtra("TASK_ID", taskId);

                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}