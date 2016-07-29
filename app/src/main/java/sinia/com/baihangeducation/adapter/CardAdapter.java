package sinia.com.baihangeducation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.bean.CardBean;
import sinia.com.baihangeducation.utils.ViewHolder;

/**
 * Created by 忧郁的眼神 on 2016/7/29.
 */
public class CardAdapter extends BaseAdapter {
    private Context mContext;
    private List<CardBean.ItemsEntity> list;

    public CardAdapter(Context mContext, List<CardBean.ItemsEntity> list) {
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
                    R.layout.item_card, null);
        }
        TextView tv_card = ViewHolder.get(convertView, R.id.tv_card);
        tv_card.setText(list.get(position).getCertificate());
        return convertView;
    }
}
