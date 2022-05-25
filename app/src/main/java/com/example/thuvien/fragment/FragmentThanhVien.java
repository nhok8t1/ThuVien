package com.example.thuvien.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuvien.AddSachActivity;
import com.example.thuvien.AddThanhVienActivity;
import com.example.thuvien.R;
import com.example.thuvien.SachActivity;
import com.example.thuvien.ThanhVienActivity;
import com.example.thuvien.adapter.SachAdapter;
import com.example.thuvien.adapter.ThanhVienAdapter;
import com.example.thuvien.dao.SachDAO;
import com.example.thuvien.dao.ThanhVienDAO;
import com.example.thuvien.model.Sach;
import com.example.thuvien.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class FragmentThanhVien extends Fragment implements ThanhVienAdapter.ItemListener {
    private ThanhVienAdapter adapter;
    private RecyclerView recyclerView;
    private ThanhVienDAO dao;
    private FloatingActionButton fab;
    private SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thanh_vien, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.reThanhVien);
        searchView = view.findViewById(R.id.searchThanhVien);
        fab = view.findViewById(R.id.fabThanhVien);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddThanhVienActivity.class);
                startActivity(intent);
            }
        });
        adapter = new ThanhVienAdapter();
        dao = new ThanhVienDAO(getActivity());
        List<ThanhVien> list = dao.getAll();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<ThanhVien> list = dao.getbyTitle(s);
                adapter.setList(list);
                return true;
            }
        });
    }

    @Override
    public void OnItemClick(View view, int position) {
        ThanhVien tv = adapter.getThanhVien(position);
        Intent intent = new Intent(getActivity(), ThanhVienActivity.class);
        intent.putExtra("tv", tv);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<ThanhVien> list = dao.getAll();
        adapter.setList(list);
    }
}
