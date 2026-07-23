package EducacionIT_75402.EduIT;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.annotations.Test;

import utils.AccesoBD;

public class AccesoABaseDeDatosTest {
	@Test
    public void testBaseDeDatos() throws ClassNotFoundException, SQLException {

        Connection con = AccesoBD.abrirConexionBD(
                "localhost",
                "3306",
                "db",
                "root",
                "");

        //Query to Execute
        String query = "select * from empleados;";

           // Execute the SQL Query. Store results in ResultSet
         ResultSet rs= AccesoBD.ejecutarQuery(con, query);


         // While Loop to iterate through all data and print results
        while (rs.next()){

                String myId = rs.getString(1);
                String myName = rs.getString(2);
                String myAge = rs.getString(3);
                System.out.println(myId + " " + myName + " " + myAge);

        }

        System.out.println("Resultados de la Consulta a la Base de Datos");
        System.out.println("Prueba exitosa!");


          // closing DB Connection
          AccesoBD.cerrarConexionBD(con);

    }

}
