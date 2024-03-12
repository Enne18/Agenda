package agenda;

import java.util.ArrayList;
import jbook.util.Input;
import java.io.IOException;

/** 
* @author	Matteo Cartieri 20044003
* @author	Nicholas Ternullo 20045859
*/

public class Interfaccia {
	  
    public static void main(String[] args) throws GestoreException, IOException
    {
    	Agende.crea();
        int option;
        do {
        	System.out.println("--------------------------------------------\n");
            System.out.println("Scegli un'opzione:\n");
            System.out.println("1. Crea una nuova agenda\n");
            System.out.println("2. Gestisci un'agenda\n");
            System.out.println("3. Visualizza i nomi di tutte le agende\n");
            System.out.println("0. Esci\n");
        	System.out.println("--------------------------------------------\n");

            option = Input.readInt();
            try
        	{
	            switch (option) 
	            {
	                case 1:
	                    creaAgenda();
	                    break;
	                case 2:
	                	System.out.println("\nInserisci il nome di un'agenda: \n");
	                	String nome = Input.readString();
	                    gestisciAgenda(nome);
	                    break;
	                case 3:
	                    System.out.println("\nNomi agende:\n"+Agende.agendeToString());
	                    break;
	                case 0:
	                    System.out.println("\nArrivederci!\n");
	                    return;
	                default:
	                    System.out.println("\nOpzione non valida.\n");
	                    break;
	            }
            }
        	catch (GestoreException exc)
        	{
        		System.out.println("\nErrore: "+exc.getMessage());
        	}
        	catch (IOException exc)
        	{
        		System.out.println("\nErrore: "+exc.getMessage());
        	}
        }while (option != 0);
    }

    public static void creaAgenda() throws GestoreException, IOException
    {
    	int option;
        do {
        	System.out.println("------------------------------------------------\n");
            System.out.println("Scegli un'opzione:\n");
            System.out.println("1. Crea un'agenda vuota\n");
            System.out.println("2. Crea un'agenda leggendo dei dati da file\n");
            System.out.println("0. Torna al menu principale\n");
        	System.out.println("------------------------------------------------\n");

            option = Input.readInt();
            try
            {
	            switch (option) 
	            {
	                case 1:
	                	System.out.println("\nInserisci il nome dell'agenda:\n");
	                    String nome1 = Input.readString();
	                    Agenda agenda1 = new Agenda(nome1);
	                    Agende.aggiungiAgenda(agenda1);
	                    System.out.println("\nAgenda creata con successo!\n");
	                    break;
	                case 2:
	                	System.out.println("\nInserisci il nome dell'agenda:\n");
	                    String nome2 = Input.readString();
	                    Agenda agenda2 = new Agenda(nome2);
	                    System.out.println("\nInserisci il nome di un file:\n");
	                    String nomeFile = Input.readString();
	                    agenda2.aggiungiDaFile(nomeFile);
	                    Agende.aggiungiAgenda(agenda2);
	                    System.out.println("\nAgenda creata con successo!\n");
	                    break;
	                case 0:
	                    return;
	                default:
	                    System.out.println("\nOpzione non valida.\n");
	                    break;
	            }
            }
            catch (GestoreException exc)
        	{
        		System.out.println("\nErrore: "+exc.getMessage());
        	}
        	catch (IOException exc)
        	{
        		System.out.println("\nErrore: "+exc.getMessage());
        	}
        }while (option != 0);
    }
    
    public static void gestisciAgenda(String nome) throws GestoreException, IOException
    {
    	int option;
        do 
        {
        	System.out.println("--------------------------------------------------------\n");
            System.out.println("Scegli un'opzione:\n");
            System.out.println("1. Inserisci un nuovo appuntamento nell'agenda\n");
            System.out.println("2. Modifica un appuntamento\n");
            System.out.println("3. Cerca un appuntamento per data\n");
            System.out.println("4. Cerca un appuntamento per nome della persona\n");
            System.out.println("5. Visualizza tutti gli appuntamenti\n");
            System.out.println("6. Visualizza tutti gli appuntamenti ordinati per data\n");
            System.out.println("7. Scrivi l'agenda su un file di testo\n");
            System.out.println("8. Elimina un appuntamento\n");
            System.out.println("9. Elimina tutti gli appuntamento in una data\n");
            System.out.println("10. Elimina tutti gli appuntamento con una persona\n");
            System.out.println("11. Cancella l'agenda\n");
            System.out.println("0. Torna al menu principale\n");
        	System.out.println("-----------------------------------------------------------\n");

            option = Input.readInt();
            Agenda agenda=Agende.getAgenda(nome);
            try
            {
	            switch (option) 
	            {
	                case 1:
	                	System.out.println("\nInserisci i seguenti dati separati da ', ':\nData, orario, durata (in minuti), nome, luogo\n");
	                    String dati=Input.readString();
	                    String [] dato=dati.split(", ");
	                    agenda.aggiungiAppuntamento(new Appuntamento(dato[0],dato[1],dato[2],dato[3],dato[4]));
	                    System.out.println("\nAppuntamento inserito con successo!\n");
	                    break;
	                case 2:
	                	System.out.println("\nInserisci il numero di un appuntamento:\n");
	                	int ind=Input.readInt();
	                	modificaAppuntamento(ind,agenda);
	                	break;
	                case 3:
	                	System.out.println("\nInserisci una data:\n");
	                    String data=Input.readString();
	                    ArrayList<Appuntamento> app=agenda.cercaPerData(data);
	                    if(app.isEmpty())
	                    	System.out.println("\nNessun appuntamento trovato\n");
	                    for(Appuntamento a : app)
	                    	System.out.println(a.appuntamentoToString());
	                    break;
	                case 4:
	                	System.out.println("\nInserisci un nome di persona:\n");
	                    String persona=Input.readString();
	                    app=agenda.cercaPerNome(persona);
	                    if(app.isEmpty())
	                    	System.out.println("\nNessun appuntamento trovato\n");
	                    for(Appuntamento a : app)
	                    	System.out.println(a.appuntamentoToString());
	                    break;
	                case 5:
	                	System.out.println("\n"+agenda.agendaToString()+"\n");
	                    break;
	                case 6:
	                    app=agenda.ordinaPerData();
	                    for(Appuntamento a : app)
	                    	System.out.println(a.appuntamentoToString());
	                    break;
	                case 7:
	                	System.out.println("\nInserisci il nome di un file:\n");
	                    String nomeFile = Input.readString();
	                	agenda.scriviSuFile(nomeFile);
	                	System.out.println("\nAgenda salvata con successo!\n");
	                    break;
	                case 8:
	                	System.out.println("\nInserisci il numero di un appuntamento:\n");
	                	ind=Input.readInt();
	                    agenda.eliminaAppuntamento(ind);
	                    System.out.println("\nAppuntamento eliminato con successo!\n");
	                    break;
	                case 9:
	                	System.out.println("\nInserisci una data:\n");
	                	data=Input.readString();
	                    agenda.eliminaPerData(data);
	                    System.out.println("\nAppuntamento/i eliminato/i con successo!\n");
	                    break;
	                case 10:
	                	System.out.println("\nInserisci un nome di persona:\n");
	                	persona=Input.readString();
	                    agenda.eliminaPerNome(persona);
	                    System.out.println("\nAppuntamento/i eliminato/i con successo!\n");
	                    break;
	                case 11:
	                	Agende.rimuoviAgenda(nome);
	                	agenda.svuotaAgenda();
	                	System.out.println("\nAgenda eliminata con successo!\n");
	                    break;
	                case 0:
	                    return;
	                default:
	                    System.out.println("\nOpzione non valida.\n");
	                    break;
	            }
            }
            catch (GestoreException exc)
        	{
        		System.out.println("\nErrore: "+exc.getMessage());
        	}
        	catch (IOException exc)
        	{
        		System.out.println("\nErrore: "+exc.getMessage());
        	}

        }while (option != 0);
    	
    }
    
    public static void modificaAppuntamento(int i, Agenda a) throws GestoreException
    {
    	int option;
        do 
        {
        	System.out.println("--------------------------------------------------------\n");
            System.out.println("Scegli cosa modificare nell'appuntamento:\n");
            System.out.println("1. Data\n");
            System.out.println("2. Orario\n");
            System.out.println("3. Durata\n");
            System.out.println("4. Nome della persona\n");
            System.out.println("5. Luogo\n");
            System.out.println("0. Torna indietro\n");
        	System.out.println("-----------------------------------------------------------\n");

            option = Input.readInt();
            Appuntamento app=a.getAppuntamento(i);
            try
            {
            	switch (option) 
	            {
	                case 1:
	                	System.out.println("\nInserisci una data:\n");
	                    String data=Input.readString();
	                    app.setData(data);	                
	                    System.out.println("\nData modificata con successo!\n");
	                    break;
	                case 2:
	                	System.out.println("\nInserisci un orario:\n");
	                    String orario=Input.readString();
	                    String orarioTemp=app.getOrario().toString();
	                    app.setOrario(orario);
	                    for(Appuntamento appunt:a)
	                    	if(appunt.isSovrapposto(app) && a.getIndice(appunt)!=i)
	                    	{
	                    		app.setOrario(orarioTemp);
	                    		System.out.println("\nErrore: appuntamento sovrapposto\n");
	                    		return;
	                    	}
	                    System.out.println("\nOrario modificato con successo!\n");
	                    break;
	                case 3:
	                	System.out.println("\nInserisci una durata:\n");
	                	Integer durata=Input.readInt();
	                	Integer durataTemp=app.getDurata();
	                    app.setDurata(durata.toString());
	                    for(Appuntamento appunt:a)
	                    	if(appunt.isSovrapposto(app) && a.getIndice(appunt)!=i)
	                    	{
	                    		app.setDurata(durataTemp.toString());
	                    		System.out.println("\nErrore: appuntamento sovrapposto\n");
	                    		return;
	                    	}
	                    System.out.println("\nDurata modificata con successo!\n");
	                    break;
	                case 4:
	                	System.out.println("\nInserisci un nome:\n");
	                    String nome=Input.readString();
	                    app.setNome(nome);
	                    System.out.println("\nNome modificato con successo!\n");
	                    break;
	                case 5:
	                	System.out.println("\nInserisci un luogo:\n");
	                    String luogo=Input.readString();
	                    app.setLuogo(luogo);
	                    System.out.println("\nLuogo modificato con successo!\n");
	                    break;
	                case 0:
	                    return;
	                default:
	                    System.out.println("\nOpzione non valida.\n");
	                    break;
	            }
            }
            catch (GestoreException exc)
            {
            	System.out.println("\nErrore: "+exc.getMessage());
            }
        }while(option!=0);
    }
    
}