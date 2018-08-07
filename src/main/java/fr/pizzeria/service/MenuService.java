package fr.pizzeria.service;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import fr.pizza.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

public abstract class MenuService {

	public abstract void executeUC(Scanner sc, IPizzaDao memDao)
			throws StockageException, FileNotFoundException, SQLException;

	public void executeUC(Scanner scanner, IPizzaDao memDao, Connection connexion) throws SQLException {
		// TODO Auto-generated method stub

	}

}
