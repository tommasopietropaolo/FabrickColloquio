In questo progetto ho utilizzato spring webclient al posto di restTemplate poichè ho visto che ormai è deprecato.
------
Il metodo getBalance fa una chiamata get all'api esposta e  ritorna il balance non ho fatto tornare altri dati ma si potrebbe poi scegliere magari di far tornare la currency ad esempio.
Hedgecase che è discutibile se gestirlo o meno è in caso di balance negativo cosa scegliere di fare. io non l'ho gestito.
----
Il secondo metodo gettransaction
fa una chiamata di tipo get introducendo due date come query parameters rida indietro una lista di transazioni, ho deciso di mappare tutti i parametri della risposta non sapendo le informazioni superflue.
Hedgecase quando le date non sono  valide perchè  ladatat di inizio è maggiore di quella di fine ho creato un metodo che in tal caso restituisce un errore specifico con testo  specifico, nel metodo è possibile integrare anche controllo del formato ecc..
----
Il terzo metodo makeTransfer è una chiamata POST all'api dove ppassando un body transferRequest ci ritorna una serie di dati, dato che le credenziali come specificato non facevano partire la richiesta ma ritornare l'errore ho deciso di far ritornare in caso 
positivo l'id della transazione, nella claasse transfer ho aggiunto come commento  anche i possibili  attributi mappabili dalla risposta seguendo la documentazione.
in questo caso mi ritorna quindi un errore che visualizzo.
Dal testo da voi fornito dovrebbe tornare:
"code": "API000",
"description": "Errore tecnico  La condizione BP049 non e' prevista per il conto id 14537780" ma in realtà con la chimata all'api ora ritorna:
"code": "API000",
"description": "it.sella.pagamenti.servizibonifico.exception.ServiziInvioBonificoSubsystemException: it.sella.pagamenti.sottosistemi.SottosistemiException: Errore tecnico CONTO 45685475:Conto 45685475 non esiste",
"params": ""

