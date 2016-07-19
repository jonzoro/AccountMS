package com.jonz.accountms.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jonz.accountms.DAO.TxtDAO;
import com.jonz.accountms.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DrawActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //设置卡片中的笔记内容，数据来自数据库表中已添加的数据
        final TxtDAO txtdao = new TxtDAO(this);
        TextView cardtv = (TextView)findViewById(R.id.cardtv);
        TextView cardtitle = (TextView)findViewById(R.id.cardtitle);
        cardtitle.setText("已有的笔记：");
        cardtv.setText(txtdao.getTxt().toString());

        Button clrbtn = (Button)findViewById(R.id.list_item_button2);
        assert clrbtn != null;
        clrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtdao.delete();
                Toast.makeText(DrawActivity.this,"清空笔记成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DrawActivity.this,DrawActivity.class);
                startActivity(intent);
            }
        });

        /*
        //采用ListView配合适配器实现卡片式列表的效果，
        //初始化ListView实例并绑定适配器，关键在于在适配器添加触发器
        final ListView cardlist = (ListView)findViewById(R.id.cardlist);
        List<String> cardtxt = new ArrayList<>();
        cardtxt.add("test1");
        cardtxt.add("test2");
        CardsAdapter adapter = new CardsAdapter(this,cardtxt);
        cardlist.setAdapter(adapter);
        */

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DrawActivity.this,DrawActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.draw, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent intent = new Intent(DrawActivity.this,PicActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(DrawActivity.this,ScrollingActivity.class);
            startActivity(intent);
        } else if (id == R.id.addnote) {
            Intent intent = new Intent(DrawActivity.this,EditActivity.class);
            startActivity(intent);

        } else if (id == R.id.canavsnote) {
            Intent intent = new Intent(DrawActivity.this,CanvasActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            String phonenum = "15606092459";
            String message = "错误反馈：";
            Uri uri = Uri.parse("smsto:"+phonenum);
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.putExtra("sms_body",message);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

/*
class CardsAdapter extends BaseAdapter{
    Context context;
    List<String> items;
    public CardsAdapter(Context context,
                        List<String> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_card,null);

            holder = new ViewHolder();
            holder.itemText = (TextView)convertView.findViewById(R.id.cardtv);
            holder.itemButton1 = (Button)convertView.findViewById(R.id.list_item_button1);
            holder.itemButton2 = (Button)convertView.findViewById(R.id.list_item_button2);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.itemText.setText(items.get(position));
        return convertView;
    }
    private static class ViewHolder{
        TextView itemText;
        Button itemButton1,itemButton2;
    }

    @Override
    public int getCount() {
        return 0;
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
}*/



