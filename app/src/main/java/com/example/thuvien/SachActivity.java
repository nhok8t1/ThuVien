package com.example.thuvien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.thuvien.dao.SachDAO;
import com.example.thuvien.model.Sach;

public class SachActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner sp;
    private EditText eTenSach, eGiaThue;
    private Button btUpdate, btRemove, btCancel;
    private Sach sach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach);
        sp = findViewById(R.id.spLoaiSach);
        btRemove = findViewById(R.id.btRemove);
        eTenSach = findViewById(R.id.edTenSach);
        eGiaThue = findViewById(R.id.edGiaThue);
        btUpdate = findViewById(R.id.btUpdate);
        btCancel = findViewById(R.id.btCancel);
        sp.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner_loai_sach, getResources().getStringArray(R.array.category)));
        btUpdate.setOnClickListener(this);
        btCancel.setOnClickListener(this);
        btRemove.setOnClickListener(this);
        Intent intent = getIntent();
        sach = (Sach) intent.getSerializableExtra("sach");
        eTenSach.setText(sach.getTenSach());
        eGiaThue.setText(String.valueOf(sach.getGiaThue()));
        int p = 0;
        for (int i = 0; i < sp.getCount(); i++) {
            if (sp.getItemAtPosition(i).toString().equalsIgnoreCase(sach.getMaLoai())) {
                p = i;
                break;
            }
        }
        sp.setSelection(p);
    }

    @Override
    public void onClick(View view) {
        SachDAO dao = new SachDAO(this);
        if (view == btCancel) {
            finish();
        }
        if (view == btUpdate) {
            String tenSach = eTenSach.getText().toString();
            String loaiSach = sp.getSelectedItem().toString();
            int giaThue = Integer.parseInt(eGiaThue.getText().toString());
            if (!tenSach.isEmpty()) {
                int id = sach.getMaSach();
                Sach item = new Sach(id, tenSach, giaThue, loaiSach);
                dao.update(item);
                finish();
            }
        }
        if (view == btRemove) {
            int id = sach.getMaSach();
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thông báo xóa");
            builder.setMessage("Bạn có chắc chắn muốn xóa " + sach.getTenSach() + " không?");
            builder.setIcon(R.drawable.ic_baseline_remove_24);
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SachDAO dao1 = new SachDAO(getApplicationContext());
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