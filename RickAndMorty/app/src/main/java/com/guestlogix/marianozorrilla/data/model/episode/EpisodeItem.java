package com.guestlogix.marianozorrilla.data.model.episode;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.guestlogix.marianozorrilla.data.database.CharactersConverter;
import com.guestlogix.marianozorrilla.data.database.GradientsConverter;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "episodes")
public class EpisodeItem {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "air_date")
    private String airDate;

    @ColumnInfo(name = "episode")
    private String episode;

    @ColumnInfo(name = "characters")
    private List<Integer> characters = new ArrayList<>();

    @ColumnInfo(name = "url")
    private String url;

    @ColumnInfo(name = "created")
    private String created;

    @ColumnInfo(name = "gradient")
    private List<String> gradient = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public List<Integer> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Integer> characters) {
        this.characters = characters;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public List<String> getGradient() {
        return gradient;
    }

    public void setGradient(List<String> gradient) {
        this.gradient = gradient;
    }
}
