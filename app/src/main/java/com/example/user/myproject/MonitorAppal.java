package com.example.user.myproject;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public class MonitorAppal extends AppCompatActivity
        implements ViewPager.OnPageChangeListener,
                        PageFragment_1.OnFragmentInteractionListener, PageFragment_2.OnFragmentInteractionListener {

    private final String CLASS_NAME = getClass().getSimpleName();

    private android.support.v4.app.FragmentManager mFragmentManager;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    public CustomPagerAdapter mFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(CLASS_NAME, "onCreate() start. savedInstanceState->"+savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_appl);

        mTabLayout = findViewById(R.id.tab);
        mViewPager = findViewById(R.id.view_pager);

        setToolbar();
        setTab();
        setViewPager();
    }

    private void setToolbar() {
        Log.d(CLASS_NAME, "setToolbar() start");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //ToolbarはAPI21からのサポート。API19だからActionBarをサポートする。
    }
    private void setTab() {
        Log.d(CLASS_NAME, "setTab() start");
        mTabLayout.setupWithViewPager(mViewPager); //TabLayoutとViewPagerを連動させる
    }

    private void setViewPager() {
        Log.d(CLASS_NAME, "setViewPager() start");

        mFragmentManager = getSupportFragmentManager();
        mFragmentPagerAdapter = new CustomPagerAdapter( mFragmentManager );
        mViewPager.setAdapter( mFragmentPagerAdapter );
        mViewPager.addOnPageChangeListener(this); //ページ切り替え、ページスクロール時に呼ばれるリスナー登録
    }

    //現在のページがスクロールされたときに呼び出されます。
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d(CLASS_NAME, "onPageScrolled() : position = "+position);
    }
    //新しいページが選択されると呼び出されます。
    @Override
    public void onPageSelected(int position) {
        Log.d(CLASS_NAME, "onPageSelected() start. position="+position);
    }
    //スクロール状態が変更されたときに呼び出されます。
    @Override
    public void onPageScrollStateChanged(int state) {
        Log.d(CLASS_NAME, "onPageScrollStateChanged() start. state="+state);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d(CLASS_NAME, "onFragmentInteraction() start. uri->"+uri);
    }
}
