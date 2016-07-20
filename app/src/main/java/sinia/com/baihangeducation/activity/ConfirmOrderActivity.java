package sinia.com.baihangeducation.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.base.BaseActivity;

/**
 * Created by 忧郁的眼神 on 2016/7/20.
 */
public class ConfirmOrderActivity extends BaseActivity {

    @Bind(R.id.tv_name)
    TextView tv_name;
    @Bind(R.id.tv_num)
    TextView tv_num;
    @Bind(R.id.tv_money)
    TextView tv_money;
    @Bind(R.id.tv_time)
    TextView tv_time;
    @Bind(R.id.tv_ok)
    TextView tv_ok;
    @Bind(R.id.ic_wx)
    ImageView ic_wx;
    @Bind(R.id.ic_alipay)
    ImageView ic_alipay;
    @Bind(R.id.ic_yue)
    ImageView ic_yue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order, "订单确认");
        getDoingView().setVisibility(View.GONE);
    }

    @OnClick(R.id.tv_ok)
    void tv_ok() {

    }

    @OnClick(R.id.ic_wx)
    void ic_wx() {

    }

    @OnClick(R.id.ic_alipay)
    void ic_alipay() {

    }

    @OnClick(R.id.ic_yue)
    void ic_yue() {

    }
}
