package at.jku.cis.wisch.paa.storage.mysql;

import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.jku.cis.wisch.paa.storage.PaaStorageAdmin;

public class MySQLStorageAdmin extends MySQLStorage implements PaaStorageAdmin {

	private static final Logger logger = LogManager.getLogger(MySQLStorageAdmin.class);
	
	public void resetDatabase(){
		logger.info("Resetting database...");
		
		// drop tables
		dropTable("User_Role");
		dropTable("User");
		dropTable("ItemVisit");
		
		// create tables
		createTable("User (id INT NOT NULL AUTO_INCREMENT,"
				+ "username VARCHAR(64) NOT NULL,"
				+ "password VARCHAR(64) NOT NULL,"
				+ "enabled TINYINT NOT NULL DEFAULT 1,"
				+ "PRIMARY KEY (id));");
		createTable("User_Role (user_role_id INT(11) NOT NULL AUTO_INCREMENT,"
				+ "userid INT NOT NULL,"
				+ "role VARCHAR(32) NOT NULL,"
				+ "PRIMARY KEY (user_role_id),"
				+ "UNIQUE KEY uni_username_role (role,userid),"
				+ "KEY fk_userid_idx (userid),"
				+ "CONSTRAINT fk_userid FOREIGN KEY (userid) REFERENCES User(id));");
		
		createTable("ItemVisit (id INT NOT NULL AUTO_INCREMENT,"
				+ "user VARCHAR(64) NOT NULL,"
				+ "url VARCHAR(1000) NOT NULL,"
				+ "timestamp TIMESTAMP NOT NULL,"
				+ "timestampms BIGINT NOT NULL,"
				+ "PRIMARY KEY (id));");
		
		
		// TODO remove adding of users when registration page was made
		logger.info("Adding users and roles...");
		insertInto("User (username, password) VALUES ('elboato', 's3cr3t');");
		insertInto("User_Role (userid, role) VALUES ('1', 'ROLE_USER');");
		insertInto("User_Role (userid, role) VALUES ('1', 'ROLE_ADMIN');");
		
	}

	private void insertInto(String insertString) {
		Statement statement;
		String create = "INSERT INTO "+insertString;
		try {
			logger.info("Inserting into table "+insertString);
			statement = connect.createStatement();
			statement.execute(create);
		} catch (SQLException e) {
			logger.error("error inserting...", e);
			e.printStackTrace();
		}
	}

	private void createTable(String tableDefString) {
		Statement statement;
		String create = "CREATE TABLE "+tableDefString;
		
		try {
			logger.info("Creating table "+tableDefString);
			statement = connect.createStatement();
			statement.execute(create);
		} catch (SQLException e) {
			logger.error("error while creating table", e);
			e.printStackTrace();
		}
	}

	private void dropTable(String tableName) {
		String drop = "DROP TABLE "+tableName;
		
		Statement statement;
		try {
			logger.info("Dropping table "+tableName);
			statement = connect.createStatement();
			statement.execute(drop);
		} catch (SQLException e) {
			logger.error("error while dropping table --- maybe it did not exist yet", e);
			e.printStackTrace();
		}
	}
	
	
}
