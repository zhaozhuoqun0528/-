package com.example.day02_exam.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day02_exam.App;
import com.example.day02_exam.R;
import com.example.day02_exam.Student;
import com.example.day02_exam.adpter.Rv2Adpter;

import java.util.ArrayList;
import java.util.List;


public class CollFragment extends Fragment {

    private RecyclerView rv_main;
    private ArrayList<Student> list;
    private Rv2Adpter adpter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_show, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        List<Student> students = App.daoSession.getStudentDao().loadAll();
        list.addAll(students);
        adpter.notifyDataSetChanged();
    }

    private void initView(View view) {
        rv_main = (RecyclerView) view.findViewById(R.id.rv_main);
        rv_main.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_main.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        list = new ArrayList<>();
        adpter = new Rv2Adpter(getActivity(), list);
        rv_main.setAdapter(adpter);

    }
}