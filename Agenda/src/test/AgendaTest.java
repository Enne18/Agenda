package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import agenda.*;

class AgendaTest {

	@BeforeEach
	void testCostruttoreCompleto(){
		Agenda a1=new Agenda("Scuola",8);
		assertEquals("Scuola",a1.NOME);
		assertEquals(8,a1.MAX_DIM);
		assertEquals(0,a1.numeroAppuntamenti());
	}
	
	@Test 
	void testCostruttoreSoloNome()
	{
		Agenda a1=new Agenda("Scuola");
		assertEquals("Scuola",a1.NOME);
		assertEquals(10,a1.MAX_DIM);
		assertEquals(0,a1.numeroAppuntamenti());
	}
	
	@Test 
	void testCostruttoreSoloDim()
	{
		Agenda a1=new Agenda(8);
		assertEquals("Agenda" + (Agenda.getNumeroAgende()-1),a1.NOME);
		assertEquals(8,a1.MAX_DIM);
		assertEquals(0,a1.numeroAppuntamenti());
	}
	

	@Test
	void testCostruttoreDefault() {
		Agenda a1=new Agenda();
		assertEquals("Agenda" + (Agenda.getNumeroAgende()-1),a1.NOME);
		assertEquals(10,a1.MAX_DIM);
		assertEquals(0,a1.numeroAppuntamenti());
	}
	
	@Test
	public void testIteratore() throws GestoreException
	{
		Agenda agenda1=new Agenda("Scuola",3);
		Iterator<Appuntamento> iter = agenda1.iterator();
		
		Appuntamento app1= new Appuntamento("18/10/2001","15:30","20","Nicholas","UPO");
		agenda1.aggiungiAppuntamento(app1);
		Appuntamento app2= new Appuntamento("18/10/2001","16:30","20","Matteo","UPO");
		agenda1.aggiungiAppuntamento(app2);
		Appuntamento app3= new Appuntamento("20/10/2001","17:30","20","Nicholas","UPO");
		agenda1.aggiungiAppuntamento(app3);
	
		assertTrue(iter.hasNext());
		assertEquals(iter.next().getNome(),"Nicholas");
		assertTrue(iter.hasNext());
		assertEquals(iter.next().getNome(),"Matteo");
		assertTrue(iter.hasNext());
		assertEquals(iter.next().getNome(),"Nicholas");
		assertFalse(iter.hasNext());
	}
	
	@Test
	void testAggiungiCorretto() throws GestoreException {
		Agenda agenda1=new Agenda("Scuola",8);
		Appuntamento app1= new Appuntamento("18/10/2001","15:30","20","Nicholas","UPO");
		assertEquals(1,agenda1.aggiungiAppuntamento(app1));
		Appuntamento app2= new Appuntamento("19/10/2001","16:30","20","Nicholas","UPO");
		assertEquals(1,agenda1.aggiungiAppuntamento(app2));
		Iterator<Appuntamento> iter = agenda1.iterator();
		assertEquals(iter.next(),app1);
		assertEquals(iter.next(),app2);
	}
	
	@Test
	void testAggiungiOltreDimensione() throws GestoreException {
		GestoreException exc = assertThrows(GestoreException.class, () -> 
		{Agenda agenda1=new Agenda("Scuola",3);
		Appuntamento app1= new Appuntamento("18/10/2001","15:30","20","Nicholas","UPO");
		assertEquals(1,agenda1.aggiungiAppuntamento(app1));
		Appuntamento app2= new Appuntamento("19/10/2001","16:30","20","Nicholas","UPO");
		assertEquals(1,agenda1.aggiungiAppuntamento(app2));
		Appuntamento app3= new Appuntamento("20/10/2001","17:30","20","Nicholas","UPO");
		assertEquals(1,agenda1.aggiungiAppuntamento(app3));
		Appuntamento app4= new Appuntamento("21/10/2001","17:30","30","Nicholas","UPO");
		assertEquals(-1,agenda1.aggiungiAppuntamento(app4));});
		
		assertEquals("Ecceduta dimensione\n", exc.getMessage());
	}
	
	@Test
	void testAggiungiEsistenti() throws GestoreException {
		GestoreException exc = assertThrows(GestoreException.class, () -> 
		{Agenda agenda1=new Agenda("Scuola",4);
		Appuntamento app1= new Appuntamento("18/10/2001","15:30","20","Nicholas","UPO");
		assertEquals(1,agenda1.aggiungiAppuntamento(app1));
		Appuntamento app2= new Appuntamento("19/10/2001","16:30","20","Nicholas","UPO");
		assertEquals(1,agenda1.aggiungiAppuntamento(app2));
		Appuntamento app3= new Appuntamento("20/10/2001","17:30","20","Nicholas","UPO");
		assertEquals(1,agenda1.aggiungiAppuntamento(app3));
		assertEquals(0,agenda1.aggiungiAppuntamento(app2));});
		
		assertEquals("Appuntamento uguale\n", exc.getMessage());
	}
	
	@Test
	void testAggiungiSovrapposto() throws GestoreException {
		GestoreException exc = assertThrows(GestoreException.class, () -> 
		{Agenda agenda1=new Agenda("Scuola",4);
		Appuntamento app1= new Appuntamento("18/10/2001","15:30","20","Nicholas","UPO");
		assertEquals(1,agenda1.aggiungiAppuntamento(app1));
		Appuntamento app2= new Appuntamento("19/10/2001","16:30","20","Nicholas","UPO");
		assertEquals(1,agenda1.aggiungiAppuntamento(app2));
		Appuntamento app3= new Appuntamento("18/10/2001","15:45","20","Nicholas","UPO");
		agenda1.aggiungiAppuntamento(app3);});
		
		assertEquals("Appuntamento sovrapposto\n", exc.getMessage());
	}
	
	@Test
	void testSvuota() throws GestoreException {
		Agenda agenda1=new Agenda("Scuola",4);
		Appuntamento app1= new Appuntamento("18/10/2001","15:30","20","Nicholas","UPO");
		agenda1.aggiungiAppuntamento(app1);
		Appuntamento app2= new Appuntamento("19/10/2001","16:30","20","Matteo","UPO");
		agenda1.aggiungiAppuntamento(app2);
		Appuntamento app3= new Appuntamento("20/10/2001","17:30","20","Nicholas","UPO");
		agenda1.aggiungiAppuntamento(app3);
		
		agenda1.svuotaAgenda();
		assertEquals(agenda1.numeroAppuntamenti(),0);
		Iterator<Appuntamento> iter = agenda1.iterator();
		assertFalse(iter.hasNext());
	}
	
	@Test
	void testEliminaPerNome() throws GestoreException {
		Agenda agenda1=new Agenda("Scuola",4);
		Appuntamento app1= new Appuntamento("18/10/2001","15:30","20","Nicholas","UPO");
		agenda1.aggiungiAppuntamento(app1);
		Appuntamento app2= new Appuntamento("19/10/2001","16:30","20","Matteo","UPO");
		agenda1.aggiungiAppuntamento(app2);
		Appuntamento app3= new Appuntamento("20/10/2001","17:30","20","Nicholas","UPO");
		agenda1.aggiungiAppuntamento(app3);
		
		int numAppuntamenti = agenda1.numeroAppuntamenti();
		
		assertTrue(agenda1.eliminaPerNome("Nicholas"));
		assertEquals(numAppuntamenti-2, agenda1.numeroAppuntamenti());	
		assertTrue(agenda1.eliminaPerNome("Matteo"));
		assertEquals(0, agenda1.numeroAppuntamenti());
		assertFalse(agenda1.eliminaPerNome("Nicholas"));
		Iterator<Appuntamento> iter = agenda1.iterator();
		assertFalse(iter.hasNext());
	}
	
	@Test
	void testEliminaPerData() throws GestoreException {
		Agenda agenda1=new Agenda("Scuola",4);
		Appuntamento app1= new Appuntamento("18/10/2001","15:30","20","Nicholas","UPO");
		agenda1.aggiungiAppuntamento(app1);
		Appuntamento app2= new Appuntamento("18/10/2001","16:30","20","Matteo","UPO");
		agenda1.aggiungiAppuntamento(app2);
		Appuntamento app3= new Appuntamento("20/10/2001","17:30","20","Nicholas","UPO");
		agenda1.aggiungiAppuntamento(app3);
		
		int numAppuntamenti = agenda1.numeroAppuntamenti();
		
		assertTrue(agenda1.eliminaPerData("18/10/2001"));
		assertEquals(numAppuntamenti-2, agenda1.numeroAppuntamenti());
		assertTrue(agenda1.eliminaPerData("20/10/2001"));
		assertEquals(0, agenda1.numeroAppuntamenti());
		assertFalse(agenda1.eliminaPerData("20/10/2001"));
		Iterator<Appuntamento> iter = agenda1.iterator();
		assertFalse(iter.hasNext());
	}
	
	@Test
	void testEliminaIndice() throws GestoreException {
		Agenda agenda1=new Agenda("Scuola",4);
		Appuntamento app1= new Appuntamento("18/10/2001","15:30","20","Nicholas","UPO");
		agenda1.aggiungiAppuntamento(app1);
		Appuntamento app2= new Appuntamento("18/10/2001","16:30","20","Matteo","UPO");
		agenda1.aggiungiAppuntamento(app2);
		Appuntamento app3= new Appuntamento("20/10/2001","17:30","20","Nicholas","UPO");
		agenda1.aggiungiAppuntamento(app3);
		
		int numAppuntamenti = agenda1.numeroAppuntamenti();
		
		assertTrue(agenda1.eliminaAppuntamento(1));
		assertEquals(numAppuntamenti-1, agenda1.numeroAppuntamenti());
		assertTrue(agenda1.eliminaAppuntamento(1));
		assertEquals(numAppuntamenti-2, agenda1.numeroAppuntamenti());
		assertTrue(agenda1.eliminaAppuntamento(0));
		assertEquals(numAppuntamenti-3, agenda1.numeroAppuntamenti());
		Iterator<Appuntamento> iter = agenda1.iterator();
		assertFalse(iter.hasNext());
		GestoreException exc = assertThrows(GestoreException.class, () -> {agenda1.eliminaAppuntamento(0);});
		assertEquals("Indice fuori range\n", exc.getMessage());
	}
	
	@Test
	void testCercaPerData() throws GestoreException{
		Agenda agenda1=new Agenda("Scuola",4);
		Appuntamento app1= new Appuntamento("18/10/2001","15:30","20","Nicholas","UPO");
		agenda1.aggiungiAppuntamento(app1);
		Appuntamento app2= new Appuntamento("18/10/2001","16:30","20","Matteo","UPO");
		agenda1.aggiungiAppuntamento(app2);
		Appuntamento app3= new Appuntamento("20/10/2001","17:30","20","Nicholas","UPO");
		agenda1.aggiungiAppuntamento(app3);
		
		ArrayList<Appuntamento> ris= new ArrayList<Appuntamento>();
		ris.add(app1);
		ris.add(app2);

		assertEquals(ris,agenda1.cercaPerData("18/10/2001"));
	}
	
	@Test
	void testCercaPerNome() throws GestoreException{
		Agenda agenda1=new Agenda("Scuola",4);
		Appuntamento app1= new Appuntamento("18/10/2001","15:30","20","Nicholas","UPO");
		agenda1.aggiungiAppuntamento(app1);
		Appuntamento app2= new Appuntamento("18/10/2001","16:30","20","Matteo","UPO");
		agenda1.aggiungiAppuntamento(app2);
		Appuntamento app3= new Appuntamento("20/10/2001","17:30","20","Nicholas","UPO");
		agenda1.aggiungiAppuntamento(app3);
		
		ArrayList<Appuntamento> ris= new ArrayList<Appuntamento>();
		ris.add(app1);
		ris.add(app3);

		assertEquals(ris,agenda1.cercaPerNome("Nicholas"));
	}
	
	@Test
	void testOrdinaPerData() throws GestoreException{
		Agenda agenda1=new Agenda("Scuola",4);
		Appuntamento app1= new Appuntamento("20/10/2001","15:30","20","Nicholas","UPO");
		agenda1.aggiungiAppuntamento(app1);
		Appuntamento app2= new Appuntamento("18/10/2001","16:30","20","Matteo","UPO");
		agenda1.aggiungiAppuntamento(app2);
		Appuntamento app3= new Appuntamento("19/10/2001","17:30","20","Nicholas","UPO");
		agenda1.aggiungiAppuntamento(app3);
		
		ArrayList<Appuntamento> ris= new ArrayList<Appuntamento>();
		ris.add(app2);
		ris.add(app3);
		ris.add(app1);
		
		assertEquals(ris,agenda1.ordinaPerData());
	}
	
	@Test
	void testToString() throws GestoreException {
		Agenda agenda1=new Agenda("Scuola",4);
		Appuntamento app1= new Appuntamento("18/10/2001","15:30","20","Nicholas","UPO");
		agenda1.aggiungiAppuntamento(app1);
		Appuntamento app2= new Appuntamento("18/10/2001","16:30","20","Matteo","UPO");
		agenda1.aggiungiAppuntamento(app2);
		Appuntamento app3= new Appuntamento("20/10/2001","17:30","20","Nicholas","UPO");
		agenda1.aggiungiAppuntamento(app3);
		
		assertEquals("Nome Agenda: Scuola\nAppuntamenti:\n0) 2001-10-18 15:30 20 Nicholas UPO\n1) 2001-10-18 16:30 20 Matteo UPO\n2) 2001-10-20 17:30 20 Nicholas UPO\n",agenda1.agendaToString());
		
		Agenda agenda2=new Agenda();
		GestoreException exc = assertThrows(GestoreException.class, () -> 
		{agenda2.agendaToString();});
		assertEquals(exc.getMessage(),"Nessun appuntamento!\n");
	}
	
	@Test
	void testAggiungiDaFile() throws GestoreException, IOException {
		Agenda agenda1=new Agenda("Scuola",4);
		agenda1.aggiungiDaFile("agenda.txt");
		
		assertEquals(agenda1.agendaToString(),"Nome Agenda: Scuola\nAppuntamenti:\n0) 2001-10-18 15:30 20 Nicholas UPO\n1) 2001-10-18 16:30 20 Matteo UPO\n2) 2001-10-20 17:30 20 Nicholas UPO\n");
	}

}
