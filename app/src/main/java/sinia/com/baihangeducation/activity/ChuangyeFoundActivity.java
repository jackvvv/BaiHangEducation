package sinia.com.baihangeducation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import butterknife.Bind;
import butterknife.OnClick;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.base.BaseActivity;
import sinia.com.baihangeducation.bean.FundBean;
import sinia.com.baihangeducation.bean.FundDetailBean;
import sinia.com.baihangeducation.bean.SubmitOrderBean;
import sinia.com.baihangeducation.utils.BitmapUtilsHelp;
import sinia.com.baihangeducation.utils.Constants;
import sinia.com.baihangeducation.utils.MyApplication;

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
    EditText tv_money;
    @Bind(R.id.tv_ok)
    TextView tv_ok;

    private String fundId, fundName, startMoney = "0";
    private AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuangye_found, "创业基金");
        getDoingView().setVisibility(View.GONE);
        getDetail();
    }

    private void getDetail() {
        fundId = getIntent().getStringExtra("fundId");
        fundName = getIntent().getStringExtra("fundName");
        showLoad("加载中...");
        RequestParams params = new RequestParams();
        params.put("fundId", fundId);
        Log.i("tag", Constants.BASE_URL + "startupFundDetail&" + params);
        client.post(Constants.BASE_URL + "startupFundDetail", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, String s) {
                super.onSuccess(i, s);
                dismiss();
                Gson gson = new Gson();
                if (s.contains("isSuccessful")
                        && s.contains("state")) {
                    FundDetailBean bean = gson.fromJson(s, FundDetailBean.class);
                    int state = bean.getState();
                    int isSuccessful = bean.getIsSuccessful();
                    if (0 == state && 0 == isSuccessful) {
                        BitmapUtilsHelp.getImage(ChuangyeFoundActivity.this, R.drawable.bg_jijin).display(img_jijin, bean.getImageUrl());
                        tv_content.setText(bean.getFundContent());
                        startMoney = bean.getPrice();
                        tv_money.setText(bean.getPrice());
                    } else {
                        showToast("请求失败");
                    }
                }
            }
        });
    }

    @OnClick(R.id.img_jian)
    void img_jian() {
        if (!tv_num.getText().toString().equals("1")) {
            tv_num.setText(Integer.parseInt(tv_num.getText().toString()) - 1
                    + "");
            tv_money.setText("" + Integer.parseInt(startMoney)
                    * Integer.parseInt(tv_num.getText().toString()));
        }
    }

    @OnClick(R.id.img_jia)
    void img_jia() {
        tv_num.setText(Integer.parseInt(tv_num.getText().toString()) + 1 + "");
        tv_money.setText("" + Integer.parseInt(startMoney)
                * Integer.parseInt(tv_num.getText().toString()));
    }

    @OnClick(R.id.tv_ok)
    void tv_ok() {
        if (Integer.parseInt(tv_money.getEditableText().toString().trim()) < Integer.parseInt(startMoney)) {
            showToast("请输入不小于起报金额的价格");
        } else {
            submitOrder();
        }
    }

    private void submitOrder() {
        showLoad("正在提交订单...");
        RequestParams params = new RequestParams();
        params.put("userId", MyApplication.getInstance().getStringValue("userId"));
        params.put("fundId", fundId);
        params.put("fundName", fundName);
        params.put("buyNum", tv_num.getText().toString());
        params.put("price", tv_money.getEditableText().toString());
        Log.i("tag", Constants.BASE_URL + "buyStBussiness&" + params);
        client.post(Constants.BASE_URL + "buyStBussiness", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, String s) {
                super.onSuccess(i, s);
                dismiss();
                Gson gson = new Gson();
                if (s.contains("isSuccessful")
                        && s.contains("state")) {
                    SubmitOrderBean bean = gson.fromJson(s, SubmitOrderBean.class);
                    int state = bean.getState();
                    int isSuccessful = bean.getIsSuccessful();
                    if (0 == state && 0 == isSuccessful) {
                        Intent intent = new Intent();
                        intent.putExtra("orderId", bean.getOrderId());
                        intent.putExtra("fundId", fundId);
                        intent.putExtra("fundName", fundName);
                        intent.putExtra("buyNum", tv_num.getText().toString());
                        intent.putExtra("price", tv_money.getEditableText().toString());
                        startActivityForIntent(ConfirmOrderActivity.class,intent);
                    } else {
                        showToast("请求失败");
                    }
                }
            }
        });
    }
}
