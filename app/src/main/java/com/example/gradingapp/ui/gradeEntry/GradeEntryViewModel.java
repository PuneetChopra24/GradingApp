package com.example.gradingapp.ui.gradeEntry;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GradeEntryViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public GradeEntryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is grade entry fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}