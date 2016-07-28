package sinia.com.baihangeducation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.bean.JobBean;
import sinia.com.baihangeducation.myinterface.UpdateChoosedJob;
import sinia.com.baihangeducation.utils.ViewHolder;

/**
 * Created by 忧郁的眼神 on 2016/7/28.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private List<JobBean.ItemsEntity> list;
    private Context context;
    private LayoutInflater inflater;
    private static HashMap<Integer, Boolean> isSelected = new HashMap<Integer, Boolean>();
    private HashMap<String, String> jobs = new HashMap<String, String>();
    private List<String> chooseJobList = new ArrayList<>();

    public ExpandableListAdapter(Context context, List<JobBean.ItemsEntity> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        ExpandableListAdapter.isSelected = isSelected;
    }

    public HashMap<String, String> getJobs() {
        return jobs;
    }

    public void setJobs(HashMap<String, String> jobs) {
        this.jobs = jobs;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getItems2().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getItems2().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.header_job, null);
        }
        TextView tv_header = ViewHolder.get(convertView, R.id.tv_header);
        tv_header.setText(list.get(groupPosition).getPositionType());
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_job, null);
        }
        TextView tv_job = ViewHolder.get(convertView, R.id.tv_job);
        final CheckBox check_box = ViewHolder.get(convertView, R.id.check_box);
        tv_job.setText(list.get(groupPosition).getItems2().get(childPosition).getPositionSmall());
        check_box.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                UpdateChoosedJob updateChoosedJob = (UpdateChoosedJob)context;
                if (getIsSelected().get(childPosition)) {
                    getIsSelected().put(childPosition, false);
                    check_box.setChecked(false);
                    if (jobs.containsKey(list.get(groupPosition).getItems2().get(childPosition).getPositionSmall())) {
                        jobs.remove(list.get(groupPosition).getItems2().get(childPosition).getPositionSmall());
                    }
                    if(chooseJobList.contains(list.get(groupPosition).getItems2().get(childPosition).getPositionSmall())){
                        chooseJobList.remove(list.get(groupPosition).getItems2().get(childPosition).getPositionSmall());
                    }
                    updateChoosedJob.updatechoosedJobList(chooseJobList);
                } else {
                    if (jobs.size() < 3) {
                        check_box.setChecked(true);
                        getIsSelected().put(childPosition, true);
                        chooseJobList.add(list.get(groupPosition).getItems2().get(childPosition).getPositionSmall());
                        jobs.put(list.get(groupPosition).getItems2().get(childPosition).getPositionSmall(), list.get(groupPosition).getItems2().get(childPosition).getPositionSmall());
                        updateChoosedJob.updatechoosedJobList(chooseJobList);
                    } else {
                        Toast.makeText(context, "最多选择3个职位", Toast.LENGTH_SHORT).show();
                    }

                }
                if(check_box.isChecked()){
                    check_box.setChecked(false);
                    if(chooseJobList.contains(list.get(groupPosition).getItems2().get(childPosition).getPositionSmall())){
                        chooseJobList.remove(list.get(groupPosition).getItems2().get(childPosition).getPositionSmall());
                        updateChoosedJob.updatechoosedJobList(chooseJobList);
                    }
                }else{
                    if (jobs.size() < 3) {
                        check_box.setChecked(true);
                        chooseJobList.add(list.get(groupPosition).getItems2().get(childPosition).getPositionSmall());
                        updateChoosedJob.updatechoosedJobList(chooseJobList);
                    } else {
                        Toast.makeText(context, "最多选择3个职位", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return convertView;
    }

    /**
     * 当选择子节点的时候，调用该方法
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
