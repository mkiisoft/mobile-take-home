package com.guestlogix.marianozorrilla.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.guestlogix.marianozorrilla.data.model.character.CharacterItem;
import com.guestlogix.marianozorrilla.data.model.episode.EpisodeItem;

@Database(entities = {EpisodeItem.class, CharacterItem.class}, version = 4, exportSchema = false)
@TypeConverters({CharactersConverter.class, GradientsConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract AppDao appDao();

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "rick_and_morty_database.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
