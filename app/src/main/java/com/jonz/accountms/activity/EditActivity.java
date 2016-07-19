package com.jonz.accountms.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jonz.accountms.DAO.TxtDAO;
import com.jonz.accountms.R;
import com.jonz.accountms.model.Tb_txt;

public class EditActivity extends Activity {
    Button Okbtn,Cancelbtn;
    EditText Writetxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Writetxt = (EditText)findViewById(R.id.Writetxt);
        //将输入的数据进行转换
        //final String writetxt = Writetxt.getText().toString();

        Okbtn = (Button)findViewById(R.id.OKbtn);
        Okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //新建一个内容表，将填写好的数据保存到数据库表
                TxtDAO txtDAO = new TxtDAO(EditActivity.this);
                Tb_txt tb_txt = new Tb_txt(Writetxt.getText().toString());
                txtDAO.add(tb_txt);
                Toast.makeText(EditActivity.this,"添加笔记成功！",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditActivity.this,DrawActivity.class);
                startActivity(intent);
            }
        });

        Cancelbtn = (Button)findViewById(R.id.Cancelbtn);
        Cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
