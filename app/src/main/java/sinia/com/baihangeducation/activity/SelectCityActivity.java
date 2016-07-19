package sinia.com.baihangeducation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.liucanwen.citylist.widget.adapter.CityAdapter;
import com.liucanwen.citylist.widget.data.CityData;
import com.liucanwen.citylist.widget.widget.ContactItemInterface;
import com.liucanwen.citylist.widget.widget.ContactListViewImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.base.BaseActivity;
import sinia.com.baihangeducation.utils.ActivityManager;

/**
 * Created by 忧郁的眼神 on 2016/7/18.
 */
public class SelectCityActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.listview)
    ContactListViewImpl listview;
    private TextView NJ, SH, SZ, HZ, BJ, SHENZHEN, GZ, CQ, WH;
    private LayoutInflater inflater;
    private View headView;
    private List<ContactItemInterface> contactList;
    private CityAdapter cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city, "工作地点");
        getDoingView().setVisibility(View.GONE);
        initView();
    }

    private void initView() {
        inflater = LayoutInflater.from(this);
        contactList = CityData.getSampleContactList();
        listview = (ContactListViewImpl) findViewById(R.id.listview);
        headView = inflater.inflate(R.layout.select_city_head_view, null);
        NJ = (TextView) headView.findViewById(R.id.nanjing);
        SH = (TextView) headView.findViewById(R.id.shanghai);
        SZ = (TextView) headView.findViewById(R.id.suzhou);
        HZ = (TextView) headView.findViewById(R.id.hangzhou);
        BJ = (TextView) headView.findViewById(R.id.beijing);
        SHENZHEN = (TextView) headView.findViewById(R.id.shenzhen);
        GZ = (TextView) headView.findViewById(R.id.guangzhou);
        CQ = (TextView) headView.findViewById(R.id.chongqing);
        WH = (TextView) headView.findViewById(R.id.wuhan);
        cityAdapter = new CityAdapter(this, R.layout.city_item, contactList);
        listview.addHeaderView(headView);
        listview.setFastScrollEnabled(true);
        listview.setAdapter(cityAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position,
                                    long id) {
                if (id < 0) {
                    return;
                }
                if (position != 0) {
                    Intent intent = new Intent();
                    intent.putExtra("Select_City", contactList.get(position - 1)
                            .getDisplayInfo());
                    setResult(RESULT_OK, intent);
                    ActivityManager.getInstance().finishCurrentActivity();
                }
            }
        });
        setClickListener();
    }

    private void setClickListener() {
        NJ.setOnClickListener(this);
        SH.setOnClickListener(this);
        SZ.setOnClickListener(this);
        HZ.setOnClickListener(this);
        BJ.setOnClickListener(this);
        SHENZHEN.setOnClickListener(this);
        GZ.setOnClickListener(this);
        CQ.setOnClickListener(this);
        WH.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nanjing: {
                Intent intent = new Intent();
                intent.putExtra("Select_City", "南京");
                setResult(RESULT_OK, intent);
                ActivityManager.getInstance().finishCurrentActivity();
            }
            break;
            case R.id.shanghai: {
                Intent intent = new Intent();
                intent.putExtra("Select_City", "上海");
                setResult(RESULT_OK, intent);
                ActivityManager.getInstance().finishCurrentActivity();
            }
            break;
            case R.id.suzhou: {
                Intent intent = new Intent();
                intent.putExtra("Select_City", "苏州");
                setResult(RESULT_OK, intent);
                ActivityManager.getInstance().finishCurrentActivity();
            }
            break;
            case R.id.hangzhou: {
                Intent intent = new Intent();
                intent.putExtra("Select_City", "杭州");
                setResult(RESULT_OK, intent);
                ActivityManager.getInstance().finishCurrentActivity();
            }
            break;
            case R.id.beijing: {
                Intent intent = new Intent();
                intent.putExtra("Select_City", "北京");
                setResult(RESULT_OK, intent);
                ActivityManager.getInstance().finishCurrentActivity();
            }
            break;
            case R.id.shenzhen: {
                Intent intent = new Intent();
                intent.putExtra("Select_City", "深圳");
                setResult(RESULT_OK, intent);
                ActivityManager.getInstance().finishCurrentActivity();
            }
            break;
            case R.id.guangzhou: {
                Intent intent = new Intent();
                intent.putExtra("Select_City", "广州");
                setResult(RESULT_OK, intent);
                ActivityManager.getInstance().finishCurrentActivity();
            }
            break;
            case R.id.chongqing: {
                Intent intent = new Intent();
                intent.putExtra("Select_City", "重庆");
                setResult(RESULT_OK, intent);
                ActivityManager.getInstance().finishCurrentActivity();
            }
            break;
            case R.id.wuhan: {
                Intent intent = new Intent();
                intent.putExtra("Select_City", "武汉");
                setResult(RESULT_OK, intent);
                ActivityManager.getInstance().finishCurrentActivity();
            }
            break;
        }
    }
}
