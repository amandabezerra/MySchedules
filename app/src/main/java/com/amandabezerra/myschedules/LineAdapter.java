package com.amandabezerra.myschedules;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

public class LineAdapter extends RecyclerView.Adapter<LineHolder> {

    private final List<String> mDataset;

    public LineAdapter(List<String> mDataset) {
        this.mDataset = mDataset;
    }

    @Override
    public LineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LineHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_line_view, parent, false));
    }

    @Override
    public void onBindViewHolder(LineHolder holder, int position) {
        holder.title.setText(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}