package sinia.com.baihangeducation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.bean.MyChuangYeBean;
import sinia.com.baihangeducation.utils.BitmapUtilsHelp;
import sinia.com.baihangeducation.utils.ViewHolder;

/**
 * Created by 忧郁的眼神 on 2016/7/21.
 */
public class RewardAdapter extends BaseAdapter {

    private Context mContext;

    private List<MyChuangYeBean.ItemsEntity> list;

    public RewardAdapter(Context mContext, List<MyChuangYeBean.ItemsEntity> list) {
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
                    R.layout.item_reward, null);
        }
        ImageView img = ViewHolder.get(convertView, R.id.img);
        TextView tv_name = ViewHolder.get(convertView, R.id.tv_name);
        TextView tv_money = ViewHolder.get(convertView, R.id.tv_money);
        TextView tv_status = ViewHolder.get(convertView, R.id.tv_status);

        BitmapUtilsHelp.getImage(mContext).display(img, list.get(position).getImageUrl());
        tv_name.setText(list.get(position).getFundName());
        tv_money.setText("起投金额：" + list.get(position).getPrice());
        if ("3".equals(list.get(position).getState())) {
            tv_status.setText("已领取");
            tv_status.setBackgroundResource(R.drawable.btn_notbuy);
        } else if ("4".equals(list.get(position).getState())) {
            tv_status.setText("未领取");
            tv_status.setBackgroundResource(R.drawable.btn_buy);
        }
        return convertView;
    }
}
