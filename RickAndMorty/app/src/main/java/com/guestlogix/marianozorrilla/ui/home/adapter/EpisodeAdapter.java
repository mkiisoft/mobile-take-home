package com.guestlogix.marianozorrilla.ui.home.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guestlogix.marianozorrilla.R;
import com.guestlogix.marianozorrilla.data.listener.ItemListener;
import com.guestlogix.marianozorrilla.data.model.episode.EpisodeItem;

import java.util.ArrayList;
import java.util.List;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeViewHolder> {

    private List<EpisodeItem> episodes;
    private ItemListener<EpisodeItem> listener;

    public EpisodeAdapter(ItemListener<EpisodeItem> listener) {
        this.episodes = new ArrayList<>();
        this.listener = listener;
    }

    public void setEpisodes(List<EpisodeItem> episodes) {
        this.episodes = episodes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EpisodeViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.episode_item, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeViewHolder holder, int position) {
        holder.bind(episodes.get(position));
    }

    @Override
    public int getItemCount() {
        return episodes.size();
    }
}
