package fr.trxyy.alternative.alternative_authv2.microsoft.model;

public class MinecraftSkin {
	public String id;
	public String state;
	public String url;
	public String variant;
	public String alias;

	public MinecraftSkin(MinecraftSkin o) {
		if (o.id != null) {
			this.id = o.id;
		}
		if (o.state != null) {
			this.state = o.state;
		}
		if (o.url != null) {
			this.url = o.url;
		}
		if (o.variant != null) {
			this.variant = o.variant;
		}
		if (o.alias != null) {
			this.alias = o.alias;
		}
	}

	public String getId() {
		return id;
	}

	public String getState() {
		return state;
	}

	public String getUrl() {
		return url;
	}

	public String getVariant() {
		return variant;
	}

	public String getAlias() {
		return alias;
	}
}