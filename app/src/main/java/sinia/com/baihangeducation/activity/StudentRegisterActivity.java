package sinia.com.baihangeducation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import sinia.com.baihangeducation.bean.RegisterBean;
import sinia.com.baihangeducation.utils.ActivityManager;
import sinia.com.baihangeducation.utils.Constants;
import sinia.com.baihangeducation.utils.MyApplication;
import sinia.com.baihangeducation.utils.StringUtils;
import sinia.com.baihangeducation.utils.ValidationUtils;

/**
 * Created by 忧郁的眼神 on 2016/7/15.
 */
public class StudentRegisterActivity extends BaseActivity {
    @Pattern(regex = "^(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$", message = "请输入正确的手机号码")
    @Order(1)
    @Bind(R.id.et_tel)
    EditText et_tel;
    @NotEmpty(message = "请输入验证码")
    @Order(2)
    @Bind(R.id.et_code)
    EditText et_code;
    @Password(sequence = 1, message = "请输入密码")
    @Order(3)
    @Bind(R.id.et_pwd)
    EditText et_pwd;
    @ConfirmPassword(message = "两次输入的密码不一致")
    @Order(4)
    @Bind(R.id.et_confirm)
    EditText et_confirm;
    @Bind(R.id.tv_register)
    TextView tv_register;
    @Bind(R.id.tv_getcode)
    TextView tv_getcode;
    private Validator validator;
    private AsyncHttpClient client = new AsyncHttpClient();
    private int i = 60;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register, "注册");
        getDoingView().setVisibility(View.GONE);
        initViewsAndEvents();
    }

    protected void initViewsAndEvents() {
        type = getIntent().getStringExtra("type");
        validator = new Validator(this);
        validator.setValidationListener(new ValidationUtils.ValidationListener() {
            @Override
            public void onValidationSucceeded() {
                super.onValidationSucceeded();
                register();
            }

        });
    }

    private void register() {
        showLoad("加载中...");
        RequestParams params = new RequestParams();
        params.put("volidateCode", et_code.getEditableText().toString().trim());
        params.put("type", type);
        params.put("password", et_pwd.getEditableText().toString().trim());
        params.put("telephone", et_tel.getEditableText().toString().trim());
        client.post(Constants.BASE_URL + "regist", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, String s) {
                        super.onSuccess(i, s);
                        dismiss();
                        Gson gson = new Gson();
                        if (s.contains("isSuccessful")
                                && s.contains("state")) {
                            RegisterBean bean = gson.fromJson(s, RegisterBean.class);
                            int state = bean.getState();
                            int isSuccessful = bean.getIsSuccessful();
                            if (0 == state && 0 == isSuccessful) {
                                if ("1".equals(type)) {
                                    MyApplication.getInstance().setBooleanValue(
                                            "is_login", true);
                                    MyApplication.getInstance().setStringValue(
                                            "userId", bean.getCustomerId());
                                    //进入首页
                                    startActivityForNoIntent(MainActivity.class);
                                    ActivityManager.getInstance()
                                            .finishCurrentActivity();
                                } else if ("2".equals(type)) {
                                    Intent intent = new Intent();
                                    intent.putExtra("userId", bean.getCustomerId());
                                    startActivityForIntent(HighTalentActivity.class, intent);
                                } else {
                                    Intent intent = new Intent();
                                    intent.putExtra("userId", bean.getCustomerId());
                                    startActivityForIntent(CompanyRegisterActivity.class, intent);
                                }
                            } else if (0 == state && 1 == isSuccessful) {
                                showToast("您的手机号已经注册过了");
                            } else {
                                showToast("注册失败");
                            }
                        }

                    }
                }

        );
    }


    @OnClick(R.id.tv_getcode)
    void tv_getcode() {
        if (!StringUtils.isMobileNumber(et_tel.getEditableText().toString().trim())) {
            showToast("请输入正确的手机号码");
        } else {
            tv_getcode.setClickable(false);
            tv_getcode.setText("重新发送(" + i + ")");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (; i > 0; i--) {
                        handler.sendEmptyMessage(-9);
                        if (i <= 0) {
                            break;
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    handler.sendEmptyMessage(-8);
                }
            }).start();
            getCode();
        }
    }

    private void getCode() {
        showLoad("正在发送短信...");
        RequestParams params = new RequestParams();
        params.put("telephone", et_tel.getEditableText().toString().trim());
        client.post(Constants.BASE_URL + "gainvolidate", params, new AsyncHttpResponseHandler() {
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
                        showToast("验证码已发送");
                    } else if (0 == state && 1 == isSuccessful) {
                        showToast("该手机号已经被注册");
                    } else {
                        showToast("发送失败");
                    }
                }
            }
        });
    }

    @OnClick(R.id.tv_register)
    void tv_register() {
        validator.validate();
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == -9) {
                tv_getcode.setText("重新发送(" + i + ")");
            } else if (msg.what == -8) {
                tv_getcode.setText("获取验证码");
                tv_getcode.setClickable(true);
                i = 60;
            }
        }
    };

}
