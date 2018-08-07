package fr.pizzeria.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import fr.pizza.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

public class ListerPizzasService extends MenuService {

	@Override
	public void executeUC(Scanner scanner, IPizzaDao memDao) throws SQLException {
		ArrayList<Pizza> pizzas = memDao.findAllPizzas();

		for (int i = 0; i < pizzas.size(); i++) {

			System.out.println(pizzas.get(i));
		}

	}
}