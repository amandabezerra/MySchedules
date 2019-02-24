package com.amandabezerra.myschedules.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.amandabezerra.myschedules.R;

public class LineHolder extends RecyclerView.ViewHolder {

    public TextView title;

    public LineHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.view_task_title);
    }
}
