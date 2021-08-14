package com.example.gradingapp.ui.search;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gradingapp.MyDatabaseHelper;
import com.example.gradingapp.R;
import com.example.gradingapp.databinding.FragmentSearchBinding;
import com.example.gradingapp.ui.viewGrades.CustomAdapter;

import java.util.ArrayList;

import javax.security.auth.Subject;


public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;
    private FragmentSearchBinding binding;
    EditText enterId;
    RadioButton searchById;
    RadioButton searchByProgram;
    ListView subjects;
    RecyclerView results;
    Button findButton;
    ArrayList<String> studentId, studentName,studentProg_Code, studentGrade, courseDuration, studentFees;
    String[] progCodeList = {"PROG8170", "PROG8480", "PROG8185", "PROG8080","INFO8240"};
    String subject = "";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        enterId = root.findViewById(R.id.enterId);
        searchById = root.findViewById(R.id.lookById);
        searchByProgram = root.findViewById(R.id.lookByProgram);
        subjects = root.findViewById(R.id.subject);
        results = root.findViewById(R.id.recycleView);
        findButton = root.findViewById(R.id.findButton);

        subjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                subject = adapterView.getItemAtPosition(i).toString();
                Log.i("SELECTION",subject);
            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line,progCodeList);
        subjects.setAdapter(arrayAdapter);
        enterId.setAlpha(0);
        subjects.setAlpha(0);

        searchById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterId.setAlpha(1);
                subjects.setAlpha(0);
            }
        });

        searchByProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterId.setAlpha(0);
                subjects.setAlpha(1);
            }
        });

            findButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                    if (searchById.isChecked()) {
                        Cursor cursor;
                        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(getContext());
                        cursor = myDatabaseHelper.readDataWithId(Integer.parseInt(enterId.getText().toString()));
                        studentId = new ArrayList<>();
                        studentName = new ArrayList<>();
                        studentProg_Code = new ArrayList<>();
                        courseDuration = new ArrayList<>();
                        studentGrade = new ArrayList<>();
                        studentFees = new ArrayList<>();

                        if (cursor.getCount() == 0) {
                            Toast.makeText(getContext(), "No Data", Toast.LENGTH_SHORT).show();
                        } else {
                            while (cursor.moveToNext()) {
                                studentId.add(cursor.getString(0));
                                studentName.add(cursor.getString(1));
                                studentProg_Code.add(cursor.getString(2));
                                studentGrade.add(cursor.getString(3));
                                courseDuration.add(cursor.getString(4));
                                studentFees.add(cursor.getString(5));
                            }
                            CustomAdapter customAdapter = new CustomAdapter(getContext(), studentId, studentName, studentProg_Code, courseDuration, studentGrade, studentFees);
                            results.setAdapter(customAdapter);
                            results.setLayoutManager(new LinearLayoutManager(getContext()));
                        }


                    } else if (searchByProgram.isChecked()) {

                        Cursor cursor;
                        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(getContext());
                        cursor = myDatabaseHelper.readDataWithProgram(subject);
                        studentId = new ArrayList<>();
                        studentName = new ArrayList<>();
                        studentProg_Code = new ArrayList<>();
                        courseDuration = new ArrayList<>();
                        studentGrade = new ArrayList<>();
                        studentFees = new ArrayList<>();

                        if (cursor.getCount() == 0) {
                            Toast.makeText(getContext(), "No Data", Toast.LENGTH_SHORT).show();
                        } else {
                            while (cursor.moveToNext()) {
                                studentId.add(cursor.getString(0));
                                studentName.add(cursor.getString(1));
                                studentProg_Code.add(cursor.getString(2));
                                studentGrade.add(cursor.getString(3));
                                courseDuration.add(cursor.getString(4));
                                studentFees.add(cursor.getString(5));
                            }
                            CustomAdapter customAdapter = new CustomAdapter(getContext(), studentId, studentName, studentProg_Code, courseDuration, studentGrade, studentFees);
                            results.setAdapter(customAdapter);
                            results.setLayoutManager(new LinearLayoutManager(getContext()));
                        }

                    } else {
                        Toast.makeText(getContext(), "Please select a seach option", Toast.LENGTH_SHORT).show();
                    }
                 }
                    catch (Exception e)
                    {
                        Toast.makeText(getContext(),"Please enter correct details", Toast.LENGTH_SHORT).show();
                    }
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