package com.innovagenesis.aplicaciones.android.secondactivityprojectnine;

import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Login extends AppCompatActivity implements DialogoLogin.DatosHacerLogin,
        LoaderManager.LoaderCallbacks<Cursor> {


    public static final String nom_usuario = "nom_usuario";
    public final String pass_usuario = "pass_usuario";
    public final String rol_user = "rol_user";

    private String compar_usuario;
    private String comparar_contrasena;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DialogoLogin dialogoLogin = new DialogoLogin();
        dialogoLogin.show(getSupportFragmentManager(), DialogoLogin.TAG);
        dialogoLogin.setCancelable(false); // Evita que se cierre el dialogo


        this.setTitle("Ingreso al sistema");

        if (!mValidarConexion()) { // Valida si existe conexion
            Toast.makeText(this, "Error al establecef conexion", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Valida si existe conexion
     */
    private boolean mValidarConexion() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    /**
     * Realiza la validacion del usuario y envia el Json para listar las tareas
     * del usuario
     */
    @Override
    public void HacerLogin(String usuario, String contrasena, Boolean recordar) {
        compar_usuario = usuario;
        comparar_contrasena = contrasena;

        //Incia el loader del provider
        getSupportLoaderManager().initLoader(1, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String NOMBREPROVIDER = "com.innovagenesis.aplicaciones.android.examennueve" +
                ".provider.ProvedorContenidosUsuarios";
        return new CursorLoader(this,
                Uri.parse("content://" + NOMBREPROVIDER + "/cte"), null, null, null, null);
    }

    /** Carga el cursor y valida que los elementos listados sean deacuerdo
     * al usurio ingresado*/
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        String temp_user;
        String temp_pass;
        int temp_rol;

        try {
            data.moveToFirst();

            while (!data.isAfterLast()) {

                temp_user = data.getString(data.getColumnIndex(nom_usuario));
                temp_pass = data.getString(data.getColumnIndex(pass_usuario));
                temp_rol = data.getInt(data.getColumnIndex(rol_user));

                /** Compara la activity y envia el usuario que va a ser listado*/
                if ((temp_user.equals(compar_usuario) )
                        && (temp_pass.equals(comparar_contrasena))
                        && temp_rol == 3) {
                    mCargarActivity(temp_user);
                }
                data.moveToNext();
            }
        } catch (Exception e) {
            Toast.makeText(Login.this, "No se pudo recuperar datos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    /**
     * Método encargado de realizar la carga del main
     * */
    private void mCargarActivity(String usuario) {
        Intent intent = new Intent(Login.this, MainActivity.class);
        intent.putExtra(nom_usuario,usuario);
        startActivity(intent);
        finish();
    }
}
