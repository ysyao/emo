package com.phl.emoproject.widget.radio_button;

import android.view.View;

public interface ILifeRadiobuttonListener {
    void onRadioButtonClicked(View view, boolean isChecked);
    void onCheckStateChanged(boolean isChecked);
}
