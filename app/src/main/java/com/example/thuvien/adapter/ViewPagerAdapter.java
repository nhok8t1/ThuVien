package com.example.thuvien.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.thuvien.fragment.FragmentHome;
import com.example.thuvien.fragment.FragmentNguoiDung;
import com.example.thuvien.fragment.FragmentPhieuMuon;
import com.example.thuvien.fragment.FragmentSach;
import com.example.thuvien.fragment.FragmentThanhVien;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentHome();
            case 1:
                return new FragmentPhieuMuon();
            case 2:
                return new FragmentSach();
            case 3:
                return new FragmentThanhVien();
            case 4:
                return new FragmentNguoiDung();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
