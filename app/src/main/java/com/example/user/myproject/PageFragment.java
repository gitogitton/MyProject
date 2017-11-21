package com.example.user.myproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;
import static android.content.pm.PackageManager.INSTALL_REASON_UNKNOWN;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

//initial create by android studio.
// select [file]->[new]->[fragment]->[blank fragment]

public class PageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private OnFragmentInteractionListener mListener;

    private final String CLASS_NAME = getClass().getSimpleName();

    public PageFragment() {
        Log.d(CLASS_NAME, "constructor start (empty)");
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PageFragment newInstance(int page) {
        Log.d("newInstance()", "PageFragment page="+page);
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(CLASS_NAME, "onCreate() start");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            int mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    final int TABPAGE_RUNNING_PROCESS = 0;
    final int TABPAGE_INSTALLED_APPLICATION = 1;
    /*
    * argument : int page       0 origin
    */
    private void showList(View view, int page){
        Log.d(CLASS_NAME, "showList() start");

        ArrayList<String> arrayList = new ArrayList<>();

        Context context = this.getContext();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        switch (page) {
            case TABPAGE_RUNNING_PROCESS:
                List<ActivityManager.RunningAppProcessInfo> runningApp = activityManager.getRunningAppProcesses();
                PackageManager packageManager = (PackageManager) context.getPackageManager();
                Log.d(CLASS_NAME, "running appl count : "+runningApp.size());
                if( ! runningApp.isEmpty() ) {
                    int i=0;
                    for(ActivityManager.RunningAppProcessInfo app : runningApp) {
                        i++;
                        try {
                            // アプリ名をリストに追加
                            ApplicationInfo appInfo = packageManager.getApplicationInfo(app.processName, INSTALL_REASON_UNKNOWN);
                            arrayList.add( i+") "+(String)packageManager.getApplicationLabel(appInfo) );
                        } catch(PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case TABPAGE_INSTALLED_APPLICATION:
                arrayList.add("44444");
                arrayList.add("55555");
                break;
            default:
                break;
        }
        ArrayAdapter<String> mAdapterOfList = new ArrayAdapter<>(getContext(), R.layout.page_low, R.id.list_row_text ,arrayList);
//違いがいまいち判らない。↑        ArrayAdapter<String> mAdapterOfList = new ArrayAdapter<>(getContext(), R.layout.page_low);
        ListView listView = view.findViewById(R.id.process_list);
        listView.setAdapter(mAdapterOfList);
        mAdapterOfList.notifyDataSetChanged(); //listViewに通知
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(CLASS_NAME, "onCreateView() start");
        // Inflate the layout for this fragment
        int page = getArguments().getInt(ARG_PARAM1, 0);
        View view = inflater.inflate(R.layout.fragment_page, container, false);

        showList(view, page); //tabのデータを表示

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        Log.d(CLASS_NAME, "onButtonPressed() start");
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Activity activity) {
//        Log.d(CLASS_NAME, "onAttach(activity) start");
//        super.onAttach(activity);
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) return;
//        if (activity instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) activity;
//        } else {
//            throw new RuntimeException(activity.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }
//    }
    @Override
    public void onAttach(Context context) {
        //API23（Android 6）からこの仕様に変わったらしい。
        //それまではonAttach(Activity)だったとの事。前述のAPIレベルより低いものに対応が必要なら両方実装するらしい（？）
        Log.d(CLASS_NAME, "onAttach(context) start");
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        Log.d(CLASS_NAME, "onDetach() start");
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
