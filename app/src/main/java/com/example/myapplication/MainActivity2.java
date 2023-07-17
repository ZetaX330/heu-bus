package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class MainActivity2 extends AppCompatActivity {
    ImageView btn1, btn2, btn3;
    Navigation1 fragmentA;
    Navigation2 fragmentB;
    Navigation3 fragmentC;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn1 = findViewById(R.id.buttonA1);
        btn2 = findViewById(R.id.buttonA2);
        btn3 = findViewById(R.id.buttonA3);
        onResume();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        boolean[] navigationState = new boolean[3];
        navigationState[0] = true;
        navigationState[1] = false;
        navigationState[2] = false;
        btn1.setImageResource(R.drawable.navigation1);
        btn2.setImageResource(R.drawable.navigation2a);
        btn3.setImageResource(R.drawable.navigation3a);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
                //获取 FragmentManager中用于 Fragment 替换之类的类 FragmentTransaction
                fragmentTransaction = fragmentManager.beginTransaction();
                //创建一个替换Fragment的事件
                fragmentA = new Navigation1();
                fragmentTransaction.replace(R.id.home_container, fragmentA);
                fragmentTransaction.commit();
                if (navigationState[0] == false) {
                    btn1.setImageResource(R.drawable.navigation1);
                    btn2.setImageResource(R.drawable.navigation2a);
                    btn3.setImageResource(R.drawable.navigation3a);
                    navigationState[0]=true;
                    navigationState[1]=false;
                    navigationState[2]=false;
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentB = new Navigation2();
                fragmentTransaction.replace(R.id.home_container, fragmentB);
                fragmentTransaction.commit();
                if (navigationState[1] == false) {
                    btn2.setImageResource(R.drawable.navigation2);
                    btn1.setImageResource(R.drawable.navigation1a);
                    btn3.setImageResource(R.drawable.navigation3a);
                    navigationState[0]=false;
                    navigationState[1]=true;
                    navigationState[2]=false;
                }

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
                //获取 FragmentManager中用于 Fragment 替换之类的类 FragmentTransaction
                fragmentTransaction = fragmentManager.beginTransaction();
                //创建一个替换Fragment的事件
                fragmentC = new Navigation3();
                fragmentTransaction.replace(R.id.home_container, fragmentC);   // 替换的是framelayout_1中的fragment
                //将新的Fragment对象压入一个栈内，点击back会进行回退，而非退出app
                //fragmentTransaction.addToBackStack(null);
                //提交事件
                fragmentTransaction.commit();
                if (navigationState[2] == false) {
                    btn3.setImageResource(R.drawable.navigation3);
                    btn1.setImageResource(R.drawable.navigation1a);
                    btn2.setImageResource(R.drawable.navigation2a);
                    navigationState[0]=false;
                    navigationState[1]=false;
                    navigationState[2]=true;
                }
            }
        });
    }
}