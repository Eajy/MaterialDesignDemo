package com.eajy.materialdesigndemo.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.eajy.materialdesigndemo.interf.onMoveAndSwipedListener;

/**
 * Created by zhang on 2016.08.21.
 */
public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private onMoveAndSwipedListener moveAndSwipedListener;

    public ItemTouchHelperCallback(onMoveAndSwipedListener listener) {
        this.moveAndSwipedListener = listener;
    }

    //设置拖动方向以及侧滑方向
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            //单列的RecyclerView支持上下拖动和左右侧滑
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
            return makeMovementFlags(dragFlags, swipeFlags);
        } else {
            //多列的RecyclerView支持上下左右拖动和不支持左右侧滑
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            final int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        }
    }

    //拖动item时会调用此方法
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //如果两个item不是同一个类型的，不让他拖拽
        if (viewHolder.getItemViewType() != target.getItemViewType()) {
            return false;
        }
        moveAndSwipedListener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    //侧滑item时会调用此方法
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        moveAndSwipedListener.onItemDismiss(viewHolder.getAdapterPosition());
    }
}
