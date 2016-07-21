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
public class BuyedAdapter extends BaseAdapter {

    private Context mContext;

    public BuyedAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return 3;
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
                    R.layout.item_buyed, null);
        }
        ImageView img = ViewHolder.get(convertView, R.id.img);
        TextView tv_name = ViewHolder.get(convertView, R.id.tv_name);
        TextView tv_content = ViewHolder.get(convertView, R.id.tv_content);
        TextView tv_num = ViewHolder.get(convertView, R.id.tv_num);
        TextView tv_time = ViewHolder.get(convertView, R.id.tv_time);
        TextView tv_total = ViewHolder.get(convertView, R.id.tv_total);
        TextView tv_money = ViewHolder.get(convertView, R.id.tv_money);
        TextView tv_buy = ViewHolder.get(convertView, R.id.tv_buy);
        return convertView;
    }
}
