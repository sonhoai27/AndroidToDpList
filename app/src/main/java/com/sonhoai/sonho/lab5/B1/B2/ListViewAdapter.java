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
    private ArrayList<Employee> employees;

    public ListViewAdapter(Context context, ArrayList<Employee> employees) {
        this.context = context;
        this.employees = employees;
    }

    @Override
    public int getCount() {
        return employees.size();
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
        view = LayoutInflater.from(context).inflate(R.layout.custom_employee, null);
        TextView txtFirst = view.findViewById(R.id.tvFirstName);
        TextView txtLast = view.findViewById(R.id.tvLastName);
        TextView txtGender = view.findViewById(R.id.tvGender);
        TextView txtHire = view.findViewById(R.id.tvHire);
        TextView txtDept = view.findViewById(R.id.tvDept);
        txtFirst.setText(employees.get(i).getFistName());
        txtLast.setText(employees.get(i).getLastName());
        txtGender.setText(employees.get(i).getGender());
        txtHire.setText(employees.get(i).getHireDate());
        txtDept.setText(employees.get(i).getDept());
        return view;
    }
}
