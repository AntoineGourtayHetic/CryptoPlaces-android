package com.antoinegourtay.mob_e16_android.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.antoinegourtay.mob_e16_android.R;
import com.antoinegourtay.mob_e16_android.activities.MainActivity;
import com.antoinegourtay.mob_e16_android.adapters.ViewPagerAdapter;

public class WalletFragment extends Fragment {

    private MainActivity mainActivity;

    private TabLayout tabLayout;
    private ViewPager viewPager;

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

        mainActivity = (MainActivity) getActivity();
        mainActivity.getSupportActionBar().setTitle("Portefeuille");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_wallet, container, false);


        viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) getActivity().findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFrag(new CoursFragment(), "Cours");
        adapter.addFrag(new TransactionFragment(), "Transactions");
        viewPager.setAdapter(adapter);
    }

}
