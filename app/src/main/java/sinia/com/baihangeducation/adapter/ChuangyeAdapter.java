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
 * Created by 忧郁的眼神 on 2016/7/20.
 */
public class ChuangyeAdapter extends BaseAdapter {

    private Context context;

    public ChuangyeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 8;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_chuangye, null);
        }
        TextView tv_title = ViewHolder.get(convertView, R.id.tv_title);
        TextView tv_num = ViewHolder.get(convertView, R.id.tv_num);
        TextView tv_money = ViewHolder.get(convertView, R.id.tv_money);
        TextView tv_toubao = ViewHolder.get(convertView, R.id.tv_toubao);
        ImageView img = ViewHolder.get(convertView, R.id.img);
        return convertView;
    }
}
