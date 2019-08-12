package com.guestlogix.marianozorrilla.ui.character.presenter;

import com.guestlogix.marianozorrilla.data.model.character.CharacterItem;
import com.guestlogix.marianozorrilla.data.model.episode.EpisodeItem;
import com.guestlogix.marianozorrilla.ui.presenter.FragmentView;

import java.util.List;

public interface CharacterFragmentView extends FragmentView {
    void setCharacterContent(CharacterItem character);
    void handleError(Exception exception);
    void loading();
}
