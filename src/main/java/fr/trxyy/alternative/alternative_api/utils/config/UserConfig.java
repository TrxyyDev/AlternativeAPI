package fr.trxyy.alternative.alternative_api.utils.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import fr.trxyy.alternative.alternative_api.GameEngine;
import fr.trxyy.alternative.alternative_api.GameMemory;
import fr.trxyy.alternative.alternative_api.GameSize;
import fr.trxyy.alternative.alternative_api.utils.Logger;

/**
 * @author Trxyy
 */
public class UserConfig {

	/**
	 * The ram
	 */
	public String ram;
	/**
	 * The window Size
	 */
	public String windowsSize;
	/**
	 * Use Auto Login
	 */
	public String autoLogin;
	/**
	 * The suer config file
	 */
	public File userConfig;

	/**
	 * The Constructor
	 * @param engine The GameEngine instance
	 */
	public UserConfig(GameEngine engine) {
		this.userConfig = new File(engine.getGameFolder().getBinDir(), "user_config.cfg");
		if (!this.userConfig.exists()) {
			try {
				this.userConfig.createNewFile();
				this.writeConfig("1", "854x480", "false");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		readConfig();
		engine.reg(convertMemory(getMemory()));
		engine.reg(getWindowSize(getWindowSize()));
	}

	/**
	 * Write a config in the file
	 * @param s The ram
	 * @param s1 The window size
	 * @param s2 Auto Login usage
	 */
	public void writeConfig(String s, String s1, String s2) {
		try {
			FileWriter fw = new FileWriter(this.userConfig);
			fw.write(s + ";");
			fw.write(s1 + ";");
			fw.write(s2);
			fw.close();
		} catch (IOException e) {
			Logger.log(e.toString());
		}
	}

	/**
	 * Read the config file
	 */
	public void readConfig() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.userConfig));
			String line = br.readLine();
			String[] result = line.split(";");
			this.ram = result[0];
			this.windowsSize = result[1];
			try {
				this.autoLogin = result[2];
			} catch (Exception e) {
				if (this.userConfig.exists()) {
					this.userConfig.delete();
					this.userConfig.createNewFile();
					this.writeConfig("1", "854x480", "false");
				}
				else {
					this.userConfig.createNewFile();
					this.writeConfig("1", "854x480", "false");
				}
			}
			br.close();
		} catch (IOException e) {
			Logger.log(e.toString());
		}
	}

	/**
	 * @return The RAM as a String
	 */
	public String getRamString() {
		return this.ram;
	}

	/**
	 * @return The Ram as a double
	 */
	public double getRam() {
		if (ram.contentEquals("1.0")) {
			return 1;
		} else if (ram.contentEquals("2.0")) {
			return 2;
		} else if (ram.contentEquals("3.0")) {
			return 3;
		} else if (ram.contentEquals("4.0")) {
			return 4;
		} else if (ram.contentEquals("5.0")) {
			return 5;
		} else if (ram.contentEquals("6.0")) {
			return 6;
		} else if (ram.contentEquals("7.0")) {
			return 7;
		} else if (ram.contentEquals("8.0")) {
			return 8;
		} else if (ram.contentEquals("9.0")) {
			return 9;
		} else if (ram.contentEquals("10.0")) {
			return 10;
		}
		return 1;
	}

	/**
	 * @param value The Value as a String
	 * @return The GameMemory from a String
	 */
	public GameMemory convertMemory(String value) {
		if (value.equals("0.0")) {
			return GameMemory.DEFAULT;
		} else if (value.equals("1.0")) {
			return GameMemory.DEFAULT;
		} else if (value.equals("2.0")) {
			return GameMemory.RAM_2G;
		} else if (value.equals("3.0")) {
			return GameMemory.RAM_3G;
		} else if (value.equals("4.0")) {
			return GameMemory.RAM_4G;
		} else if (value.equals("5.0")) {
			return GameMemory.RAM_5G;
		} else if (value.equals("6.0")) {
			return GameMemory.RAM_6G;
		} else if (value.equals("7.0")) {
			return GameMemory.RAM_7G;
		} else if (value.equals("8.0")) {
			return GameMemory.RAM_8G;
		} else if (value.equals("9.0")) {
			return GameMemory.RAM_9G;
		} else if (value.equals("10.0")) {
			return GameMemory.RAM_10G;
		}
		return GameMemory.DEFAULT;
	}

	/**
	 * @param value The value as a double
	 * @return The Memory from the double
	 */
	public GameMemory getMemory(double value) {
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

	/**
	 * @param value The value as a String
	 * @return The GameSize from the String
	 */
	public GameSize getWindowSize(String value) {
		if (value.equals("854x480")) {
			return GameSize.DEFAULT;
		} else if (value.equals("1024x768")) {
			return GameSize.SIZE_1024x768;
		} else if (value.equals("1280x1024")) {
			return GameSize.SIZE_1280x1024;
		} else if (value.equals("1366x768")) {
			return GameSize.SIZE_1366x768;
		} else if (value.equals("1600x900")) {
			return GameSize.SIZE_1600x900;
		} else if (value.equals("1920x1080")) {
			return GameSize.SIZE_1920x1080;
		} else if (value.equals("2560x1440")) {
			return GameSize.SIZE_2560x1440;
		}
		return GameSize.DEFAULT;
	}

	/**
	 * @return The window size
	 */
	public String getWindowSize() {
		return this.windowsSize;
	}
	
	/**
	 * @return The auto login as a String
	 */
	public String getAutoLoginString() {
		return this.autoLogin;
	}
	
	/**
	 * @return The auto login as a boolean
	 */
	public boolean getAutoLogin() {
		if (this.autoLogin.equals("false")) {
			return false;
		}
		else {
			return true;
		}
	}

	/**
	 * @return The Memory as a String
	 */
	public String getMemory() {
		return ram;
	}

}
