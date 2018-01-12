package com.example.yjh.yjh_bottomnavdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void page_first(View view) {
        Intent intent = new Intent();
        intent.setClass(this,TabLayoutActivity.class);
        startActivity(intent);
    }

    public void page_second(View view) {
        Intent intent = new Intent();
        intent.setClass(this,TabFragmentHostActivity.class);
        startActivity(intent);
    }

    public void page_third(View view) {
        Intent intent = new Intent();
        intent.setClass(this,RadioGroupActivity.class);
        startActivity(intent);
    }

    public void page_forth(View view) {
        Intent intent = new Intent();
        intent.setClass(this,BottomNavigationActivity.class);
        startActivity(intent);
    }
}
