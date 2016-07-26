package sinia.com.baihangeducation.activity;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

import butterknife.Bind;
import butterknife.OnClick;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.adapter.BeansRecordAdapter;
import sinia.com.baihangeducation.base.BaseActivity;
import sinia.com.baihangeducation.view.ObservableListView;
import sinia.com.baihangeducation.view.ObservableScrollViewCallbacks;
import sinia.com.baihangeducation.view.ScrollState;
import sinia.com.baihangeducation.view.ScrollUtils;

/**
 * Created by 忧郁的眼神 on 2016/7/21.
 */
public class MyBeansActivity2 extends BaseActivity implements ObservableScrollViewCallbacks {

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
    ObservableListView mListView;

    private View mHeaderView;
    private View mToolbarView;
    private int mBaseTranslationY;

    private BeansRecordAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_beans2, "我的创业豆");
        getDoingView().setVisibility(View.GONE);
        initData();
    }

    private void initData() {
        mHeaderView = findViewById(R.id.header);
        ViewCompat.setElevation(mHeaderView, 4);
        mToolbarView = findViewById(R.id.mToolbarView);
        mListView.setScrollViewCallbacks(this);

        LayoutInflater inflater = LayoutInflater.from(this);
        View headView = inflater.inflate(R.layout.mybeans_headview, mListView, false);
        mListView.addHeaderView(headView); // beans view
        mListView.addHeaderView(inflater.inflate(R.layout.padding, mListView, false)); // sticky view

        adapter = new BeansRecordAdapter(this);
        mListView.setAdapter(adapter);
        mListView.setEmptyView(tv_norecord);
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

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        if (dragging) {
            int toolbarHeight = mToolbarView.getHeight();
            if (firstScroll) {
                float currentHeaderTranslationY = ViewHelper.getTranslationY(mHeaderView);
                if (-toolbarHeight < currentHeaderTranslationY) {
                    mBaseTranslationY = scrollY;
                }
            }
            float headerTranslationY = ScrollUtils.getFloat(-(scrollY - mBaseTranslationY), -toolbarHeight, 0);
            ViewPropertyAnimator.animate(mHeaderView).cancel();
            ViewHelper.setTranslationY(mHeaderView, headerTranslationY);
        }
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        mBaseTranslationY = 0;

        if (scrollState == ScrollState.DOWN) {
            showToolbar();
        } else if (scrollState == ScrollState.UP) {
            int toolbarHeight = mToolbarView.getHeight();
            int scrollY = mListView.getCurrentScrollY();
            if (toolbarHeight <= scrollY) {
                hideToolbar();
            } else {
                showToolbar();
            }
        } else {
            // Even if onScrollChanged occurs without scrollY changing, toolbar should be adjusted
            if (!toolbarIsShown() && !toolbarIsHidden()) {
                // Toolbar is moving but doesn't know which to move:
                // you can change this to hideToolbar()
                showToolbar();
            }
        }
    }

    private boolean toolbarIsShown() {
        return ViewHelper.getTranslationY(mHeaderView) == 0;
    }

    private boolean toolbarIsHidden() {
        return ViewHelper.getTranslationY(mHeaderView) == -mToolbarView.getHeight();
    }

    private void showToolbar() {
        float headerTranslationY = ViewHelper.getTranslationY(mHeaderView);
        if (headerTranslationY != 0) {
            ViewPropertyAnimator.animate(mHeaderView).cancel();
            ViewPropertyAnimator.animate(mHeaderView).translationY(0).setDuration(200).start();
        }
    }

    private void hideToolbar() {
        float headerTranslationY = ViewHelper.getTranslationY(mHeaderView);
        int toolbarHeight = mToolbarView.getHeight();
        if (headerTranslationY != -toolbarHeight) {
            ViewPropertyAnimator.animate(mHeaderView).cancel();
            ViewPropertyAnimator.animate(mHeaderView).translationY(-toolbarHeight).setDuration(200).start();
        }
    }
}
