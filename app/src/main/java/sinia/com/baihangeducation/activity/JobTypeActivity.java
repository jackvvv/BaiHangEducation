package sinia.com.baihangeducation.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.adapter.ChoosedJobGridAdapter;
import sinia.com.baihangeducation.adapter.ExpandableListAdapter;
import sinia.com.baihangeducation.adapter.JobStickyListAdapter;
import sinia.com.baihangeducation.base.BaseActivity;
import sinia.com.baihangeducation.bean.JobBean;
import sinia.com.baihangeducation.bean.JsonBean;
import sinia.com.baihangeducation.myinterface.UpdateChoosedJob;
import sinia.com.baihangeducation.utils.ActivityManager;
import sinia.com.baihangeducation.utils.AppInfoUtil;
import sinia.com.baihangeducation.utils.Constants;

/**
 * Created by 忧郁的眼神 on 2016/7/19.
 */
public class JobTypeActivity extends BaseActivity implements UpdateChoosedJob {

    @Bind(R.id.rl_title)
    RelativeLayout rl_title;
    @Bind(R.id.tv_num)
    TextView tv_num;
    @Bind(R.id.doing)
    TextView doing;
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.img_jt)
    ImageView img_jt;
    @Bind(R.id.grid_job)
    GridView grid_job;
    @Bind(R.id.list_job)
    ExpandableListView list_job;
    //    private JobStickyListAdapter jobStickyListAdapter;
    private ChoosedJobGridAdapter gridAdapter;
    private ExpandableListAdapter jobadapter;
    private boolean isExpand = false;
    private String workType;
    private AsyncHttpClient client = new AsyncHttpClient();
    private List<JobBean.ItemsEntity> list = new ArrayList<JobBean.ItemsEntity>();
    private ArrayList<String> choosedList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_type2);
        ButterKnife.bind(this);
        initViews();
        getJobList();
        initData();
    }

    private void initViews() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        int stateHeight = AppInfoUtil.getStateHeight(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, AppInfoUtil.dip2px(this, 45)
        );
        layoutParams.topMargin = stateHeight;
        rl_title.setLayoutParams(layoutParams);
    }

    private void getJobList() {
        workType = getIntent().getStringExtra("workType");
        showLoad("加载中...");
        RequestParams params = new RequestParams();
        params.put("workType", workType);
        Log.i("tag", Constants.BASE_URL + "jobList&" + params);
        client.post(Constants.BASE_URL + "jobList", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, String s) {
                super.onSuccess(i, s);
                dismiss();
                Gson gson = new Gson();
                if (s.contains("isSuccessful")
                        && s.contains("state")) {
                    JobBean bean = gson.fromJson(s, JobBean.class);
                    int state = bean.getState();
                    int isSuccessful = bean.getIsSuccessful();
                    if (0 == state && 0 == isSuccessful) {
                        list.clear();
                        list.addAll(bean.getItems());
                        jobadapter.notifyDataSetChanged();
                        for (int a = 0; a < list.size(); a++) {
                            jobadapter.getIsSelected().put(a, false);
                        }
                        int groupCount = list_job.getCount();
                        for (int a = 0; a < groupCount; a++) {
                            list_job.expandGroup(a);
                        }
                    }
                }
            }
        });
    }

    private void initData() {
        // 设置默认图标为不显示状态
        list_job.setGroupIndicator(null);
        jobadapter = new ExpandableListAdapter(this, list);
        list_job.setAdapter(jobadapter);
        gridAdapter = new ChoosedJobGridAdapter(this, choosedList);
        grid_job.setAdapter(gridAdapter);

    }

    @OnClick(R.id.img_jt)
    void img_jt() {
        if (isExpand) {
            grid_job.setVisibility(View.VISIBLE);
            isExpand = false;
            RotateAnimation animation = new RotateAnimation(-180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(350);
            animation.setFillAfter(true);
            img_jt.startAnimation(animation);
        } else {
            grid_job.setVisibility(View.GONE);
            isExpand = true;
            RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(350);
            animation.setFillAfter(true);
            img_jt.startAnimation(animation);
        }
    }

    @Override
    public void updatechoosedJobList(List<String> list) {
        tv_num.setText(list.size() + "/3");
        choosedList.clear();
        choosedList.addAll(list);
        gridAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        if (choosedList.size() != 0) {
            Intent intent = new Intent();
            intent.putStringArrayListExtra("joblist", choosedList);
            setResult(RESULT_OK, intent);
        }
        ActivityManager.getInstance().finishCurrentActivity();
        super.onBackPressed();
    }

    @OnClick(R.id.doing)
    public void doing() {
        if (choosedList.size() != 0) {
            Intent intent = new Intent();
            intent.putStringArrayListExtra("joblist", choosedList);
            setResult(RESULT_OK, intent);
        }
        ActivityManager.getInstance().finishCurrentActivity();
    }

    @OnClick(R.id.back)
    protected void back() {
        if (choosedList.size() != 0) {
            Intent intent = new Intent();
            intent.putStringArrayListExtra("joblist", choosedList);
            setResult(RESULT_OK, intent);
        }
        ActivityManager.getInstance().finishCurrentActivity();
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
