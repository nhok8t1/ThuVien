package com.example.thuvien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.thuvien.adapter.SachSpinnerAdapter;
import com.example.thuvien.adapter.ThanhVienSpinnerAdapter;
import com.example.thuvien.dao.PhieuMuonDAO;
import com.example.thuvien.dao.SachDAO;
import com.example.thuvien.dao.ThanhVienDAO;
import com.example.thuvien.model.PhieuMuon;
import com.example.thuvien.model.Sach;
import com.example.thuvien.model.ThanhVien;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PhieuMuonActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner spTV, spSach;
    private TextView ngay, tien;
    private CheckBox cbthue;
    private Button btUpdate, btCancel, btRemove;
    private SachDAO sachDAO;
    private ThanhVienDAO thanhVienDAO;
    private ThanhVienSpinnerAdapter thanhVienSpinnerAdapter;
    private SachSpinnerAdapter sachSpinnerAdapter;
    private ArrayList<ThanhVien> listThanhVien;
    private ArrayList<Sach> listSach;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private int tienSP;
    private PhieuMuon pm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phieu_muon);
        spTV = findViewById(R.id.spMaTV);
        spSach = findViewById(R.id.spMaSach);
        ngay = findViewById(R.id.tvNgayThue);
        tien = findViewById(R.id.tvTienThue);
        cbthue = findViewById(R.id.chkTrasach);
        btUpdate = findViewById(R.id.btUpdatePM);
        btCancel = findViewById(R.id.btCancelPM);
        btRemove = findViewById(R.id.btRemovePM);
        thanhVienDAO = new ThanhVienDAO(this);
        listThanhVien = new ArrayList<ThanhVien>();
        listThanhVien = (ArrayList<ThanhVien>) thanhVienDAO.getAll();
        thanhVienSpinnerAdapter = new ThanhVienSpinnerAdapter(this, listThanhVien);
        spTV.setAdapter(thanhVienSpinnerAdapter);
        sachDAO = new SachDAO(this);
        listSach = new ArrayList<Sach>();
        listSach = (ArrayList<Sach>) sachDAO.getAll();
        sachSpinnerAdapter = new SachSpinnerAdapter(this, listSach);
        spSach.setAdapter(sachSpinnerAdapter);
        Intent intent = getIntent();
        pm = (PhieuMuon) intent.getSerializableExtra("pm");
        ThanhVien p = new ThanhVien();
        int dem = 0;
        for (ThanhVien i : listThanhVien) {
            if (i.getMaTV() == pm.getMaTV()) {
                p = i;
                break;
            } else dem++;
        }
        spTV.setSelection(dem);
        Sach s = new Sach();
        dem = 0;
        for (Sach i : listSach) {
            if (i.getMaSach() == pm.getMaSach()) {
                s = i;
                break;
            } else dem++;
        }
        spSach.setSelection(dem);
        ngay.setText(sdf.format(pm.getNgay()));
        tien.setText(String.valueOf(pm.getTienThue()));
        if (pm.getTraSach() == 1) {
            cbthue.setChecked(true);
        } else {
            cbthue.setChecked(false);
        }
        btUpdate.setOnClickListener(this);
        btRemove.setOnClickListener(this);
        btCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        PhieuMuonDAO dao = new PhieuMuonDAO(this);
        if (view == btCancel) {
            finish();
        }
        if (view == btUpdate) {
            int check;
            final int idTV, idSach, tienMuon;
            idTV = listThanhVien.get(spTV.getSelectedItemPosition()).getMaTV();
            idSach = listSach.get(spSach.getSelectedItemPosition()).getMaSach();
            tienMuon = listSach.get(spSach.getSelectedItemPosition()).getGiaThue();
            if (cbthue.isChecked()) {
                check = 1;
            } else {
                check = 0;
            }
            int id = pm.getMaPM();
            PhieuMuon pm1 = new PhieuMuon(id, "test", idTV, idSach, new Date(), tienMuon, check);
            dao.update(pm1);
            finish();
        }
        if (view == btRemove) {
            int id = pm.getMaPM();
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thông báo xóa");
            builder.setMessage("Bạn có chắc chắn muốn xóa " + pm.getMaPM() + " không?");
            builder.setIcon(R.drawable.ic_baseline_remove_24);
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    PhieuMuonDAO dao1 = new PhieuMuonDAO(getApplicationContext());
                    dao1.delete(String.valueOf(id));
                    finish();
                }
            });
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}