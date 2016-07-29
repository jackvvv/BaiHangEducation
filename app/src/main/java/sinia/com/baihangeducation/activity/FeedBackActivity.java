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
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import butterknife.Bind;
import butterknife.OnClick;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.base.BaseActivity;
import sinia.com.baihangeducation.bean.JobBean;
import sinia.com.baihangeducation.bean.JsonBean;
import sinia.com.baihangeducation.utils.ActivityManager;
import sinia.com.baihangeducation.utils.Constants;
import sinia.com.baihangeducation.utils.MyApplication;
import sinia.com.baihangeducation.utils.StringUtil;
import sinia.com.baihangeducation.utils.ValidationUtils;

/**
 * Created by 忧郁的眼神 on 2016/7/20.
 */
public class FeedBackActivity extends BaseActivity {
    @NotEmpty(message = "请输入反馈内容")
    @Order(1)
    @Bind(R.id.et_content)
    EditText et_content;
    @Pattern(regex = "(\\+\\d+)?1[34578]\\d{9}$", message = "请输入正确的手机号码")
    @Order(2)
    @Bind(R.id.et_tel)
    EditText et_tel;
    @Bind(R.id.tv_submit)
    TextView tv_submit;

    private Validator validator;
    private AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit_feedback, "意见反馈");
        getDoingView().setVisibility(View.GONE);
        initViewsAndEvents();
    }

    protected void initViewsAndEvents() {
        validator = new Validator(this);
        validator.setValidationListener(new ValidationUtils.ValidationListener() {
            @Override
            public void onValidationSucceeded() {
                super.onValidationSucceeded();
                feedback();
            }

        });
    }

    private void feedback() {
        showLoad("正在提交意见...");
        RequestParams params = new RequestParams();
        params.put("content", et_content.getEditableText().toString().trim());
        params.put("telephone", et_tel.getEditableText().toString().trim());
        Log.i("tag", Constants.BASE_URL + "addAdvice&" + params);
        client.post(Constants.BASE_URL + "addAdvice", params, new AsyncHttpResponseHandler() {
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
                        showToast("意见反馈成功");
                        ActivityManager.getInstance().finishCurrentActivity();
                    } else {
                        showToast("意见反馈失败");
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
