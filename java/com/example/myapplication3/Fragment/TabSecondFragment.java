package com.example.myapplication3.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication3.Adapter.TableAdapter;
import com.example.myapplication3.DataDealer.GetURLString;
import com.example.myapplication3.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;


public class TabSecondFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private TableAdapter tableAdapter;
    BottomSheetDialog bottomSheetDialog;
    String[] categories = {"全部","娱乐","军事","教育","文化","健康","财经","体育","汽车","科技","社会"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 在这里加载和配置Fragment的布局
        View view = inflater.inflate(R.layout.page_two_fragment , container, false);
        // 执行其他的布局配置和初始化操作
        bottomSheetDialog = new BottomSheetDialog(getActivity());
        View diaglogView=LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet_dialogs,null);
        bottomSheetDialog.setContentView(diaglogView);
        viewPager=view.findViewById(R.id.view_pager_two);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button selectButton = getView().findViewById(R.id.select_button);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    bottomSheetDialog.show();


            }
        });

        tabLayout = getView().findViewById(R.id.tab_layout_page_two);


        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        Button determineButton = bottomSheetDialog.findViewById(R.id.determine_button);
        determineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTab(tabLayout);
            }

        });



    }




    private void addTab(TabLayout tabLayout) {
        CheckBox[] checkBoxes = {bottomSheetDialog.findViewById(R.id.checkBoxAll),bottomSheetDialog.findViewById(R.id.checkBoxAmusement),(bottomSheetDialog.findViewById(R.id.checkBoxMilitary)),bottomSheetDialog.findViewById(R.id.checkBoxEducation),bottomSheetDialog.findViewById(R.id.checkBoxCulture),bottomSheetDialog.findViewById(R.id.checkBoxHealth),bottomSheetDialog.findViewById(R.id.checkBoxFinance),bottomSheetDialog.findViewById(R.id.checkBoxSports),bottomSheetDialog.findViewById(R.id.checkBoxCar),bottomSheetDialog.findViewById(R.id.checkBoxTechonlogy),bottomSheetDialog.findViewById(R.id.checkBoxSocial)};
        tabLayout.removeAllTabs();
        TableAdapter tableAdapter1;
        List<FatherFragment> fatherFragments=new ArrayList<>();
        int k = 0;
        String[] cat = new String[11];
        for(int i=0;i<11;i++)
        {
            if(checkBoxes[i].isChecked())
            {
                Log.d("checkbox", "addTab: "+i);
                TabLayout.Tab tab = tabLayout.newTab();

                tab.setText(categories[i]);
                tabLayout.addTab(tab);
                cat[k]=categories[i];
                k++;

                String urlToGetFrom = new String();
                urlToGetFrom = GetURLString.GetString(categories[i],"","","");

                if (i==0) {
                    urlToGetFrom = GetURLString.GetString("","","","");
                }
                FatherFragment fragment = new FatherFragment(urlToGetFrom);
                fatherFragments.add(fragment);
            }
        }

        tableAdapter = new TableAdapter(getActivity().getSupportFragmentManager(),getLifecycle(),fatherFragments);

        viewPager.setAdapter(tableAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // 切换到另一个页面
                viewPager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Do nothing
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

    }
}

