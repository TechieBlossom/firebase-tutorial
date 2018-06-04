package com.techieblossom.firebase.ui;

import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.techieblossom.firebase.R;
import com.techieblossom.firebase.dao.AppDao;
import com.techieblossom.firebase.dao.impl.AppDaoImpl;
import com.techieblossom.firebase.model.ShoppingAppModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DataChangeListener {

    private RecyclerView appsView;
    private ContentLoadingProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appsView = findViewById(R.id.apps);
        progressBar = findViewById(R.id.contentLoadingProgressBar);
        progressBar.show();
        AppDao appDaoImpl = new AppDaoImpl();
        appDaoImpl.fetchApplicationsInfo(this);
    }

    @Override
    public void onDataLoaded(List<ShoppingAppModel> apps) {
        AppsAdapter adapter = new AppsAdapter(this, apps);
        appsView.setLayoutManager(new LinearLayoutManager(this));
        appsView.setAdapter(adapter);
        appsView.setVisibility(View.VISIBLE);
        progressBar.hide();
    }
}
