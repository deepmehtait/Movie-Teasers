package deepmehtait.com.movieteasers.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
import deepmehtait.com.movieteasers.utils.AppStatus;

public class MainActivity extends AppCompatActivity {
    ArrayList<Movie> movies;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movies = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_s);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        adapter = new MovieResultListAdapter(movies, getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        AsyncHttpClient client = new AsyncHttpClient();
        if (AppStatus.getInstance(getApplicationContext()).isOnline()) {
            client.get(AppBaseURLs.THE_MOVIEDB_BASE_URL, new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    JSONArray movieJsonArray = null;
                    try {
                        movieJsonArray = response.getJSONArray("results");
                        movies.addAll(Movie.parseJsonArrayRespone(movieJsonArray));
                        adapter.notifyDataSetChanged();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                }
            });
        } else {
            Snackbar.make(findViewById(R.id.mainRelativeLayout), "No Internet Connection", Snackbar.LENGTH_LONG)
                    .show();
        }

    }
}
