package com.example.gradingapp.ui.viewGrades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gradingapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList student_Id, student_Name, student_Prog_Code,course_Duration, student_Grade, student_Fees;
    CustomAdapter(Context context, ArrayList studentId, ArrayList studentName, ArrayList studentProg_Code, ArrayList courseDuration, ArrayList studentGrade, ArrayList studentFees){
        this.context = context;
        this.student_Id = studentId;
        this.student_Name = studentName;
        this.student_Prog_Code = studentProg_Code;
        this.course_Duration = courseDuration;
        this.student_Grade = studentGrade;


        this.student_Fees = studentFees;
    }


    @NonNull
    @NotNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CustomAdapter.MyViewHolder holder, int position) {

        holder.studentId_txt.setText(String.valueOf(student_Id.get(position)));
        holder.studentName_txt.setText(String.valueOf(student_Name.get(position)));
        holder.studentProg_Code_txt.setText(String.valueOf(student_Prog_Code.get(position)));
        holder.courseDuration_txt.setText(String.valueOf(course_Duration.get(position)));
        holder.studentGrade_txt.setText(String.valueOf(student_Grade.get(position)));
        holder.studentFees_txt.setText(String.valueOf(student_Fees.get(position)));

    }

    @Override
    public int getItemCount() {
        return student_Id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView studentId_txt, studentName_txt, studentProg_Code_txt, courseDuration_txt, studentGrade_txt, studentFees_txt;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            studentId_txt = itemView.findViewById(R.id.studentId);
            studentName_txt = itemView.findViewById(R.id.studentName);
            studentProg_Code_txt = itemView.findViewById(R.id.StudentProg_Code);
            studentGrade_txt = itemView.findViewById(R.id.studentGrade);
            courseDuration_txt = itemView.findViewById(R.id.courseDuration);
            studentFees_txt = itemView.findViewById(R.id.studentFees);
        }
    }
}
