package com.antoinegourtay.mob_e16_android.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.antoinegourtay.mob_e16_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by antoinegourtay on 20/12/2017.
 */

public class TransactionFragment extends Fragment {

    @BindView(R.id.recyclerViewTransaction)
    RecyclerView recyclerView;

    public static TransactionFragment newInstance() {
        TransactionFragment fragment = new TransactionFragment();
        return fragment;
    }

    public TransactionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_transaction, container, false);

        ButterKnife.bind(this, rootView);

        return rootView;
    }
}
