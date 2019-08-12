package com.guestlogix.marianozorrilla.ui.character;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.guestlogix.marianozorrilla.R;
import com.guestlogix.marianozorrilla.data.database.DatabaseAccess;
import com.guestlogix.marianozorrilla.data.model.character.CharacterItem;
import com.guestlogix.marianozorrilla.ui.character.presenter.CharacterFragmentPresenter;
import com.guestlogix.marianozorrilla.ui.character.presenter.CharacterFragmentView;
import com.guestlogix.marianozorrilla.ui.presenter.MvpFragment;

import java.util.Objects;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.guestlogix.marianozorrilla.util.Constants.EXTRA_ID;

public class CharacterFragment extends MvpFragment<CharacterFragmentPresenter> implements CharacterFragmentView {

    private int itemId;

    private ImageView avatar;
    private TextView name;
    private TextView species;
    private TextView gender;
    private TextView type;
    private TextView origin;
    private TextView episodes;
    private TextView killOrNot;
    private CardView killOrNotHolder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemId = Objects.requireNonNull(Objects.requireNonNull(getActivity())
                .getIntent().getExtras()).getInt(EXTRA_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character, container, false);
        initToolbar(view);
        setupViews(view);
        return view;
    }

    private void initToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.character_toolbar);
        AppCompatActivity activity = ((AppCompatActivity) getActivity());
        Objects.requireNonNull(activity).setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.arrow_back, null));
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
    }


    private void setupViews(View view) {
        avatar = view.findViewById(R.id.character_avatar);
        name = view.findViewById(R.id.character_name);
        species = view.findViewById(R.id.species);
        gender = view.findViewById(R.id.gender);
        type = view.findViewById(R.id.type);
        origin = view.findViewById(R.id.origin);
        episodes = view.findViewById(R.id.episodes);
        killOrNot = view.findViewById(R.id.kill_or_not);
        killOrNotHolder = view.findViewById(R.id.kill_or_not_holder);

        CharacterFragmentPresenter presenter = new CharacterFragmentPresenter(this, view.getContext());
        setPresenter(presenter);
        presenter.init(itemId);
    }

    @Override
    public void setCharacterContent(CharacterItem character) {
        name.setText(character.getName());
        species.setText(String.format(getString(R.string.species), character.getSpecies()));
        gender.setText(String.format(getString(R.string.gender), character.getGender()));
        type.setText(String.format(getString(R.string.type), character.getType()));
        type.setVisibility(character.getType().isEmpty() ? GONE : VISIBLE);
        origin.setText(String.format(getString(R.string.origin), character.getOrigin(),
                character.getLocation()));
        StringBuilder episodeList = new StringBuilder();
        for (Integer episode : character.getEpisodes()) {
            episodeList.append("- ").append(episode.toString()).append("\n");
        }
        episodes.setText(String.format(getString(R.string.episodes), episodeList));
        Glide.with(Objects.requireNonNull(getContext())).load(character.getImage()).into(avatar);

        boolean dead = character.getStatus().toLowerCase().equals("dead");
        killOrNot.setText(dead ? "ALREADY RIP" : "RIP ME");
        killOrNotHolder.setEnabled(!dead);
        killOrNotHolder.setAlpha(dead ? 0.5f : 1f);
        killOrNotHolder.setOnClickListener(view -> {
            character.setStatus("Dead");
            new DatabaseAccess(getActivity()).updateCharacter(character, result -> {
                Objects.requireNonNull(getActivity()).finish();
            });
        });
    }

    @Override
    public void handleError(Exception exception) {

    }

    @Override
    public void loading() {

    }
}
