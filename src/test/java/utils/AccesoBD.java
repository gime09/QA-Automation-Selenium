package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccesoBD {
	public static Connection abrirConexionBD(
            String server,
            String port,
            String dbName,
            String user,
            String password) throws ClassNotFoundException, SQLException {
        //Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"
        String dbUrl = "jdbc:mysql://" +
                        server + ":" + port + "/" + dbName;

        System.out.println(dbUrl);

         //Load mysql jdbc driver
           Class.forName("com.mysql.jdbc.Driver");

           //Create Connection to DB
        Connection con = DriverManager.getConnection(dbUrl,user,password);

          return con;
    }

    public static ResultSet ejecutarQuery(
                        Connection con,
                        String query) throws SQLException {
        //Create Statement Object
        Statement stmt = con.createStatement();

           // Execute the SQL Query. Store results in ResultSet
         ResultSet rs= stmt.executeQuery(query);

         return rs;
    }

    public static void cerrarConexionBD(Connection con) throws SQLException {
        con.close();
    }

}
