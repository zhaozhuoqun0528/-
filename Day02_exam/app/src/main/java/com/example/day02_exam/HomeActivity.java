package com.example.day02_exam;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.day02_exam.adpter.Vp1Adpter;
import com.example.day02_exam.fragment.CollFragment;
import com.example.day02_exam.fragment.HomeFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private ViewPager vp;
    private TabLayout tab;
    private Vp1Adpter adpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        tab = (TabLayout) findViewById(R.id.tab);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new CollFragment());
        adpter = new Vp1Adpter(getSupportFragmentManager(), fragments);
        vp.setAdapter(adpter);
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setText("A页面");
        tab.getTabAt(1).setText("B页面");

    }
}