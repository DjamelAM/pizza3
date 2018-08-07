package fr.pizza.dao;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.pizzeria.model.Pizza;

public interface IPizzaDao {

	ArrayList<Pizza> findAllPizzas() throws SQLException;

	void saveNewPizza(Pizza pizza) throws SQLException, FileNotFoundException;

	void updatePizza(String codePizza, Pizza pizza) throws SQLException;

	void deletePizza(String codePizza) throws SQLException;

	Pizza findPizzaByCode(String codePizza) throws SQLException;

	boolean pizzaExists(String codePizza) throws SQLException;

}
