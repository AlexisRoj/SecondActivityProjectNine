package com.innovagenesis.aplicaciones.android.secondactivityprojectnine;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        LoaderManager.LoaderCallbacks<Cursor> {

    public static final String nom_tarea = "nom_tarea";
    public static final String asingna_tarea = "asigna_tarea";
    public static final String estud_tarea = "estud_tarea";
    public static final String nota_tarea = "nota_tarea";



    //URI TAREAS
    private final String NOMBREPROVIDER2 = "com.innovagenesis.aplicaciones.android.examennueve" +
            ".provider.ProvedorContenidosTareas";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        LoaderManager.enableDebugLogging(true);
        getSupportLoaderManager().initLoader(2, null, MainActivity.this);

        this.setTitle(getString(R.string.listado));
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
        if (id == R.id.salir) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.salir) {
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                Uri.parse("content://" + NOMBREPROVIDER2 + "/cte"), null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {


        ArrayList<Tareas> arrayList = new ArrayList<>();

        try {
            data.moveToFirst();

            while (!data.isAfterLast()) {
                Tareas tareas = new Tareas();

                String nom_usuario = data.getString(data.getColumnIndex(estud_tarea));
                String comp_usuario = getIntent().getExtras().getString(Login.nom_usuario);

                if (nom_usuario.equals(comp_usuario)){

                    tareas.setNomTarea(data.getString(data.getColumnIndex(nom_tarea)));
                    tareas.setNomAsignaTarea(data.getString(data.getColumnIndex(asingna_tarea)));
                    tareas.setNomEstuTarea(data.getString(data.getColumnIndex(estud_tarea)));
                    tareas.setNotaTarea(data.getInt(data.getColumnIndex(nota_tarea)));

                    arrayList.add(tareas);

                }

                data.moveToNext();
            }
        } catch (Exception e) {
            Toast.makeText(this, "No se pudo recuperar datos", Toast.LENGTH_SHORT).show();
        }

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        RecyclerViewAdapterTarea adapter = new RecyclerViewAdapterTarea(arrayList,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
