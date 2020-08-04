# Flyway Grundlagen mit Maven

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
   
   3. Starte einen Integrationstest mit `sh ./mvnw test`.
      Der Test führt für uns _ohne Flyway_ die SQL-Statements 
      von `src/test/resources/init-todos.sql` auf der gestarteten Datenbank aus. 
      Die Todo-Tabelle existiert auch nach dem Test und können für den Start der echten Anwendung genutzt werden.
      
   4. Starte die Anwendung mit `sh ./mvnw spring-boot:run` und öffne den Browser http://localhost:9001/api/todos

2. Nutze die SQL-Statements aus `src/test/resources/init-todos.sql`, 
um die SQL-Skripte unter `src/main/resources/db/migration/all` für das Schema und 
`src/main/resources/db/migration/LOCAL` für die Daten anzulegen.
  
   **Frage:** Bauchen wir noch das `DROP TABLE` Statement?
   
3. Verwende eine der beiden Integrationsformen für Flyway:
   Zur Build- oder zur Laufzeit.
   
   **Tipp:** Beide Integrationsformen lassen sich auch kombinieren.

### Flyway Migration beim Build

Folge der Anleitung unter 

https://flywaydb.org/documentation/maven/

zum Einrichten der Datenbankmigration über Maven.

Hinweis zur Konfiguration des Maven-Plugins:
```
<!-- Flyway-Plugin Properties haben einen 'flyway.'-Präfix -->
<flyway.url>jdbc:postgresql://localhost:65432/flyway</flyway.url>
<flyway.user>flyway</flyway.user>
<flyway.password>flyway</flyway.password>
```

#### Ziel

Mit dem Befehl `sh ./mvnw flyway:clean flyway:migrate` soll die Datenbank zurückgesetzt und 
die Skripte aus `src/main/resources/db/migration/all` und `src/main/resources/db/migration/LOCAL`
eingespielt werden.

Beispielausgabe:

```
$ sh ./mvnw flyway:clean flyway:migrate
...
[INFO] --- flyway-maven-plugin:6.1.4:clean (default-cli) @ flyway-maven ---
[INFO] Flyway Community Edition 6.1.4 by Redgate
[INFO] Database: jdbc:postgresql://localhost:65432/flyway (PostgreSQL 12.2)
[INFO] Successfully cleaned schema "flyway" (execution time 00:00.026s)
[INFO] 
[INFO] --- flyway-maven-plugin:6.1.4:migrate (default-cli) @ flyway-maven ---
[INFO] Database: jdbc:postgresql://localhost:65432/flyway (PostgreSQL 12.2)
[INFO] Successfully validated 2 migrations (execution time 00:00.012s)
[INFO] Creating Schema History table "flyway"."flyway_schema_history" ...
[INFO] Current version of schema "flyway": << Empty Schema >>
[INFO] Migrating schema "flyway" to version 1.0 - todo create table
[INFO] Migrating schema "flyway" to version 1.1 - todo insert todos
[INFO] Successfully applied 2 migrations to schema "flyway" (execution time 00:00.050s)
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

### Flyway Migration zur Laufzeit

Zur Einrichtung einer Datenbankmigration zur Laufzeit folge dieser Anleitung:

https://flywaydb.org/documentation/plugins/springboot

und nutze diese Parameter für die `resources/application.properties` Datei:

https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html

Parameter zur Datenbank-Anbindung:
```
spring.flyway.url=jdbc:postgresql://localhost:65432/flyway
spring.flyway.user=flyway
spring.flyway.password=flyway
```

#### Ziel

Mit dem Befehl `sh ./mvnw spring-boot:run` soll beim Start der Anwendung die Datenbank zurückgesetzt und 
die Skripte aus `src/main/resources/db/migration/all` und `src/main/resources/db/migration/LOCAL`
eingespielt werden.