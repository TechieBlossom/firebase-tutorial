package com.techieblossom.firebase.database.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techieblossom.firebase.R;
import com.techieblossom.firebase.database.model.PlayerModel;

import java.util.List;

class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.AppViewHolder> {

    private List<PlayerModel> data;
    private Context context;

    PlayerAdapter(Context context, List<PlayerModel> appsList) {
        this.context = context;
        data = appsList;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.line_item_player, parent, false);
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

        private TextView playerName, playerDescription;

        AppViewHolder(View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.playerName);
            playerDescription = itemView.findViewById(R.id.playerDescription);
        }

        void bind(PlayerModel playerModel) {
            playerName.setText(playerModel.getName());
            playerDescription.setText(playerModel.getCountry());
        }
    }
}
