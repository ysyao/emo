package com.phl.emoproject.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phl.emoproject.widget.BaseListAdapter;

import org.apache.http.NameValuePair;
import org.w3c.dom.Text;

import java.util.List;


public class SpinnerAdapter extends BaseListAdapter<NameValuePair> {
    public SpinnerAdapter(Context context, List<NameValuePair> items) {
        super(context, items);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = getInflater().inflate(android.R.layout.simple_spinner_item, viewGroup, false);
        TextView tv =(TextView) view.findViewById(android.R.id.text1);
        tv.setText(getItem(i).getName());
        return tv;
    }
}
