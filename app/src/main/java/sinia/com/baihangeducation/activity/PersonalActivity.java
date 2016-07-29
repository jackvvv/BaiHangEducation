package sinia.com.baihangeducation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import butterknife.Bind;
import butterknife.OnClick;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.base.BaseActivity;
import sinia.com.baihangeducation.bean.FundDetailBean;
import sinia.com.baihangeducation.bean.PersonalBean;
import sinia.com.baihangeducation.utils.ActivityManager;
import sinia.com.baihangeducation.utils.BitmapUtilsHelp;
import sinia.com.baihangeducation.utils.Constants;
import sinia.com.baihangeducation.utils.DialogUtils;
import sinia.com.baihangeducation.utils.MyApplication;

/**
 * Created by 忧郁的眼神 on 2016/7/20.
 */
public class PersonalActivity extends BaseActivity {

    @Bind(R.id.ll_work)
    LinearLayout ll_work;
    @Bind(R.id.ll_chy)
    LinearLayout ll_chy;
    @Bind(R.id.ll_setting)
    LinearLayout ll_setting;
    @Bind(R.id.ll_logout)
    LinearLayout ll_logout;
    @Bind(R.id.img_head)
    ImageView img_head;
    @Bind(R.id.tv_tel)
    TextView tv_tel;
    @Bind(R.id.tv_name)
    TextView tv_name;
    @Bind(R.id.rl_person)
    RelativeLayout rl_person;
    private AsyncHttpClient client = new AsyncHttpClient();
    private PersonalBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal, "个人中心");
        getDoingView().setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        showLoad("加载中...");
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
                        BitmapUtilsHelp.getImage(PersonalActivity.this).display(img_head, bean.getImageUrl());
                        tv_name.setText("姓名：" + bean.getUserName());
                        tv_tel.setText("电话：" + bean.getTelephone());
                    } else {
                        showToast("请求失败");
                    }
                }
            }
        });
    }

    @OnClick(R.id.ll_work)
    void ll_work() {
        startActivityForNoIntent(MyJobActivity.class);
    }

    @OnClick(R.id.ll_chy)
    void ll_chy() {
        startActivityForNoIntent(MyChuangyeActivity.class);
    }

    @OnClick(R.id.ll_setting)
    void ll_setting() {
        startActivityForNoIntent(SettingsActivity.class);
    }

    @OnClick(R.id.rl_person)
    void rl_person() {
        if (null != bean) {
            Intent intent = new Intent();
            intent.putExtra("bean", bean);
            startActivityForIntent(EditPersonInfoActivity.class, intent);
        }
    }

    @OnClick(R.id.ll_logout)
    void ll_logout() {
        DialogUtils.createLoginOutDialog(this, new DialogUtils.OnOkListener() {

            @Override
            public void onClick() {
                MyApplication.getInstance().setBooleanValue("is_login",
                        false);
                MyApplication.getInstance().setLoginBean(null);
                startActivityForNoIntent(LoginActivity.class);
                ActivityManager.getInstance().finishCurrentActivity();
                ActivityManager.getInstance()
                        .finishActivity(MainActivity.class);
            }
        });
    }
}
