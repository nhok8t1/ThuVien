package com.example.thuvien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.thuvien.adapter.ViewPagerAdapter;
import com.example.thuvien.fragment.FragmentHome;
import com.example.thuvien.fragment.FragmentNguoiDung;
import com.example.thuvien.fragment.FragmentPhieuMuon;
import com.example.thuvien.fragment.FragmentSach;
import com.example.thuvien.fragment.FragmentThanhVien;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.bottom_nav);
        viewPager = findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        navigationView.getMenu().findItem(R.id.mHome).setChecked(true);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.mNote).setChecked(true);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.mBook).setChecked(true);
                        break;
                    case 3:
                        navigationView.getMenu().findItem(R.id.mMember).setChecked(true);
                        break;
                    case 4:
                        navigationView.getMenu().findItem(R.id.mNguoidung).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setTitle("Trang chủ");
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mHome:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.mNote:
                        setTitle("Phiếu mượn");
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.mBook:
                        setTitle("Sách");
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.mMember:
                        setTitle("Độc giả thành viên");
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.mNguoidung:
                        setTitle("Thủ thư");
                        viewPager.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });
    }
}