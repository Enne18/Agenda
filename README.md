# Agenda

**Progetto per la prova finale del corso di Paradigmi di Programmazione a.a. 2022/2023**

Il progetto ha l'obiettivo di implementare in Java un sistema per la gestione di un insieme di agende, focalizzandosi sulla gestione degli appuntamenti. Ogni agenda è caratterizzata da un nome unico e contiene un elenco di appuntamenti con le seguenti informazioni:

- Data dell'appuntamento (formato: gg-mm-aaaa)
- Orario dell'appuntamento (formato: hh-mm)
- Durata prevista espressa in minuti
- Nome della persona con cui si ha l'appuntamento
- Luogo in cui si terrà l'appuntamento

Il programma offre le seguenti funzionalità:

### Operazioni su Agende

1. Creare una nuova agenda vuota fornendone il nome.
2. Cancellare un'agenda, anche se sono presenti appuntamenti.
3. Creare una nuova agenda leggendo gli appuntamenti da un file.
4. Scrivere un'agenda su file.

### Operazioni sugli Appuntamenti

Dopo la selezione di un'agenda, è possibile:

1. Inserire un nuovo appuntamento verificando correttezza e completezza dei dati, evitando sovrapposizioni con altri appuntamenti già definiti per lo stesso orario.
2. Modificare i dati di un appuntamento già registrato.
3. Cercare gli appuntamenti per data o usando il nome della persona coinvolta.
4. Elencare, ordinati per data, tutti gli appuntamenti dell'agenda.

L'agenda è iterabile, ma non consente la rimozione di elementi durante l'iterazione.
