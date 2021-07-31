package com.example.gradingapp.ui.viewGrades;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewGradesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ViewGradesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is view grades fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}