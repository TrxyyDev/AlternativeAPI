package fr.trxyy.alternative.alternative_authv2.base;

import fr.trxyy.alternative.alternative_apiv2.base.GameEngine;
import fr.trxyy.alternative.alternative_authv2.microsoft.MicrosoftAuth;
import fr.trxyy.alternative.alternative_authv2.microsoft.ParamType;
import fr.trxyy.alternative.alternative_authv2.microsoft.model.MicrosoftModel;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * @author Trxyy
 */
public class GameAuth {
	/**
	 * Is player authed ?
	 */
	public boolean isAuthenticated = false;
	/**
	 * The session
	 */
	private Session session = new Session();
	
	private AuthConfig authConfig;

	/**
	 * Connect to minecraft with a Microsoft account
	 * 
	 * @param root The parent to show in
	 * @return The result in a WebView
	 */
	public WebView connectMicrosoft(Pane root, GameEngine engine) {
			this.authConfig = new AuthConfig(engine);
			final WebView webView = new WebView();
			final WebEngine webEngine = webView.getEngine();
			webEngine.load(AuthConstants.MICROSOFT_BASE_URL);
			webEngine.setJavaScriptEnabled(true);
			webView.setPrefWidth(500);
			webView.setPrefHeight(600);
			root.getChildren().add(webView);
			webEngine.getHistory().getEntries().addListener((ListChangeListener<WebHistory.Entry>) c -> {
				if (c.next() && c.wasAdded()) {
					c.getAddedSubList().forEach(entry -> {
						try {
							if (this.authConfig.canRefresh()) {
								Logger.log("Trying to logIn with RefreshToken.");
								this.authConfig.loadConfiguration();
								MicrosoftModel model = null;
								try {
									model = new MicrosoftAuth().getAuthorizationCode(ParamType.REFRESH, authConfig.microsoftModel.getRefresh_token());
								} catch (Exception e) {
									e.printStackTrace();
								}
								authConfig.updateValues(model);
								Session result = null;
								try {
									result = new MicrosoftAuth().getLiveToken(model.getAccess_token());
								} catch (Exception e) {
									e.printStackTrace();
									if (entry.getUrl().startsWith(AuthConstants.MICROSOFT_RESPONSE_URL)) {
										String authCode = entry.getUrl().substring(entry.getUrl().indexOf("=") + 1, entry.getUrl().indexOf("&"));
										MicrosoftModel model2 = new MicrosoftAuth().getAuthorizationCode(ParamType.AUTH, authCode);
										authConfig.createConfigFile(model2);
										Session result2 = new MicrosoftAuth().getLiveToken(model2.getAccess_token());
										this.setSession(result2.getUsername(), result2.getToken(), result2.getUuid());
										Stage stage = (Stage) root.getScene().getWindow();
										stage.close();
									} else {
										this.isAuthenticated = false;
									}
								}
								this.setSession(result.getUsername(), result.getToken(), result.getUuid());
								Stage stage = (Stage) root.getScene().getWindow();
								stage.close();
							}
							else {
								if (entry.getUrl().startsWith(AuthConstants.MICROSOFT_RESPONSE_URL)) {
									String authCode = entry.getUrl().substring(entry.getUrl().indexOf("=") + 1, entry.getUrl().indexOf("&"));
									MicrosoftModel model = new MicrosoftAuth().getAuthorizationCode(ParamType.AUTH, authCode);
									authConfig.createConfigFile(model);
									Session result = new MicrosoftAuth().getLiveToken(model.getAccess_token());
									this.setSession(result.getUsername(), result.getToken(), result.getUuid());
									Stage stage = (Stage) root.getScene().getWindow();
									stage.close();
								} else {
									this.isAuthenticated = false;
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
							;
						}
					});
				}
			});
			return webView;
	}
	
	/**
	 * Set the session credentials
	 * @param user
	 * @param token
	 * @param id
	 */
	private void setSession(String user, String token, String id) {
		this.session.setUsername(user);
		this.session.setToken(token);
		this.session.setUuid(id);
		this.isAuthenticated = true;
		Logger.log("Connected Successfully !");
	}

	/**
	 * @return  If the user is successfully authenticated
	 */
	public boolean isLogged() {
		return this.isAuthenticated;
	}

	/**
	 * @return The session of the user
	 */
	public Session getSession() {
		return this.session;
	}
}
