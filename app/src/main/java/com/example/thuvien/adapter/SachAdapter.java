package com.example.thuvien.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuvien.R;
import com.example.thuvien.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.SachViewHolder> {
    private List<Sach> list;
    private ItemListener itemListener;

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public SachAdapter() {
        list = new ArrayList<>();
    }

    public void setList(List<Sach> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public Sach getSach(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public SachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sach, parent, false);
        return new SachViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SachViewHolder holder, int position) {
        Sach sach = list.get(position);
        //holder.maSach.setText(String.valueOf(sach.getMaSach()));
        holder.tenSach.setText(sach.getTenSach());
        holder.giaThue.setText(String.valueOf(sach.getGiaThue()));
        holder.maLoai.setText(sach.getMaLoai());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SachViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView maSach, tenSach, giaThue, maLoai;

        public SachViewHolder(@NonNull View view) {
            super(view);
            //maSach = view.findViewById(R.id.tv_masach);
            tenSach = view.findViewById(R.id.tv_tensach);
            maLoai = view.findViewById(R.id.tv_maloais);
            giaThue = view.findViewById(R.id.tv_giasach);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemListener != null) {
                itemListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface ItemListener {
        void onItemClick(View view, int position);
    }
}
