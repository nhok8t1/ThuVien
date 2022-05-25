package com.example.thuvien.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thuvien.dal.SQLiteHelper;
import com.example.thuvien.model.Sach;
import com.example.thuvien.model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDAO {
    private SQLiteDatabase db;

    public ThanhVienDAO(Context context) {
        SQLiteHelper dbHelper = new SQLiteHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(ThanhVien obj) {
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("namSinh", obj.getNamSinh());
        return db.insert("ThanhVien", null, values);
    }

    public int update(ThanhVien obj) {
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("namSinh", obj.getNamSinh());
        return db.update("ThanhVien", values, "maTV=?", new String[]{String.valueOf(obj.getMaTV())});
    }

    public int delete(String id) {
        return db.delete("ThanhVien", "maTV=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<ThanhVien> getData(String sql, String... selectionArgs) {
        List<ThanhVien> list = new ArrayList<ThanhVien>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            ThanhVien obj = new ThanhVien();
            obj.setMaTV(Integer.parseInt(c.getString(c.getColumnIndex("maTV"))));
            obj.setHoTen(c.getString(c.getColumnIndex("hoTen")));
            obj.setNamSinh(c.getString(c.getColumnIndex("namSinh")));
            list.add(obj);
        }
        return list;
    }

    public List<ThanhVien> getAll() {
        String sql = "SELECT * FROM ThanhVien";
        return getData(sql);
    }

    public List<ThanhVien> getbyTitle(String key) {
        List<ThanhVien> list = new ArrayList<>();
        String whereClause = "hoTen like ?";
        String[] whereArgs = {"%" + key + "%"};
        Cursor rs = db.query("ThanhVien", null, whereClause, whereArgs, null, null, null);
        while (rs != null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String title = rs.getString(1);
            String nam = rs.getString(2);
            list.add(new ThanhVien(id, title, nam));
        }
        return list;
    }

    public ThanhVien getID(String id) {
        String sql = "SELECT * FROM Sach WHERE maTV=?";
        List<ThanhVien> list = getData(sql, id);
        return list.get(0);
    }

    public ThanhVien getbyID(String key) {
        List<ThanhVien> list = new ArrayList<>();
        String whereClause = "maTV=?";
        String[] whereArgs = {key};
        Cursor rs = db.query("ThanhVien", null, whereClause, whereArgs, null, null, null);
        while (rs != null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String title = rs.getString(1);
            String nam = rs.getString(2);
            list.add(new ThanhVien(id, title, nam));
        }
        return list.get(0);
    }
}
