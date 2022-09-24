package fr.trxyy.alternative.alternative_authv2.base;

import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;

public class Authenticator {

	public void loginWithCredentials(String email, String password) {
		CookieHandler currentHandler = CookieHandler.getDefault();
		CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));

		Map<String, String> params = new HashMap<String, String>();
		params.put("login", email);
		params.put("loginfmt", email);
		params.put("passwd", password);
		
		
		
	}
	
	public void test() {
		try {
		     Map<String, String> values = getUrlValues("https://login.live.com/oauth20_desktop.srf?code=M.R3_BL2.dadd3368-e0aa-123a-a51e-75549a49235e");
		     String formId = values.get("code");
		     Logger.log("code " + formId);
		} catch (UnsupportedEncodingException e) {
		     Logger.err("Error " + e.getMessage());
		} 
	}
	
	public Map<String, String> getUrlValues(String url) throws UnsupportedEncodingException {
	    int i = url.indexOf("?");
	    Map<String, String> paramsMap = new HashMap<String, String>();
	    if (i > -1) {
	        String searchURL = url.substring(url.indexOf("?") + 1);
	        String params[] = searchURL.split("&");

	        for (String param : params) {
	            String temp[] = param.split("=");
	            paramsMap.put(temp[0], java.net.URLDecoder.decode(temp[1], "UTF-8"));
	        }
	    }

	    return paramsMap;
	}

}
