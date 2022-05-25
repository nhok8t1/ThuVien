package com.example.thuvien.dal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "LibraryManager";
    private static final int DATABASE_VERSION = 5;
    static final String CREATE_TABLE_SACH = "CREATE TABLE Sach (maSach  INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "tenSach TEXT NOT NULL," +
            "giaThue INTEGER NOT NULL," +
            "maLoai TEXT NOT NULL)";
    static final String CREATE_TABLE_THANHVIEN = "CREATE TABLE ThanhVien (maTV INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "hoTen   TEXT NOT NULL,"
            + "namSinh INTEGER NOT NULL)";
    static final String CREATE_TABLE_PHIEU_MUON = "CREATE TABLE PhieuMuon (maPM INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "maTT TEXT REFERENCES ThuThu (maTT),"
            + "maTV TEXT REFERENCES ThanhVien (maTV),"
            + "maSach INTEGER REFERENCES Sach(maSach),"
            + "tienThue INTEGER NOT NULL,"
            + "ngay DATE NOT NULL,"
            + "traSach INTEGER NOT NULL)";
    static final String CREATE_TABLE_THU_THU = "CREATE TABLE ThuThu (maTT TEXT PRIMARY KEY,"
            + "hoTen TEXT NOT NULL,"
            + "matKhau TEXT NOT NULL)";
    static final String INSERT_THU_THU = "INSERT INTO ThuThu(maTT, HoTen, MatKhau) values"
            + "('test','admin','admin')";

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SACH);
        db.execSQL(CREATE_TABLE_THANHVIEN);
        db.execSQL(CREATE_TABLE_PHIEU_MUON);
        db.execSQL(CREATE_TABLE_THU_THU);
        db.execSQL(INSERT_THU_THU);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropTableSach = "DROP TABLE IF EXISTS Sach";
        db.execSQL(dropTableSach);
        String dropTableThanhVien = "DROP TABLE IF EXISTS ThanhVien";
        db.execSQL(dropTableThanhVien);
        String dropTableThuThu = "DROP TABLE IF EXISTS ThuThu";
        db.execSQL(dropTableThuThu);
        String dropTablePhieuMuon = "DROP TABLE IF EXISTS PhieuMuon";
        db.execSQL(dropTablePhieuMuon);
        onCreate(db);
    }

    public Cursor readAllDataSach() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM Sach";
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(sql, null);
        }
        return cursor;
    }

    public Cursor readAllDataThanhVien() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM ThanhVien";
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(sql, null);
        }
        return cursor;
    }

    public Cursor readAllDataThuThu() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM ThuThu";
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(sql, null);
        }
        return cursor;
    }

    public Cursor readAllDataPhieuMuon() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM PhieuMuon";
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(sql, null);
        }
        return cursor;
    }
}
