package com.dulnev.contactsexpert;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;



public class Master extends Activity implements ContactsListFragment.OnInteractionListener {

    RawContactsListFragment rawContactsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_detail);
        rawContactsFragment = new RawContactsListFragment();
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.master_container, new ContactsListFragment())
                    .add(R.id.detail_container, rawContactsFragment)
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.master, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onContactClick() {
        rawContactsFragment.update();
    }
}
