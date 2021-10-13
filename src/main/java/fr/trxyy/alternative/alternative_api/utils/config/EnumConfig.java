package fr.trxyy.alternative.alternative_api.utils.config;

public enum EnumConfig {
	
	USERNAME("username", "Player"),
	RAM("allocatedram", 1.0),
	GAME_SIZE("gamesize", "0"),
	AUTOLOGIN("autologin", false),
	VM_ARGUMENTS("vmarguments", "-XX:+CMSIncrementalMode"),
	USE_VM_ARGUMENTS("usevmarguments", false);
	
	public String option;
	public Object def;
	
	EnumConfig(String opt, Object d) {
		this.option = opt;
		this.def = d;
	}
	
	public String getOption() {
		return this.option;
	}
	
	public Object getDefault() {
		return this.def;
	}
	
}
