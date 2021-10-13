package fr.trxyy.alternative.alternative_api.minecraft.utils;

public enum EnumJavaOS {
	WIN_64_ALPHA("https://launchermeta.mojang.com/v1/packages/0586267ba40236e176925da17ca0d29dead3d30d/manifest.json"),
	WIN_64_LEGACY("https://launchermeta.mojang.com/v1/packages/ddc568a50326d2cf85765abb61e752aab191c366/manifest.json"),
	WIN_32_ALPHA("https://launchermeta.mojang.com/v1/packages/85c8e2653b5197bbb0fc9257932b670c630e1c77/manifest.json"),
	WIN_32_LEGACY("https://launchermeta.mojang.com/v1/packages/baa62193c2785f54d877d871d9859c67d65f08ba/manifest.json"),
	LINUX_ALPHA("https://launchermeta.mojang.com/v1/packages/e968e71afd3360e5032deac19e1c14d7aa32f5bb/manifest.json"),
	LINUX_LEGACY("https://launchermeta.mojang.com/v1/packages/a1c15cc788f8893fba7e988eb27404772f699a84/manifest.json"),
	LINUX_i386_LEGACY("https://launchermeta.mojang.com/v1/packages/64c6a0b8e3427c6c3f3ce82729aada8b2634a955/manifest.json"),
	MACOS_ALPHA("https://launchermeta.mojang.com/v1/packages/5a480fde2214534ab0b51ae78c70455ffd7c0e6a/manifest.json"),
	MACOS_LEGACY("https://launchermeta.mojang.com/v1/packages/341663b48a0d4e1c448dc789463fced6ba0962e1/manifest.json");
	
	private String url;
	
	EnumJavaOS(String u) {
		this.url = u;
	}

	public String getUrl() {
		return this.url;
	}
}
