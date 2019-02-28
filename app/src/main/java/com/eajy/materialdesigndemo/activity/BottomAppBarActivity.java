package com.eajy.materialdesigndemo.activity;

import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.chip.Chip;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.eajy.materialdesigndemo.R;
import com.eajy.materialdesigndemo.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class BottomAppBarActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private FloatingActionButton fab;
    private RecyclerViewAdapter adapter;
    private List<String> data;
    private BottomAppBar bottomAppBar;
    private TextView bottomAppBarTitle;
    private Chip positionChip;
    private Chip radiusChip;
    private Chip marginChip;
    private Chip showTitleChip;

    boolean isFabAlignRight = false;
    boolean isCutoutMarginZero = false;
    boolean isCutoutRadiusZero = false;
    boolean showBottomBarTitle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_app_bar);

        initData();
        initViews();
    }

    private void initData() {
        data = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            data.add("List item row: " + i);
        }
    }

    private void initViews() {
        fab = findViewById(R.id.fab_bottom_appbar);
        mRecyclerView = findViewById(R.id.recycler_view_bottom_appbar);
        bottomAppBar = findViewById(R.id.bottom_App_bar);
        positionChip = findViewById(R.id.position_chip);
        radiusChip = findViewById(R.id.radius_chip);
        marginChip = findViewById(R.id.margin_chip);
        showTitleChip = findViewById(R.id.show_title_chip);

        bottomAppBarTitle = findViewById(R.id.bottom_app_bar_title);
        setSupportActionBar(bottomAppBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (getScreenWidthDp() >= 1200) {
            final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            mRecyclerView.setLayoutManager(gridLayoutManager);
        } else if (getScreenWidthDp() >= 800) {
            final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            mRecyclerView.setLayoutManager(gridLayoutManager);
        } else {
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(linearLayoutManager);
        }

        adapter = new RecyclerViewAdapter(this);
        mRecyclerView.setAdapter(adapter);
        adapter.setItems(data);

        setupUiClicks();
    }

    private void setupUiClicks() {
        positionChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isFabAlignRight = b;
                resetBottomAppBar();
            }
        });
        radiusChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isCutoutRadiusZero = b;
                resetBottomAppBar();
            }
        });
        marginChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isCutoutMarginZero = b;
                resetBottomAppBar();
            }
        });
        showTitleChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                showBottomBarTitle = b;
                resetBottomAppBar();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.addItem(0, "1");
                mRecyclerView.smoothScrollToPosition(0);
            }
        });

    }

    private void resetBottomAppBar() {
        bottomAppBar.setFabAlignmentMode(isFabAlignRight ? BottomAppBar.FAB_ALIGNMENT_MODE_END : BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
        bottomAppBar.setFabCradleMargin(isCutoutMarginZero ? 0 : 17f); //initial default value 17f
        bottomAppBar.setFabCradleRoundedCornerRadius(isCutoutRadiusZero ? 0 : 28f); //initial default value 28f

        bottomAppBarTitle.setVisibility(showBottomBarTitle ? View.VISIBLE : View.GONE); //By Default its not suggested to add title but this is just a method if required.
        if (showBottomBarTitle)
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);

        bottomAppBar.invalidate();
    }

    private int getScreenWidthDp() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return (int) (displayMetrics.widthPixels / displayMetrics.density);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.popup_menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

