package com.example.user.myproject;

import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public class MonitorAppal extends AppCompatActivity
        implements ViewPager.OnPageChangeListener, PageFragment.OnFragmentInteractionListener {

    private final String CLASS_NAME = getClass().getSimpleName();

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(CLASS_NAME, "onCreate() start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_appl);

        mTabLayout = findViewById(R.id.tab);
        mViewPager = findViewById(R.id.view_pager);

        setToolbar(); //toolbar setting

        setTab();
        setViewPager();

//        getList(); //get listed data.
//        showListedData(); //show data to listView

        //https://qiita.com/teradonburi/items/24f25a7c6590c8acad71
        if ( null==savedInstanceState ) {
            android.support.v4.app.FragmentManager fragmentManager;
            fragmentManager = getSupportFragmentManager();
            PageFragment pageFragment = new PageFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(PageFragment.ARG_PARAM1, PageFragment.TABPAGE_RUNNING_PROCESS);

            pageFragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.view_pager, pageFragment);
            fragmentTransaction.commit();
            fragmentManager.executePendingTransactions(); // FragmentのTransaction処理の完了同期待ち（必須ではない）
        }
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
        mTabLayout.setupWithViewPager(mViewPager); //TabLayoutとViewPagerを連動させる
    }

    final String[] pageTitle = {"Running Process", "Installed Application"};//, "Setting"}; //タブページのタイトル
    private void setViewPager() {
        Log.d(CLASS_NAME, "setViewPager() start");

        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
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

            /**
             * This method may be called by the ViewPager to obtain a title string
             * to describe the specified page. This method may return null
             * indicating no title for this page. The default implementation returns
             * null.
             *
             * @param position The position of the title requested
             * @return A title for the requested page
             */
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0 :
                        return pageTitle[0];
                    case 1 :
                        return pageTitle[1];
                    default:
                        return "----";
                }
            }
        };
        mViewPager.setAdapter(fragmentPagerAdapter);
        mViewPager.addOnPageChangeListener(this); //ページ切り替え、ページスクロール時に呼ばれるリスナー登録
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
