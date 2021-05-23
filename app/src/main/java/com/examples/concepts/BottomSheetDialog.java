package com.examples.concepts;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.jetbrains.annotations.NotNull;

import static android.content.ContentValues.TAG;
import static com.examples.concepts.utils.Constants.MOVIE_TYPE_POPULAR;
import static com.examples.concepts.utils.Constants.MOVIE_TYPE_TOP_RATED;
import static com.examples.concepts.utils.Constants.MOVIE_TYPE_UPCOMING;

public class BottomSheetDialog extends BottomSheetDialogFragment {

    RadioGroup radioGroup;
    RadioButton radioButton;
    BottomSheetListener bottomSheetListener;

    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet, container, false);

        radioGroup = view.findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            radioButton = view.findViewById(group.getCheckedRadioButtonId());
            CharSequence text = radioButton.getText();
            radioButton.setChecked(true);
            if (getString(R.string.popular).contentEquals(text)) {
                bottomSheetListener.onClicked(MOVIE_TYPE_POPULAR);
                dismiss();
            } else if (getString(R.string.top_rated).contentEquals(text)) {
                bottomSheetListener.onClicked(MOVIE_TYPE_TOP_RATED);
                dismiss();
            } else if (getString(R.string.upcoming).contentEquals(text)) {
                bottomSheetListener.onClicked(MOVIE_TYPE_UPCOMING);
                dismiss();
            }
        });

        return view;
    }

    public interface BottomSheetListener{
        void onClicked(String url);
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        try {
            bottomSheetListener = (BottomSheetListener) context;
        }catch (Exception e){
            Log.d(TAG, "onAttach: " + e);
        }
    }
}
