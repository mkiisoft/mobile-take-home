package com.guestlogix.marianozorrilla.ui.episode;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guestlogix.marianozorrilla.R;
import com.guestlogix.marianozorrilla.data.listener.ItemListener;
import com.guestlogix.marianozorrilla.data.model.character.CharacterItem;
import com.guestlogix.marianozorrilla.data.model.episode.EpisodeItem;
import com.guestlogix.marianozorrilla.ui.character.CharacterActivity;
import com.guestlogix.marianozorrilla.ui.episode.adapter.CharacterAdapter;
import com.guestlogix.marianozorrilla.ui.episode.presenter.EpisodeFragmentPresenter;
import com.guestlogix.marianozorrilla.ui.episode.presenter.EpisodeFragmentView;
import com.guestlogix.marianozorrilla.ui.presenter.MvpFragment;
import com.guestlogix.marianozorrilla.util.Constants;

import java.util.List;
import java.util.Objects;

public class EpisodeFragment extends MvpFragment<EpisodeFragmentPresenter> implements EpisodeFragmentView, ItemListener<CharacterItem>, Constants {

    private EpisodeFragmentPresenter presenter;

    private int itemId;

    private CharacterAdapter adapter;

    private TextView name;
    private TextView episode;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemId = Objects.requireNonNull(Objects.requireNonNull(getActivity())
                .getIntent().getExtras()).getInt(EXTRA_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_episode, container, false);
        setupViews(view);
        return view;
    }

    private void setupViews(View view) {
        presenter = new EpisodeFragmentPresenter(this, view.getContext());
        setPresenter(presenter);

        int itemsSpan = 2;

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            itemsSpan = 4;
        }

        name = view.findViewById(R.id.name);
        episode = view.findViewById(R.id.episode);

        view.findViewById(R.id.close).setOnClickListener(view1 ->
                Objects.requireNonNull(getActivity()).finish());

        RecyclerView recycler = view.findViewById(R.id.recycler_characters);
        recycler.setLayoutManager(new GridLayoutManager(getActivity(), itemsSpan));
        adapter = new CharacterAdapter(this);
        recycler.setAdapter(adapter);


    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.init(itemId);
    }

    @Override
    public void setCharactersListContent(List<CharacterItem> characters) {
        adapter.setCharacters(characters);
    }

    @Override
    public void setEpisodeInfo(EpisodeItem episodeItem) {
        name.setText(episodeItem.getName());
        episode.setText(episodeItem.getEpisode());
    }

    @Override
    public void handleError(Exception exception) {

    }

    @Override
    public void loading() {

    }

    @Override
    public void click(CharacterItem item) {
        Intent intent = new Intent(getActivity(), CharacterActivity.class);
        intent.putExtra(EXTRA_ID, item.getId());
        Objects.requireNonNull(getActivity()).startActivity(intent);
    }
}
