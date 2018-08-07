package fr.pizzeria.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.pizza.dao.PizzaMemDao;
import fr.pizzeria.exception.StockageException;

public class ModifierPizzaServiceTest {

	/**
	 * Cr�ation d'une "Rule" qui va permettre de substituer le System.in utilis�
	 * par le Scanner par un mock: systemInMock
	 */

	@Rule
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

	@Test
	public void testExecuteUC() throws FileNotFoundException, StockageException, SQLException {

		MenuService service = null;
		PizzaMemDao memdao = new PizzaMemDao();
		assertEquals(8, memdao.findAllPizzas().size());
		service = new ModifierPizzaService();

		systemInMock.provideLines("PEP", "POP", "poporoni", "15", "POISSON");

		service.executeUC(new Scanner(System.in), memdao);

		assertEquals(8, memdao.findAllPizzas().size());
		assertNotNull(memdao.findPizzaByCode("POP"));

	}

}
