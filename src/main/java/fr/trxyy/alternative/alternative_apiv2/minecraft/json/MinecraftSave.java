package fr.trxyy.alternative.alternative_apiv2.minecraft.json;

import java.util.List;
import java.util.Map;

public class MinecraftSave {

	public String minecraftArguments;
	public Map<ArgumentType, List<Argument>> arguments;
	public List<MinecraftLibrary> libraries;

	public MinecraftSave() {
	}

	public void addMinecraftArguments(String args) {
		this.minecraftArguments = this.minecraftArguments + args;
	}

	public void addArguments(Map<ArgumentType, List<Argument>> args) {
		this.arguments = args;
	}

//	public void addLibraries(MinecraftLibrary libs) {
//		this.libraries.add(libs);
//	}

	public void addLibraries(List<MinecraftLibrary> libs) {
		this.libraries = libs;
	}

	public Map<ArgumentType, List<Argument>> getArguments() {
		return this.arguments;
	}
}
