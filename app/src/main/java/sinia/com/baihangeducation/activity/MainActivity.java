package sinia.com.baihangeducation.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
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
import me.drakeet.materialdialog.MaterialDialog;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.base.BaseActivity;
import sinia.com.baihangeducation.bean.PersonalBean;
import sinia.com.baihangeducation.utils.ActivityManager;
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
    private String companyType;//企业认证状态(0.未认证1.认证审核中2.认证审核成功3.认证审核失败)

    private MaterialDialog materialDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
                        companyType = bean.getCompanyType();
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
        if ("2".equals(companyType)) {
            startActivityForNoIntent(CompanyWantedActivity.class);
        } else if ("1".equals(companyType)) {
            showToast("企业认证正在审核中...");
        } else if ("3".equals(companyType)) {
            materialDialog = new MaterialDialog(this);
            materialDialog.setTitle("提示").setMessage("您的认证审核失败，是否重新认证？")
                    .setPositiveButton("去审核", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.putExtra("fromHome", "1");
                            intent.putExtra("userId", MyApplication.getInstance().getStringValue("userId"));
                            startActivityForIntent(CompanyRegisterActivity.class, intent);
                            materialDialog.dismiss();
                        }
                    }).setNegativeButton("知道了", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    materialDialog.dismiss();
                }
            }).show();
        } else {
            materialDialog = new MaterialDialog(this);
            materialDialog.setTitle("提示").setMessage("您还未通过审核，需要提交资料并审核。")
                    .setPositiveButton("去审核", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.putExtra("fromHome", "1");
                            intent.putExtra("userId", MyApplication.getInstance().getStringValue("userId"));
                            startActivityForIntent(CompanyRegisterActivity.class, intent);
                            materialDialog.dismiss();
                        }
                    }).setNegativeButton("知道了", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    materialDialog.dismiss();
                }
            }).show();
        }
    }

    @OnClick(R.id.tv_chuangye)
    void tv_chuangye() {
        startActivityForNoIntent(ChuangyeProtecolActivity.class);
    }

    @OnClick(R.id.tv_rencai)
    void tv_rencai() {
        if ("2".equals(talentType)) {
            showToast("已通过人才认证");
        } else if ("1".equals(talentType)) {
            showToast("您的人才认证正在审核中...");
        } else if ("3".equals(talentType)) {
            materialDialog = new MaterialDialog(this);
            materialDialog.setTitle("提示").setMessage("您的认证审核失败，是否重新认证？")
                    .setPositiveButton("去审核", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.putExtra("fromHome", "1");
                            intent.putExtra("userId", MyApplication.getInstance().getStringValue("userId"));
                            startActivityForIntent(HighTalentActivity.class, intent);
                            materialDialog.dismiss();
                        }
                    }).setNegativeButton("知道了", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    materialDialog.dismiss();
                }
            }).show();
        } else {
            materialDialog = new MaterialDialog(this);
            materialDialog.setTitle("提示").setMessage("您还未通过审核，需要提交资料并审核。")
                    .setPositiveButton("去审核", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.putExtra("fromHome", "1");
                            intent.putExtra("userId", MyApplication.getInstance().getStringValue("userId"));
                            startActivityForIntent(HighTalentActivity.class, intent);
                            materialDialog.dismiss();
                        }
                    }).setNegativeButton("知道了", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    materialDialog.dismiss();
                }
            }).show();
        }
    }

    public Dialog dialog;

    public Dialog createTipsDialog(final Context context, final String type) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_tips, null);
        dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置
        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = (display.getWidth() - 100); // 设置宽度
        dialog.getWindow().setAttributes(lp);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(v, lp);
        final TextView cancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        final TextView tv_ok = (TextView) dialog.findViewById(R.id.tv_ok);
        tv_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent();
                intent.putExtra("fromHome", "1");
                intent.putExtra("userId", MyApplication.getInstance().getStringValue("userId"));
                if (type.equals("1")) {
                    startActivityForIntent(HighTalentActivity.class, intent);
                } else {
                    startActivityForIntent(CompanyRegisterActivity.class, intent);
                }
                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });
        return dialog;
    }

    public Dialog createCheckFailedDialog(final Context context, final String type) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_check_failed, null);
        dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置
        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = (display.getWidth() - 100); // 设置宽度
        dialog.getWindow().setAttributes(lp);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(v, lp);
        final TextView cancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        final TextView tv_ok = (TextView) dialog.findViewById(R.id.tv_ok);
        tv_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent();
                intent.putExtra("fromHome", "1");
                intent.putExtra("userId", MyApplication.getInstance().getStringValue("userId"));
                if (type.equals("1")) {
                    startActivityForIntent(HighTalentActivity.class, intent);
                } else {
                    startActivityForIntent(CompanyRegisterActivity.class, intent);
                }
                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });
        return dialog;
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
