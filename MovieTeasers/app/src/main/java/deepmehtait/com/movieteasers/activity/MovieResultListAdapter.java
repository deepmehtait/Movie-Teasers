package deepmehtait.com.movieteasers.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import deepmehtait.com.movieteasers.R;
import deepmehtait.com.movieteasers.modals.Movie;
import deepmehtait.com.movieteasers.utils.AppBaseURLs;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by deepmetha on 9/17/17.
 */

public class MovieResultListAdapter extends RecyclerView.Adapter<MovieResultListAdapter.MovieResultListViewHolder> {
    List<Movie> moviesResultList = new ArrayList<Movie>();
    Context context;

    public MovieResultListAdapter(ArrayList<Movie> movieResultList, Context context) {
        this.moviesResultList = movieResultList;
        this.context = context;
    }

    @Override
    public MovieResultListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card_view, parent, false);
        MovieResultListViewHolder mrlvh = new MovieResultListViewHolder(view, context);
        return mrlvh;
    }

    @Override
    public void onBindViewHolder(MovieResultListViewHolder holder, int position) {
        Movie md = moviesResultList.get(position);
        holder.movieTitle.setText(md.getTitle());
        holder.movieYear.setText("Release Date: " + md.getRelease_date());
        holder.gener.setText("Gener: " + md.getGenre());
        holder.rating.setText("Rating: " + Double.toString(md.getVote_average()));
        if (md.getVote_average().intValue() < 5) {
            holder.playIc.setVisibility(View.GONE);
        } else {
            holder.playIc.setVisibility(View.VISIBLE);
        }
        int orientation = context.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            final int radius = 15;
            final int margin = 15;
            final Transformation transformation = new RoundedCornersTransformation(radius, margin);
            Picasso.with(context).load(AppBaseURLs.IMDB_BASE_IMAGE_URL + "w342" + md.getPoster_path()).centerCrop().fit().transform(transformation).into(holder.posterImage);

        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            final int radius = 25;
            final int margin = 25;
            final Transformation transformation = new RoundedCornersTransformation(radius, margin);

            Picasso.with(context).load(AppBaseURLs.IMDB_BASE_IMAGE_URL + "w780" + md.getBackdrop_path()).fit().transform(transformation).into(holder.posterImage);
            holder.overview.setText(md.getOverview());
        }


    }

    @Override
    public int getItemCount() {
        return moviesResultList.size();
    }

    public class MovieResultListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.posterImage)
        ImageView posterImage;
        @BindView(R.id.movieTitle)
        TextView movieTitle;
        @BindView(R.id.movieYear)
        TextView movieYear;
        @BindView(R.id.gener)
        TextView gener;
        @BindView(R.id.rating)
        TextView rating;
        @BindView(R.id.playIcon)
        ImageView playIc;
        TextView overview;
        Movie movieDetails;

        public MovieResultListViewHolder(final View itemView, final Context context) {
            super(itemView);
            int orientation = context.getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                overview = (TextView) itemView.findViewById(R.id.overivew);
            }
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Bitmap bitmap = ((BitmapDrawable) posterImage.getDrawable()).getBitmap();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    int orientation = context.getResources().getConfiguration().orientation;
                    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        // bitmap.compress(Bitmap.CompressFormat.PNG, 20, stream);
                    } else {
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    }
                    byte[] byteArray = stream.toByteArray();
                    Intent i = new Intent(context.getApplicationContext(), DetailViewActivity.class);
                    i.putExtra("posterUrl", AppBaseURLs.IMDB_BASE_IMAGE_URL + "w342" + moviesResultList.get(position).getPoster_path());
                    i.putExtra("id", String.valueOf(moviesResultList.get(position).getId()));
                    i.putExtra("movieTitle", moviesResultList.get(position).getTitle());
                    i.putExtra("gener", moviesResultList.get(position).getGenre());
                    i.putExtra("overview", moviesResultList.get(position).getOverview());
                    i.putExtra("rating", Double.toString(moviesResultList.get(position).getVote_average()));
                    i.putExtra("voteCount", String.valueOf(moviesResultList.get(position).getVote_count()));
                    if (byteArray != null) {
                        i.putExtra("bmpImage", byteArray);
                    }

                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    context.getApplicationContext().startActivity(i);
                }
            });

        }
    }
}
