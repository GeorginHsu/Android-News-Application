package com.example.myapplication3.Fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication3.Adapter.MyAdapter;
import com.example.myapplication3.DataDealer.NetworkUtils;
import com.example.myapplication3.DataDealer.DatasGetter;
import com.example.myapplication3.DataDealer.MyData;

import com.example.myapplication3.R;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;


public class FatherFragment extends Fragment{
    private RecyclerView recyclerView;
    private List<MyData> datas=new ArrayList<>();
    private int page = 1;
    public String urlToGetFrom;



    public FatherFragment(String urlToGetFrom)
    {
        this.urlToGetFrom=urlToGetFrom;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 在这里加载和配置Fragment的布局

        View view = inflater.inflate(R.layout.father_fragment, container, false);


        SmartRefreshLayout refreshLayout = view.findViewById(R.id.refreshLayoutPageOne);//使用smartrefresh
        refreshLayout.setEnableRefresh(true); // 启用下拉刷新功能
        refreshLayout.setEnableNestedScroll(true); // 启用嵌套滚动支持
        refreshLayout.setEnableLoadMore(true); // 启用上拉加载更多功能
        refreshLayout.setEnableAutoLoadMore(false);// 禁止自动加载更多


        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));

        if(!NetworkUtils.isNetworkConnected(getActivity()))
        {
            TextView netText = view.findViewById(R.id.father_net_tv);
            netText.setText("找不到网络哦{{(>_<)}}");
            return view;
        }

        DatasGetter datasGetter = new DatasGetter();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                datasGetter.get(urlToGetFrom+page);
            }
        }).start();

        datas=datasGetter.getResult();
        Log.d("data_size1",""+datas.size());
        MyAdapter myAdapter= new MyAdapter((getActivity()),datas);



        refreshLayout.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshlayout) {
                // 执行刷新操作
                // 在这里可以发送请求获取最新的新闻数据
                // 刷新完成后，调用 refreshlayout.finishRefresh() 结束刷新

                if(!NetworkUtils.isNetworkConnected(getActivity()))
                {
                    Toast.makeText(getActivity(), "找不到网络哦{{(>_<)}}", Toast.LENGTH_SHORT).show();
                    refreshLayout.finishRefresh();
                } else {
                    DatasGetter datasGetter = new DatasGetter();
                    page++;

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            datasGetter.get(urlToGetFrom + page);
                        }
                    }).start();
                    datas = datasGetter.getResult();
                    Log.d("data_size2", "" + datas.size());
                    if(datas.size()==0){
                        Toast.makeText(getActivity(), "已经没有更多新闻了哦{{(>_<)}}", Toast.LENGTH_SHORT).show();
                        refreshlayout.finishRefresh();
                    } else {
                        myAdapter.FreshMyAdatper(datas);
                        myAdapter.notifyDataSetChanged();
                        refreshlayout.finishRefresh();
                    }
                }
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                // 在这里执行上拉加载更多的逻辑
                // 加载完成后调用 finishLoadMore() 结束上拉加载更多动画

                if(!NetworkUtils.isNetworkConnected(getActivity()))
                {
                    Toast.makeText(getActivity(), "找不到网络哦{{(>_<)}}", Toast.LENGTH_SHORT).show();
                    refreshLayout.finishLoadMore();
                } else {
                    DatasGetter datasGetter = new DatasGetter();
                    page++;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            datasGetter.get(urlToGetFrom + page);
                        }
                    }).start();
                    datas.addAll(datasGetter.getResult());
                    Log.d("data_size3", "" + datas.size());
                    if(datas.size()==0){
                        Toast.makeText(getActivity(), "已经没有更多新闻了哦{{(>_<)}}", Toast.LENGTH_SHORT).show();
                        refreshLayout.finishLoadMore();
                    } else {
                        myAdapter.FreshMyAdatper(datas);
                        myAdapter.notifyDataSetChanged();
                        refreshLayout.finishLoadMore();
                    }
                }
            }
        });

        myAdapter.notifyDataSetChanged();
        recyclerView = view.findViewById(R.id.recyclerViewPageOne);
        recyclerView.setAdapter(myAdapter);
        Log.d("position5",myAdapter.mdatas.get(0).title);



        return view;
    }


}


