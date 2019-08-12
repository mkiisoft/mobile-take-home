package com.guestlogix.marianozorrilla.data.model.episode;

import com.guestlogix.marianozorrilla.util.Gradients;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Episode {

    private EpisodeInfo info;
    private List<EpisodeItem> results;

    public EpisodeInfo getInfo() {
        return info;
    }

    private void setInfo(EpisodeInfo info) {
        this.info = info;
    }

    public List<EpisodeItem> getResults() {
        return results;
    }

    private void setResults(List<EpisodeItem> results) {
        this.results = results;
    }

    public static Episode parseJson(String response) throws JSONException {
        Episode episode = new Episode();

        JSONObject base = new JSONObject(response);
        JSONObject info = base.getJSONObject("info");
        episode.setInfo(new EpisodeInfo(info.getInt("count"), info.getInt("pages"),
                info.getString("next"), info.getString("prev")));
        JSONArray results = base.getJSONArray("results");

        List<EpisodeItem> items = new ArrayList<>();

        for (int i = 0; i < results.length(); i++) {
            JSONObject item = results.getJSONObject(i);
            EpisodeItem episodeItem = new EpisodeItem();
            episodeItem.setId(item.getInt("id"));
            episodeItem.setName(item.getString("name"));
            episodeItem.setAirDate(item.getString("air_date"));
            episodeItem.setEpisode(item.getString("episode"));
            episodeItem.setUrl(item.getString("url"));
            episodeItem.setCreated(item.getString("created"));
            episodeItem.setGradient(Gradients.getGradient());

            List<Integer> characters = new ArrayList<>();
            JSONArray charactersArray = item.getJSONArray("characters");
            for (int j = 0; j < charactersArray.length(); j++) {
                String character = charactersArray.getString(j);
                String characterId = character.substring(character.lastIndexOf("/") + 1);
                characters.add(Integer.parseInt(characterId));
            }
            episodeItem.setCharacters(characters);
            items.add(episodeItem);
        }
        episode.setResults(items);

        return episode;
    }
}