package com.example.day02_exam.prcenter;

import com.example.day02_exam.bean.BannerBean;
import com.example.day02_exam.fragment.BlankFragment;
import com.example.day02_exam.model.HomeModel;
import com.example.day02_exam.view.Iview;
import com.example.day02_exam.view.ResultCallBack;

public class HomePrcenter {
    private Iview iview;
    private final HomeModel homeModel;

    public HomePrcenter(Iview iview) {

        this.iview = iview;
        homeModel = new HomeModel();
    }

    public void getData() {
        homeModel.getData(new ResultCallBack<BannerBean>() {
            @Override
            public void getSuccess(BannerBean bannerBean) {
                iview.getOk(bannerBean);
            }

            @Override
            public void getFail(String msg) {

            }
        });
    }
}
