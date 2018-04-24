package com.gameball.school;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public class MMSpacesItemDecoration extends ItemDecoration {
    private int space;

    public MMSpacesItemDecoration(int space) {
        this.space = space;
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        outRect.left = this.space;
        outRect.right = this.space;
        outRect.bottom = this.space;
        outRect.top = this.space;
    }
}
