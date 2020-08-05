# Flyway Grundlagen

Dieses Beispiel-Projekt soll dir helfen die verschiedenen Flyway Integrationen und Strategien kennenzulernen.

Dazu wird eine Docker-Umgebung mit einer Datenbank bereitgestellt. Die Kurse enthalten Projekte und zusätzliche Ressourcen, 
um bestimmte Aufgabe mit Flyway zu erledigen und auf diese Weise den Umgang mit Flyway zu lernen.

### Kurse

- [Flyway mit Maven](https://github.com/lehnert-andre/flyway-test-environment/blob/maven/flyway-maven/README.md)
  
  Kurs starten per: `git checkout -b maven origin/maven`
  
  Es erscheint ein `flyway-maven` Spring Boot Projekt. 
  Die Lösungen sind:
  
  - Flyway als Plugin nutzen: `git checkout -b maven-plugin-solution origin/maven-plugin-solution`
  - Flyway zur Laufzeit nutzen: `git checkout -b maven-runtime-solution origin/maven-runtime-solution`
  
- [Flyway mit Gradle](https://github.com/lehnert-andre/flyway-test-environment/blob/gradle/flyway-gradle/README.md)

  Kurs starten per: `git checkout -b gradle origin/gradle`

  Es erscheint ein `flyway-gradle` Spring Boot Projekt.
  Die Lösungen sind:
    
  - Flyway als Plugin nutzen: `git checkout -b gradle-plugin-solution origin/gradle-plugin-solution`
  - Flyway zur Laufzeit nutzen: `git checkout -b gradle-runtime-solution origin/gradle-runtime-solution`
    

## Testumgebung starten

`sh ./startup.sh` startet eine PostgreSQL-Datenbank im Docker.

```
Host: localhost:65432
User: flyway
Password: flyway
Database: flyway
Schema: flyway
```

Für die Kurse muss der passende Branch genutzt werden. 
Die Branches besitzen eine eigene README.

**Achtung:** Bitte die Datenbank und ggf. eine laufende Anwendung vor dem Branch-Wechsel beenden.

## Flyway Skripte

Bei allen Integrationsformen und Strategien brauchen wir SQL-Skripte, 
die Flyway verarbeiten soll.

Die Skripte liegen meistens im Classpath unter `resources/db/migration`. 
Für das Kommandozeilen-Werkzeug kann dieser Ordner ebenfalls genutzt werden.

Siehe https://flywaydb.org/documentation/maven/migrate#locations

### Verschiedene Umgebungen mit Flyway verwalten

Haben wir verschiedene Umgebungen, wie LOCAL oder PROD, 
empfiehlt sich eine Aufteilung in Unterordner. Zum Beispiel:

```
resources/db/migration/
   all/
   LOCAL/
   PROD/
   <Umgebung>/
```

Pro Umgebung wird ein Ordner genutzt, um die verschiedenen Datenbankzustände zu verwalten. 
Außerdem gibt es einen allgemeinen Ordner, 
der die Skripte zur Verwaltung des Datenbankschemas enthält.
Über eine Release-Strategie werden die Änderungen am Datenbankschema und 
die Datenstände in den Umgebungen aufgespielt.

### Namensschema für Flyway-Skripte

`V1_2_3__create_table.sql` basiert auf dem Default-Schema von Flyway:

- `V` ist der Versionspräfix (siehe Parameter `sqlMigrationPrefix`)
- `_` Unterstriche der Version werden zu `.` und definieren Unterversionen, hier 1.2.3
- `__` ist das Trennzeichen zwischen Version und Beschreibung (siehe Parameter `sqlMigrationSeparator`)
- `__*.sql` zwischen Trennzeichen und Datei-Endung `.sql` steht die Beschreibung (siehe Parameter `sqlMigrationSuffixes`)
- `_` Leerzeichen der Beschreibung sind durch `_` ersetzt.

Siehe Liste mit Konfigurationsparametern: https://flywaydb.org/documentation/maven/migrate#configuration

**Hinweis:** 

Neben dem normalen Migrationsskript können auch wiederholbare SQL-Skripte definiert werden, 
die bei jeder Migration ausgeführt werden.

Beispiel: `R__insert_testdata.sql`

