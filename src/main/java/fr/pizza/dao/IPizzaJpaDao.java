package fr.pizza.dao;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.pizzeria.model.Pizza;

public class IPizzaJpaDao implements IPizzaDao {

	EntityManagerFactory emf;
	EntityManager em;

	public void connexion() {

		emf = Persistence.createEntityManagerFactory("pizza-console");
		em = emf.createEntityManager();

	}

	public void fermeture() {
		emf.close();
		em.close();
	}

	@Override
	public ArrayList<Pizza> findAllPizzas() throws SQLException {
		connexion();
		TypedQuery<Pizza> query = em.createQuery("SELECT p FROM Pizza p", Pizza.class);
		ArrayList<Pizza> pizzas = (ArrayList<Pizza>) query.getResultList();
		fermeture();
		return pizzas;
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws SQLException, FileNotFoundException {
		connexion();
		EntityTransaction transaction = this.em.getTransaction();
		transaction.begin();
		this.em.persist(pizza);
		transaction.commit();
		fermeture();
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws SQLException {
		connexion();
		EntityTransaction transaction = this.em.getTransaction();
		transaction.begin();
		Query query = em.createQuery(
				"UPDATE Pizza p SET p.code= :newCode, p.nom =:newNom, p.prix =:newPrix, p.categorie=:newCategorie WHERE p.code=:oldCode");
		query.setParameter("newPrix", pizza.getPrix());
		query.setParameter("newCode", pizza.getCode());
		query.setParameter("newNom", pizza.getLibelle());
		query.setParameter("newCategorie", pizza.getCategorie());
		query.setParameter("oldCode", codePizza);

		query.executeUpdate();
		transaction.commit();

		fermeture();
	}

	@Override
	public void deletePizza(String codePizza) throws SQLException {
		connexion();
		EntityTransaction transaction = this.em.getTransaction();
		transaction.begin();

		Query query = em.createQuery("DELETE FROM Pizza p WHERE p.code=:codeP");
		query.setParameter("codeP", codePizza);

		query.executeUpdate();

		transaction.commit();

		fermeture();

	}

	@Override
	public Pizza findPizzaByCode(String codePizza) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean pizzaExists(String codePizza) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
