package com.example.day02_exam;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText ed_name;
    private EditText ed_yanzheng;
    private Button btn_yanzheng;
    private EditText ed_pwd;
    private Button btn_deng;
    private Timer timer;
    private int pos = 60;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            btn_yanzheng.setText("剩余"+pos+"s");
            if(pos==0){
                timer.cancel();
                btn_yanzheng.setText("点击验证");
            }
        }
    };
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        ed_name = (EditText) findViewById(R.id.ed_name);
        ed_yanzheng = (EditText) findViewById(R.id.ed_yanzheng);
        btn_yanzheng = (Button) findViewById(R.id.btn_yanzheng);
        ed_pwd = (EditText) findViewById(R.id.ed_pwd);
        btn_deng = (Button) findViewById(R.id.btn_deng);

        btn_yanzheng.setOnClickListener(this);
        btn_deng.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yanzheng:
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        pos--;
                        handler.sendEmptyMessage(pos);

                    }
                },1000,1000);
                Random random = new Random();
                i = random.nextInt((999999) % (999999 - 0 + 1) + 0);
                NotificationManager service = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    NotificationChannel name = new NotificationChannel("1", "name", NotificationManager.IMPORTANCE_DEFAULT);
                    service.createNotificationChannel(name);
                }
                Notification build = new NotificationCompat.Builder(this, "1")
                        .setSmallIcon(R.drawable.jz_backward_icon)
                        .setContentTitle("验证码")
                        .setAutoCancel(true)
                        .setContentText(i + "")
                        .build();
                service.notify(1,build);


                break;
            case R.id.btn_deng:
                submit();
                break;
        }
    }

    private void submit() {
        String name = ed_name.getText().toString().trim();
        String yanzheng = ed_yanzheng.getText().toString().trim();
        String pwd = ed_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "name不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(yanzheng)) {
            Toast.makeText(this, "yanzheng不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "pwd不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!yanzheng.equals(i+"")){
            Toast.makeText(this, "yanzheng错误", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(new Intent(MainActivity.this,HomeActivity.class));
    }


}