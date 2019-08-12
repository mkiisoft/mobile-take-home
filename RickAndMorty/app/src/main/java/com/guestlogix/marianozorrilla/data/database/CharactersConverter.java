package com.guestlogix.marianozorrilla.data.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class CharactersConverter {

    @TypeConverter
    public static List<Integer> stringToIntegerList(String json) {
        if (json == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Integer>>() {}.getType();

        return new Gson().fromJson(json, listType);
    }

    @TypeConverter
    public static String integerListToString(List<Integer> list) {
        return new Gson().toJson(list);
    }
}
