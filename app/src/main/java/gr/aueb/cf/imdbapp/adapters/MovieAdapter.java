package gr.aueb.cf.imdbapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.cf.imdbapp.R;
import gr.aueb.cf.imdbapp.models.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> movies = new ArrayList<>();
    private Context context;

    public MovieAdapter(Context context, List<Movie> movie){
        this.movies = movie;
        this.context = context;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView textView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.movieIV);
            textView = itemView.findViewById(R.id.titleTV);
        }

        public ImageView getImageUrl() {
            return imageView;
        }

        public TextView getTextView() {
            return textView;
        }
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.textView.setText(movie.getTitle());
        Glide.with(context).load(movie.getImageUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
