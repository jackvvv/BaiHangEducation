package sinia.com.baihangeducation.activity;

import android.content.Intent;
import android.os.Bundle;
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
import sinia.com.baihangeducation.actionsheetdialog.ActionSheetDialog;
import sinia.com.baihangeducation.actionsheetdialog.ActionSheetDialogUtils;
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
public class HighTalentActivity extends BaseActivity {
    @Order(1)
    @NotEmpty(message = "请输入身份号码")
    @Bind(R.id.et_sdcard)
    EditText et_sdcard;
    @Order(3)
    @NotEmpty(message = "请输入证件号码")
    @Bind(R.id.et_cardnum)
    EditText et_cardnum;
    @Order(4)
    @NotEmpty(message = "请输入您的姓名")
    @Bind(R.id.et_name)
    EditText et_name;
    @Order(5)
    @NotEmpty(message = "请输入工作经历")
    @Bind(R.id.et_work)
    EditText et_work;
    @Order(6)
    @NotEmpty(message = "请输入学习经历")
    @Bind(R.id.et_study)
    EditText et_study;
    @Bind(R.id.tv_register)
    TextView tv_register;
    @Order(2)
    @NotEmpty(message = "请选择证件类型")
    @Bind(R.id.tv_choosecard)
    TextView tv_choosecard;
    private Validator validator;
    private String userId;
    private AsyncHttpClient client = new AsyncHttpClient();
    private String isFromHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_register, "高端人才");
        getDoingView().setVisibility(View.GONE);
        initViewsAndEvents();
    }

    protected void initViewsAndEvents() {
        userId = getIntent().getStringExtra("userId");
        isFromHome = getIntent().getStringExtra("fromHome");
        validator = new Validator(this);
        validator.setValidationListener(new ValidationUtils.ValidationListener() {
            @Override
            public void onValidationSucceeded() {
                super.onValidationSucceeded();
                talentRegister();
            }


        });
    }

    private void talentRegister() {
        showLoad("加载中...");
        RequestParams params = new RequestParams();
        params.put("userId", userId);
        params.put("idCard", et_sdcard.getEditableText().toString().trim());
        params.put("userName", et_name.getEditableText().toString().trim());
        params.put("certificate", tv_choosecard.getText().toString().trim());
        params.put("certificateNum", et_cardnum.getEditableText().toString().trim());
        params.put("workHistory", et_work.getEditableText().toString().trim());
        params.put("learnHistory", et_study.getEditableText().toString().trim());
        client.post(Constants.BASE_URL + "telentAccept", params, new AsyncHttpResponseHandler() {
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
                        showToast("提交成功");
                        MyApplication.getInstance().setBooleanValue(
                                "is_login", true);
                        MyApplication.getInstance().setStringValue(
                                "userId", userId);
                        if (isFromHome.equals("1")){

                        }else{
                            startActivityForNoIntent(MainActivity.class);
                        }
                        ActivityManager.getInstance()
                                .finishCurrentActivity();
                    } else {
                        showToast("认证失败");
                    }
                }
            }
        });
    }


    @OnClick(R.id.tv_choosecard)
    void tv_choosecard() {
//        ActionSheetDialogUtils.createCardDialog(this, tv_choosecard);
        Intent intent = new Intent(HighTalentActivity.this, CardActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            if (requestCode == 1) {
                tv_choosecard.setText(data.getStringExtra("card"));
            }
        }
    }

    @OnClick(R.id.tv_register)
    void tv_register() {
        validator.validate();
    }

}
