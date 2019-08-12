package com.guestlogix.marianozorrilla.ui.episode.presenter;

import android.content.Context;

import com.guestlogix.marianozorrilla.data.database.DatabaseAccess;
import com.guestlogix.marianozorrilla.ui.presenter.BaseFragmentPresenter;

public class EpisodeFragmentPresenter extends BaseFragmentPresenter<EpisodeFragmentView> {

    // DATABASE
    private DatabaseAccess database;

    public EpisodeFragmentPresenter(EpisodeFragmentView fragmentView, Context context) {
        super(fragmentView);
        database = new DatabaseAccess(context);
    }

    public void init(int id) {
        database.getEpisode(id, episodeItem -> {
            fragmentView.setEpisodeInfo(episodeItem);
            database.getCharacters(episodeItem.getCharacters(), result ->
                    fragmentView.setCharactersListContent(result));
        });
    }
}
