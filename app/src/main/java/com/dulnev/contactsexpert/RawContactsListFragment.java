package com.dulnev.contactsexpert;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ProgressBar;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class RawContactsListFragment extends Fragment {

    private AbsListView mListView;
    private ListAdapter mAdapter;

    float mProgress = 0;
    ProgressBar mProgressBar;

    public RawContactsListFragment() {
        // Required empty public constructor
    }

    public void update(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mAdapter = new ArrayAdapter<RawContact>(getActivity(),
                        android.R.layout.simple_list_item_1, android.R.id.text1, Utils.getRawContacts(getActivity(), mProgressBar, Data.selectedContact.getId()));
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        mListView.setAdapter(mAdapter);
                    }
                });
            }
        })
                .start();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.GONE);
        return view;
    }


}
