package com.example.day02_exam.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day02_exam.ApiService;
import com.example.day02_exam.App;
import com.example.day02_exam.R;
import com.example.day02_exam.Student;
import com.example.day02_exam.adpter.RvAdpter;
import com.example.day02_exam.bean.BannerBean;
import com.example.day02_exam.bean.ListBean;
import com.example.day02_exam.prcenter.HomePrcenter;
import com.example.day02_exam.view.Iview;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BlankFragment extends Fragment implements Iview {

    private RecyclerView rv_main;
    private ArrayList<BannerBean.DataBean> dataBeans;
    private ArrayList<ListBean.DataBean.DatasBean> list;
    private HomePrcenter homePrcenter;
    private RvAdpter adpter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        homePrcenter = new HomePrcenter(this);
        initView(view);

        initListener();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            initData();
        }else {
            if(list!=null&&list.size()>0){
                list.clear();
            }
        }
    }

    private void initListener() {
        adpter.setOnClickItemListener(new RvAdpter.onClickItemListener() {
            @Override
            public void onClickItem(int position) {
                Student student = new Student();
                student.setId(null);
                student.setPic(list.get(position).getEnvelopePic());
                student.setTitle(list.get(position).getTitle());
                App.daoSession.getStudentDao().insert(student);
            }
        });
    }

    private void initData() {
        Bundle arguments = getArguments();
        int id = arguments.getInt("id");
        homePrcenter.getData();
        new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getList(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListBean value) {
                        List<ListBean.DataBean.DatasBean> datas = value.getData().getDatas();
                        list.addAll(datas);
                        adpter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView(View view) {
        rv_main=view.findViewById(R.id.rv_main);
        rv_main.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_main.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        dataBeans = new ArrayList<>();
        list = new ArrayList<>();
        adpter = new RvAdpter(getActivity(), dataBeans, list);
        rv_main.setAdapter(adpter);


    }

    @Override
    public void getOk(BannerBean bean) {
        List<BannerBean.DataBean> data = bean.getData();
        dataBeans.addAll(data);
        adpter.notifyDataSetChanged();
    }

    @Override
    public void getNo(String msg) {

    }


}