package sinia.com.baihangeducation.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.adapter.ChoosedJobGridAdapter;
import sinia.com.baihangeducation.adapter.JobStickyListAdapter;
import sinia.com.baihangeducation.base.BaseActivity;

/**
 * Created by 忧郁的眼神 on 2016/7/19.
 */
public class JobTypeActivity extends BaseActivity {

    @Bind(R.id.tv_num)
    TextView tv_num;
    @Bind(R.id.img_jt)
    ImageView img_jt;
    @Bind(R.id.grid_job)
    GridView grid_job;
    @Bind(R.id.list_job)
    StickyListHeadersListView list_job;
    private JobStickyListAdapter jobStickyListAdapter;
    private ChoosedJobGridAdapter gridAdapter;
    private boolean isExpand = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_type, "职业类别");
        getDoingView().setText("保存");
        initData();
    }

    private void initData() {
        jobStickyListAdapter = new JobStickyListAdapter(this);
        list_job.setAdapter(jobStickyListAdapter);
        gridAdapter = new ChoosedJobGridAdapter(this);
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
}
