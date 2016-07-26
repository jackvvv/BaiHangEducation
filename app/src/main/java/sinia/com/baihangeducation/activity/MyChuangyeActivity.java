package sinia.com.baihangeducation.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.adapter.MyFragmentPagerAdapter;
import sinia.com.baihangeducation.base.BaseActivity;
import sinia.com.baihangeducation.fragment.BuyedFragment;
import sinia.com.baihangeducation.fragment.FullTimeFragment;
import sinia.com.baihangeducation.fragment.InternFragment;
import sinia.com.baihangeducation.fragment.RewardFragment;
import sinia.com.baihangeducation.utils.AppInfoUtil;

/**
 * Created by 忧郁的眼神 on 2016/7/18.
 */
public class MyChuangyeActivity extends BaseActivity {

    @Bind(R.id.tab_title)
    TabLayout tab_title;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    private MyFragmentPagerAdapter pagerAdapter;

    private List<String> titleList;
    private List<Fragment> fragmentList;
    private BuyedFragment buyFragment;
    private RewardFragment rewardFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_chuangye, "我的创业");
        getDoingView().setBackgroundResource(R.drawable.ic_beans);
        getDoingView().setText("");
        getDoingView().setWidth(AppInfoUtil.dip2px(this, 25));
        initViews();
    }

    private void initViews() {
        titleList = new ArrayList<>();
        titleList.add("已购买");
        titleList.add("已中奖");
        fragmentList = new ArrayList<>();
        buyFragment = new BuyedFragment();
        rewardFragment = new RewardFragment();
        fragmentList.add(buyFragment);
        fragmentList.add(rewardFragment);
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(pagerAdapter);
        tab_title.setTabMode(TabLayout.MODE_FIXED);
        tab_title.addTab(tab_title.newTab().setText(titleList.get(0)));
        tab_title.addTab(tab_title.newTab().setText(titleList.get(1)));
        tab_title.setupWithViewPager(viewPager);
    }

    @Override
    public void doing() {
        super.doing();
        startActivityForNoIntent(MyBeansActivity2.class);
    }
}
