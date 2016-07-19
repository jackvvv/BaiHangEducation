package sinia.com.baihangeducation.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
import sinia.com.baihangeducation.utils.StringUtils;
import sinia.com.baihangeducation.utils.ValidationUtils;

/**
 * Created by 忧郁的眼神 on 2016/7/15.
 */
public class HighTalentActivity extends BaseActivity {
    @Pattern(regex = "(\\+\\d+)?1[34578]\\d{9}$", message = "请输入正确的手机号码")
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
    @Order(4)
    @NotEmpty(message = "请输入身份号码")
    @Bind(R.id.et_sdcard)
    EditText et_sdcard;
    @Order(5)
    @NotEmpty(message = "请输入证件号码")
    @Bind(R.id.et_cardnum)
    EditText et_cardnum;
    @Order(6)
    @NotEmpty(message = "请输入您的姓名")
    @Bind(R.id.et_name)
    EditText et_name;
    @Order(7)
    @NotEmpty(message = "请输入工作经历")
    @Bind(R.id.et_work)
    EditText et_work;
    @Order(8)
    @NotEmpty(message = "请输入学习经历")
    @Bind(R.id.et_study)
    EditText et_study;
    @Bind(R.id.tv_register)
    TextView tv_register;
    @Bind(R.id.tv_choosecard)
    TextView tv_choosecard;
    @Bind(R.id.tv_getcode)
    TextView tv_getcode;
    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_register, "高端人才");
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

    @OnClick(R.id.tv_getcode)
    void tv_getcode() {
        if (!StringUtils.isMobileNumber(et_tel.getEditableText().toString().trim())) {
            showToast("请输入正确的手机号码");
        }
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
