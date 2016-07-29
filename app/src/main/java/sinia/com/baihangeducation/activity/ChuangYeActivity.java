package sinia.com.baihangeducation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.adapter.ChuangyeAdapter;
import sinia.com.baihangeducation.base.BaseActivity;
import sinia.com.baihangeducation.bean.FundBean;
import sinia.com.baihangeducation.bean.JsonBean;
import sinia.com.baihangeducation.utils.ActivityManager;
import sinia.com.baihangeducation.utils.Constants;

/**
 * Created by 忧郁的眼神 on 2016/7/20.
 */
public class ChuangYeActivity extends BaseActivity {

    @Bind(R.id.listview)
    ListView listView;
    private ChuangyeAdapter adapter;
    private AsyncHttpClient client = new AsyncHttpClient();
    private List<FundBean.ItemsEntity> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuangye, "创业");
        getDoingView().setVisibility(View.GONE);
        getFoundList();
        initData();
    }

    private void getFoundList() {
        showLoad("加载中...");
        RequestParams params = new RequestParams();
        Log.i("tag", Constants.BASE_URL + "startupFund&" + params);
        client.post(Constants.BASE_URL + "startupFund", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, String s) {
                super.onSuccess(i, s);
                dismiss();
                Gson gson = new Gson();
                if (s.contains("isSuccessful")
                        && s.contains("state")) {
                    FundBean bean = gson.fromJson(s, FundBean.class);
                    int state = bean.getState();
                    int isSuccessful = bean.getIsSuccessful();
                    if (0 == state && 0 == isSuccessful) {
                        list.clear();
                        list.addAll(bean.getItems());
                        if (null != adapter) {
                            adapter.notifyDataSetChanged();
                        }
                    } else {
                    }
                }
            }
        });
    }

    private void initData() {
        adapter = new ChuangyeAdapter(this, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String fundId = list.get(position).getFundId();
                String fundName = list.get(position).getFundName();
                Intent intent = new Intent();
                intent.putExtra("fundId", fundId);
                intent.putExtra("fundName", fundName);
                startActivityForIntent(ChuangyeFoundActivity.class, intent);
            }
        });
    }


}
