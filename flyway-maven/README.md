# Flyway Baseline mit Maven

## Aufgabe

1. Starte die Testumgebung.
   1. Starte die PostgreSQL-Datenbank mit `sh ./startup.sh` in einem eigenen Terminal. 
      
      ```
      Host: localhost:65432
      User: flyway
      Password: flyway
      Database: flyway
      Schema: flyway
      ```
      
      Bei `Strg + C` oder beim Schließen des Terminals wird auch die Datenbank beendet. 
      Beim erneuten Ausführen des Befehls wird eine leere Datenbank angelegt.
      Siehe SQL-Skripte unter `database/db-setup`.
      
   2. Öffne ein neues Terminal und navigiere unter `flyway-basics/flyway-maven`. 
   
   3. Die Migrationsskripte `V1_0__todo_create_table.sql` und 
      `V1_1__todo_insert_todos.sql` existieren bereits unter 
      `src/main/resources/db/migration`. 
      Allerdings wurden mit `database/db-setup/03-createTodoTable_insertData.sql` 
      bereits ein Datenbankschema angelegt.
      
   4. Starte die Anwendung mit `sh ./mvnw spring-boot:run` und öffne den Browser http://localhost:9001/api/todos
      
      Die Anwendung funktioniert, da das Datenbankschema und die Daten bereits existieren.
   
2. Versuche eine Datenbank-Migration mit `sh ./mvnw flyway:migrate` durchzuführen.
   
   Flyway erkennt, dass bereits Tabellen existieren und keine `flyway_schema_history` Tabelle existiert.
   ```
   $ sh ./mvnw flyway:migrate
   ...
   [INFO] BUILD FAILURE
   [INFO] ------------------------------------------------------------------------
   ...
   [ERROR] Failed to execute goal org.flywaydb:flyway-maven-plugin:6.1.4:migrate (default-cli) on project flyway-maven: org.flywaydb.core.api.FlywayException: 
     Found non-empty schema(s) "flyway" but no schema history table. 
     Use baseline() or set baselineOnMigrate to true to initialize the schema history table. -> [Help 1]
   ```
   
3. Führe ein Flyway Baseline aus, um die `flyway_schema_history` Tabelle anzulegen.
   Die Baseline-Version sollte so gewählt werden, dass die beiden Migrationsskripte 
   nicht ausgeführt werden.
   
   Siehe https://flywaydb.org/documentation/maven/baseline für die passende Baseline-Version.
   
   **Hinweis:** Flyway-Parameter besitzen den Präfix `flyway.`
  
#### Ziel

`sh ./mvnw flyway:migrate` soll erfolgreich ohne Migrationen ausgeführt werden können.

```
$ sh ./mvnw flyway:migrate
[INFO] --- flyway-maven-plugin:6.1.4:migrate (default-cli) @ flyway-maven ---
[INFO] Flyway Community Edition 6.1.4 by Redgate
[INFO] Database: jdbc:postgresql://localhost:65432/flyway (PostgreSQL 12.2)
[INFO] Successfully validated 3 migrations (execution time 00:00.018s)
[INFO] Current version of schema "flyway": 1.2
[INFO] Schema "flyway" is up to date. No migration necessary.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```