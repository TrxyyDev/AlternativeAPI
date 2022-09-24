package fr.trxyy.alternative.alternative_authv2.microsoft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONObject;

import fr.trxyy.alternative.alternative_authv2.base.AuthConstants;
import fr.trxyy.alternative.alternative_authv2.base.Logger;
import fr.trxyy.alternative.alternative_authv2.base.Session;
import fr.trxyy.alternative.alternative_authv2.microsoft.model.MicrosoftModel;
import fr.trxyy.alternative.alternative_authv2.microsoft.model.MinecraftMicrosoftModel;
import fr.trxyy.alternative.alternative_authv2.microsoft.model.MinecraftProfileModel;
import fr.trxyy.alternative.alternative_authv2.microsoft.model.MinecraftStoreModel;
import fr.trxyy.alternative.alternative_authv2.microsoft.model.XboxLiveModel;

public class MicrosoftAuth {
	
	public MicrosoftAuth() {
		Logger.log("Connecting to Microsoft services...");
	}
	
    public MicrosoftModel getAuthorizationCode(ParamType type, String authCode) throws Exception
    {
            MicrosoftModel model = AuthConstants.getGson().fromJson(this.connectMicrosoft(type, authCode), MicrosoftModel.class);
            Logger.log("Connecting with type: " + type);
            return model;
    }
	
//    public Session getAuthorizationCode(String authCode) throws Exception
//    {
//            MicrosoftModel model = AuthConstants.getGson().fromJson(this.connectMicrosoft(authCode), MicrosoftModel.class);
////            model.getRefresh_token();
//            return this.getLiveToken(model.getAccess_token());
//    }
    
    public Session getLiveToken(String accessToken) throws Exception
    {
    		XboxLiveModel model = AuthConstants.getGson().fromJson(this.postInformations(ParamType.XBL, AuthConstants.MICROSOFT_AUTHENTICATE_XBOX, accessToken, null), XboxLiveModel.class);
        	return this.getXsts(model.getToken());
    }

    private Session getXsts(String liveToken) throws Exception
    {
            XboxLiveModel model = AuthConstants.getGson().fromJson(this.postInformations(ParamType.XSTS, AuthConstants.MICROSOFT_AUTHORIZE_XSTS, liveToken, null), XboxLiveModel.class);
            return this.getMinecraftToken(model.getDisplayClaims().getUsers()[0].getUhs(), model.getToken());
    }
    
    private Session getMinecraftToken(String userHash, String xstsToken) throws Exception
    {
    	MinecraftMicrosoftModel model = AuthConstants.getGson().fromJson(this.postInformations(ParamType.MC, AuthConstants.MICROSOFT_LOGIN_XBOX, userHash, xstsToken), MinecraftMicrosoftModel.class);
		return this.checkMinecraftStore(model.getToken_type(), model.getAccess_token());
    }

    private Session checkMinecraftStore(String tokenType, String mcAccessToken)
    {
    	 MinecraftStoreModel model = AuthConstants.getGson().fromJson(this.connectToMinecraft(AuthConstants.MICROSOFT_MINECRAFT_STORE, tokenType + " " + mcAccessToken), MinecraftStoreModel.class);
    	 return this.getMinecraftProfile(tokenType, mcAccessToken);
    }

    private Session getMinecraftProfile(String tokenType, String mcAccessToken)
    {
    	MinecraftProfileModel model = AuthConstants.getGson().fromJson(this.connectToMinecraft(AuthConstants.MICROSOFT_MINECRAFT_PROFILE, tokenType + " " + mcAccessToken), MinecraftProfileModel.class);
		final String uuidValid = model.getId().replaceFirst("(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5");
		return new Session(model.getName(), mcAccessToken, uuidValid);
    }
    
	public String formatMicrosoft(ParamType paramType, String authCode) {
		final StringBuilder builder = new StringBuilder();
		try {
			for (Entry<Object, Object> entry : getAuthParameters(paramType, authCode, null).entrySet()) {
				if (builder.length() > 0) builder.append("&");
				builder.append(URLEncoder.encode(entry.getKey().toString(), AuthConstants.UTF_8.name()));
				builder.append("=");
				builder.append(URLEncoder.encode(entry.getValue().toString(), AuthConstants.UTF_8.name()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
    
    private String connectMicrosoft(ParamType auth, String authCode)
    {
        HttpsURLConnection httpUrlConnection = null;
        byte[] bytes = null;
        try
        {
        	bytes = this.formatMicrosoft(auth, authCode).getBytes(AuthConstants.UTF_8);
            httpUrlConnection = (HttpsURLConnection) new URL(AuthConstants.MICROSOFT_AUTH_TOKEN).openConnection();
            httpUrlConnection.setRequestMethod("POST");
            httpUrlConnection.setRequestProperty("Content-Type", AuthConstants.URL_ENCODED);
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setDoInput(true);

            final OutputStream outputStream = httpUrlConnection.getOutputStream();
            outputStream.write(bytes, 0, bytes.length);
            outputStream.close();
            BufferedReader bufferedReader = null;
            String body = "";
            if(httpUrlConnection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                bufferedReader = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream(), AuthConstants.UTF_8));
            }
            else if(httpUrlConnection.getErrorStream() != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(httpUrlConnection.getErrorStream(), AuthConstants.UTF_8));
            }
			if (bufferedReader != null) {
				body =  bufferedReader.lines().collect(Collectors.joining());
				bufferedReader.close();
			}
			return body;
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (httpUrlConnection != null)
                httpUrlConnection.disconnect();
        }
        return "";
    }
    
    private String postInformations(ParamType type, String url, String par3String, String par4String)
    {
        HttpsURLConnection httpUrlConnection = null;
        BufferedReader bufferedReader = null;
        byte[] bytes = null;
        String json = "";
        try {
        	bytes = new JSONObject(this.getAuthParameters(type, par3String, par4String)).toJSONString().getBytes(AuthConstants.UTF_8);
			httpUrlConnection = (HttpsURLConnection) new URL(url).openConnection();
			httpUrlConnection.setRequestMethod("POST");
			httpUrlConnection.setRequestProperty("Content-Type", AuthConstants.APP_JSON);
			httpUrlConnection.addRequestProperty("Accept", AuthConstants.APP_JSON);
			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setDoInput(true);

            final OutputStream outputStream = httpUrlConnection.getOutputStream();
            outputStream.write(bytes, 0, bytes.length);
            outputStream.close();
            if(httpUrlConnection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                bufferedReader = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream(), AuthConstants.UTF_8));
            } 
            else if(httpUrlConnection.getErrorStream() != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(httpUrlConnection.getErrorStream(), AuthConstants.UTF_8));
            }
			if (bufferedReader != null) {
				String line;
                final StringBuilder stringBuilder = new StringBuilder();
                while((line = bufferedReader.readLine()) != null)
                    stringBuilder.append(line);
                json = stringBuilder.toString();
			}
			return json;
        } 
        catch (Exception e) {
            e.printStackTrace();
        } 
        finally {
            if (httpUrlConnection != null)
				httpUrlConnection.disconnect();
        }
        return "";
    }
    
	private String connectToMinecraft(String url, String fullCodeAuthorization) {
		BufferedReader bufferedReader = null;
		HttpURLConnection httpUrlConnection = null;
		String json = "";
		try {
			httpUrlConnection = (HttpsURLConnection) new URL(url).openConnection();
			httpUrlConnection.setRequestProperty("Accept", AuthConstants.APP_JSON);
			httpUrlConnection.setRequestProperty("Authorization", fullCodeAuthorization);
			httpUrlConnection.setDoOutput(false);
			httpUrlConnection.setDoInput(true);
			if(httpUrlConnection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
			    bufferedReader = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream(), AuthConstants.UTF_8));
			} 
			else if(httpUrlConnection.getErrorStream() != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(httpUrlConnection.getErrorStream(), AuthConstants.UTF_8));
			}

			if (bufferedReader != null) {
				String line;
				final StringBuilder stringBuilder = new StringBuilder();
				try {
					while ((line = bufferedReader.readLine()) != null)
						stringBuilder.append(line);
				} catch (IOException e) {
					e.printStackTrace();
				}
				json = stringBuilder.toString();
			}

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if (httpUrlConnection != null)
			httpUrlConnection.disconnect();
	}
	return json;
	}
    
    protected Map<Object, Object> getAuthParameters(ParamType param, String par2String, String par3String)
    {
        Map<Object, Object> parameters = new HashMap<Object, Object>();
        
        if (param.equals(ParamType.AUTH)) {
            parameters.put("client_id", "00000000402b5328");
            parameters.put("code", par2String);
            parameters.put("grant_type", "authorization_code");
            parameters.put("redirect_uri", "https://login.live.com/oauth20_desktop.srf");
            parameters.put("scope", "service::user.auth.xboxlive.com::MBI_SSL");
        }
        
        if (param.equals(ParamType.REFRESH)) {
            parameters.put("client_id", "00000000402b5328");
            parameters.put("refresh_token", par2String);
            parameters.put("grant_type", "refresh_token");
            parameters.put("redirect_uri", "https://login.live.com/oauth20_desktop.srf");
            parameters.put("scope", "service::user.auth.xboxlive.com::MBI_SSL");
        }
        
        if (param.equals(ParamType.XBL)) {
            final Map<Object, Object> properties = new HashMap<Object, Object>();
            properties.put("AuthMethod", "RPS");
            properties.put("SiteName", "user.auth.xboxlive.com");
            properties.put("RpsTicket", par2String);
            parameters.put("Properties", properties);
            parameters.put("RelyingParty", "http://auth.xboxlive.com");
            parameters.put("TokenType", "JWT");
        }
        
        if (param.equals(ParamType.XSTS)) {
            final Map<Object, Object> properties = new HashMap<Object, Object>();
            properties.put("SandboxId", "RETAIL");
            properties.put("UserTokens", Collections.singletonList(par2String));
            parameters.put("Properties", properties);
            parameters.put("RelyingParty", "rp://api.minecraftservices.com/");
            parameters.put("TokenType", "JWT");
        }
        
        if (param.equals(ParamType.MC)) {
            parameters.put("identityToken", "XBL3.0 x=" + par2String + ";" + par3String);
        }
        return parameters;
    }
}
