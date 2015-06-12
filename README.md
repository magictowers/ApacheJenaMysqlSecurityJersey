# ApacheJenaMysqlSecurityJersey
Project that includes a configuration to use different technologies to a semantic web application: Jersey, Jena, Mysql, Lenskit, and Spring security.

# Instructions 

## Install MySQL
##	Create User and DB in MySQL
see \at.jku.cis.wisch.paa.db-init\db_init.txt for an example
##	Configuration of Database and Filesystem
###	use properties for DB access from previous step
###	filesystem properties for logging (log4j.appender.file.File) and Jena TDB storage folder (at.jku.cis.wisch.paa.storage.tdb.directory)
in the following files:
###	\at.jku.cis.wisch.paa.db-init\src\
####	log4j.properties
####	at.jku.cis.wisch.paa.storage.properties
###	\at.jku.cis.wisch.paa.server\src\main\webapp\WEB-INF\classes\
####	log4j.properties
####	at.jku.cis.wisch.paa.storage.properties
#	Import both projects to Eclipse
(I am BTW using Eclipse JEE Luna)
#	Run DB-initializer main class
\at.jku.cis.wisch.paa.db-init\src\at\jku\cis\wisch\paa\storage\DBInitializer.java
this will (delete existing ones and) create the required tables in MySQL
at the first run it will throw exceptions because deleting the (not yet) existing tables will fail. run it twice. if it runs without errors, your DB is set up and configured correctly.
#	Start the Apache server
to do so simply run a maven build with the goal “tomcat7:run” for the project /at.jku.cis.wisch.paa.server
#	Access the server in your browser
start with localhost:8080/ui/home
