# Flyway Grundlagen

Dieses Beispiel-Projekt soll dir helfen die verschiedenen Flyway Integrationen und Strategien kennenzulernen.

### Kurse

- [Flyway mit Maven](#headin)

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

