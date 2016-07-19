package com.jonz.accountms.activity;

import com.jonz.accountms.DAO.PwdDAO;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jonz.accountms.R;

public class LoginActivity extends Activity {
    EditText txtLogin;
    Button btnLogin,btnClose;
    ImageView banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtLogin = (EditText)findViewById(R.id.txtLogin);
        banner = (ImageView)this.findViewById(R.id.banner);
        banner.setImageDrawable(getResources().getDrawable(R.drawable.editnote));
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,DrawActivity.class);
                PwdDAO pwdDAO = new PwdDAO(LoginActivity.this);

                if ((pwdDAO.getCount() == 0 | pwdDAO.find().getPassword().isEmpty())
                        && txtLogin.getText().toString().isEmpty()) {
                    startActivity(intent);
                } else {
                    if (pwdDAO.find().getPassword().equals(txtLogin.getText().toString())) {
                        startActivity(intent);
                    } else {
                        // 弹出信息提示
                        Toast.makeText(LoginActivity.this, "请输入正确的密码！",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        btnClose = (Button)findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
