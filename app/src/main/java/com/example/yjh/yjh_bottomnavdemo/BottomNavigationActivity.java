package com.example.yjh.yjh_bottomnavdemo;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.yjh.yjh_bottomnavdemo.fragments.ForthFragment;
import com.example.yjh.yjh_bottomnavdemo.fragments.FristFragment;
import com.example.yjh.yjh_bottomnavdemo.fragments.SecondFragment;
import com.example.yjh.yjh_bottomnavdemo.fragments.ThirdFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomNavigationActivity extends AppCompatActivity {

    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView bottomNavigationView;

    private Fragment[] mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        ButterKnife.bind(this);
        initFragments();
//        initView();
    }



    private void initFragments() {
        mFragments = new Fragment[4];
        mFragments[0] = FristFragment.newInstance("","");
        mFragments[1] = SecondFragment.newInstance("","");
        mFragments[2] = ThirdFragment.newInstance("","");
        mFragments[3] = ForthFragment.newInstance("","");
    }

    private void initView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                onTabItemSelected(item.getItemId());
                return true;
            }
        });
        // 由于第一次进来没有回调onNavigationItemSelected，因此需要手动调用一下切换状态的方法
        onTabItemSelected(R.id.item_new);
    }

    private void onTabItemSelected(int id){
        Fragment fragment = null;
        switch (id){
            case R.id.item_new:
                fragment = mFragments[0];
                break;
            case R.id.item_lib:
                fragment = mFragments[1];
                break;

            case R.id.item_find:
                fragment = mFragments[2];
                break;
            case R.id.item_more:
                fragment = mFragments[3];
                break;
        }
        if(fragment!=null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content,fragment).commit();
        }
    }


}
