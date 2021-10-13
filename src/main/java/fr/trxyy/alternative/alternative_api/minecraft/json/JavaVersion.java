package fr.trxyy.alternative.alternative_api.minecraft.json;

public class JavaVersion {

	private String component;
	private int majorVersion;

	private JavaVersion(JavaVersion o) {
		this.component = o.component;
		this.majorVersion = o.majorVersion;
	}

	public String getComponent() {
		return this.component;
	}

	public int getMajorVersion() {
		return this.majorVersion;
	}

}
