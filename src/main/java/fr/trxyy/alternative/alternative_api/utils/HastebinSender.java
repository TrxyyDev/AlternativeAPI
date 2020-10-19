package fr.trxyy.alternative.alternative_api.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

/**
 * @author Trxyy
 */
public class HastebinSender {

	/**
	 * The Hastebin url to POST
	 */
	public String DEFAULT_URL = "https://hasteb.in/";

	/**
	 * Post the error to hastebin
	 * @param text The text to post
	 * @param raw is Raw ?
	 * @return A String with the response
	 * @throws IOException
	 */
	public String postError(String text, boolean raw) throws IOException {
		byte[] postData = text.getBytes(StandardCharsets.UTF_8);
		int postDataLength = postData.length;

		String requestURL = DEFAULT_URL + "documents";
		URL url = new URL(requestURL);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setInstanceFollowRedirects(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("User-Agent", "Hastebin Java Api");
		conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
		conn.setUseCaches(false);

		String response = null;
		DataOutputStream wr;
		try {
			wr = new DataOutputStream(conn.getOutputStream());
			wr.write(postData);
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			response = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (response.contains("\"key\"")) {
			response = response.substring(response.indexOf(":") + 2, response.length() - 2);

			String postURL = raw ? DEFAULT_URL + "raw/" : DEFAULT_URL;
			response = postURL + response;
		}

		return response;
	}

}
