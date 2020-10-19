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

/**
 * @author Trxyy
 */
public class GameAuth
{
	/**
	 * The Username/mail
	 */
	public String USERNAME = "";
	/**
	 * The password
	 */
	public String PASSWORD = "";
	/**
	 * Is player authed ?
	 */
	public boolean isAuthed = false;
	/**
	 * The session
	 */
	private static Session session = new Session();
  
	/**
	 * The Constructor
	 * @param userName The username/mail
	 * @param passWord The password
	 * @param realAuth The account Type (Mojang/Offline)
	 */
	public GameAuth(String userName, String passWord, AccountType realAuth) {
		if (realAuth.equals(AccountType.MOJANG)) {
			session.setUsername(userName);
			this.USERNAME = userName;
			this.PASSWORD = passWord;
			tryLogin();
		} else if (realAuth.equals(AccountType.OFFLINE)) {
			isAuthed = true;
		    session.setUsername(userName);
		    session.setToken(TokenGenerator.generateToken(userName));
		    session.setUuid(UUID.randomUUID().toString().replace("-", ""));
		}
	}
	
  /**
   * Try to login
   */
	public void tryLogin() {
		Logger.log("Try login...");
		this.connectMinecraft(this.USERNAME, this.PASSWORD);
	}
  
	/**
	 * Connect to minecraft servers using POST request
	 * @param username The username
	 * @param password The password
	 * @return The result
	 */
	public String[] connectMinecraft(String username, String password) {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();

		try {
			HttpPost request = new HttpPost("https://authserver.mojang.com/authenticate");
			StringEntity params = new StringEntity("{\"agent\":{\"name\":\"Minecraft\",\"version\":1},\"username\":\"" + username + "\",\"password\":\"" + password + "\"}", ContentType.create("application/json"));
			request.addHeader("content-type", "application/json");
			request.setEntity(params);
			CloseableHttpResponse closeableHttpResponse = httpClient.execute(request);
			BufferedReader rd = new BufferedReader(new InputStreamReader(closeableHttpResponse.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			Logger.log("Getting result...");
			return getResult(result.toString());
		} catch (Exception exception)

		{
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

		return null;
	}
  
	/**
	 * A simple function to translate the result from POST request to a lisible
	 * @param result The result from the POST request
	 * @param username The username
	 * @return Results better
	 */
  public String[] getResult(String result) {
    result = result.replace("\"", "");
    result = result.replace("{", "");
    result = result.replace("}", "");
    result = result.replace("[", "");
    result = result.replace("]", "");
    String accessToken = "";
    String name = "";
    String uuId = "";
    String clientToken = "";
    String[] split = result.split(",");
    for (int i = 0; i < split.length; i++) {
      if (split[i].startsWith("accessToken:")) {
        accessToken = split[i].replace("accessToken:", "");
      } else if (split[i].startsWith("id:")) {
        uuId = split[i].replace("id:", "");
      } else if (split[i].startsWith("clientToken:")) {
        clientToken = split[i].replace("clientToken:", "");
      } else if (split[i].startsWith("availableProfiles:")) {
        name = split[i].replace("name:", "").replace("availableProfiles:", "");
      } 
    } 
    String[] results = { name, accessToken, clientToken, uuId };
   /** String u = insertAt(clientToken, 8, "-");
    String v = insertAt(u, 13, "-");
    String k = insertAt(v, 18, "-");
    String accessTokenModif = insertAt(k, 23, "-");
    String u2 = insertAt(uuId, 8, "-");
    String v2 = insertAt(u2, 13, "-");
    String k2 = insertAt(v2, 18, "-");
    String uu2 = insertAt(k2, 23, "-");*/
    isAuthed = true;
    session.setUsername(results[0]);
    session.setToken(results[1]);
    session.setUuid(results[3]);
    Logger.log("Successful Logged In !");
    return results;
  }
  
  @Deprecated
  public static String insertAt(String target, int position, String insert) {
    int targetLen = target.length();
    if (position < 0 || position > targetLen) {
      throw new IllegalArgumentException("position=" + position);
    }
    if (insert.isEmpty()) {
      return target;
    }
    if (position == 0)
      return insert.concat(target); 
    if (position == targetLen) {
      return target.concat(insert);
    }
    int insertLen = insert.length();
    char[] buffer = new char[targetLen + insertLen];
    target.getChars(0, position, buffer, 0);
    insert.getChars(0, insertLen, buffer, position);
    target.getChars(position, targetLen, buffer, position + insertLen);
    return new String(buffer);
  }

  /**
   * @return If the user is Authed or not
   */
	public boolean isLogged() {
		return isAuthed;
	}

	/**
	 * @return The session of the user
	 */
	public Session getSession() {
		return session;
	}
}
