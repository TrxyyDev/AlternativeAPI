package fr.trxyy.alternative.alternative_api.minecraft.utils;

public enum EnumJavaVersion {
	JAVA_RUNTIME_ALPHA("java-runtime-alpha", "Java 16, run minecraft versions over 1.16"),
	JRE_LEGACY("jre-legacy", "Java 8, run minecraft versions under 1.16");
	
	private String code;
	private String description;
	
	EnumJavaVersion(String c, String desc) {
		this.code = c;
		this.description = desc;
	}

	public String getCode() {
		return this.code;
	}

	public String getDescription() {
		return this.description;
	}
}
