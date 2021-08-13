package com.example.gradingapp.ui.viewGrades;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gradingapp.MyDatabaseHelper;
import com.example.gradingapp.R;
import com.example.gradingapp.databinding.FragmentViewGradesBinding;

import java.util.ArrayList;

public class ViewGradesFragment extends Fragment {

    private ViewGradesViewModel viewGradesViewModel;
    private FragmentViewGradesBinding binding;
    RecyclerView recyclerView;

    MyDatabaseHelper myDB;
    ArrayList<String> studentId, studentName,studentProg_Code, studentGrade, courseDuration, studentFees;
    CustomAdapter customAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewGradesViewModel =
                new ViewModelProvider(this).get(ViewGradesViewModel.class);

        binding = FragmentViewGradesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recyclerView);

        myDB = new MyDatabaseHelper(getContext());
        studentId = new ArrayList<>();
        studentName = new ArrayList<>();
        studentProg_Code = new ArrayList<>();
        courseDuration = new ArrayList<>();
        studentGrade = new ArrayList<>();
        studentFees = new ArrayList<>();

        storedDataInArrays();
        customAdapter = new CustomAdapter(getContext(), studentId,studentName,studentProg_Code,courseDuration, studentGrade,studentFees);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }

    void storedDataInArrays(){
        Cursor cursor = myDB.readData();
        if(cursor.getCount() == 0){
            Toast.makeText(getContext(), "No Data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                studentId.add(cursor.getString(0));
                studentName.add(cursor.getString(1));
                studentProg_Code.add(cursor.getString(2));
                studentGrade.add(cursor.getString(3));
                courseDuration.add(cursor.getString(4));
                studentFees.add(cursor.getString(5));
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}