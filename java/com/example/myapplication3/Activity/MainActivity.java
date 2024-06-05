package com.example.myapplication3.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.myapplication3.Adapter.TabPagerAdapter;
import com.example.myapplication3.R;

public class MainActivity extends AppCompatActivity {

    //声明一个翻页试图对象
    private ViewPager vp_content;

    //声明一个单选组对象
    private RadioGroup rg_tabbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp_content = findViewById(R.id.vp_content);
        //构造一个翻页适配器
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager());
        vp_content.setAdapter(adapter);
        //给翻页视角添加页面变更适配器
        vp_content.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position){
                rg_tabbar.check(rg_tabbar.getChildAt(position).getId());
            }
        });
        rg_tabbar=findViewById(R.id.rg_tabbar);
        rg_tabbar.setOnCheckedChangeListener((group,checkedId)->{
            for(int pos=0;pos<rg_tabbar.getChildCount();pos++)
            {
                RadioButton tab = (RadioButton) rg_tabbar.getChildAt(pos);
                if(tab.getId()==checkedId)
                {
                    vp_content.setCurrentItem(pos);
                }
            }
        });

    }

}