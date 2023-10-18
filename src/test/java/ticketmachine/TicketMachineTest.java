package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation.
	void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
                // Les montants ont été correctement additionnés  
		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");              
	}

	@Test
	void impticketFalse() {
		machine.insertMoney(10);
		//La fonction qui imprime le ticket ne marche pas (return false)
		assertEquals(false, machine.printTicket(), "Le montant est insuffisant mais le ticket est imprime");
	}

	@Test
	void impticketTrue() {
		machine.insertMoney(70);
		//
		assertEquals(true, machine.printTicket(), "Le montant est suffisant mais le ticket n est pas imprime");
	}

	@Test
	void decremente() {
		machine.insertMoney(80);
		machine.printTicket();
		//
		assertEquals(80-50, machine.getBalance(), "La balance est pas decremente du prix du ticket");
	}

	@Test
	void maj() {
		machine.insertMoney(80);
		//
		assertEquals(0, machine.getTotal(), "La montant collecte est mis a jour avant l impression du ticket");
		machine.printTicket();
		assertEquals(50, machine.getTotal(), "La montant collecte n est pas mis a jour apres l impression du ticket");
	}

	@Test
	void verifrefund() {
		machine.insertMoney(80);
		//
		assertEquals(80, machine.refund(), "La monnaie a pas ete rendue ");
	}

	@Test
	void balanceajour() {
		machine.insertMoney(80);
		//
		machine.refund();
		assertEquals(0, machine.getBalance(), "La balance est bien remise a 0");
	}

	@Test
	void montantnegatif() {
		try {
			machine.insertMoney(-5);
			// Si on arrive ici, c'est qu'on n'a pas eu d'exception -> le test doit échouer
			fail("on ne peux pas mettre un montant negatif");
			} catch (IllegalArgumentException e) {
			// Si on arrive ici c'est normal, le test est réussi
			}
	}

	@Test
	void ticketnegatif() {
		try {
			TicketMachine mach = new TicketMachine(-5);
			// Si on arrive ici, c'est qu'on n'a pas eu d'exception -> le test doit échouer
			fail("on ne peux pas vendre un ticket de prix negatif");
		} catch (IllegalArgumentException e) {
			// Si on arrive ici c'est normal, le test est réussi
		}
	}




}
