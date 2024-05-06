package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TestDelete {
	
	private static final String DB_URL;
	private static final String DB_USER;
	private static final String DB_PWD;

	{

	}

	static {
		ResourceBundle dbConfig = ResourceBundle.getBundle("database");
		DB_URL = dbConfig.getString("database.url");
		DB_USER = dbConfig.getString("database.user");
		DB_PWD = dbConfig.getString("database.password");
	}

	public static void main(String[] args) {
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD );
				Statement st = connection.createStatement();){
		
			int nbSupp = st.executeUpdate("DELETE FROM FOURNISSEUR WHERE NOM = 'La Maison des Peintures'");
			System.out.println("nb elements suppr: " + nbSupp);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} 

	}

}
