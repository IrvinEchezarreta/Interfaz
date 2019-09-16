/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JTable;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author HP
 */
public class ExcelFunciones {
    
    Workbook book;
    
    public String Exportar(File archivo, JTable tabla){
        String mensaje="Error en la Exportacion";
        int NumeroFila=tabla.getRowCount(),NumeroColumna=tabla.getColumnCount();
        if(archivo.getName().endsWith("xls")){
            book=new HSSFWorkbook();
        }else{
            book=new XSSFWorkbook();
        }
        Sheet hoja=book.createSheet("Hoja1");
        
        try {
            for (int i = -1; i < NumeroFila; i++) {
                Row fila=hoja.createRow(i+1);
                for (int j = 0; j <NumeroColumna; j++) {
                    Cell celda=fila.createCell(j);
                    if(i==-1){
                        celda.setCellValue(String.valueOf(tabla.getColumnName(j)));
                    }else{
                        celda.setCellValue(String.valueOf(tabla.getValueAt(i, j)));
                    }
                    book.write(new FileOutputStream(archivo));
                }
            }
            mensaje="Exportacion Exitosa";
        } catch (Exception e) {
        }
        return mensaje;
    }
}
