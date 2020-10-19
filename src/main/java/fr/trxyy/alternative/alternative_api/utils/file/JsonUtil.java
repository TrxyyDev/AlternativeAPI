package fr.trxyy.alternative.alternative_api.utils.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.trxyy.alternative.alternative_api.minecraft.json.Argument;
import fr.trxyy.alternative.alternative_api.minecraft.utils.DateTypeAdapter;
import fr.trxyy.alternative.alternative_api.minecraft.utils.LowerCaseEnumTypeAdapterFactory;

/**
 * @author Trxyy
 */
public class JsonUtil {

	/**
	 * @return A good Gson to read Minecraft version json
	 */
	public static Gson getGson() {
		final GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapterFactory(new LowerCaseEnumTypeAdapterFactory());
		gsonBuilder.registerTypeAdapter(Date.class, new DateTypeAdapter());
		gsonBuilder.registerTypeAdapter(Argument.class, new Argument.Serializer());
		gsonBuilder.enableComplexMapKeySerialization();
		gsonBuilder.setPrettyPrinting();
		return gsonBuilder.create();
	}

	/**
	 * @param inUrl The url to load
	 * @return A String with the Json
	 * @throws IOException
	 */
	public static String loadJSON(String inUrl) throws IOException {
		URL url = new URL(inUrl);
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		String json = new String();
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			json = json + inputLine;
		}
		return json;
	}

}
