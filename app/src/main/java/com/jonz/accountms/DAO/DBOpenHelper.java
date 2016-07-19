package com.jonz.accountms.DAO;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jon-z on 2016/5/18.
 */
public class DBOpenHelper extends SQLiteOpenHelper{
    private static final int VERSION = 1;
    private static final String DBNAME = "account.db";

    public DBOpenHelper(Context context){
        super(context,DBNAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE tb_inaccount(_id integer primary key,money decimal, " +
                "time varchar(10), type varchar(10), handler varchar(100), mark varchar(200))");
        db.execSQL("CREATE TABLE tb_outaccount(_id integer primary key,money decimal, " +
                "time varchar(10), type varchar(10), address varchar(100), mark varchar(200))");
        db.execSQL("CREATE TABLE tb_pwd(password varchar(20))");
        db.execSQL("CREATE TABLE tb_flag(_id integer primary key,money decimal, flag vharchar(200))");
        db.execSQL("CREATE TABLE tb_txt(txt varchar(200))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
