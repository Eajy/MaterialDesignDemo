package com.eajy.materialdesigndemo.view;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * @author xiaokun
 * @date 2017/12/6
 */

public class FixedScroller extends Scroller
{
    private int mDuration = 500;

    public FixedScroller(Context context)
    {
        super(context);
    }

    public FixedScroller(Context context, Interpolator interpolator)
    {
        super(context, interpolator);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration)
    {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy)
    {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }
}
