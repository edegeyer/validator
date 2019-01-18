# DSGVO_Validator
Txt Datei bitte mit Excel öffene, beim Importieren habe ich leerzeichen als tausendertrennzeichen gewählt.

Die Excel Ausgabe für swap3and4 gibt an, dass Sigma length und Sigma distribution spielt gar keine Rolle bei Theta Rechnung. Ich denke das macht sinn weil wenn man drittes und viertes Character vertäuscht, die Länge dieser Zeichenkette ändert gar nicht, deshalb Theta length ist 0. Das gilt auch für Theta distribution.

Die Excel Ausgabe für domainname habe ich nach Theta Werte aufsteigend sortiert, die Umlaute werden zwar nicht korrekt angezeigt, aber die Werte sind korrekt. Ich lade die andere Dateien bald hoch.


Für Einrichtung des Systems:

- Projekt in IDE der Wahl importieren
- auf System mySql (bzw MariaDB unter Arch) installieren
- Start der Datenbank: 
  - > sudo mysql_install_db --user=mysql --basedir=/usr --datadir=/var/lib/mysql 
  - > sudo systemctl start mysqld
  - > sudo systemctl enable mysqld
  - > mysql_secure_installation
- Nutzen der Datenbank
  - > sudo mysql -u root -p
- Erzeugen der Datenbank Zahnklinik: (im Ordner der .sql Datei)
   - > mysql -u root -p
   - > CREATE DATABASE zahnklinik;
   - > zahnklinik < zahnklinik.sql

    