package com.kiemtra.vetau.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.kiemtra.vetau.model.VeTau;

import java.util.ArrayList;
import java.util.List;

public class DBContext extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "vetau_manager";
    private static final String TABLE_NAME = "vetau";
    private static final String ID = "id";
    private static final String GADI = "gadi";
    private static final String GADEN = "gaden";
    private static final String DONGIA = "dongia";
    private static final String LOAIVE = "loaive";
    private static int VERSION = 1;

    private Context context;

    private String SQLQuery = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " integer primary key, " +
            GADI + " TEXT, " +
            GADEN + " TEXT, " +
            DONGIA + " REAL, " +
            LOAIVE + " NUMERIC)";

    public DBContext(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addVeTau(VeTau veTau) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GADI, veTau.getmGaDi());
        values.put(GADEN, veTau.getmGaDen());
        values.put(DONGIA, veTau.getmDonGia());
        int flag = (veTau.ismLoaiVe())? 1 : 0;
        values.put(LOAIVE, flag);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<VeTau> getAllVeTau() {
        List<VeTau> listVeTau = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                VeTau veTau = new VeTau();
                veTau.setmId(cursor.getInt(0));
                veTau.setmGaDi(cursor.getString(1)+"");
                veTau.setmGaDen(cursor.getString(2));
                veTau.setmDonGia(cursor.getFloat(3));
                int flag = cursor.getInt(4);
                if (flag == 0) {
                    veTau.setmLoaiVe(false);
                }
                if (flag == 1){
                    veTau.setmLoaiVe(true);
                }
                listVeTau.add(veTau);

            } while (cursor.moveToNext());
        }
        db.close();
        return listVeTau;
    }
    public int updateVeTau(VeTau veTau){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GADI,veTau.getmGaDi());
        contentValues.put(GADEN,veTau.getmGaDen());
        contentValues.put(DONGIA,veTau.getmDonGia());
        int flag = (veTau.ismLoaiVe())? 1 : 0;
        contentValues.put(LOAIVE,flag);
        return db.update(TABLE_NAME,contentValues,ID+"=?",new String[]{String.valueOf(veTau.getmId())});
    }
    public int deleteVeTau(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,ID+"=?",new String[] {String.valueOf(id)});
    }
}

