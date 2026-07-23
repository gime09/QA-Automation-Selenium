package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase utilitaria para leer datos desde archivos Excel (.xlsx).
 * Devuelve un Object[][] compatible con @DataProvider de TestNG.
 */
public class DatosExcel {

    /**
     * Lee todas las filas (desde la fila 1, saltando el encabezado en fila 0)
     * de la hoja indicada y devuelve un Object[][].
     *
     * @param rutaArchivo Ruta absoluta o relativa al archivo .xlsx
     * @param nombreHoja  Nombre de la hoja dentro del Excel
     * @return Object[][] donde cada fila es un array de Strings con los valores de cada celda
     */
    public static Object[][] leerExcel(String rutaArchivo, String nombreHoja) {
        List<Object[]> datos = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(rutaArchivo);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet hoja = workbook.getSheet(nombreHoja);

            if (hoja == null) {
                throw new RuntimeException("No se encontró la hoja: " + nombreHoja);
            }

            int totalFilas = hoja.getLastRowNum(); // fila 0 = encabezado, se omite

            for (int i = 1; i <= totalFilas; i++) {
                Row fila = hoja.getRow(i);
                if (fila == null) continue;

                int totalColumnas = fila.getLastCellNum();
                String[] filaDatos = new String[totalColumnas];

                for (int j = 0; j < totalColumnas; j++) {
                    Cell celda = fila.getCell(j);
                    filaDatos[j] = obtenerValorCelda(celda);
                }

                datos.add(filaDatos);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo Excel: " + rutaArchivo, e);
        }

        return datos.toArray(new Object[0][]);
    }

    /**
     * Convierte cualquier tipo de celda de Excel a String.
     */
    private static String obtenerValorCelda(Cell celda) {
        if (celda == null) return "";

        switch (celda.getCellType()) {
            case STRING:
                return celda.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(celda)) {
                    return celda.getDateCellValue().toString();
                }
                // Evita que números enteros aparezcan como "1.0"
                double valor = celda.getNumericCellValue();
                if (valor == Math.floor(valor)) {
                    return String.valueOf((long) valor);
                }
                return String.valueOf(valor);
            case BOOLEAN:
                return String.valueOf(celda.getBooleanCellValue());
            case FORMULA:
                return celda.getCellFormula();
            case BLANK:
            default:
                return "";
        }
    }
}