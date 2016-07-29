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
import sinia.com.baihangeducation.activity.PersonalActivity;
import sinia.com.baihangeducation.bean.MyChuangYeBean;
import sinia.com.baihangeducation.utils.BitmapUtilsHelp;
import sinia.com.baihangeducation.utils.ViewHolder;

/**
 * Created by 忧郁的眼神 on 2016/7/21.
 */
public class BuyedAdapter extends BaseAdapter {

    private Context mContext;

    private List<MyChuangYeBean.ItemsEntity> list;

    public BuyedAdapter(Context mContext, List<MyChuangYeBean.ItemsEntity> list) {
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

        BitmapUtilsHelp.getImage(mContext).display(img, list.get(position).getImageUrl());
        tv_name.setText(list.get(position).getFundName());
        tv_content.setText(list.get(position).getFundContent());
        tv_num.setText("×" + list.get(position).getBuyNum());
        tv_time.setText(list.get(position).getCreateTime());
        tv_money.setText("¥ " + list.get(position).getRealMoney());
        tv_total.setText("共买" + list.get(position).getBuyNum() + "份,实付：");
        if ("1".equals(list.get(position).getState())) {
            tv_buy.setText("再次购买");
            tv_buy.setBackgroundResource(R.drawable.btn_notbuy);
        } else if ("2".equals(list.get(position).getState())) {
            tv_buy.setText("去支付");
            tv_buy.setBackgroundResource(R.drawable.btn_buy);
        }
        return convertView;
    }
}
