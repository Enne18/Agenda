package agenda;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * La classe Appuntamento rappresenta un appuntamento che ha una data, un orario, una durata, un nome e un luogo.
 * La classe fornisce metodi per la validazione e l'impostazione di questi campi, inoltre verifica la sovrapposizione di due appuntamenti.
 * 
 * @author	Matteo Cartieri 20044003
 * @author	Nicholas Ternullo 20045859
 */

public class Appuntamento {
	private LocalDate data;
	private LocalTime orario;
	private int durata;
	private String nome;
	private String luogo;
	
	DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	DateTimeFormatter formatoOra = DateTimeFormatter.ofPattern("HH:mm");
	
	public void ValidaData(String data) throws GestoreException{
		if(!(data.matches("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/[0-9]{4}"))) throw new GestoreException("Errore nell'inserimento della data!\n");
	}
	
	public void ValidaOrario(String orario) throws GestoreException{
		if(!(orario.matches("([01][0-9]|2[0-3]):[0-5][0-9]"))) throw new GestoreException("Errore nell'inserimento dell'orario!\n");
	}
	
	public void ValidaDurata(String durata) throws GestoreException{
		if(!(durata.matches("[0-9]+"))) throw new GestoreException("Errore nell'inserimento della durata!\n");
	}
	
	public void ValidaNome(String nome) throws GestoreException{
		if(!(nome.matches("[a-zA-Z ]+"))) throw new GestoreException("Errore nell'inserimento del nome!\n");
	}
	
	public void ValidaLuogo(String luogo) throws GestoreException{
		if(!(luogo.matches("[a-zA-Z ]+"))) throw new GestoreException("Errore nell'inserimento del luogo!\n");
	}
	
	/**
	  * Costruttore che inizializza i campi di un appuntamento con i valori forniti.
	  * 
	  * @param data - La data dell'appuntamento in formato "dd/MM/yyyy".
	  * @param orario - L'orario dell'appuntamento in formato "HH:mm".
	  * @param durata - La durata dell'appuntamento in minuti.
	  * @param nome - Il nome dell'appuntamento.
	  * @param luogo - Il luogo dell'appuntamento.
	  * @throws GestoreException - Se uno dei valori forniti non è valido.
	  */
	
	public Appuntamento(String data, String orario, String durata, String nome, String luogo) throws GestoreException {
		
		ValidaData(data);
		ValidaOrario(orario);
		ValidaDurata(durata);
		ValidaNome(nome);
		ValidaLuogo(luogo);
		
		this.data = LocalDate.parse(data, formatoData);
		this.orario = LocalTime.parse(orario, formatoOra);
		this.durata = Integer.parseInt(durata);
		this.nome = nome;
		this.luogo = luogo;
	}

	//Costruttore vuoto usato solo per i test
	public Appuntamento() {}
	
	public LocalDate getData() {
		return data;
	}

	public void setData(String data) throws GestoreException {
		ValidaData(data);
		this.data = LocalDate.parse(data, formatoData);
	}

	public LocalTime getOrario() {
		return orario;
	}

	public void setOrario(String orario) throws GestoreException {
		ValidaOrario(orario);
		this.orario = LocalTime.parse(orario, formatoOra);
	}

	public int getDurata() {
		return durata;
	}

	public void setDurata(String durata) throws GestoreException {
		ValidaDurata(durata);
		this.durata = Integer.parseInt(durata);
	}

	public String getNome() {
		return nome;
	}
	
	public String dataToString()
	{
		return data.toString();
	}
	
	public String orarioToString()
	{
		return orario.toString();
	}
	
	public String appuntamentoToString() {
		return data.toString()+" "+orario.toString()+" "+durata+" "+nome+" "+luogo;
	}

	public void setNome(String nome) throws GestoreException {
		ValidaNome(nome);
		this.nome = nome;
	}

	public String getLuogo() {
		return luogo;
	}

	public void setLuogo(String luogo) throws GestoreException {
		ValidaLuogo(luogo);
		this.luogo = luogo;
	}
	
	/**
	 * Verifica se questo appuntamento si sovrappone con un altro appuntamento.
	 * 
	 * @param app L'altro appuntamento da verificare.
	 * @exception GestoreException Se gli appuntamenti si sovrappongono.
	 * @return true se i due appuntamenti sono sovrapposti
	 */
	
	public boolean isSovrapposto(Appuntamento app) 
	{
		if(this.data.equals(app.getData())) 
		{
			if(this.orario.equals(app.getOrario()))
				return true;
			if(this.orario.isBefore(app.getOrario().plusMinutes(app.getDurata())) && (this.orario.plusMinutes(this.durata).isAfter(app.getOrario().plusMinutes(app.getDurata())) || this.orario.plusMinutes(this.durata).equals(app.getOrario().plusMinutes(app.getDurata()))))
				return true;
			if(app.getOrario().isBefore(this.orario.plusMinutes(this.durata))&& (app.getOrario().plusMinutes(app.getDurata()).isAfter(this.orario.plusMinutes(this.durata))|| this.orario.plusMinutes(this.durata).equals(app.getOrario().plusMinutes(app.getDurata()))))
				return true;
			return false;
		}
		return false;
	}
}