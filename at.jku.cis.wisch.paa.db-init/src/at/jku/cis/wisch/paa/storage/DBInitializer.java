package at.jku.cis.wisch.paa.storage;

import at.jku.cis.wisch.paa.storage.mysql.MySQLStorageAdmin;

public class DBInitializer {

	public static void main(String[] args) {

		PaaStorageAdmin storageAdmin = new MySQLStorageAdmin();
		storageAdmin.init();
		storageAdmin.resetDatabase();
		storageAdmin.close();
		
	}

}
