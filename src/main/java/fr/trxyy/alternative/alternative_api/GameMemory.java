package fr.trxyy.alternative.alternative_api;

/**
 * @author Trxyy
 */
public enum GameMemory {
	DEFAULT("1G"),
	RAM_2G("2G"),
	RAM_3G("3G"),
	RAM_4G("4G"),
	RAM_5G("5G"),
	RAM_6G("6G"),
	RAM_7G("7G"),
	RAM_8G("8G"),
	RAM_9G("9G"),
	RAM_10G("10G");
	
	/**
	 * The memory count
	 */
	private String count;

	/**
	 * The Constructor
	 * @param ram count
	 */
	GameMemory(String ramCount) {
		this.count = ramCount;
	}
	
	/**
	 * @return The RAM count
	 */
	public String getCount() {
		return count;
	}

}
