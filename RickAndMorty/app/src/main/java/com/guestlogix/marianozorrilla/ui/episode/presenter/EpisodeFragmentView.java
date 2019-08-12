package com.guestlogix.marianozorrilla.ui.episode.presenter;

import com.guestlogix.marianozorrilla.data.model.character.CharacterItem;
import com.guestlogix.marianozorrilla.data.model.episode.EpisodeItem;
import com.guestlogix.marianozorrilla.ui.presenter.FragmentView;

import java.util.List;

public interface EpisodeFragmentView extends FragmentView {
    void setCharactersListContent(List<CharacterItem> characters);
    void setEpisodeInfo(EpisodeItem episodeItem);
    void handleError(Exception exception);
    void loading();
}
