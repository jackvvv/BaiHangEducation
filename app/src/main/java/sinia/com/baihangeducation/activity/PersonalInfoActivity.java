package sinia.com.baihangeducation.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.feezu.liuli.timeselector.TimeSelector;
import org.feezu.liuli.timeselector.Utils.DateUtil;

import butterknife.Bind;
import butterknife.OnClick;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.actionsheetdialog.ActionSheetDialogUtils;
import sinia.com.baihangeducation.base.BaseActivity;

/**
 * Created by 忧郁的眼神 on 2016/7/18.
 */
public class PersonalInfoActivity extends BaseActivity {

    @Bind(R.id.et_name)
    EditText et_name;
    @Bind(R.id.et_city)
    EditText et_city;
    @Bind(R.id.et_tel)
    EditText et_tel;
    @Bind(R.id.et_email)
    EditText et_email;
    @Bind(R.id.tv_sex)
    TextView tv_sex;
    @Bind(R.id.tv_birthday)
    TextView tv_birthday;
    @Bind(R.id.rl_sex)
    RelativeLayout rl_sex;
    @Bind(R.id.rl_birthday)
    RelativeLayout rl_birthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info, "个人简历");
        getDoingView().setText("保存");
    }

    @Override
    public void doing() {
        super.doing();
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
