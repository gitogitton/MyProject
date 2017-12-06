package com.example.user.myproject;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static android.content.Context.ACTIVITY_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PageFragment_2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PageFragment_2#newInstance} factory method to
 * create an instance of this fragment.
 */

//initial create by android studio.
// select [file]->[new]->[fragment]->[blank fragment]

public class PageFragment_2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private final String CLASS_NAME = getClass().getSimpleName();
    protected static final String ARG_PARAM1 = "param1";
    private ListView mListView;
    private ArrayList<TextView> mArrayList;
    private ListViewAdapter mListViewAdapter;
    private OnFragmentInteractionListener mListener;

    public PageFragment_2() {
        Log.d(CLASS_NAME, "constructor start (empty)");
        mListViewAdapter = null;
        mListView = null;
        mArrayList = new ArrayList<>();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PageFragment_1.
     */
    // TODO: Rename and change types and number of parameters
    public static PageFragment_2 newInstance(int page) {
        Log.d("newInstance()", "PageFragment_2 page=" + page);
        PageFragment_2 fragment = new PageFragment_2();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(CLASS_NAME, "onCreate() start. savedInstanceState->"+savedInstanceState);
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            int param1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(CLASS_NAME, "onCreateView() start. savedInstanceState->"+savedInstanceState);
        // Inflate the layout for this fragment
        if (null!=savedInstanceState) {
            int page = getArguments().getInt(ARG_PARAM1, 0);
        }
        View view = inflater.inflate(R.layout.fragment_page, null, true);

        showList( view ); //tabのデータを表示
        setListViewListener(); //listener登録

        return view;
    }

    private void setListViewListener() {
        //set listener.
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(CLASS_NAME, "onItemClick()");
            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            //Long touch すると onItemClick() も発生する。
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(CLASS_NAME, "onItemLongClick()");
                return false;
            }
        });
    }

    /*
    * argument : int page       0 origin
    */
    private void showList( View view ) {
        Log.d(CLASS_NAME, "showList() start.");
        setInstalledApp();
        if (null != mListViewAdapter) {
            mListViewAdapter.clear();
        } else {
            mListViewAdapter = new ListViewAdapter(getContext(), R.layout.page_row, R.id.list_row_text, mArrayList);
        }
        if (null==mListView) {
            Log.d(CLASS_NAME, "mListView is null. (2)");
            mListView = view.findViewById(R.id.process_list);
        }
        mListView.setAdapter(mListViewAdapter);
        mListViewAdapter.notifyDataSetChanged(); //listViewに通知
    }

    private void setInstalledApp() {
        Log.d(CLASS_NAME, "setInstalledApp() start");
        Context context = this.getContext();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        if (null != activityManager) {
            Log.d(CLASS_NAME, "installed appl count : ");//+ runningApp.size());
        }
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
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
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
