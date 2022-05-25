package com.example.thuvien.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.thuvien.MainActivity;
import com.example.thuvien.R;
import com.example.thuvien.dao.ThuThuDAO;
import com.example.thuvien.model.ThuThu;

import java.util.List;

public class FragmentHome extends Fragment {
    private ThuThuDAO dao;
    private TextView tvHome;
    private List<ThuThu> listThuThu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvHome = view.findViewById(R.id.tvHome);
        SharedPreferences pref = getContext().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String user = pref.getString("USERNAME", null);
        tvHome.setText("Welcome " + user);
    }
}
