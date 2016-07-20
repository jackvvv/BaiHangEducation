package sinia.com.baihangeducation.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.activity.JobTypeActivity;
import sinia.com.baihangeducation.activity.PersonalInfoActivity;
import sinia.com.baihangeducation.activity.SelectCityActivity;

/**
 * Created by 忧郁的眼神 on 2016/7/18.
 */
public class FullTimeFragment extends Fragment {
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
        startActivity(intent);
    }

    @OnClick(R.id.rl_type)
    void rl_type() {
        Intent intent = new Intent(getActivity(),
                JobTypeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            if (requestCode == 1) {
                tv_place.setText(data.getStringExtra("Select_City"));
            }
        }
    }

}
