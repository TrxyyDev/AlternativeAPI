package fr.trxyy.alternative.alternative_authv2.microsoft.model;

public class MinecraftProfileModel {

	public String id;
	public String name;
	public MinecraftSkin[] skins;
	
	public String path;
	public String errorType;
	public String error;
	public String errorMessage;
	public String developerMessage;

	public MinecraftProfileModel(MinecraftProfileModel o) {
		if (o.id != null) {
			this.id = o.id;
		}
		if (o.name != null) {
			this.name = o.name;
		}
		if (o.skins != null) {
			this.skins = o.skins;
		}
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public MinecraftSkin[] getSkins() {
		return skins;
	}

}
