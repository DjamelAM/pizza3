package fr.pizzeria.service;

import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import fr.pizza.dao.IPizzaDao;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class ModifierPizzaService extends MenuService {

	@Override
	public void executeUC(Scanner sc, IPizzaDao memDao) throws UpdatePizzaException, SQLException {
		System.out.println("Liste des pizzas");
		System.out.println("Veuillez saisir le code de la pizza � modifier :");

		String modifCode = sc.next().toUpperCase();

		// if (memDao.findPizzaByCode(modifCode) == null) {
		//
		// throw new UpdatePizzaException("choisir un code existant ");
		// }
		System.out.println("Veuillez saisir le code :");
		String nCode = sc.next().toUpperCase();
		System.out.println("Veuillez saisir le nom :");
		String nNom = sc.next().toUpperCase();

		System.out.println("Veuillez saisir le prix :");

		String nPrix = sc.next();
		if (!NumberUtils.isCreatable(nPrix)) {
			throw new UpdatePizzaException("Lr prix doit �tre en chiffre");
		}
		if (nPrix.contains("-")) {

			throw new UpdatePizzaException("prix n�gatif impossible");
		}

		System.out.println("Veuillez saisir la cat�gorie :");
		String categorie = sc.next().toUpperCase();
		CategoriePizza[] tab = CategoriePizza.values();

		int a = 0;

		for (int i = 0; i < tab.length; i++) {
			if (tab[i].name().equalsIgnoreCase(categorie)) {
				Pizza newpizza = new Pizza(nCode, nNom, Double.parseDouble(nPrix), CategoriePizza.valueOf(categorie));

				memDao.updatePizza(modifCode, newpizza);
				a = 1;
				break;
			}
		}
		if (a == 0) {
			throw new UpdatePizzaException("choisir une cat�gorie existante ");
		}

		// System.out.println("Veuillez saisir le nouveau code :");
		// String nCode1 = sc.next();
		// System.out.println("Veuillez saisir le nouveau nom :");
		// String nNom1 = sc.next();
		// System.out.println("Veuillez saisir le nouveau prix :");
		// double nPrix1 = sc.nextDouble();
		// System.out.println("Veuillez saisir la cat�gorie :");
		// String categorie = sc.next().toUpperCase();

	}

}
