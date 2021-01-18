package com.example.attendancemanager_1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CustomStudentDialog extends DialogFragment {

    private EditText etStudentName;
    private EditText etRegistration;
    private Button addStudent;
    private Context mContext;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.student_dialog_layout, null);
        etStudentName = v.findViewById(R.id.etstudentname);
        etRegistration = v.findViewById(R.id.etregistration);
        addStudent = v.findViewById(R.id.btnaddstudent);
        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etStudentName.getText().toString();
                String regd = etRegistration.getText().toString();
                if(regd.length()==0 || name.length()==0)
                {
                    Toast.makeText(mContext, "Empty Fields", Toast.LENGTH_LONG).show();
                    return;
                }
                listener.addToStudentInfoHolderList(new StudentInfoHolder(etStudentName.getText().toString(), etRegistration.getText().toString(), 0, 0));
                dismiss();
            }
        });
        builder.setView(v);
        return builder.create();
    }

    public interface CustomStudentDialogListener
    {
        void addToStudentInfoHolderList(StudentInfoHolder studentInfoHolder);
    }

    CustomStudentDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
        try {
            listener = (CustomStudentDialog.CustomStudentDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "must implement CustomStudentDialogListener");
        }
    }
}
