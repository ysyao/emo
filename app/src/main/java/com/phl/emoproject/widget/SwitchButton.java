package com.phl.emoproject.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.phl.emoproject.R;


public class SwitchButton extends LinearLayout implements CompoundButton.OnCheckedChangeListener{
    public interface SwitchButtonListener {
        void onSwitchItemClicked(int position, View view);
    }
    RadioButton left;
    RadioButton right;
    SwitchButtonListener switchButtonListener;

    public SwitchButton(Context context) {
        this(context, null);
    }

    public SwitchButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(LinearLayout.HORIZONTAL);
        left = (RadioButton)LayoutInflater.from(getContext()).inflate(R.layout.switch_radio_button, this, false);
        left.setText("未完成");
        left.setId(R.id.left);
        left.setTextColor(getResources().getColor(R.color.white));

        right = (RadioButton)LayoutInflater.from(getContext()).inflate(R.layout.switch_radio_button, this, false);
        right.setText("已完成");
        right.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_switch_right));
        right.setId(R.id.right);
        right.setChecked(false);
        right.setTextColor(getResources().getColor(R.color.blue));
        left.setOnCheckedChangeListener(this);
        right.setOnCheckedChangeListener(this);
        addView(left);
        addView(right);
    }

    public SwitchButtonListener getSwitchButtonListener() {
        return switchButtonListener;
    }

    public void setSwitchButtonListener(SwitchButtonListener switchButtonListener) {
        this.switchButtonListener = switchButtonListener;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        int color = b ? R.color.white : R.color.blue;
        compoundButton.setTextColor(getResources().getColor(color));
       if (compoundButton.getId() == R.id.left && b) {
            right.setChecked(false);
           if (switchButtonListener != null) {
               switchButtonListener.onSwitchItemClicked(0, compoundButton);
           }
       } else if (compoundButton.getId() == R.id.right && b) {
           left.setChecked(false);
           if (switchButtonListener != null) {
               switchButtonListener.onSwitchItemClicked(1, compoundButton);
           }
       }
    }
}
