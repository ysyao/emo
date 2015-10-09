package com.phl.emoproject.widget.bottom_bar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.widget.TextView;

import com.phl.emoproject.R;


public class SelectableBottomTextView extends TextView implements View.OnClickListener{
    private boolean selectedDefault = false;
    private int selectedColor;
    private int unselectedColor;
    private int unselectedBackgroundColor;
    private int selectedBackgroundColor;
    private Drawable selectedIcon;
    private Drawable unselectedIcon;
    private OnSelectableTextViewClickedListener listener;

    public SelectableBottomTextView(Context context) {
        super(context);
    }

    public SelectableBottomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.SelectableView);

        selectedColor = a.getColor(R.styleable.SelectableView_text_selected_color, getResources().getColor(R.color.lightblue));
        unselectedColor = a.getColor(R.styleable.SelectableView_text_unselected_color, getResources().getColor(R.color.black));
        selectedDefault = a.getBoolean(R.styleable.SelectableView_default_selected, selectedDefault);
        selectedIcon = a.getDrawable(R.styleable.SelectableView_selected_icon);
        unselectedIcon = a.getDrawable(R.styleable.SelectableView_unselected_icon);
        selectedBackgroundColor = a.getColor(R.styleable.SelectableView_background_selected_color, getResources().getColor(R.color.lightblue));
        unselectedBackgroundColor = a.getColor(R.styleable.SelectableView_background_unselected_color, getResources().getColor(R.color.black));
        setTextViewSelected(selectedDefault);
        setOnClickListener(this);
        a.recycle();
    }

    public void setTextViewSelected(boolean isSelected) {
        this.selectedDefault = isSelected;
        setTextViewSelected();
    }

    public void setOnTextViewClickedListener( OnSelectableTextViewClickedListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        setTextViewSelected(true);
        onTextViewSelected();
        if(listener != null) {
            listener.onTextViewClicked(view);
        }
    }

    public void onTextViewSelected() {
        ViewParent parent = getParent();
        if(parent instanceof BottomTextViewGroup) {
            BottomTextViewGroup group = (BottomTextViewGroup)parent;
            group.onTextViewSelectedChanged(group, getId());
        }
    }

    private void setTextViewSelected() {
        if(selectedDefault) {
            setTextColor(selectedColor);
            setCompoundDrawablesWithIntrinsicBounds(null, selectedIcon, null, null);
            setBackgroundColor(selectedBackgroundColor);
        } else {
            setTextColor(unselectedColor);
            setCompoundDrawablesWithIntrinsicBounds(null, unselectedIcon, null, null);
            setBackgroundColor(unselectedBackgroundColor);
        }
    }
}