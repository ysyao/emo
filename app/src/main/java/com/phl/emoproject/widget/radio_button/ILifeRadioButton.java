package com.phl.emoproject.widget.radio_button;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.phl.emoproject.R;


public class ILifeRadioButton extends TextView implements View.OnClickListener{
    private boolean isChecked = false;
    private ILifeRadiobuttonListener iLifeRadiobuttonListener;
    private Drawable selectedDrawable;
    private Drawable unselectedDrawable;

    public ILifeRadioButton(Context context) {
        this(context, null);
    }

    public ILifeRadioButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ILifeRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.bg_ilife_radio_button_normal));
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ILifeRadioButton);
        isChecked = a.getBoolean(R.styleable.ILifeRadioButton_is_checked, isChecked);
        selectedDrawable = a.getDrawable(R.styleable.ILifeRadioButton_drawable_selected);
        unselectedDrawable = a.getDrawable(R.styleable.ILifeRadioButton_drawable_unselected);
        if (isChecked) {
            setCompoundDrawablesWithIntrinsicBounds(selectedDrawable, null, null, null);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(unselectedDrawable, null, null, null);
        }
        setMinWidth(20);
        setMinHeight(20);
        setOnClickListener(this);
        setGravity(Gravity.CENTER_VERTICAL);
        setCompoundDrawablePadding(4);
    }

    @Override
    public void onClick(View view) {
        isChecked = !isChecked;
        if (isChecked) {
            setCompoundDrawablesWithIntrinsicBounds(selectedDrawable, null, null, null);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(unselectedDrawable, null, null, null);
        }
        if (iLifeRadiobuttonListener != null) {
            iLifeRadiobuttonListener.onRadioButtonClicked(view, isChecked);
        }
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
        if (isChecked) {
            setCompoundDrawablesWithIntrinsicBounds(selectedDrawable, null, null, null);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(unselectedDrawable, null, null, null);
        }
        if (iLifeRadiobuttonListener != null) {
            iLifeRadiobuttonListener.onCheckStateChanged(isChecked);
        }
    }

    public void setiLifeRadiobuttonListener(ILifeRadiobuttonListener iLifeRadiobuttonListener) {
        this.iLifeRadiobuttonListener = iLifeRadiobuttonListener;
    }
}
