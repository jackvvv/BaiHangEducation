package sinia.com.baihangeducation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
import sinia.com.baihangeducation.utils.Constants;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_register, "高端人才");
        getDoingView().setVisibility(View.GONE);
        initViewsAndEvents();
    }

    protected void initViewsAndEvents() {
        userId = getIntent().getStringExtra("userId");
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
        params.put("certificate", tv_choosecard.getText().toString().trim());
        params.put("certificateNum", et_cardnum.getEditableText().toString().trim());
        params.put("workHistory", et_work.getEditableText().toString().trim());
        params.put("learnHistory", et_study.getEditableText().toString().trim());
        params.put("companyName", "-1");
        params.put("companyContent", "-1");
        client.post(Constants.BASE_URL + "telentAccept", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, String s) {
                super.onSuccess(i, s);
                dismiss();
            }
        });
    }


    @OnClick(R.id.tv_choosecard)
    void tv_choosecard() {
        ActionSheetDialogUtils.createCardDialog(this, tv_choosecard);
    }

    @OnClick(R.id.tv_register)
    void tv_register() {
        validator.validate();
    }

}
