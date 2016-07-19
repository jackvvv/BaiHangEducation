package sinia.com.baihangeducation.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import butterknife.Bind;
import butterknife.OnClick;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.base.BaseActivity;
import sinia.com.baihangeducation.utils.ValidationUtils;

/**
 * Created by 忧郁的眼神 on 2016/7/15.
 */
public class LoginActivity extends BaseActivity {
    @Pattern(regex = "(\\+\\d+)?1[34578]\\d{9}$", message = "请输入正确的手机号码")
    @Order(1)
    @Bind(R.id.et_tel)
    EditText et_tel;
    @NotEmpty(message = "请输入密码")
    @Order(2)
    @Bind(R.id.et_pwd)
    EditText et_pwd;
    @Bind(R.id.tv_findpwd)
    TextView tv_findpwd;
    @Bind(R.id.tv_login)
    TextView tv_login;
    @Bind(R.id.tv_register)
    TextView tv_register;
    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login, "登录");
        getDoingView().setVisibility(View.GONE);
        initViewsAndEvents();
    }

    protected void initViewsAndEvents() {
        validator = new Validator(this);
        validator.setValidationListener(new ValidationUtils.ValidationListener() {
            @Override
            public void onValidationSucceeded() {
                super.onValidationSucceeded();
            }
        });
    }

    @OnClick(R.id.tv_login)
    void login() {
        validator.validate();
    }

    @OnClick(R.id.tv_findpwd)
    void tv_findpwd() {
        startActivityForNoIntent(ForgetPasswordActivity.class);
    }

    @OnClick(R.id.tv_register)
    void tv_register() {
        startActivityForNoIntent(RegisterActivity.class);
    }

}
