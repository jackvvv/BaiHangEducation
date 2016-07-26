package sinia.com.baihangeducation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import butterknife.Bind;
import butterknife.OnClick;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.base.BaseActivity;
import sinia.com.baihangeducation.utils.ValidationUtils;

/**
 * Created by 忧郁的眼神 on 2016/7/15.
 */
public class RegisterActivity extends BaseActivity {
    @Bind(R.id.tv_student)
    TextView tv_student;
    @Bind(R.id.tv_high)
    TextView tv_high;
    @Bind(R.id.tv_company)
    TextView tv_company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register, "注册");
        getDoingView().setVisibility(View.GONE);
        initViewsAndEvents();
    }

    protected void initViewsAndEvents() {

    }

    @OnClick(R.id.tv_company)
    void tv_company() {
        Intent intent = new Intent();
        intent.putExtra("type", "3");
        startActivityForIntent(StudentRegisterActivity.class, intent);
    }

    @OnClick(R.id.tv_student)
    void tv_student() {
        Intent intent = new Intent();
        intent.putExtra("type", "1");
        startActivityForIntent(StudentRegisterActivity.class, intent);
    }

    @OnClick(R.id.tv_high)
    void tv_high() {
        Intent intent = new Intent();
        intent.putExtra("type", "2");
        startActivityForIntent(StudentRegisterActivity.class, intent);
    }

}
