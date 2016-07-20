package sinia.com.baihangeducation.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.base.BaseActivity;

/**
 * Created by 忧郁的眼神 on 2016/7/20.
 */
public class ChuangyeFoundActivity extends BaseActivity {

    @Bind(R.id.img_jijin)
    ImageView img_jijin;
    @Bind(R.id.tv_content)
    TextView tv_content;
    @Bind(R.id.img_jian)
    TextView img_jian;
    @Bind(R.id.img_jia)
    TextView img_jia;
    @Bind(R.id.tv_num)
    TextView tv_num;
    @Bind(R.id.tv_money)
    TextView tv_money;
    @Bind(R.id.tv_ok)
    TextView tv_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuangye_found, "创业基金");
        getDoingView().setVisibility(View.GONE);
    }

    @OnClick(R.id.img_jian)
    void img_jian() {
        if (!tv_num.getText().toString().equals("1")) {
            tv_num.setText(Integer.parseInt(tv_num.getText().toString()) - 1
                    + "");
            tv_money.setText("¥ " + 200
                    * Integer.parseInt(tv_num.getText().toString()));
            tv_money.setText("¥ " + 200
                    * Integer.parseInt(tv_num.getText().toString()));
        }
    }

    @OnClick(R.id.img_jia)
    void img_jia() {
        tv_num.setText(Integer.parseInt(tv_num.getText().toString()) + 1 + "");
        tv_money.setText("¥ " + 200
                * Integer.parseInt(tv_num.getText().toString()));
        tv_money.setText("¥ " + 200
                * Integer.parseInt(tv_num.getText().toString()));
    }

    @OnClick(R.id.tv_ok)
    void tv_ok() {
        startActivityForNoIntent(ConfirmOrderActivity.class);
    }
}
