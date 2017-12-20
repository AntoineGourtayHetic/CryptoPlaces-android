package com.antoinegourtay.mob_e16_android.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.antoinegourtay.mob_e16_android.R;

/**
 * Created by antoinegourtay on 20/12/2017.
 */

public class CoursFragment extends Fragment {

    public CoursFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cours, container, false);
    }
}
