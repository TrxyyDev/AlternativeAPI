package fr.trxyy.alternative.alternative_authv2.microsoft.model;

public class MinecraftStoreModel {

	public StoreItem[] items;
	public String signature;
	public String keyId;

	public MinecraftStoreModel(MinecraftStoreModel o) {
		if (o.items != null) {
			this.items = o.items;
		}

		if (o.signature != null) {
			this.signature = o.signature;
		}

		if (o.keyId != null) {
			this.keyId = o.keyId;
		}
	}

	public StoreItem[] getItems() {
		return items;
	}

	public String getSignature() {
		return signature;
	}

	public String getKeyId() {
		return keyId;
	}

}
