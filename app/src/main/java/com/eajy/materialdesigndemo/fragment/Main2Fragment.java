package com.eajy.materialdesigndemo.fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.eajy.materialdesigndemo.R;

import java.util.Calendar;

/**
 * Created by zhang on 2016.08.07.
 */
public class Main2Fragment extends Fragment implements View.OnClickListener {

    private Button btn_dialog_1, btn_dialog_2, btn_dialog_3, btn_dialog_4,
            btn_dialog_5, btn_dialog_6, btn_dialog_7, btn_dialog_8, btn_dialog_9;
    Calendar calendar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NestedScrollView nestedScrollView = (NestedScrollView) inflater.inflate(R.layout.fragment_main_2, container, false);

        btn_dialog_1 = (Button) nestedScrollView.findViewById(R.id.btn_dialog_1);
        btn_dialog_2 = (Button) nestedScrollView.findViewById(R.id.btn_dialog_2);
        btn_dialog_3 = (Button) nestedScrollView.findViewById(R.id.btn_dialog_3);
        btn_dialog_4 = (Button) nestedScrollView.findViewById(R.id.btn_dialog_4);
        btn_dialog_5 = (Button) nestedScrollView.findViewById(R.id.btn_dialog_5);
        btn_dialog_6 = (Button) nestedScrollView.findViewById(R.id.btn_dialog_6);
        btn_dialog_7 = (Button) nestedScrollView.findViewById(R.id.btn_dialog_7);
        btn_dialog_8 = (Button) nestedScrollView.findViewById(R.id.btn_dialog_8);
        btn_dialog_9 = (Button) nestedScrollView.findViewById(R.id.btn_dialog_9);

        // ViewGroup viewGroup = (ViewGroup) mRecyclerView.getParent();
        // if (viewGroup != null) { viewGroup.removeAllViews(); }

        return nestedScrollView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        calendar = Calendar.getInstance();

        btn_dialog_1.setOnClickListener(this);
        btn_dialog_2.setOnClickListener(this);
        btn_dialog_3.setOnClickListener(this);
        btn_dialog_4.setOnClickListener(this);
        btn_dialog_5.setOnClickListener(this);
        btn_dialog_6.setOnClickListener(this);
        btn_dialog_7.setOnClickListener(this);
        btn_dialog_8.setOnClickListener(this);
        btn_dialog_9.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_dialog_1:
                new AlertDialog.Builder(getContext())
                        .setMessage("A simple dialog")
                        .setPositiveButton("OK", null)
                        .show();
                break;

            case R.id.btn_dialog_2:
                new AlertDialog.Builder(getContext())
                        .setTitle("A simple dialog")
                        .setMessage("A material metaphor is the unifying theory of a rationalized space and a system of motion. " +
                                "The material is grounded in tactile reality, inspired by the study of paper and ink, " +
                                "yet technologically advanced and open to imagination and magic.")
                        .setPositiveButton("OK", null)
                        .setNegativeButton("CANCEL", null)
                        .show();
                break;

            case R.id.btn_dialog_3:
                String[] singleChoiceItems = new String[]{"New York", "Los Angeles", "San Francisco", "Paris", "London"};
                int itemSelected = 0;
                new AlertDialog.Builder(getContext())
                        .setTitle("Single choice dialog")
                        .setSingleChoiceItems(singleChoiceItems, itemSelected, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton("CANCEL", null)
                        .show();
                break;

            case R.id.btn_dialog_4:
                String[] multiChoiceItems = new String[]{"New York", "Los Angeles", "San Francisco", "Paris", "London"};
                boolean[] checkedItems = {true, false, false, false, false};
                new AlertDialog.Builder(getContext())
                        .setTitle("Multi choice dialog")
                        .setMultiChoiceItems(multiChoiceItems, checkedItems, null)
                        .setPositiveButton("OK", null)
                        .setNegativeButton("CANCEL", null)
                        .show();
                break;

            case R.id.btn_dialog_5:
                ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Progress dialog...");
                progressDialog.show();
                break;

            case R.id.btn_dialog_6:
                final ProgressDialog horizontalProgressDialog = new ProgressDialog(getContext());
                horizontalProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                horizontalProgressDialog.setMessage("Progress dialog...");
                horizontalProgressDialog.setCancelable(false);
                horizontalProgressDialog.setMax(100);
                horizontalProgressDialog.show();

                new Thread(new Runnable() {
                    int progress = 0;

                    @Override
                    public void run() {
                        while (progress <= 100) {
                            horizontalProgressDialog.setProgress(progress);
                            if (progress == 100) {
                                horizontalProgressDialog.dismiss();
                            }
                            try {
                                Thread.sleep(35);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            progress++;
                        }
                    }
                }).start();
                break;

            case R.id.btn_dialog_7:
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        btn_dialog_7.setText(date);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.DAY_OF_MONTH);
                datePickerDialog.show();
                break;

            case R.id.btn_dialog_8:
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        calendar.set(Calendar.HOUR_OF_DAY, i);
                        calendar.set(Calendar.MINUTE, i1);
                        btn_dialog_8.setText(i + ":" + i1);
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                timePickerDialog.show();
                break;

            case R.id.btn_dialog_9:
                PopupMenu popupMenu = new PopupMenu(getContext(), btn_dialog_9);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu_main, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return false;
                    }
                });
                popupMenu.show();
                break;

        }
    }
}
