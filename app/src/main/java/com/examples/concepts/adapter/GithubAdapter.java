package com.examples.concepts.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.examples.concepts.R;
import com.examples.concepts.model.GithubRepoModel;

import java.util.List;

public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.RecyclerViewHolder> {

    private final Context context;
    private List<GithubRepoModel> repoModelList;
    private final ItemClickListener itemClickListener;

    public GithubAdapter(Context context, List<GithubRepoModel> repoModelList, ItemClickListener itemClickListener) {
        this.context = context;
        this.repoModelList = repoModelList;
        this.itemClickListener = itemClickListener;
    }

    public void setRecyclerViewData(List<GithubRepoModel> repoModelList){
        this.repoModelList = repoModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.repo_list_item, parent, false);
        return new GithubAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.tvRepoTitle.setText(this.repoModelList.get(position).getName());
        holder.tvRepoDescription.setText(this.repoModelList.get(position).getDescription());

        holder.itemView.setOnClickListener(v -> itemClickListener.onItemClick(repoModelList.get(position)));
    }

    @Override
    public int getItemCount() {
        if(this.repoModelList != null)
            return this.repoModelList.size();
        return 0;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{

        TextView tvRepoTitle, tvRepoDescription;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRepoTitle = itemView.findViewById(R.id.tv_repo_title);
            tvRepoDescription = itemView.findViewById(R.id.tv_repo_description);
        }
    }

    public interface ItemClickListener{
        void onItemClick(GithubRepoModel githubRepoModel);
    }

}
