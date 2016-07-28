package sinia.com.baihangeducation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.base.BaseActivity;
import sinia.com.baihangeducation.utils.MyApplication;

/**
 * Created by 忧郁的眼神 on 2016/7/28.
 */
public class SplashActivity extends BaseActivity {

    RelativeLayout root;

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
                if (MyApplication.getInstance().getBoolValue("is_login")) {
                    Intent intent = new Intent(SplashActivity.this,
                            MainActivity.class);
                    startActivity(intent);
                    SplashActivity.this.finish();
                } else {
                    startActivityForNoIntent(LoginActivity.class);
//                    startActivityForNoIntent(MainActivity.class);
                    SplashActivity.this.finish();
                }
            }
        };
        timer.schedule(tt, 2000);

    }
}
