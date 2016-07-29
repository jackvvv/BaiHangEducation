package sinia.com.baihangeducation.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.adapter.MyJobAdapter;
import sinia.com.baihangeducation.base.BaseActivity;
import sinia.com.baihangeducation.bean.JsonBean;
import sinia.com.baihangeducation.bean.MyChuangYeBean;
import sinia.com.baihangeducation.bean.MyJobBean;
import sinia.com.baihangeducation.utils.AppInfoUtil;
import sinia.com.baihangeducation.utils.Constants;
import sinia.com.baihangeducation.utils.MyApplication;
import sinia.com.baihangeducation.view.swipmenulistview.SwipeMenu;
import sinia.com.baihangeducation.view.swipmenulistview.SwipeMenuCreator;
import sinia.com.baihangeducation.view.swipmenulistview.SwipeMenuItem;
import sinia.com.baihangeducation.view.swipmenulistview.SwipeMenuListView;

/**
 * Created by 忧郁的眼神 on 2016/7/21.
 */
public class MyJobActivity extends BaseActivity {

    @Bind(R.id.listview)
    SwipeMenuListView listview;
    //myJobList
    private MyJobAdapter adapter;
    private AsyncHttpClient client = new AsyncHttpClient();
    private List<MyJobBean.ItemsEntity> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myjob, "我的工作");
        getDoingView().setVisibility(View.GONE);
        getMyJobList();
        initData();
    }

    private void getMyJobList() {
        showLoad("加载中...");
        RequestParams params = new RequestParams();
        params.put("customerId", MyApplication.getInstance().getStringValue("userId"));
        Log.i("tag", Constants.BASE_URL + "myJobList&" + params);
        client.post(Constants.BASE_URL + "myJobList", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, String s) {
                super.onSuccess(i, s);
                dismiss();
                Gson gson = new Gson();
                if (s.contains("isSuccessful")
                        && s.contains("state")) {
                    MyJobBean bean = gson.fromJson(s, MyJobBean.class);
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
    }

    private void initData() {
        adapter = new MyJobAdapter(this,list);
        listview.setAdapter(adapter);
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xFF,
                        0x42, 0x41)));
                // set item width
                deleteItem.setWidth(AppInfoUtil.dip2px(MyJobActivity.this, 90));
                deleteItem.setTitle("删除");
                deleteItem.setTitleSize(16);
                deleteItem.setTitleColor(Color.WHITE);
                // set a icon
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        listview.setMenuCreator(creator);
        listview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu,
                                           int index) {
                switch (index) {
                    case 0:
//                        String id = list.get(position).getCompanyId();
//                        deleteOrder(id, position);
                        list.remove(position);
                        adapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });
        // set SwipeListener
        listview.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });

        // set MenuStateChangeListener
        listview.setOnMenuStateChangeListener(new SwipeMenuListView.OnMenuStateChangeListener() {
            @Override
            public void onMenuOpen(int position) {
            }

            @Override
            public void onMenuClose(int position) {
            }
        });
    }

    private void deleteOrder(String id, final int position) {
        showLoad("删除中...");
        RequestParams params = new RequestParams();
        params.put("userId", MyApplication.getInstance().getStringValue("userId"));
        params.put("companyId", id);
        Log.i("tag", Constants.BASE_URL + "delMyJob&" + params);
        client.post(Constants.BASE_URL + "delMyJob", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, String s) {
                super.onSuccess(i, s);
                dismiss();
                Gson gson = new Gson();
                if (s.contains("isSuccessful")
                        && s.contains("state")) {
                    JsonBean bean = gson.fromJson(s, JsonBean.class);
                    int state = bean.getState();
                    int isSuccessful = bean.getIsSuccessful();
                    if (0 == state && 0 == isSuccessful) {
                        list.remove(position);
                        adapter.notifyDataSetChanged();
                    } else {
                        showToast("请求失败");
                    }
                }
            }
        });
    }
}
