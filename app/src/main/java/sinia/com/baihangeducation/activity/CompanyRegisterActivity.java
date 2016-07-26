package sinia.com.baihangeducation.activity;

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
import sinia.com.baihangeducation.actionsheetdialog.ActionSheetDialogUtils;
import sinia.com.baihangeducation.base.BaseActivity;
import sinia.com.baihangeducation.utils.Constants;
import sinia.com.baihangeducation.utils.StringUtils;
import sinia.com.baihangeducation.utils.ValidationUtils;

/**
 * Created by 忧郁的眼神 on 2016/7/15.
 */
public class CompanyRegisterActivity extends BaseActivity {
    @Order(1)
    @NotEmpty(message = "请填写企业名称")
    @Bind(R.id.et_name)
    EditText et_name;
    @Order(2)
    @NotEmpty(message = "请填写企业介绍")
    @Bind(R.id.et_detail)
    EditText et_detail;
    @Bind(R.id.tv_register)
    TextView tv_register;
    private Validator validator;
    private String userId;
    private AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_register, "企业认证");
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
                companyRegister();
            }

        });
    }

    private void companyRegister() {
        showLoad("加载中...");
        RequestParams params = new RequestParams();
        params.put("userId", userId);
        params.put("companyName", et_name.getEditableText().toString().trim());
        params.put("companyContent", et_detail.getEditableText().toString().trim());
        client.post(Constants.BASE_URL + "companyAccept", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, String s) {
                super.onSuccess(i, s);
                dismiss();
            }
        });
    }


    @OnClick(R.id.tv_register)
    void tv_register() {
        validator.validate();
    }

}
