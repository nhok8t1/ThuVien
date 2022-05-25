package com.example.thuvien.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuvien.R;
import com.example.thuvien.dao.SachDAO;
import com.example.thuvien.dao.ThanhVienDAO;
import com.example.thuvien.model.PhieuMuon;
import com.example.thuvien.model.Sach;
import com.example.thuvien.model.ThanhVien;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.PhieuMuonViewHolder> {
    private Context context;
    private List<PhieuMuon> list;
    SachDAO sachDAO;
    ThanhVienDAO thanhVienDAO;
    private List<Sach> listSach;
    private List<ThanhVien> listThanhVien;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private ItemListener itemListener;

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public PhieuMuonAdapter() {
        list = new ArrayList<>();
        listSach = new ArrayList<>();
        listThanhVien = new ArrayList<>();
    }

    public void setList(List<PhieuMuon> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public PhieuMuon getPhieuMuon(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public PhieuMuonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phieu_muon, parent, false);
        context = parent.getContext();
        return new PhieuMuonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhieuMuonViewHolder holder, int position) {
        sachDAO = new SachDAO(context);
        thanhVienDAO = new ThanhVienDAO(context);
        listSach = sachDAO.getAll();
        listThanhVien = thanhVienDAO.getAll();
        PhieuMuon phieuMuon = list.get(position);
        Sach k = new Sach();
        for (Sach i : listSach) {
            if (i.getMaSach() == phieuMuon.getMaSach()) {
                k = i;
                break;
            }
        }
        ThanhVien tv = new ThanhVien();
        for (ThanhVien i : listThanhVien) {
            if (i.getMaTV() == phieuMuon.getMaTV()) {
                tv = i;
                break;
            }
        }
        holder.thanhVien.setText("Tên: " + tv.getHoTen());
        holder.sach.setText("Sách:" + k.getTenSach());
        holder.gia.setText("Tiền thuê:" + String.valueOf(k.getGiaThue()));
        /*holder.thanhVien.setText(String.valueOf(phieuMuon.getMaTV()));
        holder.sach.setText(String.valueOf(phieuMuon.getMaSach()));
        holder.gia.setText(String.valueOf(phieuMuon.getTienThue()));*/
        holder.ngay.setText(sdf.format(phieuMuon.getNgay()));
        if (phieuMuon.getTraSach() == 1) {
            holder.cbtra.setChecked(true);
        } else {
            holder.cbtra.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PhieuMuonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView thanhVien, sach, gia, ngay;
        private CheckBox cbtra;

        public PhieuMuonViewHolder(@NonNull View view) {
            super(view);
            thanhVien = view.findViewById(R.id.tv_TVmuon);
            sach = view.findViewById(R.id.tv_sachmuon);
            ngay = view.findViewById(R.id.tv_ngaymuon);
            gia = view.findViewById(R.id.tv_giamuonsach);
            cbtra = view.findViewById(R.id.cbtra);
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
