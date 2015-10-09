package com.phl.emoproject.widget.bottom_bar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


public class BottomTextViewGroup extends LinearLayout implements OnTextViewSelectedChanged,View.OnClickListener{
    private OnBottomTextViewSelectedInterface onBottomTextViewSelectedInterface;
    private  List<SelectableBottomTextView> textViews = new ArrayList<>();
    public BottomTextViewGroup(Context context) {
        super(context);
        init();
    }

    public BottomTextViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setOnBottomTextViewSelectedInterface(OnBottomTextViewSelectedInterface onBottomTextViewSelectedInterface) {
        this.onBottomTextViewSelectedInterface = onBottomTextViewSelectedInterface;
    }

    public void performClick(int position) {
        View v = getChildAt(position);
        if (v instanceof SelectableBottomTextView) {
            SelectableBottomTextView selectableBottomTextView = (SelectableBottomTextView) v;
            selectableBottomTextView.onTextViewSelected();
        }
    }

    private void init() {
        for(int i=0;i<getChildCount();i++) {
            View v = getChildAt(i);
            if(v instanceof SelectableBottomTextView) {
                SelectableBottomTextView selectableBottomTextView = (SelectableBottomTextView) v;
                selectableBottomTextView.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onTextViewSelectedChanged(BottomTextViewGroup group, int selectedId) {
        for(int i=0;i<getChildCount();i++) {
            View v = getChildAt(i);
            if(v instanceof SelectableBottomTextView) {
                SelectableBottomTextView selectableBottomTextView = (SelectableBottomTextView) v;
                if(selectableBottomTextView.getId() != selectedId) {
                    selectableBottomTextView.setTextViewSelected(false);
                } else {
                    selectableBottomTextView.setTextViewSelected(true);
                    if (onBottomTextViewSelectedInterface != null) {
                        onBottomTextViewSelectedInterface.onBottomTextViewSelectedInterface(v.getId(), i, selectableBottomTextView);
                    }
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        SelectableBottomTextView v = (SelectableBottomTextView)view;
        v.setTextViewSelected(true);
    }
}