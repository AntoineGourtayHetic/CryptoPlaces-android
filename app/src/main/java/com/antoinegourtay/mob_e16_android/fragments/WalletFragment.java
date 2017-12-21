package com.antoinegourtay.mob_e16_android.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.antoinegourtay.mob_e16_android.R;
import com.antoinegourtay.mob_e16_android.activities.MainActivity;
import com.antoinegourtay.mob_e16_android.adapters.WalletAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WalletFragment extends Fragment {

    private MainActivity mainActivity;

    TabLayout tabLayout;

    ViewPager viewPager;

    List<String> tabNames;
    private WalletAdapter walletAdapter;

    public WalletFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment WalletFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WalletFragment newInstance (){
        WalletFragment fragment = new WalletFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_wallet, container, false);

        mainActivity = (MainActivity) getActivity();
        mainActivity.getSupportActionBar().setTitle("Mon compte");

        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.pager_wallet);

        // Create an adapter that knows which fragment should be shown on each page
        WalletAdapter adapter = new WalletAdapter(getFragmentManager());

        // Set the adapter onto the view pager
        if (viewPager != null) {
            viewPager.setAdapter(adapter);
        }

        tabLayout = (TabLayout) rootView.findViewById(R.id.tab_layout_wallet);
        viewPager = (ViewPager) rootView.findViewById(R.id.pager_wallet);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        createViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }

    private void createViewPager(ViewPager viewPager) {
        WalletAdapter adapter = new WalletAdapter(getChildFragmentManager());
        adapter.addFrag(new CoursFragment(), "Portefeuile");
        adapter.addFrag(new TransactionFragment(), "Transactions");
        viewPager.setAdapter(adapter);
    }

}
