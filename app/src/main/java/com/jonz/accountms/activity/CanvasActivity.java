package com.jonz.accountms.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.jonz.accountms.R;

public class CanvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
    }

    // 创建选项菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflator = new MenuInflater(this);//实例化一个MenuInflater对象
        inflator.inflate(R.menu.toolsmenu, menu);	//解析菜单文件
        return super.onCreateOptionsMenu(menu);
    }

    // 当菜单项被选择时，作出相应的处理
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        canvasview dv = (canvasview) findViewById(R.id.drawView1);	//获取自定义的绘图视图
        dv.paint.setXfermode(null);		//取消擦除效果
        dv.paint.setStrokeWidth(1);		//初始化画笔的宽度
        switch (item.getItemId()) {
            case R.id.red:
                dv.paint.setColor(Color.RED);	//设置画笔的颜色为红色
                item.setChecked(true);
                break;
            case R.id.green:
                dv.paint.setColor(Color.GREEN);	//设置画笔的颜色为绿色
                item.setChecked(true);
                break;
            case R.id.blue:
                dv.paint.setColor(Color.BLUE);	//设置画笔的颜色为蓝色
                item.setChecked(true);
                break;
            case R.id.width_1:
                dv.paint.setStrokeWidth(1);	//设置笔触的宽度为1像素
                break;
            case R.id.width_2:
                dv.paint.setStrokeWidth(5);	//设置笔触的宽度为5像素
                break;
            case R.id.width_3:
                dv.paint.setStrokeWidth(10);//设置笔触的宽度为10像素
                break;
            case R.id.clear:
                dv.clear();		//擦除绘画
                break;
            case R.id.save:
                dv.save();	//保存绘画
                Toast.makeText(this,"涂鸦笔记成功保存于/sdcard/Image/路径下",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

}
