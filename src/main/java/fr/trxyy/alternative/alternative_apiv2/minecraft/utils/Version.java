package fr.trxyy.alternative.alternative_apiv2.minecraft.utils;

public class Version {

	public String displayName;
	public String id;
	public String type;
	public String url;

	public Version() {
	}

	public Version(String dpName, String id_, String type_, String url_) {
		this.displayName = dpName;
		this.id = id_;
		this.type = type_;
		this.url = url_;
	}
	
	public String getDisplayName() {
		return displayName;
	}

	public String getId() {
		return id;
	}
	
	public String getType() {
		return type;
	}

	public String getUrl() {
		return url;
	}
}
