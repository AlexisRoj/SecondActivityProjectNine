package com.innovagenesis.aplicaciones.android.secondactivityprojectnine;

import java.io.Serializable;

/**
 * Clase encargada de administrar el objeto tarea
 * Created by alexi on 04/04/2017.
 */

public class Tareas implements Serializable{

    public int idTarea;
    public String nomTarea;
    public int idAsignaTarea;
    public String nomAsignaTarea;
    public int idEstuTarea;
    public String nomEstuTarea;
    public int notaTarea;

    public Tareas() {
        //Constructor vacio
    }

    /**
     *  Constructor que muestra los datos del listar
     *  */
    public Tareas(String nomTarea, String nomAsignaTarea, String nomEstuTarea, int notaTarea) {
        this.nomTarea = nomTarea;
        this.nomAsignaTarea = nomAsignaTarea;
        this.nomEstuTarea = nomEstuTarea;
        this.notaTarea = notaTarea;
    }

    /**
     * Constructor para guardas las tareas
     * */
    public Tareas(String nomTarea, int idAsignaTarea, int idEstuTarea, int notaTarea) {
        this.nomTarea = nomTarea;
        this.idAsignaTarea = idAsignaTarea;
        this.idEstuTarea = idEstuTarea;
        this.notaTarea = notaTarea;
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public String getNomTarea() {
        return nomTarea;
    }

    public void setNomTarea(String nomTarea) {
        this.nomTarea = nomTarea;
    }

    public int getIdAsignaTarea() {
        return idAsignaTarea;
    }

    public void setIdAsignaTarea(int idAsignaTarea) {
        this.idAsignaTarea = idAsignaTarea;
    }

    public String getNomAsignaTarea() {
        return nomAsignaTarea;
    }

    public void setNomAsignaTarea(String nomAsignaTarea) {
        this.nomAsignaTarea = nomAsignaTarea;
    }

    public int getIdEstuTarea() {
        return idEstuTarea;
    }

    public void setIdEstuTarea(int idEstuTarea) {
        this.idEstuTarea = idEstuTarea;
    }

    public String getNomEstuTarea() {
        return nomEstuTarea;
    }

    public void setNomEstuTarea(String nomEstuTarea) {
        this.nomEstuTarea = nomEstuTarea;
    }

    public int getNotaTarea() {
        return notaTarea;
    }

    public void setNotaTarea(int notaTarea) {
        this.notaTarea = notaTarea;
    }
}
