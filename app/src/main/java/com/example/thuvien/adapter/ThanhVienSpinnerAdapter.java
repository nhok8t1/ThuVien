package com.example.thuvien.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.thuvien.R;
import com.example.thuvien.model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienSpinnerAdapter extends ArrayAdapter<ThanhVien> {
    Context context;
    ArrayList<ThanhVien> lists;
    TextView tvTV;

    public ThanhVienSpinnerAdapter(@NonNull Context context, ArrayList<ThanhVien> lists) {
        super(context, 0, lists);
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_spinner_thanh_vien, null);
        }
        final ThanhVien item = lists.get(position);
        if (item != null) {
            tvTV = view.findViewById(R.id.tv_spTV);
            tvTV.setText(item.getHoTen());
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_spinner_thanh_vien, null);
        }
        final ThanhVien item = lists.get(position);
        if (item != null) {
            tvTV = view.findViewById(R.id.tv_spTV);
            tvTV.setText(item.getHoTen());
        }
        return view;
    }
}
