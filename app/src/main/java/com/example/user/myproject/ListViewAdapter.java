package com.example.user.myproject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by User on 2017/11/22.
 *
 */

public class ListViewAdapter extends ArrayAdapter<TextView> {

    private final String CLASS_NAME = "ListViewAdapter";
    private LayoutInflater mInflater = null;

    public ListViewAdapter(@NonNull Context context, int resource, int textViewResourceId, List<TextView> objects) {
        super(context, resource, textViewResourceId, objects);
        mInflater = LayoutInflater.from(context);
        Log.d( CLASS_NAME,"ListViewAdapter start" );
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;

        if (null!=convertView) {
            view = convertView;
        } else {
            view = mInflater.inflate(R.layout.page_row, parent, false);
        }

        //set text and icon on TextView
        TextView textView = view.findViewById(R.id.list_row_text); //layoutのコントロールＩＤを取得
        if (null!=textView) {
            //icon
            Drawable[] applicationIcon = getItem(position).getCompoundDrawables();
            AtomicReference<Drawable> icon = new AtomicReference<>();
            icon.set( applicationIcon[0] );
            icon.get().setBounds(0, 0, applicationIcon[0].getBounds().width(), applicationIcon[0].getBounds().height()); //ICONの表示位置を設定 (引数：座標 x, 座標 y, 幅, 高さ)
            textView.setCompoundDrawables(icon.get(), null, null, null); //TextViewにアイコンセット（四辺(left, top, right, bottom)に対して別個にアイコンを描画できる）
            //text
            textView.setText( getItem(position).getText() );
        } else {
            Log.d(CLASS_NAME, "textView is null at getView().");
        }//if(textView)

        return view;
    }
}
