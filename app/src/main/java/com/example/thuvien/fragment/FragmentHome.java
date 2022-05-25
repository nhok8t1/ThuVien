package com.example.thuvien.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.thuvien.MainActivity;
import com.example.thuvien.R;
import com.example.thuvien.dao.PhieuMuonDAO;
import com.example.thuvien.dao.ThuThuDAO;
import com.example.thuvien.model.ThuThu;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.List;

public class FragmentHome extends Fragment implements View.OnClickListener {
    private ThuThuDAO dao;
    private TextView tvHome, tvDoanhThu;
    private List<ThuThu> listThuThu;
    private TextInputEditText edFrom, edTo;
    private Button btDoanhThu;
    private PhieuMuonDAO phieuMuonDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvHome = view.findViewById(R.id.tvHome);
        edFrom = view.findViewById(R.id.edFrom);
        edTo = view.findViewById(R.id.edTo);
        tvDoanhThu = view.findViewById(R.id.tvDoanhThu);
        btDoanhThu = view.findViewById(R.id.btDoanhThu);
        phieuMuonDAO = new PhieuMuonDAO(getActivity());
        SharedPreferences pref = getContext().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String user = pref.getString("USERNAME", null);
        tvHome.setText("Welcome " + user);
        edFrom.setOnClickListener(this);
        edTo.setOnClickListener(this);
        btDoanhThu.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == edFrom) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    String date = "";
                    if (m > 8) {
                        date = d + "/" + (m + 1) + "/" + y;
                    } else {
                        date = d + "/0" + (m + 1) + "/" + y;
                    }
                    edFrom.setText(date);
                }
            }, year, month, day);
            dialog.show();
        }
        if (view == edTo) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    String date = "";
                    if (m > 8) {
                        date = d + "/" + (m + 1) + "/" + y;
                    } else {
                        date = d + "/0" + (m + 1) + "/" + y;
                    }
                    edTo.setText(date);
                }
            }, year, month, day);
            dialog.show();
        }
        if (view == btDoanhThu) {
            tvDoanhThu.setText(String.valueOf(phieuMuonDAO.getDoanhThu(edFrom.getText().toString(), edTo.getText().toString())));
        }
    }
}
