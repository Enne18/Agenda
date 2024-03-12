package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import agenda.*;

/** 
* @author	Matteo Cartieri 20044003
* @author	Nicholas Ternullo 20045859
*/

class AgendeTest {

	@BeforeEach
	void testCreazione() 
	{
		Agende.crea();
	}
	
	@AfterEach
	void testReset() 
	{
		Agende.svuota();
	}
	
	@Test
	void testAggiungiCorrettamente() throws GestoreException
	{
		int numEl=Agende.numAgende();
		Agenda a1=new Agenda();
		int ris=Agende.aggiungiAgenda(a1);
		assertEquals(numEl+1,Agende.numAgende());
		assertEquals(1,ris);
	}
	
	@Test
	void testAggiungiEsistente() throws GestoreException
	{
		GestoreException exc = assertThrows(GestoreException.class, () -> 
		{Agenda a1=new Agenda("Scuola");
		Agenda a2=new Agenda("Scuola");
		Agende.aggiungiAgenda(a1);
		Agende.aggiungiAgenda(a2);
		assertEquals(1,Agende.numAgende());});
		assertEquals(exc.getMessage(),"Agenda uguale\n");
	}
	
	@Test
	void testCercaTrovato() throws GestoreException
	{
		Agenda a1=new Agenda("Scuola");
		Agenda a2=new Agenda("Upo");
		Agenda a3=new Agenda("Amici");
		Agende.aggiungiAgenda(a1);
		Agende.aggiungiAgenda(a2);
		Agende.aggiungiAgenda(a3);
		assertEquals(a1,Agende.getAgenda("Scuola"));
	}
	
	@Test
	void testCercaNonTrovato() throws GestoreException
	{
		GestoreException exc = assertThrows(GestoreException.class, () -> 
		{Agenda a1=new Agenda("Scuola");
		Agenda a2=new Agenda("Upo");
		Agenda a3=new Agenda("Amici");
		Agende.aggiungiAgenda(a1);
		Agende.aggiungiAgenda(a2);
		Agende.aggiungiAgenda(a3);
		Agende.getAgenda("Agenda");});
		assertEquals(exc.getMessage(),"Agenda non trovata\n");
	}
	
	@Test
	void testEliminaCorretto() throws GestoreException
	{
		Agenda a1=new Agenda("Scuola");
		Agenda a2=new Agenda("Upo");
		Agenda a3=new Agenda("Amici");
		Agende.aggiungiAgenda(a1);
		Agende.aggiungiAgenda(a2);
		Agende.aggiungiAgenda(a3);
		assertTrue(Agende.rimuoviAgenda("Upo"));
		assertEquals(2,Agende.numAgende());
	}
	
	@Test
	void testEliminaAssente() throws GestoreException
	{
		GestoreException exc = assertThrows(GestoreException.class, () -> 
		{Agenda a1=new Agenda("Scuola");
		Agenda a2=new Agenda("Upo");
		Agenda a3=new Agenda("Amici");
		Agende.aggiungiAgenda(a1);
		Agende.aggiungiAgenda(a2);
		Agende.aggiungiAgenda(a3);
		Agende.rimuoviAgenda("Agenda");});
		assertEquals(exc.getMessage(),"Agenda non trovata\n");
	}
	
	@Test
	void testToString() throws GestoreException
	{
		Agenda a1=new Agenda("Scuola");
		Agenda a2=new Agenda("Upo");
		Agenda a3=new Agenda("Amici");
		Agende.aggiungiAgenda(a1);
		Agende.aggiungiAgenda(a2);
		Agende.aggiungiAgenda(a3);
		assertEquals(Agende.agendeToString(),"Scuola\nUpo\nAmici\n");
		Agende.svuota();
		GestoreException exc = assertThrows(GestoreException.class, () -> 
		{Agende.agendeToString();});
		assertEquals(exc.getMessage(),"Nessuna agenda!\n");
	}
}
