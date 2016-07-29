package sinia.com.baihangeducation.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import butterknife.Bind;
import butterknife.OnClick;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.base.BaseActivity;
import sinia.com.baihangeducation.bean.JsonBean;
import sinia.com.baihangeducation.bean.MyChuangYeBean;
import sinia.com.baihangeducation.utils.ActivityManager;
import sinia.com.baihangeducation.utils.Constants;
import sinia.com.baihangeducation.utils.MyApplication;
import sinia.com.baihangeducation.utils.StringUtils;
import sinia.com.baihangeducation.utils.ValidationUtils;

/**
 * Created by 忧郁的眼神 on 2016/7/15.
 */
public class ChangePasswordActivity extends BaseActivity {
    @NotEmpty(message = "请输入旧密码")
    @Order(1)
    @Bind(R.id.et_old)
    EditText et_old;
    @Password(sequence = 1, message = "请输入密码")
    @Order(2)
    @Bind(R.id.et_pwd)
    EditText et_pwd;
    @ConfirmPassword(message = "两次输入的密码不一致")
    @Order(3)
    @Bind(R.id.et_confirm)
    EditText et_confirm;
    @Bind(R.id.tv_submit)
    TextView tv_submit;
    private Validator validator;
    private AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd, "修改密码");
        getDoingView().setVisibility(View.GONE);
        initViewsAndEvents();
    }

    protected void initViewsAndEvents() {
        validator = new Validator(this);
        validator.setValidationListener(new ValidationUtils.ValidationListener() {
            @Override
            public void onValidationSucceeded() {
                super.onValidationSucceeded();
                changePassword();
            }
        });
    }

    private void changePassword() {
        RequestParams params = new RequestParams();
        params.put("customerId", MyApplication.getInstance().getStringValue("userId"));
        params.put("oldPwd", et_old.getEditableText().toString().trim());
        params.put("newPwd", et_confirm.getEditableText().toString().trim());
        Log.i("tag", Constants.BASE_URL + "changePassword&" + params);
        client.post(Constants.BASE_URL + "changePassword", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, String s) {
                super.onSuccess(i, s);
                dismiss();
                Gson gson = new Gson();
                if (s.contains("isSuccessful")
                        && s.contains("state")) {
                    JsonBean bean = gson.fromJson(s, JsonBean.class);
                    int state = bean.getState();
                    int isSuccessful = bean.getIsSuccessful();
                    if (0 == state && 0 == isSuccessful) {
                        showToast("修改成功");
                        ActivityManager.getInstance().finishCurrentActivity();
                    } else if (0 == state && 1 == isSuccessful) {
                        showToast("修改失败");
                    } else if (0 == state && 2 == isSuccessful) {
                        showToast("旧密码错误");
                    } else {
                        showToast("请求失败");
                    }
                }
            }
        });
    }


    @OnClick(R.id.tv_submit)
    void tv_submit() {
        validator.validate();
    }

}
