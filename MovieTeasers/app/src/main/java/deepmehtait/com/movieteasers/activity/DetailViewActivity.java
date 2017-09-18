package deepmehtait.com.movieteasers.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import deepmehtait.com.movieteasers.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by deepmetha on 9/17/17.
 */

public class DetailViewActivity extends YouTubeBaseActivity {
    @BindView(R.id.overivewRelativeLayout)
    RelativeLayout rvOverview;
    @BindView(R.id.overivewRelative)
    TextView tvOverview;
    @BindView(R.id.movieTitleRelativeLayout)
    RelativeLayout rvMovieTitle;
    @BindView(R.id.movieTitleRelative)
    TextView tvMovieTitle;
    @BindView(R.id.generRelativeLayout)
    RelativeLayout rvGener;
    @BindView(R.id.generRelative)
    TextView tvGener;
    @BindView(R.id.detailViewPoster)
    ImageView ImPoster;
    @BindView(R.id.thumbsImage)
    ImageView thumbs;
    @BindView(R.id.player)
    YouTubePlayerView youTubePlayerView;
    @BindView(R.id.youtubeRelativeLayout)
    RelativeLayout rvYoutubeRelativeLayout;
    @BindView(R.id.voteRelativeLayout)
    RelativeLayout rvVoteRelative;
    @BindView(R.id.voteRelative)
    TextView tvVote;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view);
        ButterKnife.bind(this);
        Intent i = getIntent();
        String imdbid = String.valueOf(i.getStringExtra("id"));
        final Double rating = Double.valueOf(i.getStringExtra("rating"));
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/movie/" + imdbid + "/trailers?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    final String JsonObject = response.body().string();
                    DetailViewActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = new JSONObject(JsonObject);
                                final JSONArray youtubeArr = jsonObject.getJSONArray("youtube");
                                if (youtubeArr.length() > 0) {
                                    youTubePlayerView.initialize("AIzaSyATtBQwpn5TmYnVeAo2Za8KA4k2wrhI9_k", new YouTubePlayer.OnInitializedListener() {
                                        @Override
                                        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                                            try {
                                                if (rating >= 5.0) {
                                                    youTubePlayer.loadVideo(youtubeArr.getJSONObject(0).getString("source").toString());
                                                    Picasso.with(getApplicationContext()).load(R.drawable.thumbsup_green).into(thumbs);
                                                } else {
                                                    youTubePlayer.cueVideo(youtubeArr.getJSONObject(0).getString("source").toString());
                                                    Picasso.with(getApplicationContext()).load(R.drawable.thumbsdown_red).into(thumbs);
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                                        }
                                    });
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });


                }
            }
        });
        String movieTitle = i.getStringExtra("movieTitle");
        String gener = i.getStringExtra("gener");
        String overview = i.getStringExtra("overview");
        String posterUrl = i.getStringExtra("posterUrl");
        String Rating = "Rating: " + i.getStringExtra("rating") + "/10 (" + i.getStringExtra("voteCount") + " users rated)";
        byte[] byteArray = getIntent().getByteArrayExtra("bmpImage");
        Bitmap bitmap = null;
        if (byteArray != null) {
            bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        }
        tvOverview.setText(overview);
        tvMovieTitle.setText(movieTitle);
        tvGener.setText(gener);
        tvVote.setText(Rating);
        Picasso.with(getApplicationContext()).load(posterUrl).centerCrop().fit().into(ImPoster);
        // Bitmap bitmap = ((BitmapDrawable)ImPoster.getDrawable()).getBitmap();
        if (bitmap != null) {
            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    setViewSwatch(tvMovieTitle, palette.getDominantSwatch());
                    setRelativeBackground(rvMovieTitle, palette.getDominantSwatch());
                    setViewSwatch(tvGener, palette.getLightVibrantSwatch());
                    setRelativeBackground(rvGener, palette.getLightVibrantSwatch());
                    setRelativeBackground(rvYoutubeRelativeLayout, palette.getLightVibrantSwatch());
                    setViewSwatch(tvOverview, palette.getLightMutedSwatch());
                    setRelativeBackground(rvOverview, palette.getLightMutedSwatch());
                    setViewSwatch(tvVote, palette.getLightMutedSwatch());
                    setRelativeBackground(rvVoteRelative, palette.getLightMutedSwatch());
                }
            });
        }

    }

    private void setViewSwatch(TextView view, Palette.Swatch swatch) {
        if (swatch != null) {
            view.setTextColor(swatch.getBodyTextColor());
        }
    }

    private void setRelativeBackground(RelativeLayout view, Palette.Swatch swatch) {
        if (swatch != null) {
            view.setBackgroundColor(swatch.getRgb());
        }
    }
}
