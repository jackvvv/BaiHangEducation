package sinia.com.baihangeducation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import sinia.com.baihangeducation.R;

/**
 * Created by 忧郁的眼神 on 2016/7/18.
 */
public class InternFragment extends Fragment {
    private View rootView, v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_intern, null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

}
