package com.example.day02_exam.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.day02_exam.ApiService;
import com.example.day02_exam.R;
import com.example.day02_exam.adpter.Vp2Adpter;
import com.example.day02_exam.bean.TabBean;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private TabLayout tab;
    private ViewPager vp;
    private ArrayList<TabBean.DataBean> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        vp = view.findViewById(R.id.vp);
        tab = view.findViewById(R.id.tab);
        list = new ArrayList<>();
        initData();
    }

    private void initData() {
        new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getTab()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TabBean>() {

                    private Vp2Adpter adpter;

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TabBean value) {
                        List<TabBean.DataBean> data = value.getData();
                        list.addAll(data);
                        ArrayList<Fragment> fragments = new ArrayList<>();
                        for (int i = 0; i < list.size(); i++) {
                            BlankFragment blankFragment = new BlankFragment();
                            int id = list.get(i).getId();
                            Bundle bundle = new Bundle();
                            bundle.putInt("id",id);
                            blankFragment.setArguments(bundle);
                            fragments.add(blankFragment);
                        }
                        adpter = new Vp2Adpter(getChildFragmentManager(), fragments, list);
                        vp.setAdapter(adpter);
                        tab.setupWithViewPager(vp);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}