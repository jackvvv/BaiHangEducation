package sinia.com.baihangeducation.activity;

import android.app.TabActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.adapter.MyFragmentPagerAdapter;
import sinia.com.baihangeducation.base.BaseActivity;
import sinia.com.baihangeducation.fragment.FullTimeFragment;
import sinia.com.baihangeducation.fragment.InternFragment;

/**
 * Created by 忧郁的眼神 on 2016/7/18.
 */
public class StudentJobActivity extends BaseActivity {

    @Bind(R.id.tab_title)
    TabLayout tab_title;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.tv_ok)
    TextView tv_ok;

    private MyFragmentPagerAdapter pagerAdapter;

    private List<String> titleList;
    private List<Fragment> fragmentList;
    private FullTimeFragment fullTimeFragment;
    private InternFragment internFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_job, "学生就业");
        getDoingView().setVisibility(View.GONE);
        initViews();
    }

    private void initViews() {
        titleList = new ArrayList<>();
        titleList.add("全职");
        titleList.add("实习");
        fragmentList = new ArrayList<>();
        fullTimeFragment = new FullTimeFragment();
        internFragment = new InternFragment();
        fragmentList.add(fullTimeFragment);
        fragmentList.add(internFragment);
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(pagerAdapter);
        tab_title.setTabMode(TabLayout.MODE_FIXED);
        tab_title.addTab(tab_title.newTab().setText(titleList.get(0)));
        tab_title.addTab(tab_title.newTab().setText(titleList.get(1)));
        tab_title.setupWithViewPager(viewPager);
    }

    @OnClick(R.id.tv_ok)
    void tv_ok() {

    }

}
