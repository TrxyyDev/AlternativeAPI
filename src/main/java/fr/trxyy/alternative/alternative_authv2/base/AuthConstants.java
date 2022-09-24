package fr.trxyy.alternative.alternative_authv2.base;

import java.nio.charset.Charset;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AuthConstants {
	/**
	 * Mojang Auth Constants
	 */
	public static String MOJANG_BASE_URL = "https://authserver.mojang.com/authenticate";
	/**
	 * Microsoft Auth Constants
	 */
	public static String MICROSOFT_BASE_URL = "https://login.live.com/oauth20_authorize.srf?client_id=00000000402b5328&response_type=code&redirect_uri=https%3A%2F%2Flogin.live.com%2Foauth20_desktop.srf&scope=XboxLive.signin%20offline_access";
	public static String MICROSOFT_RESPONSE_URL = "https://login.live.com/oauth20_desktop.srf?code=";
	public static String MICROSOFT_AUTHENTICATE_XBOX = "https://user.auth.xboxlive.com/user/authenticate";
	public static String MICROSOFT_AUTHORIZE_XSTS = "https://xsts.auth.xboxlive.com/xsts/authorize";
	public static String MICROSOFT_LOGIN_XBOX = "https://api.minecraftservices.com/authentication/login_with_xbox";
	public static String MICROSOFT_MINECRAFT_STORE = "https://api.minecraftservices.com/entitlements/mcstore";
	public static String MICROSOFT_MINECRAFT_PROFILE = "https://api.minecraftservices.com/minecraft/profile";
	public static String MICROSOFT_AUTH_TOKEN = "https://login.live.com/oauth20_token.srf";
	/**
	 * Utils Constants
	 */
	public static String APP_JSON = "application/json";
	public static String URL_ENCODED = "application/x-www-form-urlencoded";
	public static Charset UTF_8 = Charset.forName("UTF-8");
	
	/**
	 * Library Constants
	 */
	public static String VERSION_ID = "1.0.2";
	
	/**
	 * @return A Gson to read json
	 */
	public static Gson getGson() {
		final GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.enableComplexMapKeySerialization();
		gsonBuilder.setPrettyPrinting();
		return gsonBuilder.create();
	}
	
	/**
	 * Display some copyrights
	 */
	public static void displayCopyrights() {
		Logger.log("========================================");
		Logger.log("|            AlternativeAuth           |");
		Logger.log("|            Version: " + getVersion() + "            |");
		Logger.log("========================================");
	}

	private static String getVersion() {
		return VERSION_ID;
	}
	
}
