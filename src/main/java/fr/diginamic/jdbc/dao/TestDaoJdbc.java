package fr.diginamic.jdbc.dao;

import java.util.List;

import fr.diginamic.jdbc.entites.Fournisseur;

public class TestDaoJdbc {

	public static void main(String[] args) {
		
		FournisseurDao db = new FournisseurDaoJdbc();
		
//		Insérer un fournisseur appelé « France de matériaux »
		Fournisseur fournisseur = new Fournisseur("France de matériaux");
		db.insert(fournisseur);
		
//		Extraire la liste des fournisseurs de la base et les afficher
		List<Fournisseur> listeFou = db.extraire();
		System.out.println(listeFou);
		
//		Modifier le fournisseur appelé « France de matériaux » en « France matériaux »
		db.update(fournisseur.getNom(), "France matériaux");
		
//		Extraire la liste des fournisseurs de la base de données et les afficher à nouveau:
		listeFou = db.extraire();
		System.out.println(listeFou);
		
//		 supprimer le fournisseur « France matériaux »
		db.delete(fournisseur);
		
//		Extraire la liste des fournisseurs de la base de données et les afficher à nouveau:
		listeFou = db.extraire();
		System.out.println(listeFou);
		
//		Essayez d’insérer en base de données un fournisseur dont le nom contient un simple quote
//		comme par exemple « L’Espace Création ». Que se passe-t-il ?
		
		db.insert(new Fournisseur("L’Espace Création")); // Ca fonctionne parfaitement.
		listeFou = db.extraire();
		System.out.println(listeFou);


	}

}
