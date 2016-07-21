package sinia.com.baihangeducation.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.adapter.MyJobAdapter;
import sinia.com.baihangeducation.base.BaseActivity;
import sinia.com.baihangeducation.utils.AppInfoUtil;
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

    private MyJobAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myjob, "我的工作");
        getDoingView().setVisibility(View.GONE);

        initData();
    }

    private void initData() {
        adapter = new MyJobAdapter(this);
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
}
