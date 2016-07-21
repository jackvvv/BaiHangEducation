package sinia.com.baihangeducation.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.Validator;

import butterknife.Bind;
import butterknife.OnClick;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.base.BaseActivity;

/**
 * Created by 忧郁的眼神 on 2016/7/21.
 */
public class WithdrawActivity extends BaseActivity {

    @Bind(R.id.tv_ok)
    TextView tv_ok;
    @Bind(R.id.et_name)
    EditText et_name;
    @Bind(R.id.et_account)
    EditText et_account;
    @Bind(R.id.et_money)
    EditText et_money;
    @Bind(R.id.tv_money)
    TextView tv_money;

    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw, "提现");
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
