package com.techieblossom.firebase.ui;

import com.techieblossom.firebase.model.ShoppingAppModel;

import java.util.List;

public interface DataChangeListener {

    void onDataLoaded(List<ShoppingAppModel> apps);
}
