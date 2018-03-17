package com.sonhoai.sonho.lab5.B1.B2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sonhoai.sonho.lab5.R;

import java.util.ArrayList;

/**
 * Created by sonho on 3/13/2018.
 */

public class ListViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<GhiChu> ghiChus;

    public ListViewAdapter(Context context, ArrayList<GhiChu> ghiChus) {
        this.context = context;
        this.ghiChus = ghiChus;
    }

    @Override
    public int getCount() {
        return ghiChus.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.custom_item_note, null);
        TextView txtContent = view.findViewById(R.id.txtContent);
        TextView txtDate = view.findViewById(R.id.txtDate);
        txtContent.setText(ghiChus.get(i).getContent());
        txtDate.setText(ghiChus.get(i).getDate());
        return view;
    }
}
