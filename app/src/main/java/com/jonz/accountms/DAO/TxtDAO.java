package com.jonz.accountms.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;

import com.jonz.accountms.model.Tb_txt;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jon-z on 2016/7/8.
 */
public class TxtDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;

    public TxtDAO(Context context){
        helper = new DBOpenHelper(context);
    }

    public void add(Tb_txt tb_txt){
        db = helper.getWritableDatabase();
        db.execSQL("insert into Tb_txt (txt) values (?)",
                new Object[]{tb_txt.getTxt()});
    }

    public List<String> getTxt(){
        List<String> listtxt = new ArrayList<>();
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_txt", null);
        while (cursor.moveToNext()){
            listtxt.add(cursor.getString(cursor.getColumnIndex("txt")));
        }
        return listtxt;
    }

    public void delete(){
        db = helper.getWritableDatabase();
        db.execSQL("delete from tb_txt");
    }
}
