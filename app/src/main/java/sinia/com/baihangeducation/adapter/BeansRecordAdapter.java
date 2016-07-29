package sinia.com.baihangeducation.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.bean.MyDouBean;
import sinia.com.baihangeducation.utils.ViewHolder;

/**
 * Created by 忧郁的眼神 on 2016/7/21.
 */
public class BeansRecordAdapter extends BaseAdapter {

    private Context mContext;
    private List<MyDouBean.Beans> list;

    public BeansRecordAdapter(Context mContext, List<MyDouBean.Beans> list) {
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
                    R.layout.item_beans_record, null);
        }
        TextView tv_title = ViewHolder.get(convertView, R.id.tv_title);
        TextView tv_time = ViewHolder.get(convertView, R.id.tv_time);
        TextView tv_remain = ViewHolder.get(convertView, R.id.tv_remain);
        TextView tv_range = ViewHolder.get(convertView, R.id.tv_range);

        if ("1".endsWith(list.get(position).getChangeType())) {
            tv_title.setText("第三方充值");
            tv_range.setText("+" + list.get(position).getMoney());
            tv_range.setTextColor(mContext.getResources().getColor(R.color.themeColor));
        } else if ("2".endsWith(list.get(position).getChangeType())) {
            tv_title.setText("账户支付");
            tv_range.setText("-" + list.get(position).getMoney());
            tv_range.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        } else if ("3".endsWith(list.get(position).getChangeType())) {
            tv_title.setText("创业豆提现");
        }
        tv_time.setText(list.get(position).getCreateTime());
        tv_remain.setText("余豆：" + list.get(position).getDou());
        return convertView;
    }
}
