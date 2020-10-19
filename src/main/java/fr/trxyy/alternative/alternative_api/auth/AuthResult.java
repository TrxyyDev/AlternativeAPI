package fr.trxyy.alternative.alternative_api.auth;

import java.util.List;

public class AuthResult {
	private String accessToken;
	private String clientToken;
	private Profile selectedProfile;
	private List<Profile> availableProfiles;

	public String getAccessToken() {
		return accessToken;
	}

	public String getClientToken() {
		return clientToken;
	}

	public Profile getSelectedProfile() {
		return selectedProfile;
	}

	public List<Profile> getAvailableProfiles() {
		return availableProfiles;
	}
}