package fr.trxyy.alternative.alternative_api.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import fr.trxyy.alternative.alternative_api.account.AccountType;
import fr.trxyy.alternative.alternative_api.account.Session;
import fr.trxyy.alternative.alternative_api.utils.Logger;
import fr.trxyy.alternative.alternative_api.utils.file.JsonUtil;

/**
 * @author Trxyy
 */
public class GameAuth
{
	/**
	 * Is player authed ?
	 */
	public boolean isAuthed = false;
	/**
	 * The session
	 */
	private Session session = new Session();
  
	/**
	 * The Constructor
	 * @param user The username/mail
	 * @param passWord The password
	 * @param accountType The account Type (Mojang/Offline)
	 */
	public GameAuth(String user, String passWord, AccountType accountType) {
		if (accountType.equals(AccountType.MOJANG)) {
			this.session.setUsername(user);
			this.tryLogin(user, passWord);
		} else if (accountType.equals(AccountType.OFFLINE)) {
			this.isAuthed = true;
		    this.session.setUsername(user);
		    this.session.setToken(TokenGenerator.generateToken(user));
		    this.session.setUuid(UUID.randomUUID().toString().replace("-", ""));
		}
	}
	
  /**
   * Try to login
   */
	public void tryLogin(String user, String pwd) {
		Logger.log("Try login...");
		this.connectMinecraft(user, pwd);
	}
  
	/**
	 * Connect to minecraft servers using POST request
	 * @param username The username
	 * @param password The password
	 * @return The result
	 */
	public void connectMinecraft(String username, String password) {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();

		try {
			HttpPost httpPost = new HttpPost("https://authserver.mojang.com/authenticate");
			StringEntity parameters = new StringEntity("{\"agent\":{\"name\":\"Minecraft\",\"version\":1},\"username\":\"" + username + "\",\"password\":\"" + password + "\"}", ContentType.create("application/json"));
			httpPost.addHeader("content-type", "application/json");
			httpPost.setEntity(parameters);
			CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(closeableHttpResponse.getEntity().getContent()));
			String response = bufferedReader.readLine();
			Logger.log("Authentication Result: " + response);
			if (!response.contains("\"name\"")) {
				this.isAuthed = false;
			}
			AuthResult authResult = JsonUtil.getGson().fromJson(response, AuthResult.class);
		    this.session.setUsername(authResult.getSelectedProfile().getName());
		    this.session.setToken(authResult.getAccessToken());
		    this.session.setUuid(authResult.getSelectedProfile().getId());
			this.isAuthed = true;
		} catch (Exception exception) {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

  /**
   * @return If the user is Authed or not
   */
	public boolean isLogged() {
		return this.isAuthed;
	}

	/**
	 * @return The session of the user
	 */
	public Session getSession() {
		return this.session;
	}
}
