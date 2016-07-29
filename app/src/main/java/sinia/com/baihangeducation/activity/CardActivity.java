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
import sinia.com.baihangeducation.adapter.CardAdapter;
import sinia.com.baihangeducation.base.BaseActivity;
import sinia.com.baihangeducation.bean.CardBean;
import sinia.com.baihangeducation.bean.MyChuangYeBean;
import sinia.com.baihangeducation.utils.ActivityManager;
import sinia.com.baihangeducation.utils.Constants;
import sinia.com.baihangeducation.utils.MyApplication;

/**
 * Created by 忧郁的眼神 on 2016/7/29.
 */
public class CardActivity extends BaseActivity {

    @Bind(R.id.listview)
    ListView listview;

    private CardAdapter adapter;
    private AsyncHttpClient client = new AsyncHttpClient();
    private List<CardBean.ItemsEntity> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_card, "选择证件");
        getDoingView().setVisibility(View.GONE);
        getCardList();
    }

    private void getCardList() {
        adapter = new CardAdapter(this, list);
        listview.setAdapter(adapter);
        showLoad("加载中...");
        RequestParams params = new RequestParams();
        Log.i("tag", Constants.BASE_URL + "cerList&" + params);
        client.post(Constants.BASE_URL + "cerList", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, String s) {
                super.onSuccess(i, s);
                dismiss();
                Gson gson = new Gson();
                if (s.contains("isSuccessful")
                        && s.contains("state")) {
                    CardBean bean = gson.fromJson(s, CardBean.class);
                    int state = bean.getState();
                    int isSuccessful = bean.getIsSuccessful();
                    if (0 == state && 0 == isSuccessful) {
                        list.clear();
                        list.addAll(bean.getItems());
                        adapter.notifyDataSetChanged();
                    } else {
                        showToast("请求失败");
                    }
                }
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("card", list.get(position).getCertificate());
                setResult(RESULT_OK, intent);
                ActivityManager.getInstance().finishCurrentActivity();
            }
        });
    }


}
