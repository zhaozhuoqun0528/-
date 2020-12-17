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
import com.example.day02_exam.bean.BannerBean;
import com.example.day02_exam.bean.ListBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class RvAdpter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<BannerBean.DataBean> dataBeans;
    private ArrayList<ListBean.DataBean.DatasBean> list;
    private onClickItemListener onClickItemListener;

    public void setOnClickItemListener(RvAdpter.onClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    public RvAdpter(Context context, ArrayList<BannerBean.DataBean> dataBeans, ArrayList<ListBean.DataBean.DatasBean> list) {
        this.context = context;
        this.dataBeans = dataBeans;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_banner, parent, false);
            return new First(view);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if(itemViewType==1){
            First first = (First) holder;
            first.banner.setImages(dataBeans);
            first.banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    BannerBean.DataBean dataBean = (BannerBean.DataBean) path;
                    Glide.with(context).load(dataBean.getImagePath()).into(imageView);
                }
            }).start();
        }
        if(itemViewType==2){
            ViewHolder viewHolder = (ViewHolder) holder;
            ListBean.DataBean.DatasBean datasBean = list.get(position-1);
            viewHolder.tv_text.setText(datasBean.getTitle());
            Glide.with(context).load(datasBean.getEnvelopePic()).into(viewHolder.iv_image);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickItemListener.onClickItem(position-1);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        }
        return 2;
    }

    public static
    class First extends RecyclerView.ViewHolder {
        public View rootView;
        public Banner banner;

        public First(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.banner = (Banner) rootView.findViewById(R.id.banner);
        }

    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
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
    public interface onClickItemListener{
        void onClickItem(int position);
    }
}
