package com.eajy.materialdesigndemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

import com.eajy.materialdesigndemo.R;
import com.eajy.materialdesigndemo.view.BottomNavigationPageTransformer;
import com.eajy.materialdesigndemo.view.BottomNavigationViewHelper;
import com.eajy.materialdesigndemo.view.FixedScroller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BottomNavigationActivity extends AppCompatActivity
{

    private ViewPager viewPager;
    private BottomNavigationView navigation;
    private List<View> viewList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.argb(33, 0, 0, 0));

        initView();
    }

    private void initView()
    {
        View view1 = getLayoutInflater().inflate(R.layout.item_view_pager_1, null);
        View view2 = getLayoutInflater().inflate(R.layout.item_view_pager_2, null);
        View view3 = getLayoutInflater().inflate(R.layout.item_view_pager_3, null);
        View view4 = getLayoutInflater().inflate(R.layout.item_view_pager_4, null);

        viewList = new ArrayList<>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);

        viewPager = findViewById(R.id.view_pager_bottom_navigation);
        changViewpagerTime();
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(pageChangeListener);
        viewPager.setPageTransformer(true, new BottomNavigationPageTransformer());

        navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // If BottomNavigationView has more than 3 items, using reflection to disable shift mode
        BottomNavigationViewHelper.disableShiftMode(navigation);
    }

    /**
     * 改变viewpager自动播放的滑动时间为500ms 默认是250ms
     */
    private void changViewpagerTime()
    {
        try
        {
            Field mScroller;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            Interpolator sInterpolator = new AccelerateDecelerateInterpolator();
            FixedScroller scroller = new FixedScroller(viewPager.getContext(), sInterpolator);
            mScroller.set(viewPager, scroller);
        } catch (NoSuchFieldException e)
        {
        } catch (IllegalArgumentException e)
        {
        } catch (IllegalAccessException e)
        {
        }
    }

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener()
    {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
        {

        }

        @Override
        public void onPageSelected(int position)
        {
            switch (position)
            {
                case 0:
                    navigation.setSelectedItemId(R.id.bottom_navigation_blue);
                    break;
                case 1:
                    navigation.setSelectedItemId(R.id.bottom_navigation_green);
                    break;
                case 2:
                    navigation.setSelectedItemId(R.id.bottom_navigation_yellow);
                    break;
                case 3:
                    navigation.setSelectedItemId(R.id.bottom_navigation_red);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state)
        {

        }
    };

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener()
    {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.bottom_navigation_blue:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.bottom_navigation_green:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.bottom_navigation_yellow:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.bottom_navigation_red:
                    viewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };

    private PagerAdapter pagerAdapter = new PagerAdapter()
    {
        @Override
        public int getCount()
        {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object)
        {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {
            container.removeView(viewList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }
    };

}

