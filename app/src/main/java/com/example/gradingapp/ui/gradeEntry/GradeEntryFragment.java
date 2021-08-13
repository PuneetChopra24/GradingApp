package com.example.gradingapp.ui.gradeEntry;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.gradingapp.MyDatabaseHelper;
import com.example.gradingapp.R;
import com.example.gradingapp.databinding.FragmentGradeEntryBinding;

import com.example.gradingapp.ui.gradeEntry.GradeEntryViewModel;

public class GradeEntryFragment extends Fragment {

   private GradeEntryViewModel gradeentryViewModel;

    FragmentGradeEntryBinding binding;

    EditText name,grade, duration, fees;
    Button submit;
    ListView prog_code;
    String[] progCodeList = {"PROG8170", "PROG8480", "PROG8185", "PROG8080","INFO8240"};

    String subject="";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       gradeentryViewModel =
               new ViewModelProvider(this).get(GradeEntryViewModel.class);



        // Context context = inflater.getContext();
       binding = FragmentGradeEntryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //binding.setProgCode("Program Code");

        name = root.findViewById(R.id.name);
        prog_code = root.findViewById(R.id.programCode);
        grade = root.findViewById(R.id.grade);
        duration = root.findViewById(R.id.duration);
        fees = root.findViewById(R.id.fees);
        submit = root.findViewById(R.id.submit);

        prog_code.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               subject= prog_code.getItemAtPosition(position).toString();
            }
        });


     ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line,progCodeList);
    prog_code.setAdapter(arrayAdapter);

        //SimpleCursorAdapter adapter = new SimpleCursorAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, progCodeList);


        submit.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             MyDatabaseHelper myDB = new MyDatabaseHelper(getContext());
          myDB.addStudentInfo(name.getText().toString().trim(),subject,
                   grade.getText().toString().trim(), duration.getText().toString().trim(),
                  Integer.valueOf(fees.getText().toString().trim()));

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