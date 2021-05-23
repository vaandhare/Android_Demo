package com.examples.concepts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.examples.concepts.R;
import com.examples.concepts.model.MovieModel;
import com.examples.concepts.utils.Constants;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.RecyclerViewHolder> {

    private final Context context;
    private List<MovieModel.Results> movieList;
    private final ItemClickListener itemClickListener;

    public MovieAdapter(Context context, List<MovieModel.Results> movieList, ItemClickListener itemClickListener) {
        this.context = context;
        this.movieList = movieList;
        this.itemClickListener = itemClickListener;
    }

    public void setRecyclerData(List<MovieModel.Results> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.tvTitle.setText(this.movieList.get(position).getTitle());
        holder.tvVoteAvg.setText(this.movieList.get(position).getVote_average());
        Glide.with(context).load(Constants.TMDB_IMAGES_BASE_URL + this.movieList.get(position)
                .getPoster_path())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.ivPoster);

        holder.itemView.setOnClickListener(v -> itemClickListener.onItemClick(movieList.get(position)));
    }

    @Override
    public int getItemCount() {
        if(this.movieList != null)
            return this.movieList.size();
        return 0;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle, tvVoteAvg;
        ImageView ivPoster;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvVoteAvg = itemView.findViewById(R.id.tv_vote_avg);
        }
    }

    public interface ItemClickListener{
        void onItemClick(MovieModel.Results results);
    }

}
