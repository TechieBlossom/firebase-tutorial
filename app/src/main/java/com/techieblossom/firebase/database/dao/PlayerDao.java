package com.techieblossom.firebase.database.dao;

import com.techieblossom.firebase.database.model.PlayerModel;
import com.techieblossom.firebase.database.ui.DataChangeListener;

import java.util.List;

public interface PlayerDao {

    List<PlayerModel> fetchAllPlayers(DataChangeListener listener);
}