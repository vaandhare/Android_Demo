package com.examples.concepts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.examples.concepts.adapter.GithubAdapter;
import com.examples.concepts.model.GithubModel;
import com.examples.concepts.model.GithubRepoModel;
import com.examples.concepts.viewmodel.GithubViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Objects;

public class GithubActivity extends AppCompatActivity implements GithubAdapter.ItemClickListener {

    GithubModel githubModel;
    List<GithubRepoModel> repoModelList;
    GithubAdapter githubAdapter;
    TextInputLayout editTextUsername;
    MaterialButton searchButton;
    TextView tvUsername, tvUserBio, tvUserLocation, tvFollowersCount, tvFollowingsCount, tvRepository, tvNoResultFound;
    ImageView ivUserProfile;
    RelativeLayout agRL;
    LinearLayout agLL2;
    SharedPreferences sharedPreferences;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github);

        sharedPreferences = getSharedPreferences("github_username", MODE_PRIVATE);

        editTextUsername = findViewById(R.id.ag_etv_username);
        searchButton = findViewById(R.id.ag_btn_search);
        tvUsername = findViewById(R.id.tv_username);
        ivUserProfile = findViewById(R.id.iv_user_profile);
        tvUserBio = findViewById(R.id.tv_user_bio);
        tvUserLocation = findViewById(R.id.tv_user_location);
        tvFollowersCount = findViewById(R.id.tv_followers_count);
        tvFollowingsCount = findViewById(R.id.tv_following_count);
        agRL = findViewById(R.id.ag_relativeLayout);
        agLL2 = findViewById(R.id.ag_ll_2);
        tvRepository = findViewById(R.id.tv_repository);
        tvNoResultFound = findViewById(R.id.tv_no_result);


        RecyclerView recyclerView = findViewById(R.id.repos_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        githubAdapter = new GithubAdapter(this, repoModelList, this);
        recyclerView.setAdapter(githubAdapter);

        GithubViewModel githubViewModel = ViewModelProviders.of(this).get(GithubViewModel.class);
        githubViewModel.getGithubModelMutableLiveData().observe(this, githubRepoModels -> {
            if(githubRepoModels != null){

                if(githubRepoModels.getFollowers() < 10){
                    tvFollowersCount.setText("0" + githubRepoModels.getFollowers());
                }else{
                    tvFollowersCount.setText(String.valueOf(githubRepoModels.getFollowers()));
                }

                if(githubRepoModels.getFollowing() < 10){
                    tvFollowingsCount.setText("0" + githubRepoModels.getFollowing());
                }else{
                    tvFollowingsCount.setText(String.valueOf(githubRepoModels.getFollowing()));
                }

                githubModel = githubRepoModels;
                tvUsername.setText(githubRepoModels.getName());
                tvUserBio.setText(githubRepoModels.getBio());
                tvUserLocation.setText(githubRepoModels.getLocation());

                Glide.with(this).load(githubRepoModels.getAvatarUrl())
                        .apply(RequestOptions.centerCropTransform())
                        .into(ivUserProfile);

                agRL.setVisibility(View.VISIBLE);
                agLL2.setVisibility(View.VISIBLE);
                tvRepository.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                tvNoResultFound.setVisibility(View.GONE);
            }else {
                agRL.setVisibility(View.GONE);
                agLL2.setVisibility(View.GONE);
                tvRepository.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                tvNoResultFound.setVisibility(View.VISIBLE);
            }
        });

        githubViewModel.getListMutableLiveData().observe(this, githubRepos -> {
            if(githubRepos != null) {
                repoModelList = githubRepos;
                githubAdapter.setRecyclerViewData(githubRepos);
            }
        });


        if(sharedPreferences.contains("username")){
            String username = sharedPreferences.getString("username", "");
            Objects.requireNonNull(editTextUsername.getEditText()).setText(username);
            githubViewModel.userApiCalls(username);
        }

        searchButton.setOnClickListener(v -> {
            String username = Objects.requireNonNull(editTextUsername.getEditText()).getText().toString();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", username);
            editor.apply();
            editor.commit();
            githubViewModel.userApiCalls(username);
        });
    }

    @Override
    public void onItemClick(GithubRepoModel githubRepoModel) {
        Intent intent = new Intent(GithubActivity.this, WebViewActivity.class);
        intent.putExtra("webURL", githubRepoModel.getHtmlUrl());
        startActivity(intent);
    }
}