package fr.trxyy.alternative.alternative_authv2.base;

public enum EnumAuthConfig {
	
	ACCESS_TOKEN("access_token"),
	REFRESH_TOKEN("refresh_token"),
	USER_ID("user_id"),
	SCOPE("scope"),
	TOKEN_TYPE("token_type"),
	EXPIRES_IN("expires_in"),
	FOCI("foci");
	
	public String option;
	public Object def;
	
	EnumAuthConfig(String opt) {
		this.option = opt;
	}
	
	public String getOption() {
		return this.option;
	}
	
}
