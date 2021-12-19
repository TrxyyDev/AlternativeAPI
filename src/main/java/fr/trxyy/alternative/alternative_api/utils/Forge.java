package fr.trxyy.alternative.alternative_api.utils;

/**
 * @author Trxyy
 */
public enum Forge {
	FML_CLIENT("fmlclient", "net.minecraftforge"),
	FORGE_CLIENT("forgeclient", "net.minecraftforge");
	
	private String target;
	private String group;
	
	Forge(String targetForge, String forgeGroup) {
		this.target = targetForge;
		this.group = forgeGroup;
	}
	
	public String getTarget() {
		return this.target;
	}
	
	public String getGroup() {
		return this.group;
	}
}