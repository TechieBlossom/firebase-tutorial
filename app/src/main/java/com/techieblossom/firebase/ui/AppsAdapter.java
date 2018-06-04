package com.techieblossom.firebase.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techieblossom.firebase.R;
import com.techieblossom.firebase.model.ShoppingAppModel;

import java.util.List;

class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.AppViewHolder> {

    private List<ShoppingAppModel> data;
    private Context context;

    AppsAdapter(Context context, List<ShoppingAppModel> appsList) {
        this.context = context;
        data = appsList;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.line_item_app, parent, false);
        return new AppViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class AppViewHolder extends RecyclerView.ViewHolder {

        private TextView appName, appDescription;

        AppViewHolder(View itemView) {
            super(itemView);
            appName = itemView.findViewById(R.id.appName);
            appDescription = itemView.findViewById(R.id.appDescription);
        }

        void bind(ShoppingAppModel appModel) {
            appName.setText(appModel.getName());
            appDescription.setText(appModel.getDescription());
        }
    }
}
