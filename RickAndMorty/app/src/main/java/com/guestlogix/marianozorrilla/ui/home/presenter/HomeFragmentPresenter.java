package com.guestlogix.marianozorrilla.ui.home.presenter;

import android.content.Context;

import com.guestlogix.marianozorrilla.R;
import com.guestlogix.marianozorrilla.data.client.API;
import com.guestlogix.marianozorrilla.data.client.ApiClient;
import com.guestlogix.marianozorrilla.data.database.DatabaseAccess;
import com.guestlogix.marianozorrilla.data.model.character.Character;
import com.guestlogix.marianozorrilla.data.model.episode.Episode;
import com.guestlogix.marianozorrilla.data.sharedpreferences.KeyState;
import com.guestlogix.marianozorrilla.ui.presenter.BaseFragmentPresenter;
import com.guestlogix.marianozorrilla.util.connectivity.ConnectivityManager;

import org.json.JSONException;

import java.util.ArrayList;

public class HomeFragmentPresenter extends BaseFragmentPresenter<HomeFragmentView> implements ConnectivityManager.ConnectionChangedListener {

    private ConnectivityManager connectivityManager;

    private boolean isLoading = false;

    private Context context;

    private ApiClient client;

    // GET EPISODES
    private Integer episodePageLimit;
    private int episodePage = 1;

    private Episode mainEpisode;

    // GET CHARACTERS
    private Integer charsPageLimit;
    private Integer charsPage = 1;

    private Character mainCharacter;

    // DATABASE
    private DatabaseAccess database;

    public HomeFragmentPresenter(HomeFragmentView fragmentView, Context context) {
        super(fragmentView);
        this.context = context;
        database = new DatabaseAccess(context);
    }

    public void init() {
        connectivityManager = ConnectivityManager.get(this.context);
        client = new ApiClient(context);
        getData();
    }

    @Override
    public void onResume() {
        super.onResume();
        connectivityManager.addConnectivityListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        connectivityManager.removeConnectivityListener(this);
    }

    private void getEpisodes() {
        if (fragmentView != null) {
            fragmentView.notifyUpdates(context.getString(R.string.loading_episodes));
        }
        client.initClient(API.EPISODES,
                episodePage,
                response -> {
                    try {
                        mainEpisode = Episode.parseJson(response);
                        episodePageLimit = mainEpisode.getInfo().getPages();
                        if (fragmentView != null) {
                            fragmentView.notifyUpdates(context.getString(R.string.loading_percentage_episodes) + " " + ((episodePage * 100) / episodePageLimit) + "%");

                        }
                        episodePage++;
                        if (episodePage <= episodePageLimit) {
                            loadMoreEpisodes();
                        } else {
                            database.insertEpisodes(mainEpisode.getResults(),
                                    result -> getCharacters());
                        }
                    } catch (JSONException error) {
                        if (fragmentView != null) {
                            fragmentView.handleError(error);
                        }

                    }
                },
                error -> {
                    if (fragmentView != null) {
                        fragmentView.handleError(error);
                    }
                });
    }

    private void loadMoreEpisodes() {
        if (episodePage <= episodePageLimit) {
            client.initClient(API.EPISODES, episodePage, response -> {
                try {
                    Episode episode = Episode.parseJson(response);
                    mainEpisode.getResults().addAll(episode.getResults());
                    if (fragmentView != null) {
                        fragmentView.notifyUpdates(context.getString(R.string.loading_percentage_episodes) + " " + ((episodePage * 100) / episodePageLimit) + "%");
                    }
                    episodePage++;
                    if (episodePage <= episodePageLimit) {
                        loadMoreEpisodes();
                    } else {
                        database.insertEpisodes(mainEpisode.getResults(),
                                result -> getCharacters());
                    }
                } catch (JSONException error) {
                    if (fragmentView != null) {
                        fragmentView.handleError(error);
                    }
                }
            }, error -> {
                if (fragmentView != null) {
                    fragmentView.handleError(error);
                }
            });
        } else {
            database.insertEpisodes(mainEpisode.getResults(),
                    result -> getCharacters());
        }
    }

    private void getCharacters() {
        if (fragmentView != null) {
            fragmentView.notifyUpdates(context.getString(R.string.loading_characters));
        }
        client.initClient(API.CHARACTERS, charsPage, response -> {
            try {
                mainCharacter = Character.parseJson(response);
                charsPageLimit = mainCharacter.getInfo().getPages();
                if (fragmentView != null) {
                    fragmentView.notifyUpdates(context.getString(R.string.loading_percentage_characters) + " " + ((charsPage * 100) / charsPageLimit) + "%");
                }
                charsPage++;
                if (charsPage <= charsPageLimit) {
                    loadMoreCharacters();
                } else {
                    database.insertCharacters(mainCharacter.getResults(),
                            result -> {
                                if (fragmentView != null) {
                                    KeyState.setEpisodesDone(context);
                                    KeyState.setCharactersDone(context);
                                    fragmentView.setHomeListContent(mainEpisode.getResults());
                                }
                            });
                }
            } catch (JSONException error) {
                if (fragmentView != null) {
                    fragmentView.handleError(error);
                }
            }
        }, error -> {
            if (fragmentView != null) {
                fragmentView.handleError(error);
            }
        });
    }

    private void loadMoreCharacters() {
        if (charsPage <= charsPageLimit) {
            client.initClient(API.CHARACTERS, charsPage, response -> {
                try {
                    Character character = Character.parseJson(response);
                    mainCharacter.getResults().addAll(character.getResults());
                    if (fragmentView != null) {
                        fragmentView.notifyUpdates(context.getString(R.string.loading_percentage_characters) + " " + ((charsPage * 100) / charsPageLimit) + "%");
                    }
                    charsPage++;
                    if (charsPage <= charsPageLimit) {
                        loadMoreCharacters();
                    } else {
                        database.insertCharacters(mainCharacter.getResults(),
                                result -> {
                                    if (fragmentView != null) {
                                        KeyState.setEpisodesDone(context);
                                        KeyState.setCharactersDone(context);
                                        fragmentView.setHomeListContent(mainEpisode.getResults());
                                    }
                                });
                    }
                } catch (JSONException error) {
                    if (fragmentView != null) {
                        fragmentView.handleError(error);
                    }
                }
            }, error -> {
                if (fragmentView != null) {
                    fragmentView.handleError(error);
                }
            });
        } else {
            database.insertCharacters(mainCharacter.getResults(),
                    result -> {
                        if (fragmentView != null) {
                            KeyState.setEpisodesDone(context);
                            KeyState.setCharactersDone(context);
                            fragmentView.setHomeListContent(mainEpisode.getResults());
                        }
                    });
        }
    }

    @Override
    public void onConnected() {
        getData();
    }

    @Override
    public void onDisconnected() {
        isLoading = false;
        if (fragmentView != null) {
            fragmentView.setHomeListContent(new ArrayList<>());
            fragmentView.noNetwork();
        }
    }

    public void setLoading(boolean loading) {
        episodePage = 1;
        charsPage = 1;
        isLoading = loading;
    }

    public void getData() {
        System.out.println("GET_DATA: " + isLoading);
        if (!isLoading) {
            isLoading = true;
            if (fragmentView != null) {
                fragmentView.loading();
            }
            database.getEpisodes(episodesResult -> database.getCharacters(charactersResult -> {
                if (!KeyState.episodesDone(context) && !KeyState.charactersDone(context)) {
                    getEpisodes();
                } else {
                    if (fragmentView != null) {
                        KeyState.setEpisodesDone(context);
                        KeyState.setCharactersDone(context);
                        fragmentView.setHomeListContent(episodesResult);
                    }
                }
            }));
        }

    }
}