package com.guestlogix.marianozorrilla.data.model.character;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "characters")
public class CharacterItem {

    @PrimaryKey
    @ColumnInfo(name = "id")
    int id;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "status")
    String status;

    @ColumnInfo(name = "species")
    String species;

    @ColumnInfo(name = "type")
    String type;

    @ColumnInfo(name = "gender")
    String gender;

    @ColumnInfo(name = "origin")
    String origin;

    @ColumnInfo(name = "location")
    String location;

    @ColumnInfo(name = "image")
    String image;

    @ColumnInfo(name = "episodes")
    List<Integer> episodes = new ArrayList<>();

    @ColumnInfo(name = "url")
    String url;

    @ColumnInfo(name = "created")
    String created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Integer> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Integer> episodes) {
        this.episodes = episodes;
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
}
