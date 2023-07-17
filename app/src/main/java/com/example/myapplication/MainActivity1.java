package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity1 extends AppCompatActivity {
    boolean passwordClick = false;//初始状态密码不可见
    ImageView iv;//密码是否可见的眼睛图标
    EditText et1;//密码
    EditText et2;//账号
    ImageView delete;//删除键
    ImageButton log;//登录按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //密码是否可视的图标变换的点击事件监听
        iv = (ImageView) findViewById(R.id.password_visible_image);
        et1 = (EditText) findViewById(R.id.password_editText);
        iv.setImageResource(R.drawable.passwordhide);
        et1.setInputType(129);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == iv) {//与上同理点击后判断密码状态从而更改图标
                    passwordClick = !passwordClick;//更改图标同时更改状态参数
                    System.out.println(et1.getText().length());
                    if (!passwordClick) {//对密码状态进行判断从而更改图标
                        iv.setImageResource(R.drawable.passwordhide);//不可见图标闭眼
                        et1.setInputType(129);//密码不可见
                        et1.setSelection(et1.getText().length());//光标移动到文本末尾
                    } else {
                        iv.setImageResource(R.drawable.passwordshow);//可见图标睁眼
                        et1.setInputType(128);//密码可见
                        et1.setSelection(et1.getText().length());//光标移动到文本末尾
                    }
                }

            }
        });
        //对账号的输入框的文本进行监听
        et2 = (EditText) findViewById(R.id.account_editText);
        delete = (ImageView) findViewById(R.id.account_delete_image);
        delete.setVisibility(View.INVISIBLE);
        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty())//当更变后的文本长度不为空时，删除键可见
                    delete.setVisibility(View.VISIBLE);
                else//否则不可见
                    delete.setVisibility(View.INVISIBLE);
            }

        });
        //点击删除键，对账号输入文本删除
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et2.setText("");
            }//内容设为空达到删除效果
        });

        //登录按钮的点击事件监听
        Intent intent = new Intent();
        intent.setClass(MainActivity1.this, MainActivity2.class);
        String password = "666666";
        //Toast的设置
        String text1 = "请输入账号";
        String text2 = "请输入密码";
        String text3 = "密码错误";
        int duration = Toast.LENGTH_SHORT;//SHORT代表2秒，LONG代表3.5秒
        //this表示对当前对象的引用，text为消息的内容，duration为消息显示时间
        Toast toast1 = Toast.makeText(this /* MyActivity */, text1, duration);
        Toast toast2 = Toast.makeText(this /* MyActivity */, text2, duration);
        Toast toast3 = Toast.makeText(this /* MyActivity */, text3, duration);
        log = findViewById(R.id.log_button);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et2.getText().length() == 0)//账号输入为空
                {
                    toast1.show();
                } else if (et2.getText().length() != 0 && et1.getText().length() == 0)//密码输入为空
                {
                    toast2.show();
                } else if (et2.getText().length() != 0 && et1.getText().length() != 0) {
                    if (et1.getText().toString().equals(password)) {
                        //EditText中getText方法返回的是Editable，不能直接用equals方法与String类的password比较
                        // 需要用toSting方法将其转化为CharSequence
                        // 因为String是CharSequence的实现类，可以直接equals
                        startActivity(intent);//密码正确,跳转
                    } else {
                        toast3.show();
                    }
                }
            }
        });
    }
}