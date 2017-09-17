package deepmehtait.com.movieteasers.utils;

import java.util.HashMap;

/**
 * Created by deepmetha on 9/16/17.
 */

public class genreHashMap {
    public static String getGenereValueFromHashMap (int id) {
        HashMap<Integer, String> generMap = new HashMap<Integer,String>();
        generMap.put(28, "Action");
        generMap.put(12, "Adventure");
        generMap.put(16, "Animation");
        generMap.put(35, "Comedy");
        generMap.put(80, "Crime");
        generMap.put(99, "Documentary");
        generMap.put(18, "Drama");
        generMap.put(10751, "Family");
        generMap.put(14, "Fantasy");
        generMap.put(36, "History");
        generMap.put(27, "Horror");
        generMap.put(10402, "Music");
        generMap.put(9648, "Mystery");
        generMap.put(10749, "Romance");
        generMap.put(878, "Science Fiction");
        generMap.put(10770, "TV Movie");
        generMap.put(53, "Thriller");
        generMap.put(10752, "War");
        generMap.put(37, "Western");
        return generMap.get(new Integer(id));
    }
}
