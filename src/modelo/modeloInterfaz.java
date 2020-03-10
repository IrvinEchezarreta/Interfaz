/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/*////////////////////////////////////////////////////
@author Meew
Esta la clase del modelo de la interfaz, donde se guardan las cosas 
y se setean en otras clases
////////////////////////////////////////////////////*/


public class modeloInterfaz {



   
    public String lbCarrera2;           //El campo donde se muestra el nombre de la carrera del alumno
    public String lbFechaHora2;         //El campo donde se muestran la fecha y la hora del sistema
    public String lbIngreso2;           //El campo donde se muestra el numero de ingreso del alumno
    public String lbNombre2;            //El campo donde se muestra el nombre del alumno
    public String tfMatricula;          //El campo donde el alumno ingresa su matricula

    /////Getters y Setters/////
    
    public String getLbCarrera2() {
        return lbCarrera2;
    }

    public void setLbCarrera2(String lbCarrera2) {
        this.lbCarrera2 = lbCarrera2;
    }

    public String getLbFechaHora2() {
        return lbFechaHora2;
    }

    public void setLbFechaHora2(String lbFechaHora2) {
        this.lbFechaHora2 = lbFechaHora2;
    }

    public String getLbIngreso2() {
        return lbIngreso2;
    }

    public void setLbIngreso2(String lbIngreso2) {
        this.lbIngreso2 = lbIngreso2;
    }

    public String getLbNombre2() {
        return lbNombre2;
    }

    public void setLbNombre2(String lbNombre2) {
        this.lbNombre2 = lbNombre2;
    }

    public String getTfMatricula() {
        return tfMatricula;
    }

    public void setTfMatricula(String tfMatricula) {
        this.tfMatricula = tfMatricula;
    }
} 