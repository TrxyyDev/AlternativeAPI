package fr.trxyy.alternative.alternative_api.minecraft.java;

public enum EnumJavaManifest {
	GAMECORE("gamecore"),
	LINUX("linux"),
	LINUX_i386("linux-i386"),
	MACOS("mac-os"),
	WINDOWS_64("windows-x64"),
	WINDOWS_86("windows-x86");
	
	private String name;
	
	EnumJavaManifest(String u) {
		this.name = u;
	}

	public String getName() {
		return this.name;
	}
}
