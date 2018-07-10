package com.techieblossom.firebase.database.ui;

import com.techieblossom.firebase.database.model.PlayerModel;

import java.util.List;

public interface DataChangeListener {

    void onDataLoaded(List<PlayerModel> players);
}
