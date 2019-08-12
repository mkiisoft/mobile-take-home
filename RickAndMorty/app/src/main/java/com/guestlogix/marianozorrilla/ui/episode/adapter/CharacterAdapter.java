package com.guestlogix.marianozorrilla.ui.episode.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guestlogix.marianozorrilla.R;
import com.guestlogix.marianozorrilla.data.listener.ItemListener;
import com.guestlogix.marianozorrilla.data.model.character.CharacterItem;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterViewHolder> {

    private List<CharacterItem> characters;
    private ItemListener<CharacterItem> listener;

    public CharacterAdapter(ItemListener<CharacterItem> listener) {
        this.characters = new ArrayList<>();
        this.listener = listener;
    }

    public void setCharacters(List<CharacterItem> characters) {
        this.characters = characters;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CharacterViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.character_item, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        holder.bind(characters.get(position));
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }
}
