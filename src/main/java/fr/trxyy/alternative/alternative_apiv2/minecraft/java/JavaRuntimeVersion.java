package fr.trxyy.alternative.alternative_apiv2.minecraft.java;

public class JavaRuntimeVersion {
	public String name;
	public String released;

	public JavaRuntimeVersion(JavaRuntimeVersion o) {
		this.name = o.name;
		this.released = o.released;
	}

	public String getName() {
		return name;
	}

	public String getReleased() {
		return released;
	}
}