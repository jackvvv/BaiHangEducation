package sinia.com.baihangeducation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.utils.ViewHolder;

/**
 * Created by 忧郁的眼神 on 2016/7/20.
 */
public class ChoosedJobGridAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> choosedList;

    public ChoosedJobGridAdapter(Context context, ArrayList<String> choosedList) {
        this.context = context;
        this.choosedList = choosedList;
    }

    @Override
    public int getCount() {
        return choosedList.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_choosed_job, null);
        }
        TextView tv_job = ViewHolder.get(convertView, R.id.tv_job);
        tv_job.setText(choosedList.get(position));
        return convertView;
    }
}
