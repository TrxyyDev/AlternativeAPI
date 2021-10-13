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
			configDetails.put(EnumConfig.USERNAME.getOption(), EnumConfig.USERNAME.getDefault());
			configDetails.put(EnumConfig.RAM.getOption(), EnumConfig.RAM.getDefault());
			configDetails.put(EnumConfig.GAME_SIZE.getOption(), EnumConfig.GAME_SIZE.getDefault());
			configDetails.put(EnumConfig.AUTOLOGIN.getOption(), false);
			configDetails.put(EnumConfig.VM_ARGUMENTS.getOption(), EnumConfig.VM_ARGUMENTS.getDefault());
			configDetails.put(EnumConfig.USE_VM_ARGUMENTS.getOption(), false);

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
	public Object getValue(EnumConfig option) {
		String configJson = JsonUtil.getGson().toJson(this.configVersion);
		JSONObject jsonObject = (JSONObject) JSONValue.parse(configJson);
		return jsonObject.get(option.getOption());
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
