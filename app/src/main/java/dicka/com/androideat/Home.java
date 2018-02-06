package dicka.com.androideat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import dicka.com.androideat.common.DataUserLogin;
import dicka.com.androideat.interfaces.ItemClickListener;
import dicka.com.androideat.model.Category;
import dicka.com.androideat.views.ViewsMenus;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    //deklarasi firbase
    FirebaseDatabase database;
    DatabaseReference tabel_catgory;
    TextView txtFullNama;

    //Recycler View
    RecyclerView menuRecycler;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Category, ViewsMenus> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //menambahkan title menu di halaman home
        toolbar.setTitle("Menu Food Traveller");

        setSupportActionBar(toolbar);

        //load data dari database firebase
        database = FirebaseDatabase.getInstance();
        tabel_catgory = database.getReference("catgory");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //load nama user yang aktive ketika login berhasil
        View headerView = navigationView.getHeaderView(0);
        txtFullNama = (TextView)headerView.findViewById(R.id.txtGetUserFromLogin);
        //txtFullNama.setText(String.valueOf(DataUserLogin.currentUser.getNama()));
        txtFullNama.setText(DataUserLogin.currentUser.getNama());

        //load menu category
        menuRecycler = (RecyclerView) findViewById(R.id.recycler_menu);
        menuRecycler.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        menuRecycler.setLayoutManager(layoutManager);
        loadMenu();
    }

    //load menu adapter dari category di binding ke content_home
    private void loadMenu(){
        adapter =
                new FirebaseRecyclerAdapter<Category, ViewsMenus>
                        (Category.class, R.layout.menu_item, ViewsMenus.class, tabel_catgory) {

            @Override
            protected void populateViewHolder(ViewsMenus viewHolder, Category model, int position) {
                viewHolder.textViewNamaMenuFood.setText(model.getNama());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.loadViewsImageFood);
                final Category clickItem = model;

                //category
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                       //get food berdasarkan kategori yang di klik
                        Intent foodList = new Intent(Home.this, FoodList.class);
                        foodList.putExtra("CategoryId", adapter.getRef(position).getKey());
                        startActivity(foodList);
                    }
                });
            }
        };
        menuRecycler.setAdapter(adapter);
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
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_menu) {
            // Handle the camera action
        } else if (id == R.id.nav_cart) {

        } else if (id == R.id.nav_orders) {

        } else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
