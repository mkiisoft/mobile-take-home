package com.guestlogix.marianozorrilla.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.guestlogix.marianozorrilla.data.model.character.CharacterItem;
import com.guestlogix.marianozorrilla.data.model.episode.EpisodeItem;

import java.util.List;

@Dao
public abstract class AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insertEpisodes(List<EpisodeItem> episodes);

    @Query("SELECT * FROM episodes")
    public abstract List<EpisodeItem> getEpisodes();

    @Query("SELECT * FROM episodes WHERE id = (:id)")
    public abstract EpisodeItem getEpisode(int id);

    @Update
    abstract void updateCharacter(CharacterItem character);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insertCharacters(List<CharacterItem> characters);

    @Query("SELECT * FROM characters")
    public abstract List<CharacterItem> getCharacters();

    @Query("SELECT * FROM characters WHERE id IN (:characters)")
    public abstract List<CharacterItem> getCharacters(List<Integer> characters);

    @Query("SELECT * FROM characters WHERE id = (:id)")
    public abstract CharacterItem getCharacter(int id);
}