# DSGVO_Validator

System requirements:

- Running Java Version
- MySQL Database containing at least 2 tables of the same structure (and size)

1. Get the project from github and import it into the editor of your choice
2. Set up a MySQl Database including at minimum two tables (one with the original data, one with the anonymized data) -> working at proficom: you find some example data in the sharepoint folder in the databases.zip -> see exact steps of setting up the database further down
3. Go into the project -> application.properties -> set up the url of your database and the required access data (username and password) for it
4. Adjust this data also in java -> dbOperations -> DatabaseOperations.java in both the functions “readDBTables” and “getTable” both for the getconnection Function
5. Adjust the repository files, currently they’re setup to use “patientRepository” and “patientAnonymRepository”
    - Due to restriction with JPA you need to adjust the name of the used table in the line “public Interface XXXX extends JpaRepository<A, B>” -> set A to the name of the table you’re using, otherwise JPA cannot connect to the right table and use the data in it
    -  The current Table layout is the following:
        - id
        - birthday
        - email
        - first_name
        - insurance
        - last_invoice_total
        - surname
        - contact
        - phone_number
        - private_insurance (comment: this is meant to be Boolean, but currently it seems to be only working as an integer field)
    - If you have your own table layout, you need to adjust all the data access accordingly (mainly in the template files)
6. Run your application and access it via a browser at localhost:8080
7. First page gives you all the data of both the tables integrated
8. Via the link “settings” you’re able to adjust the values for sigma and change the distance metric
    - standard values are 1.0 for everything, a value of 0.0 is not allowed
    - currently changing the distance metric means, that this is only changed for string, as only the heinrich metric offers native support of other datatypes
    - see the publications to get more information about the impact of sigma
9. via the link “compare all data” the system compares all the data with each other using the scheme line 1 from table A with line 1 from table B, line 2A with line 2B and so on
10. the link “journal for this dataset” gives you all the single values for each cell of the two rows compared with each other
11. using the link “find the most similar dataset” searches for the row of data, that is the most similar, the result depends on your data
12. currently, intelliJ marks some of the thymeleaf code as incorrect, which doesn’t seem right in use. So that can just be ignored



Database set up:

- import project into IDE of your choice
- install MySQL (or MariaDB with Arch Linux)
- start the database: 
  - > sudo mysql_install_db --user=mysql --basedir=/usr --datadir=/var/lib/mysql 
  - > sudo systemctl start mysqld
  - > sudo systemctl enable mysqld
  - > mysql_secure_installation
- use the database
  - > sudo mysql -u root -p
- create the database 'Zahnklinik': (in folder of .sql file)
   - if you're using your own database, the naming and access data is yours to pick. The structure of the steps stays the same, just replace 'zahnklink' with the name you picked. If you're using the files at profi.com AG, the database is called 'zahnklinik' 
   - > mysql -u root -p
   - > CREATE DATABASE zahnklinik;
   - > mysql -u root -p zahnklinik < zahnklinik.sql 
   - current accessdata that is set up: user = root; password = user12


.txt file uses spaces to delimit thousands

   

