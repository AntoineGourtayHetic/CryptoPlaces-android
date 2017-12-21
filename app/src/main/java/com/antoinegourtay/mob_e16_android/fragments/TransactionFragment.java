package com.antoinegourtay.mob_e16_android.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.antoinegourtay.mob_e16_android.R;
import com.antoinegourtay.mob_e16_android.item.TransactionItem;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by antoinegourtay on 20/12/2017.
 */

public class TransactionFragment extends Fragment {

    @BindView(R.id.recyclerViewTransaction)
    RecyclerView recyclerView;

    /**
     * Fake lists
     *
     */
    List<String> fakeIdList;
    List<String> fakeDateList;
    List<String> fakeAmountList;


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

        createFakeIdList();
        createFakeDateList();
        createFakeAmountList();

        fillrecycler(fakeIdList, fakeDateList, fakeAmountList);

        return rootView;
    }

    private void createFakeAmountList() {
        fakeAmountList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            fakeAmountList.add("+ 35.234234 BTC");
        }
    }

    private void createFakeDateList() {
        fakeDateList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            fakeDateList.add("20.12.17");
        }
    }

    private void createFakeIdList() {
        fakeIdList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            fakeIdList.add("Transation " + i);
        }
    }


    public void fillrecycler(final List<String> idList, List<String> dateList, List<String> amountList) {
        // Crée les adapteur de la library

        ItemAdapter<TransactionItem> itemAdapter = new ItemAdapter<>();
        FastAdapter fastAdapter = FastAdapter.with(itemAdapter);
        recyclerView.setAdapter(fastAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        // Defini le layout en grid, donc en grille de 3 colone


        for (int i = 0; i < idList.size(); i++) {
            TransactionItem item = new TransactionItem();
            item.idTransaction = idList.get(i);
            item.dateTransaction =  dateList.get(i);
            item.amountTransaction =  amountList.get(i);

            itemAdapter.add(item);
        }
    }
}
