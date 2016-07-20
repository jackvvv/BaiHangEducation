package sinia.com.baihangeducation.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import butterknife.Bind;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.adapter.ChuangyeAdapter;
import sinia.com.baihangeducation.base.BaseActivity;

/**
 * Created by 忧郁的眼神 on 2016/7/20.
 */
public class ChuangYeActivity extends BaseActivity {

    @Bind(R.id.listview)
    ListView listView;
    private ChuangyeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuangye, "创业");
        getDoingView().setVisibility(View.GONE);

        initData();
    }

    private void initData() {
        adapter = new ChuangyeAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivityForNoIntent(ChuangyeFoundActivity.class);
            }
        });
    }
}
