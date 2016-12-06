package nb.cblink.vnnews.view.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

import nb.cblink.vnnews.R;
import nb.cblink.vnnews.data.DataFactory;
import nb.cblink.vnnews.data.TopicPrereference;
import nb.cblink.vnnews.model.FeedTopic;
import nb.cblink.vnnews.view.fragment.NewsFeedFragment;
import nb.cblink.vnnews.view.fragment.SpecificColumnFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private Menu navigationMenu;
    private NavigationView navigationView;
    private MenuItem mPreviousMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationMenu = navigationView.getMenu();
        navigationView.setNavigationItemSelectedListener(this);

        appBarLayout = (AppBarLayout) findViewById(R.id.appbarlayout);


        //Lay danh sach topic neu chua ton tai
        if (!TopicPrereference.getReference(this).getListTopic(DataFactory.getInstance().data)) {
            new GetTopicData().execute();
        } else {
            updateMenuNavigation();
            displayView(R.id.nav_news_feed);
        }
    }

    private void updateMenuNavigation() {
        //Cap nhat menu cho  NavigationView
        SubMenu subMenu = navigationMenu.addSubMenu(R.string.category);

        for (FeedTopic topic : DataFactory.getInstance().data) {
            subMenu.add(Menu.NONE, topic.getNameTopic().hashCode(), Menu.NONE, topic.getNameTopic()).setIcon(topic.getNavIcon());
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_radio) {
//            toolbar.setVisibility(View.GONE);
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        item.setCheckable(true);
        item.setChecked(true);
        if (mPreviousMenuItem != null) {
            mPreviousMenuItem.setChecked(false);
        }
        mPreviousMenuItem = item;

        navigationView.setCheckedItem(id);
        displayView(id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void displayView(int id) {
        String title = getString(R.string.app_name);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = new SpecificColumnFragment();
        Bundle bundle = new Bundle();
        if (id == R.id.nav_news_feed) {
            fragment = new NewsFeedFragment();
            title = getString(R.string.news_feed);
        } else {
            for (int i = 0; i < DataFactory.getInstance().data.size(); i++) {
                FeedTopic topic = DataFactory.getInstance().data.get(i);
                if (topic.getNameTopic().hashCode() == id) {
                    bundle.putInt("idColumn", i);
                    title = topic.getNameTopic();
                    break;
                }
            }
        }

        fragment.setArguments(bundle);
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    class GetTopicData extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(MainActivity.this);
            progress.setTitle("Waiting");
            progress.setMessage("Get topic data...");
            progress.setCancelable(false);
            progress.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            FirebaseDatabase.getInstance().getReference().child("label").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //parse data from server
                    Iterator<DataSnapshot> listTopic = dataSnapshot.getChildren().iterator();
                    while (listTopic.hasNext()) {
                        FeedTopic tempTopic = new FeedTopic();
                        DataSnapshot topic = listTopic.next();
                        tempTopic.setNameTopic(topic.getKey());
                        Iterator<DataSnapshot> listReference = topic.getChildren().iterator();
                        while (listReference.hasNext()) {
                            tempTopic.getListReference().add((String) listReference.next().getValue());
                        }
                        for (FeedTopic current : DataFactory.getInstance().data) {
                            if (current.getNameTopic().equals(tempTopic.getNameTopic())) {
                                current.setListReference(tempTopic.getListReference());
                                break;
                            }
                        }
                    }
                    updateMenuNavigation();
                    displayView(R.id.nav_news_feed);
                    if (progress != null) {
                        progress.dismiss();
                        progress = null;
                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            return null;
        }
    }
}
