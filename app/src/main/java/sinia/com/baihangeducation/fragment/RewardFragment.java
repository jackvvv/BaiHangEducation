package sinia.com.baihangeducation.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.adapter.BuyedAdapter;
import sinia.com.baihangeducation.adapter.RewardAdapter;
import sinia.com.baihangeducation.utils.AppInfoUtil;
import sinia.com.baihangeducation.view.swipmenulistview.SwipeMenu;
import sinia.com.baihangeducation.view.swipmenulistview.SwipeMenuCreator;
import sinia.com.baihangeducation.view.swipmenulistview.SwipeMenuItem;
import sinia.com.baihangeducation.view.swipmenulistview.SwipeMenuListView;

/**
 * Created by 忧郁的眼神 on 2016/7/18.
 */
public class RewardFragment extends Fragment {
    private View rootView;
    @Bind(R.id.listview)
    SwipeMenuListView listview;
    private RewardAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_reward, null);
        ButterKnife.bind(this, rootView);
        initData();
        return rootView;
    }

    private void initData() {
        adapter = new RewardAdapter(getActivity());
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
    }

}
