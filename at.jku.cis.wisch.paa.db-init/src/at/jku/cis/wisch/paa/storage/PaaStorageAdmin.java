package at.jku.cis.wisch.paa.storage;

public interface PaaStorageAdmin extends PaaStorage {
	
	
	/**
	 * drop all tables and create new ones with the required DB schema
	 */
	public void resetDatabase();

}
