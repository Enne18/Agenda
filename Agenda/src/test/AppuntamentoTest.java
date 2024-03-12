package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import agenda.Appuntamento;
import agenda.GestoreException;
import java.time.LocalDate;
import java.time.LocalTime;

/** 
* @author	Matteo Cartieri 20044003
* @author	Nicholas Ternullo 20045859
*/

class AppuntamentoTest {

	@BeforeEach
	void TestAppuntamento() throws GestoreException {
		Appuntamento app = new Appuntamento("18/10/2001","12:30","20","Nicholas","UPO");
		assertEquals(app.dataToString(),"2001-10-18");
		assertEquals(app.orarioToString(),"12:30");
		assertEquals(app.getDurata(),20);
		assertEquals(app.getNome(),"Nicholas");
		assertEquals(app.getLuogo(),"UPO");
	}
	
	@Test
	void TestValoriAppuntamento() throws GestoreException {
		
		GestoreException exception = assertThrows(GestoreException.class, () -> {
			new Appuntamento("18/10/2001","15:30","20","Nicholas","UPO");
			new Appuntamento("18/10/01","15:30","20","Nicholas","UPO");
		});

	    assertEquals("Errore nell'inserimento della data!\n" , exception.getMessage());
	    
	    exception = assertThrows(GestoreException.class, () -> {
	    	new Appuntamento("18/10/2001","15:a","20","Nicholas","UPO");
	    });
	    assertEquals("Errore nell'inserimento dell'orario!\n" , exception.getMessage());
	    
	    exception = assertThrows(GestoreException.class, () -> {
	    	new Appuntamento("18/10/2001","15:30","venti","Nicholas","UPO");
	    });
	    assertEquals("Errore nell'inserimento della durata!\n" , exception.getMessage());
	    
	    exception = assertThrows(GestoreException.class, () -> {
	    	new Appuntamento("18/10/2001","15:30","20","Nicholas18","UPO");
	    });
	    assertEquals("Errore nell'inserimento del nome!\n" , exception.getMessage());
	    
	    exception = assertThrows(GestoreException.class, () -> {
		    new Appuntamento("18/10/2001","15:30","20","Nicholas","Scu0l4");
	    });
	    assertEquals("Errore nell'inserimento del luogo!\n" , exception.getMessage());
	}
	
	@Test
	void testValidaData() throws GestoreException {	  
	  Appuntamento app = new Appuntamento();
	  // Test con data valida
	  app.ValidaData("01/01/2022");
	  
	  // Test con data non valida (formato non corretto)
	  Exception exception = assertThrows(GestoreException.class, () -> app.ValidaData("01-01-2022"));
	  assertEquals("Errore nell'inserimento della data!\n", exception.getMessage());
	  
	  // Test con data non valida (giorno non esistente)
	  exception = assertThrows(GestoreException.class, () -> app.ValidaData("32/01/2022"));
	  assertEquals("Errore nell'inserimento della data!\n", exception.getMessage());
	}

	@Test
	void testValidaOrario() throws GestoreException {
	  Appuntamento app = new Appuntamento();
	  GestoreException exception = assertThrows(GestoreException.class, () -> app.ValidaOrario("25:00"));
	  assertEquals("Errore nell'inserimento dell'orario!\n", exception.getMessage());
	}


	  @Test
	  void testValidaDurata() throws GestoreException {
	    Appuntamento app = new Appuntamento();
	    GestoreException exception = assertThrows(GestoreException.class, () -> app.ValidaDurata("a"));
	    assertEquals("Errore nell'inserimento della durata!\n", exception.getMessage());
	  }

	  @Test
	  void testValidaNome() throws GestoreException {
	    Appuntamento app = new Appuntamento();
	    GestoreException exception = assertThrows(GestoreException.class, () -> app.ValidaNome("1abc"));
	    assertEquals("Errore nell'inserimento del nome!\n", exception.getMessage());
	  }

	  @Test
	  void testValidaLuogo() throws GestoreException {
	    Appuntamento app = new Appuntamento();
	    GestoreException exception = assertThrows(GestoreException.class, () -> app.ValidaLuogo("1abc"));
	    assertEquals("Errore nell'inserimento del luogo!\n", exception.getMessage());
	  }

	  @Test
	  void testAppuntamento() throws GestoreException {
	    Appuntamento app = new Appuntamento("08/02/2023", "12:00", "1", "Test", "Test location");
	    assertEquals(LocalDate.of(2023, 2, 8), app.getData());
	    assertEquals(LocalTime.of(12, 0), app.getOrario());
	    assertEquals(1, app.getDurata());
	    assertEquals("Test", app.getNome());
	    assertEquals("Test location", app.getLuogo());
	  }

	  @Test
	  void testSetData() throws GestoreException {
	    Appuntamento app = new Appuntamento("08/02/2023", "12:00", "50", "Test", "Test location");
	    app.setData("08/02/2023");
	    assertEquals(LocalDate.of(2023, 2, 8), app.getData());
	  }

	  @Test
	  void testSetOrario()
		throws GestoreException {
		Appuntamento app = new Appuntamento("08/02/2023", "12:00", "50", "Test", "Test location");
		app.setOrario("12:00");
		assertEquals(LocalTime.of(12, 0), app.getOrario());
	  }

	  @Test
		void testSetDurata() throws GestoreException {
		Appuntamento app = new Appuntamento("08/02/2023", "12:00", "50", "Test", "Test location");
		app.setDurata("1");
		assertEquals(1, app.getDurata());
	  }

	  @Test
		void testSetNome() throws GestoreException {
		Appuntamento app = new Appuntamento("08/02/2023", "12:00", "50", "Test", "Test location");
		app.setNome("Test");
		assertEquals("Test", app.getNome());
	  }

	  @Test
		void testSetLuogo() throws GestoreException {
		Appuntamento app = new Appuntamento("08/02/2023", "12:00", "50", "Test", "Test location");
		app.setLuogo("Test location");
		assertEquals("Test location", app.getLuogo());
	  }
	  
	  @Test
		void testIsSovrapposto() throws GestoreException{
		  Appuntamento app1 = new Appuntamento("08/02/2023", "12:00", "50", "Test", "Test location");
		  Appuntamento app2 = new Appuntamento("08/02/2023", "12:30", "30", "Test", "Test location");
		  Appuntamento app3 = new Appuntamento("08/02/2023", "11:30", "45", "Test", "Test location");
		  Appuntamento app4 = new Appuntamento("09/02/2023", "12:30", "30", "Test", "Test location");
		  Appuntamento app5 = new Appuntamento("09/02/2023", "12:00", "60", "Test", "Test location");

		  assertTrue(app1.isSovrapposto(app2));
		  assertTrue(app1.isSovrapposto(app3));
		  assertTrue(app4.isSovrapposto(app5));
		  assertFalse(app2.isSovrapposto(app3));
		  assertFalse(app2.isSovrapposto(app4));
	  }
}