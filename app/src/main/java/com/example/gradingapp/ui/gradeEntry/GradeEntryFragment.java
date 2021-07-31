package com.example.gradingapp.ui.gradeEntry;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.gradingapp.R;
import com.example.gradingapp.databinding.FragmentGradeEntryBinding;

import com.example.gradingapp.ui.gradeEntry.GradeEntryViewModel;

public class GradeEntryFragment extends Fragment {

    private GradeEntryViewModel gradeentryViewModel;

    private FragmentGradeEntryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        gradeentryViewModel =
                new ViewModelProvider(this).get(GradeEntryViewModel.class);

        binding = FragmentGradeEntryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGradeEntry;
        gradeentryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}