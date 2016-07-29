package sinia.com.baihangeducation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.bean.MyChuangYeBean;
import sinia.com.baihangeducation.bean.MyJobBean;
import sinia.com.baihangeducation.utils.ViewHolder;

/**
 * Created by 忧郁的眼神 on 2016/7/21.
 */
public class MyJobAdapter extends BaseAdapter {

    private Context mContext;
    private List<MyJobBean.ItemsEntity> list;

    public MyJobAdapter(Context mContext, List<MyJobBean.ItemsEntity> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_myjob, null);
        }
        TextView tv_name = ViewHolder.get(convertView, R.id.tv_name);
        TextView tv_job = ViewHolder.get(convertView, R.id.tv_job);
        TextView tv_tel = ViewHolder.get(convertView, R.id.tv_tel);
        TextView tv_time = ViewHolder.get(convertView, R.id.tv_time);
        tv_name.setText("公司名称：" + list.get(position).getCompanyName());
        tv_job.setText("招聘职位：" + list.get(position).getPosition());
        tv_tel.setText("联系方式：" + list.get(position).getTelephone());
        tv_time.setText(list.get(position).getCreateTime());
        return convertView;
    }
}
