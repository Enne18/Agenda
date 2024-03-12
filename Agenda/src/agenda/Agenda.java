package agenda;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * La classe Agenda rappresenta una lista di Appuntamenti, e ne permette l'inserimento (anche dalla lettura di un file) o l'eliminazione.
 * Permette di salvare tutti i suoi dati in un file di testo.
 * 
 * @author      Matteo Cartieri 20044003
 * @author		Nicholas Ternullo 20045859
 */

public class Agenda implements Iterable<Appuntamento>
{
	public final String NOME;
	private ArrayList<Appuntamento> agenda;
	public final int MAX_DIM;
	public static int DIM_DEFAULT=10;
	
	private static int numeroAgende=0;
	
	public Iterator<Appuntamento> iterator()
	{
		return new Iteratore();
	}
	
	private class Iteratore implements Iterator<Appuntamento>
	{
		private int indiceCorrente;

		public Iteratore()
		{
			indiceCorrente=0;
		}

		public boolean hasNext()
		{
			if (agenda.size()>MAX_DIM) return false;
			return (indiceCorrente<agenda.size());
		}
		
		public Appuntamento next()
		{
			if (!this.hasNext())
				return null;
			return agenda.get(indiceCorrente++);
		}

	}
	
	/**
	 * Costruttore per l'agenda
	 * 
	 * @param nomeAgenda il nome dell'agenda
	 * @param dim dimensione massima dell'agenda
	 */
	
	public Agenda(String nomeAgenda, int dim) 
	{
		this.NOME = nomeAgenda;
		this.MAX_DIM = dim;
		agenda = new ArrayList<Appuntamento>();
		numeroAgende++;
	}
	
	/**
	 * Costruttore per l'agenda con il solo nome
	 * 
	 * @param nomeAgenda il nome dell'agenda
	 */
	
	public Agenda(String nomeAgenda) 
	{
		this(nomeAgenda,DIM_DEFAULT);
	}
	
	/**
	 * Costruttore per l'agenda con la sola dimensione
	 * 
	 * @param dim dimensione massima dell'agenda
	 */
	
	public Agenda(int dim) 
	{
		this("Agenda" + numeroAgende,dim);
	}
	
	/**
	 * Costruttore per l'agenda senza parametri
	 */
	
	public Agenda() 
	{
		this("Agenda" + numeroAgende,DIM_DEFAULT);
	}
	
	public static int getNumeroAgende() 
	{
		return numeroAgende;
	}
	
	public Appuntamento getAppuntamento(int indice) 
	{
		return agenda.get(indice);
	}
	
	/**
	 * Ritorna il numero degli appuntamenti nell'agenda
	 * 
	 * @return il numero di appuntamenti in agenda
	 */
	
	public int numeroAppuntamenti() 
	{
		return agenda.size();
	}
	
	/**
	 * Cancella l'intera agenda
	 */
	
	public void svuotaAgenda() 
	{
		agenda.clear();
	}
	
	/**
	 * Trasforma l'agenda, con tutti i suoi appuntamenti, in una stringa
	 * 
	 * @return l'elenco degli appuntamenti come stringa
	 */
	
	public String agendaToString() throws GestoreException
	{
		if(agenda.isEmpty())
			throw new GestoreException("Nessun appuntamento!\n");
		String str = new String();
		for(Appuntamento a : agenda)
			str+=agenda.indexOf(a)+") "+a.appuntamentoToString()+"\n";
		return "Nome Agenda: "+this.NOME+"\n"+"Appuntamenti:\n"+str;
	}
	
	public int getIndice(Appuntamento a)
	{
		return agenda.indexOf(a);
	}
	
	/**
	 * Ricerca tutti gli appuntamenti con una stessa data
	 * 
	 * @param data stringa con la data da cercare
	 * @return lista degli appuntamenti con quella data
	 */
	
	public ArrayList<Appuntamento> cercaPerData(String data) throws GestoreException
	{
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		ArrayList<Appuntamento> risultato = new ArrayList<Appuntamento>();
		for(Appuntamento a : agenda)
		{
			a.ValidaData(data);
			if(a.getData().equals(LocalDate.parse(data, formatoData)))
				risultato.add(a);
		}
		return risultato;
	}
	
	/**
	 * Ricerca tutti gli appuntamenti per nome della persona con cui si ha l'appuntamento
	 * 
	 * @param nome stringa con il nome da cercare
	 * @return lista degli appuntamenti con quel nome
	 */
	
	public ArrayList<Appuntamento> cercaPerNome(String nome)
	{
		ArrayList<Appuntamento> risultato = new ArrayList<Appuntamento>();
		for(Appuntamento a : agenda)
			if(a.getNome().equals(nome))
				risultato.add(a);
		return risultato;
	}
	
	/**
	 * Rimuove tutti gli appuntamenti con lo stesso nome della persona 
	 * 
	 * @param nome stringa con il nome da cercare
	 * @return boolean
	 */
	
	public boolean eliminaPerNome(String nome) 
	{
		return agenda.removeAll(cercaPerNome(nome));
	}
	
	/**
	 * Rimuove tutti gli appuntamenti con la stessa data
	 * 
	 * @param data stringa con la data da cercare
	 * @return boolean
	 */
	
	public boolean eliminaPerData(String data) throws GestoreException 
	{
		return agenda.removeAll(cercaPerData(data));
	}
	
	/**
	 * Rimuove l'appuntamento all'indice specificato
	 * 
	 * @param data stringa con la data da cercare
	 * @return boolean
	 */
	
	public boolean eliminaAppuntamento(int indice) throws GestoreException 
	{
		if(indice<0 || indice>=agenda.size())
			throw new GestoreException("Indice fuori range\n");
		return agenda.remove(agenda.get(indice));
	}
	
	/**
	 * Ordina tutti gli appuntamenti in base alla data
	 * 
	 * @return lista degli appuntamenti ordinati per data
	 */
	
	public ArrayList<Appuntamento> ordinaPerData()
	{
		ArrayList<Appuntamento> res=agenda; 
		res.sort((a1, a2)-> a1.getData().compareTo(a2.getData()));
		return res;
	}
	
	/**
	 * Inserisce un appuntamento nell'agenda
	 * 
	 * @param app appuntamento da aggiungere all'agenda
	 * @exception GestoreException solleva un'eccezione se l'appuntamento &egrave; gi&agrave; presente in agenda o se l'inserimento fa superare la dimensione massima
	 * @return 1 se l'inserimento ha avuto successo
	 */
	
	public int aggiungiAppuntamento(Appuntamento app) throws GestoreException 
	{
		Iterator<Appuntamento> iter = agenda.iterator();
		if (agenda.contains(app)) throw new GestoreException("Appuntamento uguale\n");
		while(iter.hasNext())
			if(iter.next().isSovrapposto(app)) throw new GestoreException("Appuntamento sovrapposto\n");
		if(agenda.size()>=MAX_DIM) throw new GestoreException("Ecceduta dimensione\n");
		agenda.add(app);
		return 1;
	}
	
	/**
	 * Inserisce nell'agenda appuntamenti importati da un file di testo
	 * 
	 * @param file il nome del file contenente gli appuntamenti
	 * @exception IOException solleva un'eccezione se il file non esiste, o se c'&egrave; un errore nella lettura, o se il file non viene chiuso
	 * @exception GestoreException solleva un'eccezione se il numero di dati nel file &egrave; errato o se l'inserimento dell'appuntamento non avviene con successo
	 */
	
	public void aggiungiDaFile (String file) throws IOException, GestoreException 
	{
		BufferedReader in = new BufferedReader (new FileReader (file));
		String linea = in.readLine();
		while ( linea != null) 
		{
			String [] dati = linea.split(", ");
			
			if ( dati.length >= 5)
			{
				this.aggiungiAppuntamento(new Appuntamento(dati[0],dati[1],dati[2],dati[3],dati[4]));
			}
			else 
			{ 
				in.close();
				throw new GestoreException("Numero di dati inseriti errato\n");
			}
			linea = in.readLine();
		}
		in.close();
	}
	
	/**
	 * Copia l'agenda come stringa su un file di testo
	 * 
	 * @param file il nome del file su cui scrivere
	 * @exception FileNotFoundException solleva un'eccezione se il formato del file non &egrave; corretto
	 */
	
	public void scriviSuFile(String file) throws FileNotFoundException, GestoreException  
	{
		PrintWriter out = new PrintWriter(new File(file));
		out.printf(this.agendaToString());
		out.close();
	}
}
