package sinia.com.baihangeducation.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.Validator;

import butterknife.Bind;
import butterknife.OnClick;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.base.BaseActivity;

/**
 * Created by 忧郁的眼神 on 2016/7/21.
 */
public class RechargeActivity extends BaseActivity {

    @Bind(R.id.tv_ok)
    TextView tv_ok;
    @Bind(R.id.et_money)
    EditText et_money;
    @Bind(R.id.ic_wx)
    ImageView ic_wx;
    @Bind(R.id.ic_alipay)
    ImageView ic_alipay;
    @Bind(R.id.tv_money)
    TextView tv_money;

    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge, "充值");
        getDoingView().setVisibility(View.GONE);

        initData();
    }

    private void initData() {
        validator = new Validator(this);
    }

    @OnClick(R.id.tv_ok)
    void tv_ok() {
//        validator.validate();
    }
}
