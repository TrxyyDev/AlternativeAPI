package fr.trxyy.alternative.alternative_authv2.microsoft.model;

public class StoreItem {

	public String name;
	public String signature;

	public StoreItem(StoreItem o) {
		if (o.name != null) {
			this.name = o.name;
		}

		if (o.signature != null) {
			this.signature = o.signature;
		}
	}

	public String getName() {
		return name;
	}

	public String getSignature() {
		return signature;
	}

}
