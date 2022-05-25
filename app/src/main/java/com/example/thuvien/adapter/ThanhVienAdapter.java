package com.example.thuvien.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuvien.R;
import com.example.thuvien.model.Sach;
import com.example.thuvien.model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ThanhVienViewHolder> {
    private List<ThanhVien> list;
    private ItemListener itemListener;

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public ThanhVienAdapter() {
        list = new ArrayList<>();
    }

    public void setList(List<ThanhVien> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public ThanhVien getThanhVien(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public ThanhVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thanh_vien, parent, false);
        return new ThanhVienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhVienViewHolder holder, int position) {
        ThanhVien thanhVien = list.get(position);
        holder.tenTV.setText(thanhVien.getHoTen());
        holder.namSinh.setText(thanhVien.getNamSinh());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ThanhVienViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tenTV, namSinh;

        public ThanhVienViewHolder(@NonNull View view) {
            super(view);
            tenTV = view.findViewById(R.id.tvTenTV);
            namSinh = view.findViewById(R.id.tvNamSinh);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemListener != null) {
                itemListener.OnItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface ItemListener {
        void OnItemClick(View view, int position);
    }
}
