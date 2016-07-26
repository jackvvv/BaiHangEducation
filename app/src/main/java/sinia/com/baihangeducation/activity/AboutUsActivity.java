package sinia.com.baihangeducation.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.base.BaseActivity;

/**
 * Created by 忧郁的眼神 on 2016/7/20.
 */
public class AboutUsActivity extends BaseActivity {

    @Bind(R.id.imageView5)
    ImageView imageView5;
    @Bind(R.id.tv_version)
    TextView tvVersion;
    @Bind(R.id.textView6)
    TextView textView6;
    @Bind(R.id.textView7)
    TextView textView7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus, "关于我们");
        ButterKnife.bind(this);
        getDoingView().setVisibility(View.GONE);
    }
}
