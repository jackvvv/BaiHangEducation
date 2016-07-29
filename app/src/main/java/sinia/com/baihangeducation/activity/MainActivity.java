package sinia.com.baihangeducation.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.base.BaseActivity;
import sinia.com.baihangeducation.bean.PersonalBean;
import sinia.com.baihangeducation.utils.AppInfoUtil;
import sinia.com.baihangeducation.utils.BitmapUtilsHelp;
import sinia.com.baihangeducation.utils.Constants;
import sinia.com.baihangeducation.utils.MyApplication;
import sinia.com.baihangeducation.utils.ValidationUtils;

public class MainActivity extends BaseActivity {

    @Bind(R.id.rl_title)
    RelativeLayout rl_title;
    @Bind(R.id.tomine)
    ImageView tomine;
    @Bind(R.id.tv_student)
    TextView tv_student;
    @Bind(R.id.tv_company)
    TextView tv_company;
    @Bind(R.id.tv_chuangye)
    TextView tv_chuangye;
    @Bind(R.id.tv_rencai)
    TextView tv_rencai;

    private AsyncHttpClient client = new AsyncHttpClient();
    private PersonalBean bean;
    private String talentType;//人才认证状态(0.未认证1.认证审核中2.认证审核成功3.认证审核失败)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
        getData();
    }

    private void getData() {
        RequestParams params = new RequestParams();
        params.put("userId", MyApplication.getInstance().getStringValue("userId"));
        Log.i("tag", Constants.BASE_URL + "personalCenter&" + params);
        client.post(Constants.BASE_URL + "personalCenter", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, String s) {
                super.onSuccess(i, s);
                dismiss();
                Gson gson = new Gson();
                if (s.contains("isSuccessful")
                        && s.contains("state")) {
                    bean = gson.fromJson(s, PersonalBean.class);
                    int state = bean.getState();
                    int isSuccessful = bean.getIsSuccessful();
                    if (0 == state && 0 == isSuccessful) {
                        talentType = bean.getTalentType();
                    } else {
                        showToast("请求失败");
                    }
                }
            }
        });
    }

    private void initViews() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        int stateHeight = AppInfoUtil.getStateHeight(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, AppInfoUtil.dip2px(this, 45)
        );
        layoutParams.topMargin = stateHeight;
        rl_title.setLayoutParams(layoutParams);
    }

    @OnClick(R.id.tomine)
    void tomine() {
        startActivityForNoIntent(PersonalActivity.class);
    }

    @OnClick(R.id.tv_student)
    void tv_student() {
        startActivityForNoIntent(StudentJobActivity.class);
    }

    @OnClick(R.id.tv_company)
    void tv_company() {
        startActivityForNoIntent(CompanyWantedActivity.class);
    }

    @OnClick(R.id.tv_chuangye)
    void tv_chuangye() {
        startActivityForNoIntent(ChuangyeProtecolActivity.class);
    }

    @OnClick(R.id.tv_rencai)
    void tv_rencai() {
        if("2".equals(talentType)){
            showToast("已通过人才认证");
        }else{
            showToast("您还不是高级人才，需要提交资料并审核");
        }
    }

    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}
