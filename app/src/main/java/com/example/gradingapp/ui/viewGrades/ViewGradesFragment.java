package com.example.gradingapp.ui.viewGrades;

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
import com.example.gradingapp.databinding.FragmentViewGradesBinding;
import com.example.gradingapp.ui.viewGrades.ViewGradesViewModel;

public class ViewGradesFragment extends Fragment {

    private ViewGradesViewModel viewGradesViewModel;
    private FragmentViewGradesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewGradesViewModel =
                new ViewModelProvider(this).get(ViewGradesViewModel.class);

        binding = FragmentViewGradesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textViewGrades;
        viewGradesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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