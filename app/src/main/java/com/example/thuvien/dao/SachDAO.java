package com.example.thuvien.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thuvien.dal.SQLiteHelper;
import com.example.thuvien.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    private SQLiteDatabase db;

    public SachDAO(Context context) {
        SQLiteHelper dbHelper = new SQLiteHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(Sach obj) {
        ContentValues values = new ContentValues();
        values.put("tenSach", obj.getTenSach());
        values.put("giaThue", obj.getGiaThue());
        values.put("maLoai", obj.getMaLoai());
        return db.insert("Sach", null, values);
    }

    public int update(Sach obj) {
        ContentValues values = new ContentValues();
        values.put("tenSach", obj.getTenSach());
        values.put("giaThue", obj.getGiaThue());
        values.put("maLoai", obj.getMaLoai());
        return db.update("Sach", values, "maSach=?", new String[]{String.valueOf(obj.getMaSach())});
    }

    public int delete(String id) {
        return db.delete("Sach", "maSach=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<Sach> getData(String sql, String... selectionArgs) {
        List<Sach> list = new ArrayList<Sach>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            Sach obj = new Sach();
            obj.setMaSach(Integer.parseInt(c.getString(c.getColumnIndex("maSach"))));
            obj.setGiaThue(Integer.parseInt(c.getString(c.getColumnIndex("giaThue"))));
            obj.setTenSach(c.getString(c.getColumnIndex("tenSach")));
            obj.setMaLoai(c.getString(c.getColumnIndex("maLoai")));
            list.add(obj);
        }
        return list;
    }

    public List<Sach> getAll() {
        String sql = "SELECT * FROM Sach";
        return getData(sql);
    }

    public List<Sach> getbyTitle(String key) {
        List<Sach> list = new ArrayList<>();
        String whereClause = "tenSach like ?";
        String[] whereArgs = {"%" + key + "%"};
        Cursor rs = db.query("Sach", null, whereClause, whereArgs, null, null, null);
        while (rs != null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String title = rs.getString(1);
            int gia = rs.getInt(2);
            String loai = rs.getString(3);
            list.add(new Sach(id, title, gia, loai));
        }
        return list;
    }

    public Sach getID(String id) {
        String sql = "SELECT * FROM Sach WHERE maSach=?";
        List<Sach> list = getData(sql, id);
        return list.get(0);
    }

    public Sach getbyID(String key) {
        List<Sach> list = new ArrayList<>();
        String whereClause = "maSach=?";
        String[] whereArgs = {key};
        Cursor rs = db.query("Sach", null, whereClause, whereArgs, null, null, null);
        while (rs != null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String title = rs.getString(1);
            int gia = rs.getInt(2);
            String loai = rs.getString(3);
            list.add(new Sach(id, title, gia, loai));
        }
        return list.get(0);
    }
}
