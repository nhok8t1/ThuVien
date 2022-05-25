package com.example.thuvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

public class AddPhieuMuonActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner spTV, spSach;
    private TextView ngay, tien;
    private CheckBox cbthue;
    private Button btUpdate, btCancel;
    private SachDAO sachDAO;
    private ThanhVienDAO thanhVienDAO;
    private ThanhVienSpinnerAdapter thanhVienSpinnerAdapter;
    private SachSpinnerAdapter sachSpinnerAdapter;
    private ArrayList<ThanhVien> listThanhVien;
    private ArrayList<Sach> listSach;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private int tienSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phieu_muon);
        spTV = findViewById(R.id.spMaTV);
        spSach = findViewById(R.id.spMaSach);
        ngay = findViewById(R.id.tvNgayThue);
        tien = findViewById(R.id.tvTienThue);
        cbthue = findViewById(R.id.chkTrasach);
        btUpdate = findViewById(R.id.btUpdatePM);
        btCancel = findViewById(R.id.btCancelPM);
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
        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tienSP = listSach.get(i).getGiaThue();
                tien.setText(String.valueOf(tienSP));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ngay.setText(sdf.format(new Date()));
        btUpdate.setOnClickListener(this);
        btCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
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
            PhieuMuon pm = new PhieuMuon("test", idTV, idSach, new Date(), tienMuon, check);
            PhieuMuonDAO daoPM = new PhieuMuonDAO(this);
            daoPM.insert(pm);
            finish();
        }
    }
}