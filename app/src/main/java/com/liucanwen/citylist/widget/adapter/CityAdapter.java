package com.liucanwen.citylist.widget.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.liucanwen.citylist.widget.widget.ContactItemInterface;
import com.liucanwen.citylist.widget.widget.ContactListAdapter;

import java.util.List;

import sinia.com.baihangeducation.R;

public class CityAdapter extends ContactListAdapter {

	public CityAdapter(Context _context, int _resource,
			List<ContactItemInterface> _items) {
		super(_context, _resource, _items);
	}

	public void populateDataForRow(View parentView, ContactItemInterface item,
			int position) {
		View infoView = parentView.findViewById(R.id.infoRowContainer);
		TextView nicknameView = (TextView) infoView.findViewById(R.id.cityName);

		nicknameView.setText(item.getDisplayInfo());
	}

}
