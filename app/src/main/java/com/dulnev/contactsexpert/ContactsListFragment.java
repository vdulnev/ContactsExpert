package com.dulnev.contactsexpert;

import android.app.Activity;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p />
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p />
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class ContactsListFragment extends Fragment implements AbsListView.OnItemClickListener {

    float mProgress = 0;
    ProgressBar mProgressBar;

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
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

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        mProgressBar.setVisibility(View.GONE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                mAdapter = new ArrayAdapter<Contact>(getActivity(),
                        android.R.layout.simple_list_item_1, android.R.id.text1, getContacts());
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
            mListener = (OnFragmentInteractionListener) activity;
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
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            //mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
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

    /**
    * This interface must be implemented by activities that contain this
    * fragment to allow an interaction in this fragment to be communicated
    * to the activity and potentially other fragments contained in that
    * activity.
    * <p>
    * See the Android Training lesson <a href=
    * "http://developer.android.com/training/basics/fragments/communicating.html"
    * >Communicating with Other Fragments</a> for more information.
    */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

    private List<Contact> getContacts() {
        Cursor lCursor = Utils.getContactsCursor(getActivity());
        if (lCursor.getCount() > 0) {
            //Handler lHandler = new Handler();
            float lStep = (float) (100.0/lCursor.getCount());
            lCursor.moveToPosition(-1);
            getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            });

            while (lCursor.moveToNext()) {
                Integer lID = lCursor.getInt(lCursor
                        .getColumnIndex(ContactsContract.Contacts._ID));
                String lLookupKey = lCursor.getString(lCursor
                        .getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
                String lDisplayName = lCursor.getString(lCursor
                        .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                Data.Contacts.add(Utils.getContact(getActivity(), lID, lLookupKey, lDisplayName));
                mProgress = mProgress + lStep;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar.setProgress(Math.round(mProgress));
                    }
                });
            }
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mProgressBar.setVisibility(View.GONE);
                }
            });
        }
        return Data.Contacts;
    }

}
