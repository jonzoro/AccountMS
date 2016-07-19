package com.jonz.accountms.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.jonz.accountms.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScrollingActivity extends AppCompatActivity {
    private List<String> imgPath = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        String sdpath = Environment.getExternalStorageDirectory()+"/Image";
        Toast.makeText(this,sdpath,Toast.LENGTH_LONG).show();
        getFiles(sdpath);
        if(imgPath.size()<1){
            return;
        }

        GridView gallery = (GridView)findViewById(R.id.gallery);
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageview;
                if(convertView == null){
                    imageview = new ImageView(ScrollingActivity.this);
                    imageview.setPadding(5,5,5,5);
                }else{
                    imageview = (ImageView)convertView;
                }
                Bitmap bm = BitmapFactory.decodeFile(imgPath.get(position));
                imageview.setImageBitmap(bm);
                return imageview;
            }
            @Override
            public int getCount() {
                return imgPath.size();
            }
            @Override
            public Object getItem(int position) {
                return position;
            }
            @Override
            public long getItemId(int position) {
                return position;
            }
        };
        gallery.setAdapter(adapter);
    }

    private void getFiles(String url){
        File files = new File(url);
        File[] file = files.listFiles();
        try {
            for(File f:file){
                if(f.isDirectory()){
                    getFiles(f.getAbsolutePath());
                }else {
                    if(isImgFile(f.getPath())){
                        imgPath.add(f.getPath());
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static String[] imgFormatSet = new String[]{"jpg","png","jpeg"};
    private static boolean isImgFile(String path){
        for(String format:imgFormatSet){
            if(path.contains(format)){
                return true;
            }
        }
        return false;
    }
}
