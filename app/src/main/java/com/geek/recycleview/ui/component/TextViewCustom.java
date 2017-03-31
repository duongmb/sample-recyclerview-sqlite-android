package com.geek.recycleview.ui.component;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;

/**
 * Created by PK on 3/30/2017.
 */

public class TextViewCustom extends android.support.v7.widget.AppCompatTextView {
    public TextViewCustom(Context context) {
        super(context);
    }

    public TextViewCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextViewCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        TableRowCustom.LayoutParams tvparams = new TableRowCustom.LayoutParams(0, TableRowCustom
                .LayoutParams.WRAP_CONTENT);
        tvparams.weight = (float) 0.3;
        this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        this.setTypeface(null, Typeface.BOLD);
        this.setGravity(Gravity.LEFT);
        super.setLayoutParams(tvparams);
    }
}
