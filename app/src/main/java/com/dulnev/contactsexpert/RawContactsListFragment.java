package com.dulnev.contactsexpert;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class RawContactsListFragment extends Fragment {

    float mProgress = 0;
    ProgressBar mProgressBar;

    public RawContactsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.GONE);
        return view;
    }


}
