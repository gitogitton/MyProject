package com.example.user.myproject;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by User on 2017/11/29.
 */

public class CustomPagerAdapter extends FragmentPagerAdapter {

    final String[] pageTitle = {"Running Process", "Installed Application"};//, "Setting"}; //タブページのタイトル
    final static int TABPAGE_RUNNING_PROCESS = 0;
    final static int TABPAGE_INSTALLED_APPLICATION = 1;

    private final String CLASS_NAME =getClass().getSimpleName();
    private PageFragment_1 mPage1;
    private PageFragment_2 mPage2;

    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
        Log.d(CLASS_NAME, "constructor start.");
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        Log.d(CLASS_NAME, "getCount() start.");
        return pageTitle.length;
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

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        Log.d(CLASS_NAME, "FragmentPagerAdapter.getItem() start. position=" + position);
        switch (position) {
            case TABPAGE_RUNNING_PROCESS :
                return mPage1 = PageFragment_1.newInstance( TABPAGE_RUNNING_PROCESS );
            case TABPAGE_INSTALLED_APPLICATION :
                return mPage2 = PageFragment_2.newInstance( TABPAGE_INSTALLED_APPLICATION );
            default:
                Log.d(CLASS_NAME, "illegal argument. (position)");
                return null;
        }
    }

//    /**
//     * Create the page for the given position.  The adapter is responsible
//     * for adding the view to the container given here, although it only
//     * must ensure this is done by the time it returns from
//     * {@link #finishUpdate(ViewGroup)}.
//     *
//     * @param container The containing View in which the page will be shown.
//     * @param position  The page position to be instantiated.
//     * @return Returns an Object representing the new page.  This does not
//     * need to be a View, but can be some other container of the page.
//     */
//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        Log.d(CLASS_NAME, "instantiateItem() start. position->"+position);
////        return super.instantiateItem(container, position);
//
//        switch ( position ) {
//            case TABPAGE_RUNNING_PROCESS :
//                //mPage1;
//                mPage1 = new PageFragment_1();
//                return mPage1;
//            case TABPAGE_INSTALLED_APPLICATION :
//                //mPage2;
//                mPage2 = new PageFragment_2();
//                return mPage1;
//            default :
//                break;
//        }
//        return null;
//    }

    /**
     * Determines whether a page View is associated with a specific key object
     * as returned by {@link #instantiateItem(ViewGroup, int)}. This method is
     * required for a PagerAdapter to function properly.
     *
     * @param view   Page View to check for association with <code>object</code>
     * @param object Object to check for association with <code>view</code>
     * @return true if <code>view</code> is associated with the key object <code>object</code>
     */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
}
