package fr.trxyy.alternative.alternative_authv2.base;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import fr.trxyy.alternative.alternative_apiv2.base.GameEngine;
import fr.trxyy.alternative.alternative_apiv2.minecraft.utils.JsonUtil;
import fr.trxyy.alternative.alternative_authv2.microsoft.model.MicrosoftModel;


public class AuthConfig {

	public File authConfig;
	public MicrosoftModel microsoftModel;
	public boolean read = false;

	/**
	 * The Constructor
	 * @param engine The GameEngine instance
	 */
	public AuthConfig(GameEngine engine) {
		this.authConfig = new File(engine.getGameFolder().getGameDir(), "auth_infos.json");
	}
	
	public boolean canRefresh() {
		if (!this.authConfig.exists()) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * Update a value in the config json
	 */
	@SuppressWarnings("unchecked")
	public void createConfigFile(MicrosoftModel model) {
		if (!this.authConfig.exists()) {
			try {
				this.authConfig.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		JSONObject configDetails = new JSONObject();
		configDetails.put(EnumAuthConfig.ACCESS_TOKEN.getOption(), model.getAccess_token());
		configDetails.put(EnumAuthConfig.REFRESH_TOKEN.getOption(), model.getRefresh_token());
		configDetails.put(EnumAuthConfig.USER_ID.getOption(), model.getUser_id());
		configDetails.put(EnumAuthConfig.SCOPE.getOption(), model.getScope());
		configDetails.put(EnumAuthConfig.TOKEN_TYPE.getOption(), model.getToken_type());
		configDetails.put(EnumAuthConfig.EXPIRES_IN.getOption(), model.getExpires_in());
		configDetails.put(EnumAuthConfig.FOCI.getOption(), model.getFoci());

		try {
			FileWriter fw = new FileWriter(this.authConfig);
			JsonUtil.getGson().toJson(configDetails, fw);
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get a specified value
	 */
	public Object getValue(EnumAuthConfig option) {
		String configJson = JsonUtil.getGson().toJson(this.microsoftModel);
		JSONObject jsonObject = (JSONObject) JSONValue.parse(configJson);
		return jsonObject.get(option.getOption());
	}
	
	/**
	 * Load the configuration
	 */
	public void loadConfiguration() {
		String json = null;
		try {
			json = JsonUtil.loadJSON(this.authConfig.toURI().toURL().toString());
			this.read = true;
		} catch (IOException e) {
			Logger.err("ERROR !!!");
			e.printStackTrace();
		} finally {
			if (this.read) {
				this.microsoftModel = JsonUtil.getGson().fromJson(json, MicrosoftModel.class);
			}
		}
	}
	
	
	/**
	 * Update a value in the config json
	 */
	public void updateValue(String toUpdate, Object value) {
		this.loadConfiguration();
		String configJson = JsonUtil.getGson().toJson(this.microsoftModel);
		JSONObject jsonObject = (JSONObject) JSONValue.parse(configJson);
		jsonObject.put(toUpdate, value);
		try {
			FileWriter fileWriter = new FileWriter(this.authConfig);
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
		String configJson = JsonUtil.getGson().toJson(this.microsoftModel);
		JSONObject jsonObject = (JSONObject) JSONValue.parse(configJson);
		for (Entry<String, String> entry : values.entrySet()) {
			jsonObject.put(entry.getKey(), entry.getValue());
		}
		
		try {
			FileWriter fileWriter = new FileWriter(this.authConfig);
			JsonUtil.getGson().toJson(jsonObject, fileWriter);
			fileWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateValues(MicrosoftModel model) {
		HashMap<String, String> values = new HashMap<String, String>();
		values.put(EnumAuthConfig.ACCESS_TOKEN.getOption(), model.getAccess_token());
		values.put(EnumAuthConfig.REFRESH_TOKEN.getOption(), model.getRefresh_token());
		values.put(EnumAuthConfig.USER_ID.getOption(), model.getUser_id());
		values.put(EnumAuthConfig.SCOPE.getOption(), model.getScope());
		values.put(EnumAuthConfig.TOKEN_TYPE.getOption(), model.getToken_type());
		values.put(EnumAuthConfig.EXPIRES_IN.getOption(), model.getExpires_in());
		values.put(EnumAuthConfig.FOCI.getOption(), model.getFoci());
		this.updateValues(values);
	}
}
