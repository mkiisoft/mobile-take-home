package com.guestlogix.marianozorrilla.data.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.guestlogix.marianozorrilla.data.listener.Complete;
import com.guestlogix.marianozorrilla.data.model.character.CharacterItem;
import com.guestlogix.marianozorrilla.data.model.episode.EpisodeItem;

import java.util.List;

public class DatabaseAccess {

    private AppDao dao;

    public DatabaseAccess(Context context) {
        AppDatabase appDatabase = AppDatabase.getDatabase(context);
        dao = appDatabase.appDao();
    }

    @SuppressLint("StaticFieldLeak")
    public void insertEpisodes(List<EpisodeItem> episodes, Complete<List<EpisodeItem>> listener) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                dao.insertEpisodes(episodes);
                return null;
            }

            @Override
            protected void onPreExecute() {
                listener.finish(episodes);
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void getEpisodes(Complete<List<EpisodeItem>> listener) {
        new AsyncTask<Void, Void, List<EpisodeItem>>() {

            @Override
            protected List<EpisodeItem> doInBackground(Void... voids) {
                return dao.getEpisodes();
            }

            @Override
            protected void onPostExecute(List<EpisodeItem> episodes) {
                listener.finish(episodes);
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void getEpisode(int id, Complete<EpisodeItem> listener) {
        new AsyncTask<Void, Void, EpisodeItem>() {

            @Override
            protected EpisodeItem doInBackground(Void... voids) {
                return dao.getEpisode(id);
            }

            @Override
            protected void onPostExecute(EpisodeItem episodeItem) {
                listener.finish(episodeItem);
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void insertCharacters(List<CharacterItem> characters,
                                 Complete<List<CharacterItem>> listener) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                dao.insertCharacters(characters);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                listener.finish(characters);
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void getCharacters(List<Integer> characters, Complete<List<CharacterItem>> listener) {
        new AsyncTask<Void, Void, List<CharacterItem>>() {

            @Override
            protected List<CharacterItem> doInBackground(Void... voids) {
                return dao.getCharacters(characters);
            }

            @Override
            protected void onPostExecute(List<CharacterItem> result) {
                listener.finish(result);
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void getCharacters(Complete<List<CharacterItem>> listener) {
        new AsyncTask<Void, Void, List<CharacterItem>>() {

            @Override
            protected List<CharacterItem> doInBackground(Void... voids) {
                return dao.getCharacters();
            }

            @Override
            protected void onPostExecute(List<CharacterItem> result) {
                listener.finish(result);
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void getCharacter(int id, Complete<CharacterItem> listener) {
        new AsyncTask<Void, Void, CharacterItem>() {

            @Override
            protected CharacterItem doInBackground(Void... voids) {
                return dao.getCharacter(id);
            }

            @Override
            protected void onPostExecute(CharacterItem result) {
                listener.finish(result);
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void updateCharacter(CharacterItem item, Complete<CharacterItem> listener) {
        new AsyncTask<Void, Void, CharacterItem>() {

            @Override
            protected CharacterItem doInBackground(Void... voids) {
                dao.updateCharacter(item);
                return item;
            }

            @Override
            protected void onPostExecute(CharacterItem result) {
                listener.finish(result);
            }
        }.execute();
    }
}
