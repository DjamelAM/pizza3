package fr.pizza.dao;

import java.util.ArrayList;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaMemDao implements IPizzaDao {

	public ArrayList<Pizza> listPizza;

	public PizzaMemDao() {

		// tab
		listPizza = new ArrayList<Pizza>();

		listPizza.add(new Pizza(0, "PEP", "Pépéroni", 12.50, CategoriePizza.POISSON));
		listPizza.add(new Pizza(1, "MAR", "Margherita", 14.00, CategoriePizza.VIANDE));
		listPizza.add(new Pizza(2, "REIN", "La Reine", 11.50, CategoriePizza.SANS_VIANDE));
		listPizza.add(new Pizza(3, "FRO", "La 4 fromages", 12.00, CategoriePizza.VIANDE));
		listPizza.add(new Pizza(4, "CAN", "La cannibale", 12.50, CategoriePizza.POISSON));
		listPizza.add(new Pizza(5, "SAV", "La savoyarde", 13.00, CategoriePizza.SANS_VIANDE));
		listPizza.add(new Pizza(6, "ORI", "L’orientale", 13.50, CategoriePizza.POISSON));
		listPizza.add(new Pizza(7, "IND", "L’indienne", 14.00, CategoriePizza.POISSON));

	}

	public ArrayList<Pizza> findAllPizzas() {

		return listPizza;
	}

	public void saveNewPizza(Pizza pizza) {

		listPizza.add(pizza);

	}

	public void updatePizza(String codePizza, Pizza pizza) {

		deletePizza(codePizza);
		saveNewPizza(pizza);
	}

	public void deletePizza(String codePizza) {

		listPizza.remove(findPizzaByCode(codePizza));

	}

	public Pizza findPizzaByCode(String codePizza) {
		Pizza pizzaTrouver = null;
		for (int i = 0; i < listPizza.size(); i++) {
			if ((listPizza.get(i).getCode()).equals(codePizza)) {
				pizzaTrouver = listPizza.get(i);
			}
		}
		return pizzaTrouver;

	}

	public boolean pizzaExists(String codePizza) {
		if (findPizzaByCode(codePizza).getCode().equals(codePizza)) {

			return true;
		}

		return false;
	}

}
