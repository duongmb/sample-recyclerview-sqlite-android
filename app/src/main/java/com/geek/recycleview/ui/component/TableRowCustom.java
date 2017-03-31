package com.geek.recycleview.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TableRow;

/**
 * Created by PK on 3/30/2017.
 */

public class TableRowCustom extends TableRow {
    public TableRowCustom(Context context) {
        super(context);
    }

    public TableRowCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        layoutParams.weight = (float) 1.0;
        layoutParams.setMargins(0, 15, 0, 15);
        super.setLayoutParams(layoutParams);
    }
}
