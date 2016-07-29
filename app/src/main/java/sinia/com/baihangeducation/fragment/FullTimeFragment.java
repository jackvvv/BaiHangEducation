package sinia.com.baihangeducation.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.activity.JobTypeActivity;
import sinia.com.baihangeducation.activity.PersonalInfoActivity;
import sinia.com.baihangeducation.activity.SelectCityActivity;
import sinia.com.baihangeducation.base.BaseFragment;
import sinia.com.baihangeducation.bean.JobBean;
import sinia.com.baihangeducation.bean.JsonBean;
import sinia.com.baihangeducation.utils.Constants;
import sinia.com.baihangeducation.utils.MyApplication;
import sinia.com.baihangeducation.utils.StringUtil;

/**
 * Created by 忧郁的眼神 on 2016/7/18.
 */
public class FullTimeFragment extends BaseFragment {
    private View rootView;
    @Bind(R.id.rl_type)
    RelativeLayout rl_type;
    @Bind(R.id.rl_workplace)
    RelativeLayout rl_workplace;
    @Bind(R.id.rl_info)
    RelativeLayout rl_info;
    @Bind(R.id.tv_type)
    TextView tv_type;
    @Bind(R.id.tv_place)
    TextView tv_place;
    @Bind(R.id.tv_info)
    TextView tv_info;
    @Bind(R.id.tv_ok)
    TextView tv_ok;

    private List<String> jobList = new ArrayList<>();
    private String jobIds, workCity;
    private AsyncHttpClient client = new AsyncHttpClient();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_fulltime, null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.rl_workplace)
    void rl_workplace() {
        Intent intent = new Intent(getActivity(),
                SelectCityActivity.class);
        startActivityForResult(intent, 1);
    }

    @OnClick(R.id.rl_info)
    void rl_info() {
        Intent intent = new Intent(getActivity(),
                PersonalInfoActivity.class);
        intent.putExtra("workType", "1");
        startActivity(intent);
    }

    @OnClick(R.id.rl_type)
    void rl_type() {
        Intent intent = new Intent(getActivity(),
                JobTypeActivity.class);
        intent.putExtra("workType", "2");
        startActivityForResult(intent, 2);
    }

    @OnClick(R.id.tv_ok)
    void tv_ok() {
        if (StringUtil.isEmpty(jobIds)) {
            showToast("请选择职业类别");
        } else if (StringUtil.isEmpty(workCity)) {
            showToast("请选择意向工作地点");
        } else if (StringUtil.isEmpty(MyApplication.getInstance().getStringValue("resumeId"))) {
            showToast("请选择完善个人简历");
        } else {
            publicResume();
        }
    }

    private void publicResume() {
        showLoad("加载中...");
        RequestParams params = new RequestParams();
        params.put("userId", MyApplication.getInstance().getStringValue("userId"));
        params.put("workType", "1");
        params.put("jobName", jobIds);
        params.put("intentionalCity", workCity);
        params.put("resumeId", MyApplication.getInstance().getStringValue("resumeId"));
        Log.i("tag", Constants.BASE_URL + "stuSendDemand&" + params);
        client.post(Constants.BASE_URL + "stuSendDemand", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, String s) {
                super.onSuccess(i, s);
                dismiss();
                Gson gson = new Gson();
                if (s.contains("isSuccessful")
                        && s.contains("state")) {
                    JsonBean bean = gson.fromJson(s, JsonBean.class);
                    int state = bean.getState();
                    int isSuccessful = bean.getIsSuccessful();
                    if (0 == state && 0 == isSuccessful) {
                        showToast("发布求职信息成功");
                    } else {
                        showToast("发布求职信息失败");
                    }
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            if (requestCode == 1) {
                workCity = data.getStringExtra("Select_City");
                tv_place.setText(data.getStringExtra("Select_City"));
            }
            if (requestCode == 2) {
                jobList = data.getStringArrayListExtra("joblist");
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < jobList.size(); i++) {
                    sb.append("'").append(jobList.get(i)).append("'").append(",");
                }
                jobIds = sb.toString().substring(0, sb.toString().length() - 1);

                StringBuffer sb1 = new StringBuffer();
                for (int i = 0; i < jobList.size(); i++) {
                    sb1.append(jobList.get(i)).append("、");
                }
                tv_type.setText(sb1.toString().substring(0, sb1.toString().length() - 1));
            }
        }
    }

}
