package com.young.movie.adapter;

import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.young.movie.databinding.MovieItemBinding;
import com.young.movie.model.MovieDTO;

import java.util.List;

public class NaverViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieDTO> movieList;

    public NaverViewAdapter(List<MovieDTO> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MovieItemBinding viewBinding
                = MovieItemBinding.inflate(layoutInflater,parent,false);

        return new MovieViewHolder(viewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MovieViewHolder viewHolder = (MovieViewHolder) holder;

        MovieItemBinding movieBinding = viewHolder.viewBinding;

        MovieDTO movieDTO = movieList.get(position);

        Log.d("예?", movieDTO.toString());

        Spanned title
                = Html.fromHtml(movieDTO.getTitle(), Html.FROM_HTML_MODE_LEGACY);
        movieBinding.movieTitle.setText(title);
        Spanned actor
                = Html.fromHtml(movieDTO.getActor(), Html.FROM_HTML_MODE_LEGACY);
        movieBinding.movieActor.setText(actor);
        Spanned rating
                = Html.fromHtml(movieDTO.getUserRating(), Html.FROM_HTML_MODE_LEGACY);
        movieBinding.movieUserRating.setText("★ " + rating);
        Spanned director
                = Html.fromHtml(movieDTO.getDirector(), Html.FROM_HTML_MODE_LEGACY);
        movieBinding.movieDirector.setText(director);
        Spanned sub_title
                = Html.fromHtml(movieDTO.getSubtitle(), Html.FROM_HTML_MODE_LEGACY);
        movieBinding.movieSubTitle.setText(sub_title);


        if(!movieDTO.getImage().isEmpty()){

            Picasso.get().load(movieDTO.getImage())
                    .into(movieBinding.movieImage);

        }




    }

    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder{

        public MovieItemBinding viewBinding;

        public MovieViewHolder(@NonNull MovieItemBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding;
        }
    }
}
