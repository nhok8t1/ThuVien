package com.example.thuvien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.thuvien.dao.SachDAO;
import com.example.thuvien.model.Sach;

public class AddSachActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner sp;
    private EditText eTenSach, eGiaThue;
    private Button btUpdate, btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sach);
        sp = findViewById(R.id.spLoaiSach);
        eTenSach = findViewById(R.id.edTenSach);
        eGiaThue = findViewById(R.id.edGiaThue);
        btUpdate = findViewById(R.id.btUpdate);
        btCancel = findViewById(R.id.btCancel);
        sp.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner_loai_sach, getResources().getStringArray(R.array.category)));
        btUpdate.setOnClickListener(this);
        btCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btCancel) {
            finish();
        }
        if (view == btUpdate) {
            String tenSach = eTenSach.getText().toString();
            String loaiSach = sp.getSelectedItem().toString();
            int giaThue = Integer.parseInt(eGiaThue.getText().toString());
            if (!tenSach.isEmpty()) {
                Sach sach = new Sach(tenSach, giaThue, loaiSach);
                SachDAO dao=new SachDAO(this);
                dao.insert(sach);
                finish();
            }
        }
    }
}