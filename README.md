# jX
jX è un progetto per la materia Programmazione A Oggetti presso l'Università di Messina (Unime), comprende la creazione di una REST API tramite Spring per la gestione di un DB ispirato a X.

Un'API REST (chiamata anche API RESTful o API Web RESTful) è un'application programming interface (API) conforme ai principi di progettazione di stile architetturale REST, o representational state transfer.
A livello di base, un'API è un meccanismo che consente a un'applicazione o servizio di accedere a una risorsa all'interno di un'altra applicazione o servizio. L'applicazione o il servizio che accede alle risorse è il client e l'applicazione o il servizio che contiene la risorsa è il server.

Le API REST comunicano tramite richieste HTTP per eseguire funzioni di database standard come la creazione, la lettura, l'aggiornamento e l'eliminazione di record (insieme di operazioni noto anche come CRUD) all'interno di una risorsa.

Ad esempio, un'API REST utilizzerà una richiesta GET per recuperare un record. Una richiesta POST crea un nuovo record. Una richiesta PUT aggiorna un record e una richiesta DELETE ne elimina uno. Tutti i metodi HTTP possono essere utilizzati nelle chiamate API.

| Nome                         | Metodo | URI                                                    | Parametri                | Descrizione                             | Tipo Ritorno  |
|------------------------------|--------|--------------------------------------------------------|--------------------------|-----------------------------------------|---------------|
| add-new-user                 | POST   | `http://localhost:8080/api/v1/user/add-new-user`       | `User object`            | Aggiunge un nuovo utente                | JSON          |
| add-new-users                | POST   | `http://localhost:8080/api/v1/user/add-new-users`      | `List of User objects`   | Aggiunge nuovi utenti                   | JSON          |
| get-all-users                | GET    | `http://localhost:8080/api/v1/user/all`                | Nessuno                  | Ritorna tutti gli utenti                | JSON          |
| get-user-by-id               | GET    | `http://localhost:8080/api/v1/user`                    | `user_id` (Query Param)  | Ritorna un utente tramite ID (parametro query) | JSON  |
| update-user                  | PUT    | `http://localhost:8080/api/v1/user/update`             | `User object`            | Aggiorna le informazioni di un utente   | JSON          |
| delete-user-by-id            | DELETE | `http://localhost:8080/api/v1/user/delete`             | `user_id` (Query Param)  | Elimina un utente tramite ID (parametro query) | JSON   |
| get-profile-by-id            | GET    | `http://localhost:8080/api/v1/profile`                 | `profile_id` (Query Param) | Ritorna il profilo tramite ID (parametro query) | JSON |
| get-profile-description      | GET    | `http://localhost:8080/api/v1/profile/description`     | `profile_id` (Query Param) | Ritorna la descrizione del profilo      | JSON          |
| get-profile-num-of-posts     | GET    | `http://localhost:8080/api/v1/profile/num-of-posts`    | `profile_id` (Query Param) | Ritorna il numero di post del profilo   | JSON          |
| get-profile-num-of-followers | GET    | `http://localhost:8080/api/v1/profile/num-of-followers`| `profile_id` (Query Param) | Ritorna il numero di follower del profilo | JSON      |
| create-profile               | POST   | `http://localhost:8080/api/v1/profile`                 | `Profile object`         | Crea un nuovo profilo                   | JSON          |
| get-profile-by-query         | GET    | `http://localhost:8080/api/v1/profile`                 | `profile_id` (Query Param) | Ritorna il profilo tramite query        | JSON          |
| add-profile-post             | POST   | `http://localhost:8080/api/v1/profile-post/add`        | `Post object`            | Aggiunge un nuovo post al profilo       | JSON          |
test
