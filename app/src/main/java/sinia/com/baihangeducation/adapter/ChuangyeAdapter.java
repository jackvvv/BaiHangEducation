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
import sinia.com.baihangeducation.bean.FundBean;
import sinia.com.baihangeducation.utils.BitmapUtilsHelp;
import sinia.com.baihangeducation.utils.ViewHolder;

/**
 * Created by 忧郁的眼神 on 2016/7/20.
 */
public class ChuangyeAdapter extends BaseAdapter {

    private Context context;

    private List<FundBean.ItemsEntity> list;

    public ChuangyeAdapter(Context context, List<FundBean.ItemsEntity> list) {
        this.context = context;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_chuangye, null);
        }
        TextView tv_title = ViewHolder.get(convertView, R.id.tv_title);
        TextView tv_num = ViewHolder.get(convertView, R.id.tv_num);
        TextView tv_money = ViewHolder.get(convertView, R.id.tv_money);
        TextView tv_toubao = ViewHolder.get(convertView, R.id.tv_toubao);
        ImageView img = ViewHolder.get(convertView, R.id.img);

        tv_title.setText(list.get(position).getFundName());
        tv_num.setText("已投保人数:" + list.get(position).getNumber());
        tv_money.setText("起保金额:" + list.get(position).getPrice() + "元");
        BitmapUtilsHelp.getImage(context).display(img, list.get(position).getFundIamge());
        return convertView;
    }
}
