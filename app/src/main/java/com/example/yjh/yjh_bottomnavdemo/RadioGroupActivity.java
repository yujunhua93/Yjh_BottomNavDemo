package com.example.yjh.yjh_bottomnavdemo;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.yjh.yjh_bottomnavdemo.fragments.ForthFragment;
import com.example.yjh.yjh_bottomnavdemo.fragments.FristFragment;
import com.example.yjh.yjh_bottomnavdemo.fragments.SecondFragment;
import com.example.yjh.yjh_bottomnavdemo.fragments.ThirdFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RadioGroupActivity extends AppCompatActivity {
    @BindView(R.id.radio_group_button)
    RadioGroup radioGroup;
    @BindView(R.id.radio_button_home)
    RadioButton radioButton;
    private Fragment[] mFragments = new Fragment[4];

    public static final int []mTabRes = new int[]{R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background};
    public static final int []mTabResPressed = new int[]{R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background};
    public static final String []mTabTitle = new String[]{"首页","发现","关注","我的"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_group);
        ButterKnife.bind(this);
        initFramgent();
        initView();
    }

    private void initView() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            Fragment mFragment = null;
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.radio_button_home:
                        mFragment = mFragments[0];
                        break;
                    case R.id.radio_button_discovery:
                        mFragment = mFragments[1];
                        break;
                    case R.id.radio_button_attention:
                        mFragment = mFragments[2];
                        break;
                    case R.id.radio_button_profile:
                        mFragment = mFragments[3];
                        break;
                }
                if(mFragments!=null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.content,mFragment).commit();
                }
            }
        });
        // 保证第一次会回调OnCheckedChangeListener
        radioButton.setChecked(true);
    }

    private void initFramgent() {
        mFragments[0] = FristFragment.newInstance("","");
        mFragments[1] = SecondFragment.newInstance("","");
        mFragments[2] = ThirdFragment.newInstance("","");
        mFragments[3] = ForthFragment.newInstance("","");
    }
}
