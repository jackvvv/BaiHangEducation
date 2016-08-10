package sinia.com.baihangeducation.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.base.BaseActivity;
import sinia.com.baihangeducation.utils.ActivityManager;
import sinia.com.baihangeducation.utils.MyApplication;
import sinia.com.baihangeducation.utils.SPUtils;

/**
 * Created by 忧郁的眼神 on 2016/7/28.
 */
public class SplashActivity extends BaseActivity {

    RelativeLayout root;
    private boolean isFirst;
    private SharedPreferences mDataShared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        root = (RelativeLayout) findViewById(R.id.root);
        ObjectAnimator animator = new ObjectAnimator();
        animator.ofFloat(root, "alpha", 0.5f, 1).setDuration(1800).start();

        Timer timer = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                jumpToMainActivity();
            }
        };
        timer.schedule(tt, 2000);
    }

    protected void jumpToMainActivity() {
        isFirst = SPUtils.getShareBoolean(this, "isFirstIn");
        if (!isFirst) {
            Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
            startActivity(intent);
            SPUtils.putShareValue(this, "isFirstIn", true);
            ActivityManager.getInstance().finishCurrentActivity();
        } else {
            if (MyApplication.getInstance().getBoolValue("is_login")) {
                Intent intent = new Intent(SplashActivity.this,
                        MainActivity.class);
                startActivity(intent);
                ActivityManager.getInstance().finishCurrentActivity();
            } else {
                startActivityForNoIntent(LoginActivity.class);
                ActivityManager.getInstance().finishCurrentActivity();
            }
        }
    }
}
