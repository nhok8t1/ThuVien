package com.example.thuvien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.thuvien.dao.SachDAO;
import com.example.thuvien.dao.ThanhVienDAO;
import com.example.thuvien.model.Sach;
import com.example.thuvien.model.ThanhVien;

public class AddThanhVienActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText eHoTen, eNamSinh;
    private Button btUpdate, btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_thanh_vien);
        eHoTen = findViewById(R.id.edTenTV);
        eNamSinh = findViewById(R.id.edNamSinh);
        btUpdate = findViewById(R.id.btUpdateTV);
        btCancel = findViewById(R.id.btCancelTV);
        btUpdate.setOnClickListener(this);
        btCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btCancel) {
            finish();
        }
        if (view == btUpdate) {
            String tenTV = eHoTen.getText().toString();
            String namSinh = eNamSinh.getText().toString();
            if (!tenTV.isEmpty()) {
                ThanhVien tv = new ThanhVien(tenTV, namSinh);
                ThanhVienDAO dao = new ThanhVienDAO(this);
                dao.insert(tv);
                finish();
            }
        }
    }
}