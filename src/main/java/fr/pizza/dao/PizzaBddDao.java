package fr.pizza.dao;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaBddDao implements IPizzaDao {
	ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
	private String path;

	public Connection ConnexionBdd() throws SQLException {
		Connection connexion = null;
		List<String> lines = null;
		// TEST !! lire un fichier pour se log
		try {
			lines = FileUtils.readLines(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String line : lines) {
			StringTokenizer decoupeur = new StringTokenizer(line, ",");
			String jdcUrl = decoupeur.nextToken();
			String nomUtilisateur = decoupeur.nextToken();
			String motDePasse = decoupeur.nextToken();

			// String jdcUrl = "jdbc:mysql://localhost:3306/test";
			//
			// // Nom utilisateur d'accès à la bdd
			// String nomUtilisateur = "root";
			//
			// // Mot de passe d'accès à la bdd
			// String motDePasse = "";

			connexion = DriverManager.getConnection(jdcUrl, nomUtilisateur, motDePasse);

		}
		return connexion;

	}

	@Override
	public ArrayList<Pizza> findAllPizzas() throws SQLException {
		Connection connexion = ConnexionBdd();
		Statement statement = connexion.createStatement();
		ArrayList<Pizza> pizzas1 = new ArrayList<Pizza>();
		// Récupération du curseur de résultat de l'exécution de la
		// requête SQL
		ResultSet resultSet = statement.executeQuery("SELECT * FROM pizzas");
		while (resultSet.next()) {
			// int id = resultSet.getInt("ID");
			String code = resultSet.getString("CODE");
			String nom = resultSet.getString("NOM");
			Double prix = resultSet.getDouble("PRIX");
			CategoriePizza categorie = CategoriePizza.valueOf(resultSet.getString("CATEGORIE").toUpperCase());
			Pizza pizza = new Pizza(nom, code, prix, categorie);
			pizzas1.add(pizza);
		}
		if (pizzas.isEmpty()) {
			System.out.println("c'est vide");
		}
		resultSet.close();
		statement.close();
		connexion.close();
		pizzas = pizzas1;
		return pizzas;

	}

	@Override
	public void saveNewPizza(Pizza pizza) throws SQLException {
		Connection connexion = ConnexionBdd();
		PreparedStatement statement = connexion
				.prepareStatement("INSERT INTO pizzas(CODE, NOM, PRIX, CATEGORIE) VALUES (?, ?, ?, ?)");

		String code = pizza.getCode();
		String nom = pizza.getLibelle();
		Double prix = pizza.getPrix();
		CategoriePizza categorie = pizza.getCategorie();

		statement.setString(1, code);
		statement.setString(2, nom);
		statement.setDouble(3, prix);
		statement.setString(4, categorie.toString());

		ResultSet resultSet = statement.executeQuery();
		resultSet.close();
		statement.close();
		connexion.close();
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws SQLException {
		Connection connexion = ConnexionBdd();
		PreparedStatement statement = connexion
				.prepareStatement("UPDATE pizzas SET CODE=?, NOM=?, PRIX=?, CATEGORIE=? WHERE CODE =?");

		String code = pizza.getCode();
		String nom = pizza.getLibelle();
		Double prix = pizza.getPrix();
		CategoriePizza categorie = pizza.getCategorie();

		statement.setString(1, code);
		statement.setString(2, nom);
		statement.setDouble(3, prix);
		statement.setString(4, categorie.toString());
		statement.setString(5, codePizza);

		ResultSet resultSet = statement.executeQuery();
		resultSet.close();
		statement.close();
		connexion.close();
	}

	@Override
	public void deletePizza(String codePizza) throws SQLException {
		Connection connexion = ConnexionBdd();
		PreparedStatement statement = connexion.prepareStatement("DELETE FROM pizzas WHERE CODE =?");
		statement.setString(1, codePizza);
		ResultSet resultSet = statement.executeQuery();
		resultSet.close();
		statement.close();
		connexion.close();
	}

	@Override
	public Pizza findPizzaByCode(String codePizza) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean pizzaExists(String codePizza) {
		// TODO Auto-generated method stub
		return false;
	}

}
