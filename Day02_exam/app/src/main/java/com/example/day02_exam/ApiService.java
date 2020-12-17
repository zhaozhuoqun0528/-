package com.example.day02_exam;


import com.example.day02_exam.bean.BannerBean;
import com.example.day02_exam.bean.ListBean;
import com.example.day02_exam.bean.TabBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    String BASE_URL="http://www.wanandroid.com/";
    //"http://www.wanandroid.com/project/tree/json"  tab
    @GET("project/tree/json")
    Observable<TabBean> getTab();

    //"https://www.wanandroid.com/banner/json"  轮播图的接口
    @GET("banner/json")
    Observable<BannerBean> getBanner();

    //"https://www.wanandroid.com/project/list/1/json?cid=294"  有图有link
    @GET("project/list/1/json")
    Observable<ListBean> getList(@Query("cid")int cid);
}
