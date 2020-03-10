/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author molef
 */
public class VisitantesFunciones extends conexion{
    
     public String hora (){
    String pattern = "dd/MM/yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    long millis=System.currentTimeMillis();  
    String date = simpleDateFormat.format(new Date());
    java.sql.Time time=new java.sql.Time(millis);  
    String Fecha=(""+date+"  "+time);
    System.out.println("fecha:"+Fecha);
    return Fecha;
    }
    
  
    public void registro_SQL_VIS (String lugares[]){
        
        //String Consulta = ("SELECT FROM alumnos WHERE MATRICULA ="+Matricula+"");
        //La matricula es valida ???
        Connection con = getConexion();
        
        try{    
            String pattern = "d/MM/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            long millis=System.currentTimeMillis();  
            String date = simpleDateFormat.format(new Date());
            java.sql.Time time=new java.sql.Time(millis);   

          
           ArrayList<String> Lugares = new ArrayList<String>();
           for (int i = 0; i < lugares.length ; i++) {
                if (lugares[i]==null||lugares[i]==""){
                    
                     
                }else{
                 //    Areas  = Areas + lugares[i] + ",";  
                    Lugares.add(lugares[i]);
                     //contadorAreas = contadorAreas+1;
                }
                
            }
          
 
               
               
              //Matricula/Nombre/Segundo_Nombre/Apellido_Paterno/Apellido_Materno/Nombre_Carrera/Semestre/Tipo/AreasVisitadas/Fecha/Hora
              //La cadena SQL que sera introducida
             
              for (String Lugar : Lugares) {
                   String query = " insert into visitantes ( tipo, area, fecha, hora)"
                + " values (?, ?, ?, ?)";

                
            
                    PreparedStatement preparedStmt = con.prepareStatement(query);
              preparedStmt.setString (1, "Visitante");
              preparedStmt.setString (2, Lugar);
              preparedStmt.setString (3, date);
              preparedStmt.setTime(4, time);
                 System.out.println("FUNCIONO!!");
              // Se ejecuta la orden preparada
                preparedStmt.execute();
              
              
              }
              
              
              // create the mysql insert preparedstatement
            

                  con.close();
            }
                catch (Exception e)
            {
              System.err.println("Got an exception!");
              System.err.println(e.getMessage());
            }



                }

    
}
