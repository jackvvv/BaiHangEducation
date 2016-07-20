package sinia.com.baihangeducation.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.base.BaseActivity;

/**
 * Created by 忧郁的眼神 on 2016/7/20.
 */
public class SettingsActivity extends BaseActivity {

    @Bind(R.id.rl_notice)
    RelativeLayout rl_notice;
    @Bind(R.id.rl_pwd)
    RelativeLayout rl_pwd;
    @Bind(R.id.rl_help)
    RelativeLayout rl_help;
    @Bind(R.id.rl_aboutus)
    RelativeLayout rl_aboutus;

    @Bind(R.id.rl_feedback)
    RelativeLayout rl_feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings, "设置");
        getDoingView().setVisibility(View.GONE);
    }

    @OnClick(R.id.rl_notice)
    void rl_notice() {

    }

    @OnClick(R.id.rl_pwd)
    void rl_pwd() {
        startActivityForNoIntent(ChangePasswordActivity.class);
    }

    @OnClick(R.id.rl_help)
    void rl_help() {

    }

    @OnClick(R.id.rl_aboutus)
    void rl_aboutus() {
        startActivityForNoIntent(AboutUsActivity.class);
    }

    @OnClick(R.id.rl_feedback)
    void rl_feedback() {

    }
}
