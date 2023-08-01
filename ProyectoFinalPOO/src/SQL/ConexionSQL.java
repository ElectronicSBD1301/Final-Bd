package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQL {

	
	public static Connection getConexion(){
		String conexionUrl ="jdbc:sqlserver://192.168.100.118:1433;"
				+ "databasename=FinalNJD;"
				+ "user=jlopez;"
				+ "password=Jl2021hk;"
				+ "loginTimeout=30;"
				+ "encrypt = false;";
		
		try {
			Connection conx= DriverManager.getConnection(conexionUrl);
			return conx;
			
		}	catch(SQLException ex) {
			System.out.println(ex.toString());
			return null;
		}
	}
}
