package com.innovagenesis.aplicaciones.android.secondactivityprojectnine;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Clase encargada de usar el template para ingresar
 * elementos en el recyclerView
 * Created by alexi on 11/04/2017.
 */

public class RecyclerViewHolderTarea extends RecyclerView.ViewHolder {

    public TextView nom_tarea_item, nom_asigna_item, nom_estudiante_item, nota_tarea_item;

    public RecyclerViewHolderTarea(View itemView) {
        super(itemView);

        nom_tarea_item = (TextView) itemView.findViewById(R.id.nom_tarea_item);
        nom_asigna_item = (TextView) itemView.findViewById(R.id.nom_asigna_item);
        nom_estudiante_item = (TextView) itemView.findViewById(R.id.nom_estudiante_item);
        nota_tarea_item = (TextView) itemView.findViewById(R.id.nota_tarea_item);
    }
}
