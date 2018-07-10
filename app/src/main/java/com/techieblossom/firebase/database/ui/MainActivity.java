package com.techieblossom.firebase.database.ui;

import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.techieblossom.firebase.R;
import com.techieblossom.firebase.database.dao.PlayerDao;
import com.techieblossom.firebase.database.dao.impl.PlayerDaoImpl;
import com.techieblossom.firebase.database.model.PlayerModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DataChangeListener {

    private RecyclerView playersView;
    private ContentLoadingProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playersView = findViewById(R.id.players);
        progressBar = findViewById(R.id.contentLoadingProgressBar);
        progressBar.show();
        PlayerDao playerDaoImpl = new PlayerDaoImpl();
        playerDaoImpl.fetchAllPlayers(this);
    }

    @Override
    public void onDataLoaded(List<PlayerModel> apps) {
        PlayerAdapter adapter = new PlayerAdapter(this, apps);
        playersView.setLayoutManager(new LinearLayoutManager(this));
        playersView.setAdapter(adapter);
        playersView.setVisibility(View.VISIBLE);
        progressBar.hide();
    }
}
