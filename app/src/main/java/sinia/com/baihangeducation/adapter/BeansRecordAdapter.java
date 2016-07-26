package sinia.com.baihangeducation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.utils.ViewHolder;

/**
 * Created by 忧郁的眼神 on 2016/7/21.
 */
public class BeansRecordAdapter extends BaseAdapter {

    private Context mContext;

    public BeansRecordAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return 30;
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
                    R.layout.item_beans_record, null);
        }
        TextView tv_title = ViewHolder.get(convertView, R.id.tv_title);
        TextView tv_time = ViewHolder.get(convertView, R.id.tv_time);
        TextView tv_remain = ViewHolder.get(convertView, R.id.tv_remain);
        TextView tv_range = ViewHolder.get(convertView, R.id.tv_range);
        return convertView;
    }
}
