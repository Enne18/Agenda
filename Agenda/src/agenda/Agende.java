package agenda;

import java.util.ArrayList;

/**
 * La classe Agende rappresenta la lista con ogni Agenda creata, per agevolarne la gestione nell'Interfaccia
 * 
 * @author      Matteo Cartieri 20044003
 * @author		Nicholas Ternullo 20045859
 */

public class Agende {
	private static ArrayList<Agenda> agende;
	
	/**
	 * Crea una nuova lista di Agende
	 */
	
	public static void crea()
	{
		agende=new ArrayList<Agenda>();
	}
	
	/**
	 * Ritorna il numero di Agende inserite
	 * 
	 * @return il numero delle Agende
	 */
	
	public static int numAgende()
	{
		return agende.size();
	}
	
	/**
	 * Svuota la lista di Agende
	 */
	
	public static void svuota()
	{
		agende.clear();
	}
	
	/**
	 * Rimuove l'Agenda con il nome specificato
	 * 
	 * @param nome nome dell'Agenda da cercare
	 * @return boolean
	 */
	
	public static boolean rimuoviAgenda(String nome) throws GestoreException
	{
		return agende.remove(Agende.getAgenda(nome));
	}
	
	/**
	 * Ritorna l'Agenda con il nome specificato
	 *
	 * @param nome nome dell'Agenda da cercare
	 * @exception GestoreException solleva un'eccezione se non &egrave; presente un'Agenda con quel nome
	 * @return l'Agenda richiesta
	 */
	
	public static Agenda getAgenda(String nome) throws GestoreException
	{
		for (Agenda a : agende)
			if(a.NOME.equals(nome))
				return a;
		throw new GestoreException("Agenda non trovata\n");
	}
	
	/**
	 * Inserisce un'Agenda
	 *
	 * @param agenda Agenda da aggiungere
	 * @exception GestoreException solleva un'eccezione se l'Agenda &egrave; gi&agrave; presente 
	 * @return 1 se l'inserimento ha avuto successo
	 */
	
	public static int aggiungiAgenda(Agenda agenda) throws GestoreException
	{
		for(Agenda a:agende)
			if(a.NOME.equals(agenda.NOME)) throw new GestoreException("Agenda uguale\n");
		agende.add(agenda);
		return 1;
	}
	
	/**
	 * Restituisce l'elenco dei nomi delle Agende
	 * 
	 * @exception GestoreException solleva un'eccezione se Agende &egrave; vuoto
	 * @return l'elenco delle Agende come stringa
	 */
	
	public static String agendeToString() throws GestoreException
	{
		if(agende.isEmpty())
			throw new GestoreException("Nessuna agenda!\n");
		String elenco = new String();
		for (Agenda a : agende)
			elenco+=a.NOME+"\n";
		return elenco;
	}
}
