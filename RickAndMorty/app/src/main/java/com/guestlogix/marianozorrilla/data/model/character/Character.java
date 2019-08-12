package com.guestlogix.marianozorrilla.data.model.character;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Character {

    private CharacterInfo info;
    private List<CharacterItem> results;

    public CharacterInfo getInfo() {
        return info;
    }

    private void setInfo(CharacterInfo info) {
        this.info = info;
    }

    public List<CharacterItem> getResults() {
        return results;
    }

    private void setResults(List<CharacterItem> results) {
        this.results = results;
    }

    public static Character parseJson(String response) throws JSONException {
        Character character = new Character();

        JSONObject base = new JSONObject(response);
        JSONObject info = base.getJSONObject("info");
        character.setInfo(new CharacterInfo(info.getInt("count"), info.getInt("pages"),
                info.getString("next"), info.getString("prev")));
        JSONArray results = base.getJSONArray("results");

        List<CharacterItem> items = new ArrayList<>();

        for (int i = 0; i < results.length(); i++) {
            JSONObject item = results.getJSONObject(i);
            CharacterItem characterItem = new CharacterItem();
            characterItem.setId(item.getInt("id"));
            characterItem.setName(item.getString("name"));
            characterItem.setStatus(item.getString("status"));
            characterItem.setSpecies(item.getString("species"));
            characterItem.setType(item.getString("type"));
            characterItem.setGender(item.getString("gender"));
            characterItem.setOrigin(item.getJSONObject("origin").getString("name"));
            characterItem.setLocation(item.getJSONObject("location").getString("name"));
            characterItem.setImage(item.getString("image"));
            characterItem.setUrl(item.getString("url"));
            characterItem.setCreated(item.getString("created"));

            List<Integer> episodes = new ArrayList<>();
            JSONArray charactersArray = item.getJSONArray("episode");
            for (int j = 0; j < charactersArray.length(); j++) {
                String episode = charactersArray.getString(j);
                String episodeId = episode.substring(episode.lastIndexOf("/") + 1);
                episodes.add(Integer.parseInt(episodeId));
            }
            characterItem.setEpisodes(episodes);

            items.add(characterItem);
        }
        character.setResults(items);

        return character;
    }
}
