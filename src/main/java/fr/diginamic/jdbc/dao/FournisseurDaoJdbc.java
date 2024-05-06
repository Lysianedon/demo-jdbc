package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.entites.Fournisseur;

public class FournisseurDaoJdbc implements FournisseurDao {

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

	@Override
	public List<Fournisseur> extraire() {
		List<Fournisseur> listeFournisseurs = new ArrayList<Fournisseur>();
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
				Statement st = connection.createStatement();
				ResultSet fournisseurs = st.executeQuery("SELECT * FROM FOURNISSEUR");) {

			while (fournisseurs.next()) {
				int id = fournisseurs.getInt("ID");
				String nom = fournisseurs.getString("NOM");
				listeFournisseurs.add(new Fournisseur(id, nom));
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return listeFournisseurs;
	}

	@Override
	public void insert(Fournisseur fournisseur) {
		String INSERT_QUERY = "INSERT INTO FOURNISSEUR (NOM) VALUES (?)";

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
				PreparedStatement ps = connection.prepareStatement(INSERT_QUERY)) {
			ps.setString(1, fournisseur.getNom());
			int nbNewSupplier = ps.executeUpdate();
			System.out.println("Nb fournisseurs insérés : " + nbNewSupplier);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		int nbUpdates = 0;
		String UPDATE_QUERY = "UPDATE FOURNISSEUR SET NOM = ? WHERE NOM = ?";

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
				PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);) {
			ps.setString(1, nouveauNom);
			ps.setString(2, ancienNom);
			nbUpdates = ps.executeUpdate();

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return nbUpdates;
	}

	@Override
	public boolean delete(Fournisseur fournisseur) {
		String DELETE_QUERY = "DELETE FROM FOURNISSEUR WHERE ID = ?";

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
				PreparedStatement ps = connection.prepareStatement(DELETE_QUERY);) {
			ps.setInt(1, fournisseur.getId());

			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

}
