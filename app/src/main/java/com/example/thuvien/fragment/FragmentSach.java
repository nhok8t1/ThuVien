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
import com.example.thuvien.R;
import com.example.thuvien.SachActivity;
import com.example.thuvien.adapter.SachAdapter;
import com.example.thuvien.dal.SQLiteHelper;
import com.example.thuvien.dao.SachDAO;
import com.example.thuvien.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class FragmentSach extends Fragment implements SachAdapter.ItemListener {
    private RecyclerView recyclerView;
    private SachAdapter adapter;
    private SachDAO dao;
    private FloatingActionButton fab;
    private SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.reSach);
        searchView = view.findViewById(R.id.searchSach);
        fab = view.findViewById(R.id.fabSach);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddSachActivity.class);
                startActivity(intent);
            }
        });
        adapter = new SachAdapter();
        dao = new SachDAO(getActivity());
        List<Sach> list = dao.getAll();
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
                List<Sach> list = dao.getbyTitle(s);
                adapter.setList(list);
                return true;
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        Sach sach = adapter.getSach(position);
        Intent intent = new Intent(getActivity(), SachActivity.class);
        intent.putExtra("sach", sach);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Sach> list = dao.getAll();
        adapter.setList(list);
    }
}
