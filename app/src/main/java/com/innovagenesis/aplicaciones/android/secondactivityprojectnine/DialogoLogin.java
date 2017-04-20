package com.innovagenesis.aplicaciones.android.secondactivityprojectnine;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;


/**
 * Dialogo para inicio de seccion en el sistema
 * Created by alexi on 01/04/2017.
 */

public class DialogoLogin extends DialogFragment {

    public static final String TAG = "dialogo_inicio_sesión";

    private String nombreUsuario = "nombre_usuario";
    private String contrasenaUsuario = "contrasena_usuario";

    private CheckBox checkBoxRecordar;

    public interface DatosHacerLogin {
        void HacerLogin(String usuario, String contrasena, Boolean recordar);
    }

    private DatosHacerLogin listener;
    private TextInputEditText txtUsuario;
    private TextInputEditText txtPass;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        @SuppressLint("InflateParams")
        final View view = LayoutInflater.from(getContext())
                .inflate(R.layout.login_fragment, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);

        /* seccion de textInput*/
        final TextInputLayout textInputUser = (TextInputLayout) view.findViewById(R.id.textInputLayoutUser);
        final TextInputLayout textInputPass = (TextInputLayout) view.findViewById(R.id.textInputLayoutPass);

        txtUsuario = (TextInputEditText) view.findViewById(R.id.text_input_user);
        txtPass = (TextInputEditText) view.findViewById(R.id.text_input_pass);
        checkBoxRecordar = (CheckBox) view.findViewById(R.id.chkRecordar);

        Button btnLogin = (Button) view.findViewById(R.id.btn_login);
        Button btnSalirogin = (Button) view.findViewById(R.id.btn_salir_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean recordarLogin = false; //Valida que este marcado el check
                Boolean enviarInterface = true; //Activa la interface

                if (checkBoxRecordar.isChecked())
                    recordarLogin = true;

                /* Validacion de los text inputs*/
                String errorUsuario = "";
                if (TextUtils.isEmpty(txtUsuario.getText())) {
                    errorUsuario = getString(R.string.campoVacio);
                    enviarInterface = false;
                } else
                    nombreUsuario = txtUsuario.getText().toString();
                mValidarTextInput(textInputUser, errorUsuario);

                String errorPass = "";
                if (TextUtils.isEmpty(txtPass.getText())) {
                    errorPass = getString(R.string.campoVacio);
                    enviarInterface = false;
                } else
                    contrasenaUsuario = txtPass.getText().toString();
                mValidarTextInput(textInputPass, errorPass);

                if (enviarInterface)
                    listener.HacerLogin(nombreUsuario, contrasenaUsuario, recordarLogin);
            }
        });
        btnSalirogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return builder.create();
    }

    /**
     * Valida que los datos del textInput no esten vacios
     */
    private void mValidarTextInput(TextInputLayout textInput, String mensajeError) {
        textInput.setError(mensajeError);
        if (mensajeError == null) {
            textInput.setErrorEnabled(false);
        } else
            textInput.setErrorEnabled(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (DatosHacerLogin) getContext();
        } catch (ClassCastException e) {
            throw new ClassCastException("La inteface no ha sido implementada en el activity");
        }
    }
}
