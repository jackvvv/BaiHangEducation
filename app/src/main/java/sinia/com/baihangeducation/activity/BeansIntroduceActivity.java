package sinia.com.baihangeducation.activity;

import android.os.Bundle;
import android.view.View;

import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.base.BaseActivity;

/**
 * Created by 忧郁的眼神 on 2016/7/20.
 */
public class BeansIntroduceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help, "创业豆说明");
        getDoingView().setVisibility(View.GONE);
    }
}
