# Documentazione del Sistema di Gestione Hotel

## 1. Introduzione

Il sistema di gestione hotel è un'applicazione progettata per gestire le prenotazioni di camere, il parcheggio e altre funzionalità correlate. Permette di:

- Creare, visualizzare e cancellare prenotazioni.
- Gestire la disponibilità delle camere e dei parcheggi.
- Validare le richieste tramite controlli sui dati forniti dagli utenti.

---

## 2. Funzionalità principali

### 2.1 Prenotazioni

- **Creazione di una prenotazione:**
  - Controlla la disponibilità della camera per le date specificate.
  - Valida che il tipo di camera e il numero di ospiti corrispondano alle specifiche della camera scelta.
  - Assegna un parcheggio, se richiesto
- **Cancellazione di una prenotazione:**
  - Elimina una prenotazione esistente dal database e, se presente, il parcheggio associato.
- **Visualizzazione delle prenotazioni:**
  - Ritorna l'elenco completo delle prenotazioni salvate nel sistema.

### 2.2 Gestione delle camere

- **Creazione di una camera:**
  - Aggiunge una nuova camera con dettagli come tipo, numero di ospiti e prezzo.
- **Visualizzazione delle camere:**
  - Ritorna l'elenco completo delle camere salvate nel sistema.

### 2.3 Gestione dei parcheggi

- **Assegnazione di un parcheggio:**
  - Trova il primo posto auto disponibile e lo assegna a una prenotazione già esistente.
  - Ogni parcheggio ha un proprio codice (A1, A2, A3, A4, B1, B2, B3, B4) da dare al cliente
- **Rimozione di un parcheggio:**
  - Libera un parcheggio precedentemente assegnato.

---

## 3. Flusso del programma

### 3.1 Flusso per la creazione di una prenotazione

1. L'utente invia una richiesta con i dettagli della prenotazione per una stanza specifica (date di check-in e check-out, numero di ospiti, e opzionalmente il tipo di camera e la richiesta per il posto auto).
2. Il sistema:
   - Valida i dati forniti dall'utente.
   - Controlla che le date siano valide e che la camera sia disponibile.
   - Verifica che il numero di ospiti sia inferiore o uguale alla capacità della camera richiesta.
   - Verifica che il tipo di camera richiesta (suite, classic, modern, basic) sia corrispondente alla camera che si vuole prenotare.
3. Se tutte le verifiche sono superate, il sistema salva la prenotazione nel database.
4. Se richiesto, assegna un parcheggio disponibile alla prenotazione.

### 3.2 Flusso per la cancellazione di una prenotazione

1. L'utente specifica l'ID della prenotazione da eliminare.
2. Il sistema verifica che la prenotazione esista.
3. Rimuove la prenotazione dal database e, se applicabile, libera il parcheggio associato.

---

## 4. Validazioni e controlli

  - Il tipo di camera deve essere valido e corrispondere a uno dei valori definiti nell'enum `RoomType`.
  - Il numero di ospiti deve essere maggiore di 0.
  - Il prezzo deve essere maggiore o uguale a 0 e non nullo.
  - La data di check-in deve essere nel futuro.
  - La data di check-out deve essere successiva alla data di check-in.
  - Viene verificato che non ci siano prenotazioni sovrapposte per la stessa camera.
  - Il numero di ospiti devono essere inferiori o uguali alla capacità della camera scelta.
  - Il tipo di camera (se specificato) deve rispettare le specifiche della camera scelta.
  - Per l'assegnazione dei parcheggi: Trova il primo posto disponibile. Se non ci sono posti disponibili, viene lanciata un'eccezione dedicata.
  - La prenotazione di un posto auto viene effettuta solo se una prenotazione non ha ancora posti auto assegnati.
---

## 5. Struttura del database

Ci sono 10 stanze e 8 posto auto da associare o meno alle prenotazioni.

### 5.1 Tabelle principali

- **`rooms`****:**

  - `id`: Identificativo univoco della camera.
  - `type`: Tipo di camera (es. CLASSIC, SUITE, MODERN, BASIC).
  - `number_of_guests`: Numero massimo di ospiti.
  - `price`: Prezzo della camera.

- **`bookings`****:**

  - `id`: Identificativo univoco della prenotazione.
  - `room_id`: Identificativo della camera associata.
  - `check_in_date`: Data di check-in.
  - `check_out_date`: Data di check-out.
  - `number_of_guests`: Numero di ospiti.
  - `parking_space_id`: Identificativo del parcheggio associato

- **`parking_spaces`****:**

  - `id`: Identificativo univoco del parcheggio.
  - `code`: Identificativo del pacheggio per il cliente.
  - `assigned`: Indica se il parcheggio è già assegnato.

---

## 6. Struttura dei DTO

### 6.1 `RoomDTO`

```json
{
  "id": 1,
  "type": "CLASSIC",
  "numberOfGuests": 2,
  "price": 100.0
}
```

### 6.2 `BookingDTO` 
Racchiude informazioni sulla prenotazione, la stanza associata e il parcheggio 

```json
{
    "id": 1,
    "checkInDate": "2024-12-12",
    "checkOutDate": "2024-12-13",
    "numberOfGuests": 4,
    "roomType": "SUITE",
    "roomId": 1,
    "parkingCode": "A1",
    "reservedParking": true
  },
```

---
## 7. Struttura del programma

Il sistema segue un'architettura a livelli, separando chiaramente le responsabilità di ciascun componente:

### Controller:

I controller rappresentano il punto di ingresso per le richieste degli utenti. Ogni endpoint definito nei controller riceve i dati, li valida (utilizzando annotazioni come @Valid) e li passa al service layer.

### Service:

Il livello di servizio contiene la logica applicativa principale. Si occupa di:
- Validare dati aggiuntivi non coperti dalle annotazioni nei DTO.
- Gestire tramite un service più astratto operazioni trasversali che coinvolgono prenotazioni e posti auto.
- Coordinare le operazioni tra diversi repository e mapper.
- Applicare regole, come la verifica della disponibilità delle camere (isRoomAvailable).

### Repository:

I repository gestiscono l'accesso ai dati e interagiscono con il database utilizzando JPA.

### Mapper:

I mapper trasformano oggetti da DTO a entità e viceversa.

### Eccezioni:

 Le eccezioni vengono gestite tramite un ExceptionHandlerController, classe annotata con @RestControllerAdvice

#### Eccezioni personalizzate

- **`InvalidBookingDateException`****:** Gestisce errori relativi a date di prenotazione non valide.
- **`RoomNotAvailableException`****:** Segnala l'indisponibilità di una camera.
- **`ParkingSpaceException`****:** Segnala la mancanza di parcheggi disponibilio altri problemi relativi ai posti auto

---
