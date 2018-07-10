package com.techieblossom.firebase.database.dao.impl;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.techieblossom.firebase.database.dao.PlayerDao;
import com.techieblossom.firebase.database.model.PlayerModel;
import com.techieblossom.firebase.database.ui.DataChangeListener;

import java.util.List;

public class PlayerDaoImpl implements PlayerDao {

    private static final String TAG = PlayerDaoImpl.class.getName();
    private List<PlayerModel> players;
    private DataChangeListener mListener;

    @Override
    public List<PlayerModel> fetchAllPlayers(DataChangeListener listener) {
        mListener = listener;
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference appDatabaseReference =
                firebaseDatabase.getReference().child("players");

        final Query fetchAppInfoQuery = appDatabaseReference.orderByKey();
        fetchAppInfoQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<PlayerModel>> t =
                        new GenericTypeIndicator<List<PlayerModel>>() {};
                players = dataSnapshot.getValue(t);
                mListener.onDataLoaded(players);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, databaseError.getMessage());
            }
        });
        return players;
    }
}
