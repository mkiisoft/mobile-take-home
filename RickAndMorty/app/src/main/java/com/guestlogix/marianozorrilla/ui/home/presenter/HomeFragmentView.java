package com.guestlogix.marianozorrilla.ui.home.presenter;

import com.guestlogix.marianozorrilla.data.model.episode.EpisodeItem;
import com.guestlogix.marianozorrilla.ui.presenter.FragmentView;

import java.util.List;

public interface HomeFragmentView extends FragmentView {
    void setHomeListContent(List<EpisodeItem> episodes);
    void handleError(Exception exception);
    void notifyUpdates(String update);
    void noNetwork();
    void loading();
}