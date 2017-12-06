package com.example.user.myproject;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
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
 * {@link PageFragment_1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PageFragment_1#newInstance} factory method to
 * create an instance of this fragment.
 */

//initial create by android studio.
// select [file]->[new]->[fragment]->[blank fragment]

public class PageFragment_1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private final String CLASS_NAME = getClass().getSimpleName();
    protected static final String ARG_PARAM1 = "param1";

    private View mView; //このview
    private ListView mListView; //このviewにあるlistview
    private ArrayList<TextView> mArrayList;
    private ListViewAdapter mListViewAdapter;

    private OnFragmentInteractionListener mListener;

    public PageFragment_1() {
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
    public static PageFragment_1 newInstance(int page) {
        Log.d("newInstance()", "PageFragment_1 page=" + page);
        PageFragment_1 fragment = new PageFragment_1();
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
        mView = inflater.inflate(R.layout.fragment_page, container, false);

        return mView;
    }

//    /**
//     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
//     * has returned, but before any saved state has been restored in to the view.
//     * This gives subclasses a chance to initialize themselves once
//     * they know their view hierarchy has been completely created.  The fragment's
//     * view hierarchy is not however attached to its parent at this point.
//     *
//     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
//     * @param savedInstanceState If non-null, this fragment is being re-constructed
//     */
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        Log.d(CLASS_NAME, "onViewCreated() start.");
//        showList();
//        setListViewListener(); //listener登録
//    }

    /**
     * Called when the fragment's activity has been created and this
     * fragment's view hierarchy instantiated.  It can be used to do final
     * initialization once these pieces are in place, such as retrieving
     * views or restoring state.  It is also useful for fragments that use
     * {@link #setRetainInstance(boolean)} to retain their instance,
     * as this callback tells the fragment when it is fully associated with
     * the new activity instance.  This is called after {@link #onCreateView}
     * and before {@link #onViewStateRestored(Bundle)}.
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(CLASS_NAME, "onActivityCreated() start.");
        showList();
        setListViewListener(); //listener登録
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
    private void showList() {
        Log.d(CLASS_NAME, "showList() start.");

        setRunningProcess();

        Log.d(CLASS_NAME, "mListViewAdapter->"+mListViewAdapter);
        Log.d(CLASS_NAME, "mArrayList.size()->"+mArrayList.size());

        if (null != mListViewAdapter) {
            mListViewAdapter.clear();
        } else {
            mListViewAdapter = new ListViewAdapter(this.getContext(), R.layout.page_row, R.id.list_row_text, mArrayList);
        }
        if (null==mListView) {
            Log.d(CLASS_NAME, "mListView is null. (2)");
            mListView = mView.findViewById(R.id.process_list);
        }
        Log.d(CLASS_NAME, "mListViewAdapter.getCount()->"+mListViewAdapter.getCount());
        mListView.setAdapter(mListViewAdapter);
        mListViewAdapter.notifyDataSetChanged(); //listViewに通知
    }

    private void setRunningProcess() {
        Log.d(CLASS_NAME, "setRunningProcess() start");
        Context context = this.getContext();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        if (null != activityManager) {
            List<ActivityManager.RunningAppProcessInfo> runningApp = activityManager.getRunningAppProcesses();
            PackageManager packageManager = context.getPackageManager();
            Log.d(CLASS_NAME, "running appl count : " + runningApp.size());
            if (!runningApp.isEmpty()) {
                int i = 0;
                mArrayList.clear();
                for (ActivityManager.RunningAppProcessInfo app : runningApp) {
                    i++;
                    try {
                        ApplicationInfo applicationInfo = packageManager.getApplicationInfo(app.processName, 0);
                        Drawable applicationIcon = packageManager.getApplicationIcon(applicationInfo);
                        //set application name.
                        TextView textView = new TextView(context);
                        String packageName = i + ") " + (String) packageManager.getApplicationLabel(applicationInfo);
                        textView.setText(packageName);
                        //Log.d(CLASS_NAME, "package name -> "+packageName);
                        //set icon.
                        AtomicReference<Drawable> icon = new AtomicReference<>();
                        icon.set(applicationIcon);
                        //ICONの表示位置を設定 (引数：座標 x, 座標 y, 幅, 高さ)
//                                Log.d(CLASS_NAME, "size of icon (w/h) : "+icon.get().getIntrinsicWidth()+" / "+icon.get().getIntrinsicHeight());
//iconサイズそのままだから・・・                                icon.get().setBounds(0, 0, icon.get().getIntrinsicWidth(), icon.get().getIntrinsicHeight());
                        icon.get().setBounds(0, 0, 72, 72);
                        //TextViewにアイコンセット（四辺(left, top, right, bottom)に対して別個にアイコンを描画できる）
                        textView.setCompoundDrawables(icon.get(), null, null, null);
                        //add new data to array.
                        mArrayList.add(textView);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                        Log.d(CLASS_NAME, "exception of getapplicationinfo() : i=" + i + "processname=" + app.processName + " / " + "importance=" + app.importance);
                    }
                }//for(app)
            }//if(!runningApp)
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
