package com.example.myapplication3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication3.DataBase.MySimpleData;
import com.example.myapplication3.DataBase.NewsDatabaseDealer;
import com.example.myapplication3.Activity.NewsDetailActivity;
import com.example.myapplication3.R;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{
    private List<MySimpleData> dataList;

    public DataAdapter(List<MySimpleData> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String newsContent = dataList.get(position).content;
        String newsPublishTime = dataList.get(position).publishTime;
        String newsPublisher = dataList.get(position).publisher;
        String newsTitle = dataList.get(position).title;
        String newsID = dataList.get(position).newsID;

        holder.titleView.setText(dataList.get(position).title);
        holder.publishTimeView.setText("发布时间: "+dataList.get(position).publishTime);
        holder.publisherView.setText("来源: "+dataList.get(position).publisher);

        holder.titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = holder.itemView.getContext();  // 获取当前的上下文

                //将新闻数据导入数据库
                NewsDatabaseDealer dealer = new NewsDatabaseDealer(context);
                if (!dealer.isNewsExists(newsID)) dealer.insert(newsID,newsTitle,newsPublishTime,newsPublisher,newsContent);

                Intent intent = new Intent(context, NewsDetailActivity.class);  // 创建 Intent 对象
                intent.putExtra("newsContent", newsContent);  // 将新闻内容添加到 Intent 中
                intent.putExtra("newsTitle",newsTitle);
                intent.putExtra("newsPublishTime",newsPublishTime);
                intent.putExtra("newsVideo","");
                intent.putExtra("newsPublisher",newsPublisher);
                intent.putExtra("newsImage", "");
                intent.putExtra("newsID",newsID);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private  TextView titleView;
        private TextView publishTimeView;
        private TextView publisherView;
        public ViewHolder(View itemView) {
            super(itemView);
            titleView=itemView.findViewById(R.id.tv_title);
            publishTimeView=itemView.findViewById(R.id.tv_publish_time);
            publisherView=itemView.findViewById(R.id.tv_publisher);
        }
    }
}
