package com.techieblossom.firebase.dao;

import com.techieblossom.firebase.model.ShoppingAppModel;
import com.techieblossom.firebase.ui.DataChangeListener;

import java.util.List;

public interface AppDao {

    List<ShoppingAppModel> fetchApplicationsInfo(DataChangeListener listener);
}