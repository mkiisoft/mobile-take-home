package com.guestlogix.marianozorrilla.ui.home.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.common.primitives.Ints;
import com.guestlogix.marianozorrilla.R;
import com.guestlogix.marianozorrilla.data.listener.ItemListener;
import com.guestlogix.marianozorrilla.data.model.episode.EpisodeItem;

import java.util.ArrayList;
import java.util.List;

class EpisodeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ItemListener<EpisodeItem> listener;
    private EpisodeItem item;

    EpisodeViewHolder(@NonNull View itemView, ItemListener<EpisodeItem> listener) {
        super(itemView);
        this.listener = listener;
        itemView.setOnClickListener(this);
    }

    void bind(EpisodeItem episode) {
        item = episode;

        List<Integer> gradients = new ArrayList<>();

        if (episode.getGradient() != null) {
            for (String value : episode.getGradient()) {
                gradients.add(Color.parseColor(value));
            }

            View background = itemView.findViewById(R.id.background);
            GradientDrawable gradient = new GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    Ints.toArray(gradients));
            gradient.setCornerRadius(0f);
            background.setBackground(gradient);
        }

        TextView name = itemView.findViewById(R.id.name);
        name.setText(String.format("Episode: %1$s", episode.getName()));

        TextView episodeText = itemView.findViewById(R.id.episode);
        episodeText.setText(episode.getEpisode());

        TextView airDate = itemView.findViewById(R.id.air_date);
        airDate.setText(episode.getAirDate());
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.click(item);
        }
    }
}
