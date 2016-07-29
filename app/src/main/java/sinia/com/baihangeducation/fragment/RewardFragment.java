package sinia.com.baihangeducation.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.adapter.BuyedAdapter;
import sinia.com.baihangeducation.adapter.RewardAdapter;
import sinia.com.baihangeducation.base.BaseFragment;
import sinia.com.baihangeducation.bean.JsonBean;
import sinia.com.baihangeducation.bean.MyChuangYeBean;
import sinia.com.baihangeducation.utils.AppInfoUtil;
import sinia.com.baihangeducation.utils.Constants;
import sinia.com.baihangeducation.utils.MyApplication;
import sinia.com.baihangeducation.view.swipmenulistview.SwipeMenu;
import sinia.com.baihangeducation.view.swipmenulistview.SwipeMenuCreator;
import sinia.com.baihangeducation.view.swipmenulistview.SwipeMenuItem;
import sinia.com.baihangeducation.view.swipmenulistview.SwipeMenuListView;

/**
 * Created by 忧郁的眼神 on 2016/7/18.
 */
public class RewardFragment extends BaseFragment {
    private View rootView;
    @Bind(R.id.listview)
    SwipeMenuListView listview;
    private RewardAdapter adapter;
    private AsyncHttpClient client = new AsyncHttpClient();
    private List<MyChuangYeBean.ItemsEntity> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_reward, null);
        ButterKnife.bind(this, rootView);
        getData();
        initData();
        return rootView;
    }

    private void getData() {
        showLoad("加载中...");
        RequestParams params = new RequestParams();
        params.put("userId", MyApplication.getInstance().getStringValue("userId"));
        params.put("fundStatus", "2");
        Log.i("tag", Constants.BASE_URL + "mystBussinessList&" + params);
        client.post(Constants.BASE_URL + "mystBussinessList", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, String s) {
                super.onSuccess(i, s);
                dismiss();
                Gson gson = new Gson();
                if (s.contains("isSuccessful")
                        && s.contains("state")) {
                    MyChuangYeBean bean = gson.fromJson(s, MyChuangYeBean.class);
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
        adapter = new RewardAdapter(getActivity(), list);
        listview.setAdapter(adapter);
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xFF,
                        0x42, 0x41)));
                // set item width
                deleteItem.setWidth(AppInfoUtil.dip2px(getActivity(), 90));
                deleteItem.setTitle("删除");
                deleteItem.setTitleSize(16);
                deleteItem.setTitleColor(Color.WHITE);
                // set a icon
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        listview.setMenuCreator(creator);
        listview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP)
                    listview.getParent().requestDisallowInterceptTouchEvent(false);
                else
                    listview.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        listview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        String id = list.get(position).getOrderId();
                        deleteOrder(id, position);
                        break;
                }
                return false;
            }

        });
    }

    private void deleteOrder(String id, final int position) {
        showLoad("删除中...");
        RequestParams params = new RequestParams();
        params.put("userId", MyApplication.getInstance().getStringValue("userId"));
        params.put("orderId", id);
        params.put("fundStatus", "2");
        Log.i("tag", Constants.BASE_URL + "delmystBussinessList&" + params);
        client.post(Constants.BASE_URL + "delmystBussinessList", params, new AsyncHttpResponseHandler() {
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
