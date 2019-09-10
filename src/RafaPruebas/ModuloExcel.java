package RafaPruebas;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook; 
import org.apache.poi.ss.usermodel.Cell; 
import org.apache.poi.ss.usermodel.Row;
 
import java.io.FileOutputStream;
import java.io.IOException;
 


/**
 * A very simple program that writes some data to an Excel file
 * using the Apache POI library.
 * @author www.codejava.net
 *
 */





public class ModuloExcel {
 
    
    public static void main(String[] args) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();  //Crea un objeto de la clase workbook que se encuentra en la libreria de ApachePOI
        HSSFSheet sheet = workbook.createSheet("Hoja 1"); //Crea una hoja con este nombre
         
        //Se crea un arreglo multidimensional que va a contener la informacion que se va a colocar en la hoja de excel
        Object[][] bookData = {
                {"Matricula1", "Nombre1", "correo@correo.com"},  
                {"Matricula2", "Nombre2", "correo2@correo.com"},
                {"Matricula3", "Nombre3", "correo3@correo.com"},
                {"Matricula4", "Nombre4", "correo4@correo.com"},
        };
 
           //contador de filas
        int rowCount = -1;
         
            //ciclo for por cada posicion de bookdata
        for (Object[] aBook : bookData) {
            
            //row = crear fila
            Row row = sheet.createRow(++rowCount);
            //La fila se inicializa con row count
            
            int columnCount = -1;
             
            for (Object field : aBook) {
                Cell cell = row.createCell(++columnCount);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
             
        }
         
         
        try (FileOutputStream outputStream = new FileOutputStream("ExcelPrueba.xlsx")) {
            workbook.write(outputStream);
        }
    }
 
}

