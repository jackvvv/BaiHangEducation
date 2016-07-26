package sinia.com.baihangeducation.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.OnClick;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.adapter.BeansRecordAdapter;
import sinia.com.baihangeducation.base.BaseActivity;

/**
 * Created by 忧郁的眼神 on 2016/7/21.
 */
public class MyBeansActivity extends BaseActivity {

    @Bind(R.id.tv_detail)
    TextView tv_detail;
    @Bind(R.id.tv_beans)
    TextView tv_beans;
    @Bind(R.id.tv_dots)
    TextView tv_dots;
    @Bind(R.id.tv_withdraw)
    TextView tv_withdraw;
    @Bind(R.id.tv_recharge)
    TextView tv_recharge;
    @Bind(R.id.tv_norecord)
    TextView tv_norecord;
    @Bind(R.id.listview)
    ListView listview;

    private BeansRecordAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_beans2, "我的创业豆");
        getDoingView().setVisibility(View.GONE);
        initData();
    }

    private void initData() {
        View headView = LayoutInflater.from(this).inflate(R.layout.mybeans_headview, null);
        adapter = new BeansRecordAdapter(this);
        listview.setAdapter(adapter);
        listview.setEmptyView(tv_norecord);
    }

    @OnClick(R.id.tv_withdraw)
    void tv_withdraw() {
        startActivityForNoIntent(WithdrawActivity.class);
    }

    @OnClick(R.id.tv_recharge)
    void tv_recharge() {
        startActivityForNoIntent(RechargeActivity.class);
    }

    @OnClick(R.id.tv_detail)
    void tv_detail() {
        startActivityForNoIntent(BeansIntroduceActivity.class);
    }
}
