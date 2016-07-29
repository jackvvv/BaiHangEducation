package sinia.com.baihangeducation.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.base.BaseActivity;
import sinia.com.baihangeducation.utils.ActivityManager;

/**
 * Created by 忧郁的眼神 on 2016/7/20.
 */
public class ChuangyeProtecolActivity extends BaseActivity {

    @Bind(R.id.tv_agree)
    TextView tv_agree;
    @Bind(R.id.tv_disagree)
    TextView tv_disagree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuangye_protecol, "创业协议");
        getDoingView().setVisibility(View.GONE);
    }

    @OnClick(R.id.tv_agree)
    void tv_agree() {
        startActivityForNoIntent(ChuangYeActivity.class);
        ActivityManager.getInstance().finishCurrentActivity();
    }

    @OnClick(R.id.tv_disagree)
    void tv_disagree() {
        ActivityManager.getInstance().finishCurrentActivity();
    }
}
