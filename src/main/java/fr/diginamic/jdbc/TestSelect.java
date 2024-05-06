package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.entites.Fournisseur;

public class TestSelect {

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
			
			ResultSet suppliers = st.executeQuery("SELECT * FROM FOURNISSEUR");
			ArrayList suppliersList = new ArrayList<Fournisseur>();
			
			while(suppliers.next()) {
				int id = suppliers.getInt("ID");
				String nom = suppliers.getString("NOM");
				
//				System.out.println(id + " " + nom);
				Fournisseur supplier = new Fournisseur(id, nom);
				suppliersList.add(supplier);
			}
			System.out.println(suppliersList);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} 

	}

}
