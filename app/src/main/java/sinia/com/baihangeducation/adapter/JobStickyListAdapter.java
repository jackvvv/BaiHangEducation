package sinia.com.baihangeducation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.bean.JobBean;
import sinia.com.baihangeducation.utils.ViewHolder;

/**
 * Created by 忧郁的眼神 on 2016/7/19.
 */
public class JobStickyListAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<JobBean.ItemsEntity> list;

    public JobStickyListAdapter(Context context, List<JobBean.ItemsEntity> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.header_job, null);
        }
        TextView tv_header = ViewHolder.get(convertView, R.id.tv_header);
        tv_header.setText(list.get(position).getPositionType());
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return position;
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_job, null);
        }
        TextView tv_job = ViewHolder.get(convertView, R.id.tv_job);
        CheckBox check_box = ViewHolder.get(convertView, R.id.check_box);
        for (int i = 0; i < list.get(position).getItems2().size(); i++) {
            tv_job.setText(list.get(position).getItems2().get(i).getPositionSmall());
        }
        return convertView;
    }
}
