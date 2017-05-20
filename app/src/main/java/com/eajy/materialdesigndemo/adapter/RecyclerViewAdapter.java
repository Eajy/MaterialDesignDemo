package com.eajy.materialdesigndemo.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.eajy.materialdesigndemo.R;
import com.eajy.materialdesigndemo.activity.ShareViewActivity;
import com.eajy.materialdesigndemo.interf.onMoveAndSwipedListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by zhang on 2016.08.07.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> implements onMoveAndSwipedListener {

    private Context context;
    private List mItems;
    private int color = 0;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
        mItems = new ArrayList();
        mItems.addAll(Arrays.asList(context.getResources().getStringArray(R.array.recycler_name_array)));
    }

    public void setItems(int color) {
        this.color = color;
        notifyDataSetChanged();
    }

    public void addItem(int position) {
        mItems.add(position);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        holder.position = position;

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_recycler_item_show);
        holder.mView.startAnimation(animation);

        AlphaAnimation aa1 = new AlphaAnimation(1.0f, 0.1f);
        aa1.setDuration(400);
        holder.rela_round.startAnimation(aa1);

        AlphaAnimation aa = new AlphaAnimation(0.1f, 1.0f);
        aa.setDuration(400);

        if (color == 1) {
            holder.rela_round.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.google_blue)));
        } else if (color == 2) {
            holder.rela_round.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.google_green)));
        } else if (color == 3) {
            holder.rela_round.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.google_yellow)));
        } else if (color == 4) {
            holder.rela_round.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.google_red)));
        } else {
            holder.rela_round.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.gray)));
        }

        holder.rela_round.startAnimation(aa);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShareViewActivity.class);
                intent.putExtra("color", color);
                context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation
                        ((Activity) context, holder.rela_round, "shareView").toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mItems, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }


    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private View mView;
        private int position;
        private RelativeLayout rela_round;

        private RecyclerViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            rela_round = (RelativeLayout) itemView.findViewById(R.id.rela_round);
        }
    }

}
