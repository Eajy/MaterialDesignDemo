package com.eajy.materialdesigndemo.view;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by ANTVR-24 on 17/04/06.
 */

public class BottomNavigationPageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View view, float position) {

        if (position < 0) {
//            view.setPivotX(view.getMeasuredWidth());
//            view.setPivotY(view.getMeasuredHeight() * 0.5f);
//            view.setScaleY(1 + (float) (position * 0.2));
//            view.setRotationY(20f * position);
            view.setAlpha(position + 1);

        } else if (position < 1) {
            view.setTranslationX(view.getMeasuredWidth() * -position);
//            view.setPivotX(0);
//            view.setPivotY(view.getMeasuredHeight() * 0.5f);
//            view.setRotationY(20f * position);
//            view.setAlpha(1 - position);
        }

    }
}
