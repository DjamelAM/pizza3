package fr.pizzeria.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.pizza.dao.PizzaMemDao;
import fr.pizzeria.exception.StockageException;

public class SupprimerPizzaServiceTest {

	/**
	 * Création d'une "Rule" qui va permettre de substituer le System.in utilisé
	 * par le Scanner par un mock: systemInMock
	 */

	@Rule
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

	@Test
	public void testExecuteUC() throws FileNotFoundException, StockageException, SQLException {

		MenuService service = null;
		PizzaMemDao memdao = new PizzaMemDao();
		assertEquals(8, memdao.findAllPizzas().size());
		// on ajoute pep dans le mock
		systemInMock.provideLines("PEP");

		service = new SupprimerPizzaService();
		service.executeUC(new Scanner(System.in), memdao);

		assertEquals(7, memdao.findAllPizzas().size());
		assertNull(memdao.findPizzaByCode("PEP"));

	}

}
