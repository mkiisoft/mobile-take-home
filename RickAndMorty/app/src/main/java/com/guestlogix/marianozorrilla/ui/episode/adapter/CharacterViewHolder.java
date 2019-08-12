package com.guestlogix.marianozorrilla.ui.episode.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.guestlogix.marianozorrilla.R;
import com.guestlogix.marianozorrilla.data.listener.ItemListener;
import com.guestlogix.marianozorrilla.data.model.character.CharacterItem;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

class CharacterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ItemListener<CharacterItem> listener;
    private CharacterItem item;

    CharacterViewHolder(@NonNull View itemView, ItemListener<CharacterItem> listener) {
        super(itemView);
        this.listener = listener;
        itemView.setOnClickListener(this);
    }

    void bind(CharacterItem character) {
        item = character;

        ImageView avatar = itemView.findViewById(R.id.avatar);
        Glide.with(itemView.getContext()).load(character.getImage()).into(avatar);

        RelativeLayout death = itemView.findViewById(R.id.death);
        death.setVisibility(character.getStatus().toLowerCase().contains("dead") ? VISIBLE :
                GONE);
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.click(item);
        }
    }
}
