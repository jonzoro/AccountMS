package com.jonz.accountms.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.InstrumentationInfo;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jonz.accountms.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

public class PicActivity extends Activity {
    private Button picbtn;
    private ImageView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        view = (ImageView)findViewById(R.id.picnote);
        picbtn = (Button)findViewById(R.id.picbtn);
        picbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,1);
            }
        });

    }

    @SuppressLint("SdCardPath")
    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            String sdStatus = Environment.getExternalStorageState();
            if(!sdStatus.equals(Environment.MEDIA_MOUNTED)){
                Log.i("Test", "SDCard is not ready yet!");
                return;
            }

            new DateFormat();
            String name = DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA))+".jpg";
            Toast.makeText(this, "照片保存成功，位于/sdcard/Image/"+name, Toast.LENGTH_SHORT).show();
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap)bundle.get("data");//获取相机返回的数据并转换为bitmap格式

            FileOutputStream b = null;
            File file = new File("/sdcard/Image/");
            file.mkdirs();
            String fileName = "/sdcard/Image/"+name;

            try{
                b = new FileOutputStream(fileName);
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,b);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }finally {
                try{
                    b.flush();
                    b.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            try{
                view.setImageBitmap(bitmap);
            }catch (Exception e){
                Log.e("error",e.getMessage());
            }
        }
    }
}
