package com.example.attendancemanager_1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DatesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    private RecyclerView datesRecyclerView;

    // TODO: Rename and change types of parameters
    private ArrayList<DatesInfoHolder> mParam1;
    //private String mParam2;

    public interface DatesFragmentListener
    {
        void attachDatesRecyclerView(RecyclerView recyclerView);
    }

    public DatesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * //@param param2 Parameter 2.
     * @return A new instance of fragment DatesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DatesFragment newInstance(ArrayList<DatesInfoHolder> param1) {
        DatesFragment fragment = new DatesFragment();
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
        View v = inflater.inflate(R.layout.fragment_dates, container, false);
        datesRecyclerView = v.findViewById(R.id.datesrv);
        datesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        datesRecyclerView.setAdapter(new DatesAdapter(mParam1));
        return v;
    }
}