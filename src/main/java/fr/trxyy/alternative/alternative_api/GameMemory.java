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

	public static GameMemory getMemory(double value) {
		if (value == 0) {
			return GameMemory.DEFAULT;
		} else if (value == 1) {
			return GameMemory.DEFAULT;
		} else if (value == 2) {
			return GameMemory.RAM_2G;
		} else if (value == 3) {
			return GameMemory.RAM_3G;
		} else if (value == 4) {
			return GameMemory.RAM_4G;
		} else if (value == 5) {
			return GameMemory.RAM_5G;
		} else if (value == 6) {
			return GameMemory.RAM_6G;
		} else if (value == 7) {
			return GameMemory.RAM_7G;
		} else if (value == 8) {
			return GameMemory.RAM_8G;
		} else if (value == 9) {
			return GameMemory.RAM_9G;
		} else if (value == 10) {
			return GameMemory.RAM_10G;
		}
		return GameMemory.DEFAULT;
	}

}
