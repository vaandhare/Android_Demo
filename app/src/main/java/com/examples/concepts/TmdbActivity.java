package com.examples.concepts;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.examples.concepts.adapter.MovieAdapter;
import com.examples.concepts.model.MovieModel;
import com.examples.concepts.utils.Constants;
import com.examples.concepts.viewmodel.MovieViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class TmdbActivity extends AppCompatActivity implements MovieAdapter.ItemClickListener, BottomSheetDialog.BottomSheetListener {

    private List<MovieModel.Results> movieLists;
    private MovieAdapter adapter;
    TextView tvFilter;
    MovieViewModel viewModel;
//    BottomSheetBehavior bottomSheetBehavior;
//    RadioGroup radioGroup;
//    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmdb);

//        radioGroup = findViewById(R.id.radioGroup);
        tvFilter = findViewById(R.id.tvFilter);

//        View bottomSheet = findViewById(R.id.bottomSheet);
//        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MovieAdapter(this, movieLists, this);
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        viewModel.getModelMutableLiveDataObserver().observe(this, movieList -> {
            if(movieList != null){
                movieLists = movieList;
                adapter.setRecyclerData(movieList);
            }else{
                Toast.makeText(TmdbActivity.this, "NO Results Found", Toast.LENGTH_LONG ).show();
            }
        });

        viewModel.apiCall(Constants.MOVIE_TYPE_POPULAR);
        tvFilter.setOnClickListener(v -> {
//            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog();
            bottomSheetDialog.show(getSupportFragmentManager(), "bottomSheet");
        });

//        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
//            radioButton = findViewById(group.getCheckedRadioButtonId());
//            CharSequence text = radioButton.getText();
//            if (getString(R.string.popular).contentEquals(text)) {
//                viewModel.apiCall(Constants.MOVIE_TYPE_POPULAR);
//                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//            } else if (getString(R.string.top_rated).contentEquals(text)) {
//                viewModel.apiCall(Constants.MOVIE_TYPE_TOP_RATED);
//                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//            } else if (getString(R.string.upcoming).contentEquals(text)) {
//                viewModel.apiCall(Constants.MOVIE_TYPE_UPCOMING);
//                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//            }
//        });

    }

    @Override
    public void onItemClick(MovieModel.Results results) {
        new MaterialAlertDialogBuilder(TmdbActivity.this)
                .setTitle(results.getTitle())
                .setMessage("Description: \n\n" + results.getOverview() + "\n\nReleased: " + results.getRelease_date())
                .setPositiveButton("Close", (dialog, which) -> {
                }).show();

    }

    @Override
    public void onClicked(String url) {
        viewModel.apiCall(url);
        Toast.makeText(TmdbActivity.this, url, Toast.LENGTH_LONG ).show();
    }
}