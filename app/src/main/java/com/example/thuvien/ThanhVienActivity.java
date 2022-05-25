package com.example.thuvien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.thuvien.dao.SachDAO;
import com.example.thuvien.dao.ThanhVienDAO;
import com.example.thuvien.model.Sach;
import com.example.thuvien.model.ThanhVien;

public class ThanhVienActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText eHoTen, eNamSinh;
    private Button btUpdate, btCancel, btRemove;
    private ThanhVien thanhVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_vien);
        eHoTen = findViewById(R.id.edTenTV);
        eNamSinh = findViewById(R.id.edNamSinh);
        btUpdate = findViewById(R.id.btUpdateTV);
        btCancel = findViewById(R.id.btCancelTV);
        btRemove = findViewById(R.id.btRemoveTV);
        btUpdate.setOnClickListener(this);
        btCancel.setOnClickListener(this);
        btRemove.setOnClickListener(this);
        Intent intent = getIntent();
        thanhVien = (ThanhVien) intent.getSerializableExtra("tv");
        eHoTen.setText(thanhVien.getHoTen());
        eNamSinh.setText(thanhVien.getNamSinh());
    }

    @Override
    public void onClick(View view) {
        ThanhVienDAO dao = new ThanhVienDAO(this);
        if (view == btCancel) {
            finish();
        }
        if (view == btUpdate) {
            String hoTen = eHoTen.getText().toString();
            String namSinh = eNamSinh.getText().toString();
            if (!hoTen.isEmpty()) {
                int id = thanhVien.getMaTV();
                ThanhVien item = new ThanhVien(id, hoTen, namSinh);
                dao.update(item);
                finish();
            }
        }
        if (view == btRemove) {
            int id = thanhVien.getMaTV();
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thông báo xóa");
            builder.setMessage("Bạn có chắc chắn muốn xóa " + thanhVien.getHoTen() + " không?");
            builder.setIcon(R.drawable.ic_baseline_remove_24);
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ThanhVienDAO dao1 = new ThanhVienDAO(getApplicationContext());
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