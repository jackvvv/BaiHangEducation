package sinia.com.baihangeducation.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import org.feezu.liuli.timeselector.TimeSelector;
import org.feezu.liuli.timeselector.Utils.DateUtil;

import butterknife.Bind;
import butterknife.OnClick;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.actionsheetdialog.ActionSheetDialogUtils;
import sinia.com.baihangeducation.base.BaseActivity;
import sinia.com.baihangeducation.bean.JsonBean;
import sinia.com.baihangeducation.bean.ResumeBean;
import sinia.com.baihangeducation.utils.ActivityManager;
import sinia.com.baihangeducation.utils.Constants;
import sinia.com.baihangeducation.utils.MyApplication;
import sinia.com.baihangeducation.utils.ValidationUtils;

/**
 * Created by 忧郁的眼神 on 2016/7/18.
 */
public class PersonalInfoActivity extends BaseActivity {
    @Order(1)
    @NotEmpty(message = "请输入您的姓名")
    @Bind(R.id.et_name)
    EditText et_name;
    @Order(4)
    @NotEmpty(message = "请输入现居住城市")
    @Bind(R.id.et_city)
    EditText et_city;
    @Order(5)
    @Pattern(regex = "(\\+\\d+)?1[34578]\\d{9}$", message = "请输入正确的手机号码")
    @Bind(R.id.et_tel)
    EditText et_tel;
    @Order(6)
    @Pattern(regex = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$", message = "请输入正确的邮箱地址")
    @Bind(R.id.et_email)
    EditText et_email;
    @Order(2)
    @NotEmpty(message = "请选择性别")
    @Bind(R.id.tv_sex)
    TextView tv_sex;
    @Order(3)
    @NotEmpty(message = "请选择出生日期")
    @Bind(R.id.tv_birthday)
    TextView tv_birthday;
    @Bind(R.id.rl_sex)
    RelativeLayout rl_sex;
    @Bind(R.id.rl_birthday)
    RelativeLayout rl_birthday;

    private String workType;
    private String userId;
    private Validator validator;
    private AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info, "个人简历");
        getDoingView().setText("保存");
        initData();
        getPersonalInfo();
    }

    private void getPersonalInfo() {
        showLoad("加载中...");
        RequestParams params = new RequestParams();
        params.put("userId", MyApplication.getInstance().getStringValue("userId"));
        Log.i("tag",Constants.BASE_URL + "querypersonalresume&"+params);
        client.post(Constants.BASE_URL + "querypersonalresume", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, String s) {
                super.onSuccess(i, s);
                dismiss();
                Gson gson = new Gson();
                if (s.contains("isSuccessful")
                        && s.contains("state")) {
                    ResumeBean bean = gson.fromJson(s, ResumeBean.class);
                    int state = bean.getState();
                    int isSuccessful = bean.getIsSuccessful();
                    if (0 == state && 0 == isSuccessful) {
                        if (null != bean) {
                            et_name.setText(bean.getName());
                            if ("1".equals(bean.getSex())) {
                                tv_sex.setText("男");
                            } else {
                                tv_sex.setText("女");
                            }
                            tv_birthday.setText(bean.getBirth());
                            et_city.setText(bean.getCity());
                            et_tel.setText(bean.getTelephone());
                            et_email.setText(bean.getEmail());
                            MyApplication.getInstance().setStringValue("resumeId",bean.getResumeId());
                        }
                    } else {
                    }
                }
            }
        });
    }

    private void initData() {
        workType = getIntent().getStringExtra("workType");
        validator = new Validator(this);
        validator.setValidationListener(new ValidationUtils.ValidationListener() {
            @Override
            public void onValidationSucceeded() {
                super.onValidationSucceeded();
                saveResume();
            }

        });
    }


    private void saveResume() {
        showLoad("保存中...");
        RequestParams params = new RequestParams();
        params.put("userId", MyApplication.getInstance().getStringValue("userId"));
        params.put("userName", et_name.getEditableText().toString().trim());
        params.put("email", et_email.getEditableText().toString().trim());
        params.put("telephone", et_tel.getEditableText().toString().trim());
        params.put("regionCodeAll", et_city.getEditableText().toString().trim());
        params.put("birth", tv_birthday.getText().toString().trim());
        params.put("workType", workType);
        if ("男".equals(tv_sex.getText().toString())) {
            params.put("sex", "1");
        } else {
            params.put("sex", "2");
        }
        Log.i("tag",Constants.BASE_URL + "personalresume&"+params);
        client.post(Constants.BASE_URL + "personalresume", params, new AsyncHttpResponseHandler() {
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
                        showToast("简历保存成功");
                    } else {
                        showToast("简历保存失败");
                    }
                }
            }
        });
    }

    @Override
    public void doing() {
        super.doing();
        validator.validate();
    }

    @OnClick(R.id.rl_sex)
    void rl_sex() {
        ActionSheetDialogUtils.createSexDialog(this, tv_sex);
    }

    @OnClick(R.id.rl_birthday)
    void rl_birthday() {
        TimeSelector timeSelector = new TimeSelector(this, "请选择出生日期",
                new TimeSelector.ResultHandler() {

                    @Override
                    public void handle(String time) {
                        tv_birthday.setText(time);
                    }
                }, "1960-01-01", DateUtil.getCurYearAndMonth2());
        timeSelector.setScrollUnit(TimeSelector.SCROLLTYPE.YEAR,
                TimeSelector.SCROLLTYPE.MONTH, TimeSelector.SCROLLTYPE.DAY);
        timeSelector.show();
    }
}
