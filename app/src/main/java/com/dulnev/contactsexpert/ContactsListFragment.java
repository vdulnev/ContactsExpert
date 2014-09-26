package com.dulnev.contactsexpert;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ContactsListFragment extends Fragment implements AbsListView.OnItemClickListener {

    float mProgress = 0;
    ProgressBar mProgressBar;

    private OnInteractionListener mListener;

    private ListView mListView;
    private ListAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);

        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        mListView = (ListView) view.findViewById(android.R.id.list);
        mListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        mProgressBar.setVisibility(View.GONE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                mAdapter = new ArrayAdapter<Contact>(getActivity(),
                        android.R.layout.simple_list_item_1, android.R.id.text1, Utils.getContacts(getActivity(), mProgressBar));
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        mListView.setAdapter(mAdapter);
                    }
                });
            }
        })
                .start();

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            Data.selectedContact = Data.Contacts.get(position);
            mListener.onContactClick();
            mListView.setItemChecked(position, true);
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyText instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    public interface OnInteractionListener {
        public void onContactClick();
    }
}
