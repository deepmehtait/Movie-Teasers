package deepmehtait.com.movieteasers.modals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static deepmehtait.com.movieteasers.utils.genreHashMap.getGenereValueFromHashMap;

/**
 * Created by deepmetha on 9/16/17.
 */

public class Movie {

    boolean video;
    int id;
    int vote_count;
    long popularity;
    Double vote_average;
    String backdrop_path;
    String genre;
    String overview;
    String poster_path;
    String release_date;
    String title;

    public Movie(JSONObject jsonObject) throws JSONException {
        this.video = jsonObject.getBoolean("video");
        this.id = jsonObject.getInt("id");
        this.vote_count = jsonObject.getInt("vote_count");
        this.popularity = jsonObject.getLong("popularity");
        this.vote_average = jsonObject.getDouble("vote_average");
        this.backdrop_path = jsonObject.getString("backdrop_path");
        this.genre = parseGenerAndGetString(jsonObject.getJSONArray("genre_ids"));
        this.overview = jsonObject.getString("overview");
        this.poster_path = jsonObject.getString("poster_path");
        this.release_date = jsonObject.getString("release_date");
        this.title = jsonObject.getString("title");


    }

    public Movie(String title, String year) {
        this.title = title;
        this.release_date = year;
    }

    public static ArrayList<Movie> parseJsonArrayRespone(JSONArray jsonArray) {
        ArrayList<Movie> results = new ArrayList<Movie>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                results.add(new Movie(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    private String parseGenerAndGetString(JSONArray genre) throws JSONException {
        String gener_string = new String();
        for (int i = 0; i < genre.length(); i++) {
            int genreCode = genre.getInt(i);
            String generType = getGenereValueFromHashMap(genreCode);
            if (generType != null) {
                gener_string += " " + generType;
            }

        }
        return gener_string;
    }

    public boolean isVideo() {
        return video;
    }

    public int getId() {
        return id;
    }

    public int getVote_count() {
        return vote_count;
    }

    public long getPopularity() {
        return popularity;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getGenre() {
        return genre;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getTitle() {
        return title;
    }


}
