package deepmehtait.com.movieteasers.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import deepmehtait.com.movieteasers.R;
import deepmehtait.com.movieteasers.modals.Movie;
import deepmehtait.com.movieteasers.utils.AppBaseURLs;

public class MainActivity extends AppCompatActivity {
    ArrayList<Movie> movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(AppBaseURLs.THE_MOVIEDB_BASE_URL, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJsonArray = null;
                try {
                    movieJsonArray = response.getJSONArray("results");
                    movies = Movie.parseJsonArrayRespone(movieJsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}
