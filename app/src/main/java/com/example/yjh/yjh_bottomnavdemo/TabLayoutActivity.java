package com.example.yjh.yjh_bottomnavdemo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yjh.yjh_bottomnavdemo.fragments.ForthFragment;
import com.example.yjh.yjh_bottomnavdemo.fragments.FristFragment;
import com.example.yjh.yjh_bottomnavdemo.fragments.SecondFragment;
import com.example.yjh.yjh_bottomnavdemo.fragments.ThirdFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabLayoutActivity extends AppCompatActivity {
    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.content)
    FrameLayout content;

    private Fragment[] mFragments = new Fragment[4];

    public static final int []mTabRes = new int[]{R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background};
    public static final int []mTabResPressed = new int[]{R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background};
    public static final String []mTabTitle = new String[]{"首页","发现","关注","我的"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        ButterKnife.bind(this);
        initFragments();
        init();
    }

    private void init() {
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                onTabItemSelected(tab.getPosition());

                for (int i=0;i<mTabLayout.getTabCount();i++){
                    View view = mTabLayout.getTabAt(i).getCustomView();
                    ImageView icon = (ImageView) view.findViewById(R.id.tab_content_image);
                    TextView text = (TextView) view.findViewById(R.id.tab_content_text);
                    if(i == tab.getPosition()){
                        icon.setImageResource(mTabResPressed[i]);
                        text.setTextColor(getResources().getColor(android.R.color.black));
                    }else{
                        icon.setImageResource(mTabRes[i]);
                        text.setTextColor(getResources().getColor(android.R.color.darker_gray));
                    }
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        for(int i=0;i<4;i++){
            mTabLayout.addTab(mTabLayout.newTab().setCustomView(getTabView(i)));
        }
    }



    private void initFragments() {
        mFragments[0] = FristFragment.newInstance("","");
        mFragments[1] = SecondFragment.newInstance("","");
        mFragments[2] = ThirdFragment.newInstance("","");
        mFragments[3] = ForthFragment.newInstance("","");
    }

    private void onTabItemSelected(int position){
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = mFragments[0];
                break;
            case 1:
                fragment = mFragments[1];
                break;

            case 2:
                fragment = mFragments[2];
                break;
            case 3:
                fragment = mFragments[3];
                break;
        }
        if(fragment!=null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content,fragment).commit();
        }
    }


    private View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.home_tab_content,null);
        ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_content_image);
        tabIcon.setImageResource(mTabRes[position]);
        TextView tabText = (TextView) view.findViewById(R.id.tab_content_text);
        tabText.setText(mTabTitle[position]);
        return view;
    }
}
