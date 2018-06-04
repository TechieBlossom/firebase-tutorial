package com.techieblossom.firebase.dao.impl;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.techieblossom.firebase.dao.AppDao;
import com.techieblossom.firebase.model.ShoppingAppModel;
import com.techieblossom.firebase.ui.DataChangeListener;

import java.util.List;

public class AppDaoImpl implements AppDao{

    private static final String TAG = AppDaoImpl.class.getName();
    private List<ShoppingAppModel> apps;
    private DataChangeListener mListener;

    @Override
    public List<ShoppingAppModel> fetchApplicationsInfo(DataChangeListener listener) {
        mListener = listener;
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference appDatabaseReference =
                firebaseDatabase.getReference().child("apps");
        final Query fetchAppInfoQuery = appDatabaseReference.orderByKey();
        fetchAppInfoQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<ShoppingAppModel>> t =
                        new GenericTypeIndicator<List<ShoppingAppModel>>() {};
                apps = dataSnapshot.getValue(t);
                mListener.onDataLoaded(apps);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, databaseError.getMessage());
            }
        });
        return apps;
    }
}
