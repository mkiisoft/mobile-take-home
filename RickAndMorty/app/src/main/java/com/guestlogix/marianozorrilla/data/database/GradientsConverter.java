package com.guestlogix.marianozorrilla.data.database;

import androidx.room.TypeConverter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class GradientsConverter {

    @TypeConverter
    public static List<String> restoreList(String listOfString) {
        JSONArray jsonArray;
        List<String> list = new ArrayList<>();
        try {
            jsonArray = new JSONArray(listOfString);
            for (int i = 0; i < jsonArray.length(); i++) {
                list.add(jsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    @TypeConverter
    public static String saveList(List<String> listOfString) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray("[]");
            for (String item : listOfString) {
                jsonArray.put(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray != null ? jsonArray.toString() : "[]";
    }
}
