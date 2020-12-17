package com.example.day02_exam.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.day02_exam.R;
import com.example.day02_exam.Student;

import java.util.ArrayList;

public class Rv2Adpter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<Student> list;
    private View view;

    public Rv2Adpter(Context context, ArrayList<Student> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Student student = list.get(position);
        viewHolder.tv_text.setText(student.getPic());
        Glide.with(context).load(student.getPic()).into(viewHolder.iv_image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder extends RvAdpter.ViewHolder {
        public View rootView;
        public ImageView iv_image;
        public TextView tv_text;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv_image = (ImageView) rootView.findViewById(R.id.iv_image);
            this.tv_text = (TextView) rootView.findViewById(R.id.tv_text);
        }

    }
}
