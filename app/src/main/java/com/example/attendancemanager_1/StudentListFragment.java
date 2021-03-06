package com.example.attendancemanager_1;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerViewStudentList;
    //private RecyclerView.LayoutManager layoutManager;

    // TODO: Rename and change types of parameters
    private ArrayList<StudentInfoHolder> mParam1;
    //private String mParam2;

    public interface StudentFragmentListener {
        void attachStudentRecyclerView(RecyclerView recyclerView);
    }

    StudentFragmentListener listener;

    public StudentListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     *               //@param param2 Parameter 2.
     * @return A new instance of fragment StudentListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentListFragment newInstance(ArrayList<StudentInfoHolder> param1) {
        StudentListFragment fragment = new StudentListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getParcelableArrayList(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_student_list, container, false);
        recyclerViewStudentList = v.findViewById(R.id.studentlistrv);
        listener.attachStudentRecyclerView(recyclerViewStudentList);
        recyclerViewStudentList.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewStudentList.setAdapter(new StudentListAdapter(mParam1));
        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof StudentFragmentListener) {
            listener = (StudentFragmentListener) context;
        } else {
            //Add an Exception
        }
    }
}