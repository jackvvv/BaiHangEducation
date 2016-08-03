package sinia.com.baihangeducation.activity;

import android.os.Bundle;
import android.view.View;

import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.base.BaseActivity;

/**
 * Created by 忧郁的眼神 on 2016/8/3.
 */
public class HighTalentKnowActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talent_know, "高级人才须知");
        getDoingView().setVisibility(View.GONE);
    }
}
