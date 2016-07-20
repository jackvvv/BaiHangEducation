package sinia.com.baihangeducation.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.base.BaseActivity;
import sinia.com.baihangeducation.utils.AppInfoUtil;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
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
