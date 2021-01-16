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

public class CustomClassesDialog extends DialogFragment {

    private EditText etSubjectName;
    private EditText etSubjectCode;
    private EditText etSection;
    private Button buttonAdd;
    private Context mcontext;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.classes_dialog_layout, null);
        etSubjectName = v.findViewById(R.id.etDialogSubjectName);
        etSubjectCode = v.findViewById(R.id.etDialogSubjectCode);
        etSection = v.findViewById(R.id.etDialogSection);
        buttonAdd = v.findViewById(R.id.buttonDialogAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjectName = etSubjectName.getText().toString();
                String subjectCode = etSubjectCode.getText().toString();
                String section = etSection.getText().toString();
                if (subjectName.length() == 0 || subjectCode.length() == 0 || section.length() == 0) {
                    Toast.makeText(mcontext, "EmptyField", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    listener.addToClassesInfoHolderList(new ClassesInfoHolder(subjectCode, subjectName, section));
                    dismiss();
                }
            }
        });
        builder.setView(v);
        return builder.create();
    }

    public interface CustomClassesDialogListener {
        void addToClassesInfoHolderList(ClassesInfoHolder newClassesInfoHolder);
    }

    CustomClassesDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mcontext = context;
        try {
            listener = (CustomClassesDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "must implement CustomClassesDialogListener");
        }
    }
}
