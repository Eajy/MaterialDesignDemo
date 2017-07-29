package com.eajy.materialdesigndemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.eajy.materialdesigndemo.R;

/**
 * Created by zhang on 2016.08.07.
 */
public class Main3Fragment extends Fragment {

    private EditText et_main_3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NestedScrollView nestedScrollView = (NestedScrollView) inflater.inflate(R.layout.fragment_main_3, container, false);
        et_main_3 = (EditText) nestedScrollView.findViewById(R.id.et_main_3);

        // ViewGroup viewGroup = (ViewGroup) mRecyclerView.getParent();
        // if (viewGroup != null) { viewGroup.removeAllViews(); }

        return nestedScrollView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        et_main_3.requestFocus();
    }

}
