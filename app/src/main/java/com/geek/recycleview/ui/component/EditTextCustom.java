package com.geek.recycleview.ui.component;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;

import com.geek.recycleview.R;

/**
 * Created by PK on 3/30/2017.
 */

public class EditTextCustom extends android.support.v7.widget.AppCompatEditText {
    public EditTextCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public EditTextCustom(Context context) {
        super(context);
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        TableRowCustom.LayoutParams edtParam = new TableRowCustom.LayoutParams(0, TableRowCustom
                .LayoutParams.WRAP_CONTENT);
        edtParam.weight = (float) 0.7;
        this.setGravity(Gravity.LEFT);
        this.setBackgroundResource(R.drawable.shape);
        this.setSingleLine();
        this.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        super.setLayoutParams(edtParam);
    }
}
