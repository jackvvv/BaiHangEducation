package sinia.com.baihangeducation.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.base.BaseActivity;

/**
 * Created by 忧郁的眼神 on 2016/7/20.
 */
public class PersonalActivity extends BaseActivity {

    @Bind(R.id.ll_work)
    LinearLayout ll_work;
    @Bind(R.id.ll_chy)
    LinearLayout ll_chy;
    @Bind(R.id.ll_setting)
    LinearLayout ll_setting;
    @Bind(R.id.ll_logout)
    LinearLayout ll_logout;

    @Bind(R.id.tv_tel)
    TextView tv_tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal, "个人中心");
        getDoingView().setVisibility(View.GONE);
    }

    @OnClick(R.id.ll_work)
    void ll_work() {

    }

    @OnClick(R.id.ll_chy)
    void ll_chy() {

    }

    @OnClick(R.id.ll_setting)
    void ll_setting() {
        startActivityForNoIntent(SettingsActivity.class);
    }

    @OnClick(R.id.ll_logout)
    void ll_logout() {

    }
}
