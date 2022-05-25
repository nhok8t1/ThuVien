package com.example.thuvien.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuvien.AddPhieuMuonActivity;
import com.example.thuvien.AddSachActivity;
import com.example.thuvien.PhieuMuonActivity;
import com.example.thuvien.R;
import com.example.thuvien.SachActivity;
import com.example.thuvien.adapter.PhieuMuonAdapter;
import com.example.thuvien.adapter.SachAdapter;
import com.example.thuvien.dao.PhieuMuonDAO;
import com.example.thuvien.dao.SachDAO;
import com.example.thuvien.dao.ThanhVienDAO;
import com.example.thuvien.model.PhieuMuon;
import com.example.thuvien.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class FragmentPhieuMuon extends Fragment implements PhieuMuonAdapter.ItemListener {
    private RecyclerView recyclerView;
    private PhieuMuonAdapter adapter;
    private PhieuMuonDAO dao;
    private FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_phieu_muon, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rePhieuMuon);
        fab = view.findViewById(R.id.fabPM);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddPhieuMuonActivity.class);
                startActivity(intent);
            }
        });
        adapter = new PhieuMuonAdapter();
        dao = new PhieuMuonDAO(getActivity());
        List<PhieuMuon> list = dao.getAll();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        PhieuMuon pm = adapter.getPhieuMuon(position);
        Intent intent = new Intent(getActivity(), PhieuMuonActivity.class);
        intent.putExtra("pm", pm);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<PhieuMuon> list = dao.getAll();
        adapter.setList(list);
    }
}
