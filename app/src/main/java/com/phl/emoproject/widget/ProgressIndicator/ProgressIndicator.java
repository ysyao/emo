package com.phl.emoproject.widget.ProgressIndicator;


import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.phl.emoproject.R;

public class ProgressIndicator extends ImageView {
    public ProgressIndicator(Context context) {
        this(context, null);
    }

    public ProgressIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        setImageDrawable(getContext().getResources().getDrawable(R.drawable.anim_loading));
        AnimationDrawable animationDrawable = (AnimationDrawable) getDrawable();
        animationDrawable.start();
    }

}