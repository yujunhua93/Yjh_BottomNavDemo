package com.example.yjh.yjh_bottomnavdemo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.example.yjh.yjh_bottomnavdemo.fragments.ForthFragment;
import com.example.yjh.yjh_bottomnavdemo.fragments.FristFragment;
import com.example.yjh.yjh_bottomnavdemo.fragments.SecondFragment;
import com.example.yjh.yjh_bottomnavdemo.fragments.ThirdFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabFragmentHostActivity extends AppCompatActivity implements TabHost.OnTabChangeListener{

    @BindView(R.id.content)
    FrameLayout frameLayout;
    @BindView(R.id.tabhost)
    FragmentTabHost fragmentTabHost;

    private Fragment[] mFragments = new Fragment[4];
    public static final int []mTabRes = new int[]{R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background};
    public static final int []mTabResPressed = new int[]{R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background};
    public static final String []mTabTitle = new String[]{"首页","发现","关注","我的"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_fragment_host);
        ButterKnife.bind(this);
        initFragments();
        init();
    }

    private void init() {
        // 关联TabHost
        fragmentTabHost.setup(this,getSupportFragmentManager(),R.id.content);
        //注意，监听要设置在添加Tab之前
        fragmentTabHost.setOnTabChangedListener(this);
        //添加Tab
        for (int i=0;i<4;i++){
            //生成TabSpec
            TabHost.TabSpec tabSpec = fragmentTabHost.newTabSpec(mTabTitle[i]).setIndicator(getTabView(i));
            // 添加Tab 到TabHost，并绑定Fragment
            Bundle bundle = new Bundle();
            bundle.putString("from","FragmentTabHost Tab");
            fragmentTabHost.addTab(tabSpec,mFragments[i].getClass(),bundle);
        }


        //去掉Tab 之间的分割线
        fragmentTabHost.getTabWidget().setDividerDrawable(null);
        //
        fragmentTabHost.setCurrentTab(0);

    }

    private void initFragments() {
        mFragments[0] = FristFragment.newInstance("","");
        mFragments[1] = SecondFragment.newInstance("","");
        mFragments[2] = ThirdFragment.newInstance("","");
        mFragments[3] = ForthFragment.newInstance("","");
    }

    @Override
    public void onTabChanged(String s) {
        updateTabState();
    }

    private void updateTabState() {
        TabWidget tabWidget = fragmentTabHost.getTabWidget();
        for (int i=0;i<tabWidget.getTabCount();i++){
            View view = tabWidget.getChildTabViewAt(i);
            ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_content_image);
            TextView  tabText = (TextView) view.findViewById(R.id.tab_content_text);
            if(i == fragmentTabHost.getCurrentTab()){
                tabIcon.setImageResource(mTabResPressed[i]);
                tabText.setTextColor(getResources().getColor(android.R.color.black));
            }else{
                tabIcon.setImageResource(mTabRes[i]);
                tabText.setTextColor(getResources().getColor(android.R.color.darker_gray));
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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
