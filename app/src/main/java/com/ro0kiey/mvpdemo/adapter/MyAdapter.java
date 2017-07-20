package com.ro0kiey.mvpdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ro0kiey.mvpdemo.R;
import com.ro0kiey.mvpdemo.mvp.model.Bean.MeiziBean;

import java.util.List;

/**
 * Created by Ro0kieY on 2017/7/20.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<MeiziBean> meiziList;

    public MyAdapter(List<MeiziBean> meiziList) {
        this.meiziList = meiziList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_main_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MeiziBean meiziBean = meiziList.get(position);
        Glide.with(holder.itemView.getContext()).load(meiziBean.getUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return meiziList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.rv_image_view);
        }
    }
}
