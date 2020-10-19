package fr.trxyy.alternative.alternative_api.utils.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import fr.trxyy.alternative.alternative_api.GameEngine;
import fr.trxyy.alternative.alternative_api.utils.Logger;
import fr.trxyy.alternative.alternative_api.utils.file.JsonUtil;

@SuppressWarnings("unchecked")
public class LauncherConfig {

	public ConfigVersion configVersion;
	public boolean read = false;
	public File launcherConfig;
	public GameEngine gameEngine;

	/**
	 * The Constructor
	 * @param engine The GameEngine instance
	 */
	public LauncherConfig(GameEngine engine) {
		this.gameEngine = engine;
		this.launcherConfig = new File(engine.getGameFolder().getBinDir(), "launcher_config.json");

		if (!this.launcherConfig.exists()) {
			try {
				this.launcherConfig.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			/**
			 * Config details
			 */
			JSONObject configDetails = new JSONObject();
			configDetails.put("username", "Player");
			configDetails.put("allocatedram", 1.0);
			configDetails.put("gamesize", "0");
			configDetails.put("autologin", false);
			configDetails.put("vmarguments", "-XX:+CMSIncrementalMode");
			configDetails.put("usevmarguments", false);

			try {
				FileWriter fw = new FileWriter(this.launcherConfig);
				JsonUtil.getGson().toJson(configDetails, fw);
				fw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Update a value in the config json
	 */
	public void updateValue(String toUpdate, Object value) {
		this.loadConfiguration();
		String configJson = JsonUtil.getGson().toJson(this.configVersion);
		JSONObject jsonObject = (JSONObject) JSONValue.parse(configJson);
		jsonObject.put(toUpdate, value);
		try {
			FileWriter fileWriter = new FileWriter(this.launcherConfig);
			JsonUtil.getGson().toJson(jsonObject, fileWriter);
			fileWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Update multiple values in the config json
	 */
	public void updateValues(HashMap<String, String> values) {
		this.loadConfiguration();
		String configJson = JsonUtil.getGson().toJson(this.configVersion);
		JSONObject jsonObject = (JSONObject) JSONValue.parse(configJson);
		for (Entry<String, String> entry : values.entrySet()) {
			jsonObject.put(entry.getKey(), entry.getValue());
		}
		
		try {
			FileWriter fileWriter = new FileWriter(this.launcherConfig);
			JsonUtil.getGson().toJson(jsonObject, fileWriter);
			fileWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get a specified value
	 */
	public Object getValue(String toGet) {
		String configJson = JsonUtil.getGson().toJson(this.configVersion);
		JSONObject jsonObject = (JSONObject) JSONValue.parse(configJson);
		return jsonObject.get(toGet);
	}

	/**
	 * Load the configuration
	 */
	public void loadConfiguration() {
		String json = null;
		try {
			json = JsonUtil.loadJSON(this.launcherConfig.toURI().toURL().toString());
			this.read = true;
		} catch (IOException e) {
			Logger.err("ERROR !!!");
			e.printStackTrace();
		} finally {
			if (this.read) {
				this.configVersion = JsonUtil.getGson().fromJson(json, ConfigVersion.class);
			}
		}
	}
}
