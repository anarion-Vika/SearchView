package com.example.vika.searchview;


import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    String TAG = MainActivity.class.getSimpleName();
    MaterialSearchView searchView;
    HashSet<String> mHash;
    Toolbar toolbar;
    ArrayList<String> mHints = new ArrayList<>();
    private String list[];
    private ArrayAdapter<String> mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mHints.add("Furniture");
        mHints.add("Women");
        mHints.add("Hotels");
        mHints.add("Man");

        mListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mHints);
        searchViewCode();

    }

    private void searchViewCode() {
        searchView = findViewById(R.id.search_view);

        searchView.setAdapter(mListAdapter);
        searchView.setEllipsize(true);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getApplicationContext(), query, Toast.LENGTH_SHORT).show();
                if (!mHints.contains(query)) {
                    mHints.add(query);
                    Log.d(TAG, "!!" + mHints.size());
                    mListAdapter.add(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mListAdapter.getFilter().filter(newText);

                mListAdapter.getPosition(newText);
                Log.d(TAG, "!!!!!" + mListAdapter.getPosition(newText));
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
            }

            @Override
            public void onSearchViewClosed() {
            }
        });
    }    /*click alt+insert key */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.seacrh_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }
}
