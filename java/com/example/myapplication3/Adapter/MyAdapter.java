package com.example.myapplication3.Adapter;



import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication3.DataBase.NewsDatabaseDealer;
import com.example.myapplication3.DataDealer.MyData;

import com.example.myapplication3.Activity.NewsDetailActivity;
import com.example.myapplication3.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context mContext;
    public List<MyData> mdatas=new ArrayList<>();

    // 构造函数，用于传递数据列表
    public MyAdapter(Context context, List<MyData> datas)
    {
        mContext = context;
        mdatas = datas;
    }

    public void FreshMyAdatper(List<MyData> datas)
    {
        mdatas = datas;
    }

    //创建MyViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        private  TextView titleView;
        private TextView publishTimeView;
        private TextView publisherView;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_pic);
            titleView=itemView.findViewById(R.id.tv_title);
            publishTimeView=itemView.findViewById(R.id.tv_publish_time);
            publisherView=itemView.findViewById(R.id.tv_publisher);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String newsID = mdatas.get(position).newsID;

        holder.titleView.setText(mdatas.get(position).title);
        holder.publishTimeView.setText("发布时间: "+mdatas.get(position).publishTime);
        holder.publisherView.setText("来源: "+mdatas.get(position).publisher);

        //置灰
        NewsDatabaseDealer dealer = new NewsDatabaseDealer(mContext);
        if(dealer.isNewsExists(newsID))
        {
            holder.titleView.setTextColor(ContextCompat.getColor(mContext,R.color.gray));
            holder.publishTimeView.setTextColor(ContextCompat.getColor(mContext,R.color.gray));
            holder.publisherView.setTextColor(ContextCompat.getColor(mContext,R.color.gray));
        } else{
            holder.titleView.setTextColor(ContextCompat.getColor(mContext,R.color.black));
            holder.publishTimeView.setTextColor(ContextCompat.getColor(mContext,R.color.black));
            holder.publisherView.setTextColor(ContextCompat.getColor(mContext,R.color.black));
        }
        String imageUrl = "";
        if(mdatas.get(position).image.length > 1) {
            Log.i("imageURL","before" + imageUrl);
            imageUrl = mdatas.get(position).image[1];
            Log.i("imageURL","after" + imageUrl);
        }//getItem(position).getImageUrl();
        Log.d("image",imageUrl);
        Glide.with(holder.imageView.getContext())
                .load(imageUrl)
                .into(holder.imageView);

        //设置点击操作
        String newsContent = mdatas.get(position).content;  // 获取新闻内容
        String newsTitle = mdatas.get(position).title;
        String newsPublishTime = mdatas.get(position).publishTime;
        String newsVideo = mdatas.get(position).video;
        String newsPublisher = mdatas.get(position).publisher;


        String finalImageUrl = imageUrl;
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.titleView.setTextColor(ContextCompat.getColor(mContext,R.color.gray));
                holder.publishTimeView.setTextColor(ContextCompat.getColor(mContext,R.color.gray));
                holder.publisherView.setTextColor(ContextCompat.getColor(mContext,R.color.gray));


                Context context = holder.itemView.getContext();  // 获取当前的上下文

                //将新闻数据导入数据库
                NewsDatabaseDealer dealer = new NewsDatabaseDealer(context);
                if (!dealer.isNewsExists(newsID)) dealer.insert(newsID,newsTitle,newsPublishTime,newsPublisher,newsContent);

                Intent intent = new Intent(context, NewsDetailActivity.class);  // 创建 Intent 对象
                intent.putExtra("newsContent", newsContent);  // 将新闻内容添加到 Intent 中
                intent.putExtra("newsTitle",newsTitle);
                intent.putExtra("newsPublishTime",newsPublishTime);
                intent.putExtra("newsVideo",newsVideo);
                intent.putExtra("newsPublisher",newsPublisher);
                intent.putExtra("newsImage", finalImageUrl);
                intent.putExtra("newsID",newsID);
                context.startActivity(intent);
            }

        });
        holder.titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.titleView.setTextColor(ContextCompat.getColor(mContext,R.color.gray));
                holder.publishTimeView.setTextColor(ContextCompat.getColor(mContext,R.color.gray));
                holder.publisherView.setTextColor(ContextCompat.getColor(mContext,R.color.gray));

                Context context = holder.itemView.getContext();  // 获取当前的上下文

                //将新闻数据导入数据库
                NewsDatabaseDealer dealer = new NewsDatabaseDealer(context);
                if (!dealer.isNewsExists(newsID)) dealer.insert(newsID,newsTitle,newsPublishTime,newsPublisher,newsContent);

                Intent intent = new Intent(context, NewsDetailActivity.class);  // 创建 Intent 对象
                intent.putExtra("newsContent", newsContent);  // 将新闻内容添加到 Intent 中
                intent.putExtra("newsTitle",newsTitle);
                intent.putExtra("newsPublishTime",newsPublishTime);
                intent.putExtra("newsVideo",newsVideo);
                intent.putExtra("newsPublisher",newsPublisher);
                intent.putExtra("newsImage", finalImageUrl);
                intent.putExtra("newsID",newsID);
                context.startActivity(intent);
            }
        });


    }

    // 返回数据项的数量
    public int getItemCount() {
        return mdatas.size();
    }
}
