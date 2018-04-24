package com.gameball.tools;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.RelativeLayout;

public class MMSquareView extends RelativeLayout {
    public MMSquareView(Context context) {
        super(context);
    }

    public MMSquareView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MMSquareView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == 1073741824 && widthSize > 0) {
            size = widthSize;
        } else if (heightMode != 1073741824 || heightSize <= 0) {
            size = widthSize < heightSize ? widthSize : heightSize;
        } else {
            size = heightSize;
        }
        int finalMeasureSpec = MeasureSpec.makeMeasureSpec(size, 1073741824);
        super.onMeasure(finalMeasureSpec, finalMeasureSpec);
    }
}
