package com.example.chari.serchlistview;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private ListView listView;
    private MyAppAdapter myAppAdapter;
    private ArrayList<Post> postArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        postArrayList = new ArrayList<>();
        postArrayList.add(new Post("Dummy Title", "Dummy Sub Title"));
        postArrayList.add(new Post("Searchview in actionbar", "enjoy search functionality from actionbar in android"));
        postArrayList.add(new Post("Search in listview", "search feature that filter listview item"));
        postArrayList.add(new Post("Android Search Bar", "adding search feature in toolbar using appcompat library"));
        postArrayList.add(new Post("Android Studio SearchView example", "Android SearchView tutorial in android studio"));
        postArrayList.add(new Post("Android Tutorial", "Get latest android material with simple solution"));
        postArrayList.add(new Post("nkDroid tutorials", "A to Z Android tutorials at one place"));

        myAppAdapter = new MyAppAdapter(postArrayList, MainActivity.this);
        listView.setAdapter(myAppAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //*** setOnQueryTextFocusChangeListener ***
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchQuery) {
                myAppAdapter.filter(searchQuery.toString().trim());
                listView.invalidate();
                return true;
            }
        });

        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when collapsed
                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                return true;  // Return true to expand action view
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {

            return true;
        }if (id == R.id.action_select_all) {
            for(int i=0; i < listView.getChildCount(); i++){
                LinearLayout itemLayout = (LinearLayout)listView.getChildAt(i);
                CheckBox cb = (CheckBox)itemLayout.findViewById(R.id.checkBox1);
                cb.setChecked(true);
            }
            return true;
        } else if (id == R.id.action_deselect_all) {
            for(int i=0; i < listView.getChildCount(); i++){
                LinearLayout itemLayout = (LinearLayout)listView.getChildAt(i);
                CheckBox cb = (CheckBox)itemLayout.findViewById(R.id.checkBox1);
                cb.setChecked(false);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
