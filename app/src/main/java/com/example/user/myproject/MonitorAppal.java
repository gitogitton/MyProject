package com.example.user.myproject;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MonitorAppal extends AppCompatActivity
        implements ViewPager.OnPageChangeListener, PageFragment.OnFragmentInteractionListener {

    private final String CLASS_NAME = getClass().getSimpleName();

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(CLASS_NAME, "onCreate() start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_appl);

        mTabLayout = findViewById(R.id.tab);
        mViewPager = findViewById(R.id.view_pager);

        setToolbar(); //toolbar setting
        setTab(); //set tab
        getList(); //get listed data.
        showListedData(); //show data to listView
    }

    private void setToolbar() {
        Log.d(CLASS_NAME, "setToolbar() start");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //ToolbarはAPI21からのサポート。API19だからActionBarをサポートする。
    }
    private void getList() {
        Log.d(CLASS_NAME, "getList() start");
        //getListInfo(); //setする情報を取得 (set mProcess)
    }
    private void showListedData() {
        Log.d(CLASS_NAME, "showListedData() start");
    }
    private void setTab() {
        Log.d(CLASS_NAME, "setTab() start");

        final String[] pageTitle = {"Running Process", "Installed Application"};//, "Setting"}; //タブページのタイトル

        mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
//                Log.d(CLASS_NAME, "FragmentPagerAdapter.getCount() start");
                return pageTitle.length;
            }

            @Override
            public Fragment getItem(int position) {
                Log.d(CLASS_NAME, "FragmentPagerAdapter.getItem() start. position=" + position);
                return PageFragment.newInstance(position);
            }
        };

        mViewPager.setAdapter(mFragmentPagerAdapter);
        mViewPager.addOnPageChangeListener(this); //ページ切り替え、ページスクロール時に呼ばれるリスナー登録
        mTabLayout.setupWithViewPager(mViewPager); //TabLayoutとViewPagerを連動させる

        mTabLayout.getTabAt(0).setText(pageTitle[0]); //タブのタイトル設定
        mTabLayout.getTabAt(1).setText(pageTitle[1]);  //タブのタイトル設定
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d(CLASS_NAME, "onPageScrolled() : position/offset/offsetPixels : "+position+"/"+positionOffset+"/"+positionOffsetPixels);
        if (position==PageFragment.TABPAGE_RUNNING_PROCESS) {
            Log.d(CLASS_NAME, "onPageScrolled() : TABPAGE_RUNNING_PROCESS");
        } else if (position==PageFragment.TABPAGE_INSTALLED_APPLICATION) {
            Log.d(CLASS_NAME, "onPageScrolled() : TABPAGE_INSTALLED_APPLICATION");
        } else {
            Log.d(CLASS_NAME, "onPageScrolled() : illegal position");
            return;
        }
    }

    @Override
    public void onPageSelected(int position) {
        Log.d(CLASS_NAME, "onPageSelected() start. position="+position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.d(CLASS_NAME, "onPageScrollStateChanged() start. state="+state);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d(CLASS_NAME, "onFragmentInteraction() start");
    }
}
